package interfaces;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import DB.BDD;
import classes.*;
import javax.swing.JTable;

public class client_info_2 extends JFrame {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnNewRadioButton_1;
	BDD db;
	private JTable table;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					client_info_2 frame = new client_info_2();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public client_info_2() {
		
		db = new BDD("jdbc:mysql://localhost/mini_projet","root","");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 545, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		ButtonGroup group=new ButtonGroup();
		
		rdbtnNewRadioButton = new JRadioButton("Retrait");
		rdbtnNewRadioButton.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 13));
		rdbtnNewRadioButton.setBounds(23, 17, 103, 21);
		group.add(rdbtnNewRadioButton);
		contentPane.add(rdbtnNewRadioButton);
		
		rdbtnNewRadioButton_1 = new JRadioButton("D\u00E9p\u00F4t");
		rdbtnNewRadioButton_1.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 13));
		rdbtnNewRadioButton_1.setBounds(23, 49, 103, 21);
		group.add(rdbtnNewRadioButton_1);
		contentPane.add(rdbtnNewRadioButton_1);
		
		JLabel lblNewLabel = new JLabel("Qte d'argent :");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(301, 17, 103, 17);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(425, 18, 96, 19);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Effectuer");
		btnNewButton.setBackground(Color.LIGHT_GRAY);
		btnNewButton.setFont(new Font("Cambria", Font.BOLD, 12));
		btnNewButton.setBounds(405, 49, 85, 21);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ajouter(e);
				
			}
			
		});
		
		JButton btnNewButton_1 = new JButton("Actualiser");
		btnNewButton_1.setBackground(Color.LIGHT_GRAY);
		btnNewButton_1.setFont(new Font("Cambria", Font.BOLD, 12));
		btnNewButton_1.setBounds(64, 87, 96, 21);
		contentPane.add(btnNewButton_1);
		btnNewButton_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					table();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 121, 511, 142);
		contentPane.add(scrollPane);
		
		table = new JTable();
		resetT();
		scrollPane.setViewportView(table);
		
		JLabel lblLibell = new JLabel("Libell\u00E9 :");
		lblLibell.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblLibell.setBounds(160, 49, 71, 17);
		contentPane.add(lblLibell);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(241, 44, 96, 19);
		contentPane.add(textField_1);
		
		JButton btnNewButton_1_1 = new JButton("Exit");
		btnNewButton_1_1.setFont(new Font("Cambria", Font.BOLD, 12));
		btnNewButton_1_1.setBackground(Color.LIGHT_GRAY);
		btnNewButton_1_1.setBounds(341, 88, 96, 21);
		contentPane.add(btnNewButton_1_1);
		btnNewButton_1_1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
					setVisible(false);		
			}
			
		});
		
	}
	
	public void resetT() {
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"id", "libelle", "montant", "date"
				}
			));
	}
	
	private void ajouter(ActionEvent e) {
		String type = null;
		int c=0;
		if(!textField.getText().equals("") && !textField_1.getText().equals("")) {
			try {
				String[] colon= {"solde"};
				ResultSet rs = db.querySelectAll("compte","code="+client_info.getCode());
				if(rs.next()==true) { type=rs.getString("type");
				if(type.equals("Courant")) {
					if( rdbtnNewRadioButton.isSelected()) {
						for(int i=0;i< client_info.getListC().size();i++) {
							System.out.println(client_info.getListC().get(i).getNumeroCompte());
							if(client_info.getListC().get(i).getNumeroCompte()==client_info.getCode()) {
								CompteCourant cptC =client_info.getListC().get(i);
								double solde=cptC.retrait(Double.parseDouble(textField.getText()), textField_1.getText());
								cptC.setSolde(solde);
								Opération o=new Opération(Double.parseDouble(textField.getText()),textField_1.getText());
								String info[]= {String.valueOf(solde)};
								System.out.println(db.queryUpdate("compte",colon,info,"code="+client_info.getCode()));
								db.queryInsert("operation",new String[] { "libelle","montant","date","code"},new Object[] {textField_1.getText(),Double.parseDouble(textField.getText()),o.getDateOperation(),client_info.getCode()});
								JOptionPane.showMessageDialog(this, o.toString(),"Operation done",JOptionPane.PLAIN_MESSAGE);
								c=1;
								}
							else continue;
						}
						if(c==0) {JOptionPane.showMessageDialog(this,"Compte pas trouvé"," Erreur ", JOptionPane.ERROR_MESSAGE);
						}
					}
					else {
						JOptionPane.showMessageDialog(this,"Opération non autorisée"," Erreur du choix", JOptionPane.ERROR_MESSAGE);
					}
				}
				else if(type.equals("Remunere")) {
					for(int i=0;i< client_info.getListC().size();i++)  {
						if(client_info.getListR().get(i).getNumeroCompte()==client_info.getCode()) {
							CompteRemunere cptR=client_info.getListR().get(i);
							if(rdbtnNewRadioButton.isSelected()) {
								double solde=cptR.retrait(Double.parseDouble(textField.getText()), textField_1.getText());
								String info[]= {String.valueOf(solde)};
								cptR.setSolde(solde);
								Opération o=new Opération(Double.parseDouble(textField.getText()),textField_1.getText());
								System.out.println(db.queryUpdate("compte",colon,info,"code="+client_info.getCode()));
								db.queryInsert("operation",new String[] { "libelle","montant","date","code"},new Object[] {textField_1.getText(),Double.parseDouble(textField.getText()),o.getDateOperation(),client_info.getCode()});
								JOptionPane.showMessageDialog(this, o.toString(),"Operation done",JOptionPane.PLAIN_MESSAGE);
								c=1;
							}
							else if(rdbtnNewRadioButton_1.isSelected()) {
								double solde=cptR.depot(Double.parseDouble(textField.getText()), textField_1.getText());
								String info[]= {String.valueOf(solde)};
								cptR.setSolde(solde);
								Opération o=new Opération(Double.parseDouble(textField.getText()),textField_1.getText());
								System.out.println(db.queryUpdate("compte",colon,info,"code='"+client_info.getCode()));
								db.queryInsert("operation",new String[] { "libelle","montant","date","code"},new Object[] {textField_1.getText(),Double.parseDouble(textField.getText()),o.getDateOperation(),client_info.getCode()});
								JOptionPane.showMessageDialog(this, o.toString(),"Operation done",JOptionPane.PLAIN_MESSAGE);
								c=1;
							}
							else {
								JOptionPane.showMessageDialog(this,"Choisissez une opération"," Erreur du choix", JOptionPane.ERROR_MESSAGE);
							}
						}
						else continue;
					}
					if(c==0) {JOptionPane.showMessageDialog(this,"Compte pas trouvé"," Erreur ", JOptionPane.ERROR_MESSAGE);}	
				}
			}
			}
			catch(Exception e1) {
				JOptionPane.showMessageDialog(this, "Operation failed","Perte",JOptionPane.ERROR_MESSAGE);
				e1.printStackTrace();
			}
		}
		else {
			JOptionPane.showMessageDialog(this,"Saisir les informations complètes"," Erreur ", JOptionPane.ERROR_MESSAGE);
		}
	}
	public void table() throws SQLException {
		resetT();
		Object[]row=new Object[4];
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		ResultSet rs= db.querySelectAll("operation","code="+client_info.getCode());
		while(rs.next()) {
			row[0]=rs.getInt("id");
        	row[1]=rs.getString("libelle");
        	row[2]=rs.getDouble("montant");
        	row[3]=rs.getDate("date");
        	model.addRow(row);
        	table.setModel(model);
        }
	}
}
