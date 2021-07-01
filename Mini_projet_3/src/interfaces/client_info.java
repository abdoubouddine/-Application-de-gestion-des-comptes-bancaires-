package interfaces;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import DB.BDD;
import classes.CompteCourant;
import classes.CompteRemunere;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class client_info extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField cin;
	private static JTextField code1;
	BDD db;
	private JTable table_1;
	public static ArrayList<CompteCourant> listeComptes=login.listeR();
	public static ArrayList<CompteRemunere> listeComptes1=login.listeC();
	private JTextField code;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					client_info frame = new client_info();
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
	public client_info() {
		db = new BDD("jdbc:mysql://localhost/mini_projet","root","");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 872, 499);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("BIENVENU");
		lblNewLabel.setForeground(Color.MAGENTA);
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 35));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(279, 22, 236, 51);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Veuillez saisir vos informations :");
		lblNewLabel_1.setForeground(Color.DARK_GRAY);
		lblNewLabel_1.setFont(new Font("Georgia", Font.BOLD, 17));
		lblNewLabel_1.setBounds(10, 74, 304, 44);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Tapez votre CIN :");
		lblNewLabel_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		lblNewLabel_2.setBounds(36, 128, 131, 39);
		contentPane.add(lblNewLabel_2);
		
		JButton btnNewButton = new JButton("Afficher comptes");
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnNewButton.setBounds(159, 225, 155, 31);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				afficher(e);
			}
			
		});
		
		cin = new JTextField();
		cin.setBounds(218, 141, 96, 19);
		contentPane.add(cin);
		cin.setColumns(10);
		
		code1 = new JTextField();
		code1.setBounds(218, 332, 96, 19);
		contentPane.add(code1);
		code1.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Transactions ");
		lblNewLabel_1_1.setForeground(Color.DARK_GRAY);
		lblNewLabel_1_1.setFont(new Font("Georgia", Font.BOLD, 17));
		lblNewLabel_1_1.setBounds(10, 250, 304, 44);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_2_1_2 = new JLabel("Code :");
		lblNewLabel_2_1_2.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		lblNewLabel_2_1_2.setBounds(36, 319, 131, 39);
		contentPane.add(lblNewLabel_2_1_2);
		
		JButton btnTransactions = new JButton("Transaction");
		btnTransactions.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnTransactions.setBackground(Color.WHITE);
		btnTransactions.setBounds(91, 393, 155, 31);
		contentPane.add(btnTransactions);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(375, 87, 473, 365);
		contentPane.add(scrollPane);
		
		table_1 = new JTable();
		resetT();
		scrollPane.setViewportView(table_1);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 848, 22);
		contentPane.add(menuBar);
		
		JMenu file = new JMenu("File");
		menuBar.add(file);
		
		JMenuItem Exit = new JMenuItem("Exit");
		file.add(Exit);
		
		JLabel lblNewLabel_2_1 = new JLabel("Tapez votre Code :");
		lblNewLabel_2_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		lblNewLabel_2_1.setBounds(36, 177, 131, 39);
		contentPane.add(lblNewLabel_2_1);
		
		code = new JTextField();
		code.setColumns(10);
		code.setBounds(218, 190, 96, 19);
		contentPane.add(code);
		Exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				login lg=new login();
				lg.setVisible(true);
			}
			
		});
		btnTransactions.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {	
					ResultSet rs=db.querySelectAll("compte", "code='"+code1.getText()+"'");
					if(rs.next()) {
						client_info_2 clt= new client_info_2();
						clt.setVisible(true);
					}
					else JOptionPane.showMessageDialog(null, "COMPTE PAS TROUVÉ","Échec",JOptionPane.ERROR_MESSAGE);
				} catch (HeadlessException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				
			}
			
		});
		
		
	}
	
	static ArrayList<CompteCourant> getListC() {
		return listeComptes;
	}
	static ArrayList<CompteRemunere> getListR() {
		return listeComptes1;
	}
	public void resetT() {
		table_1.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"code", "type", "solde", "decouvertA", "tauxI", "cin","Agence"
				}
			));
	}
	
	public static int getCode() {
		return Integer.parseInt((code1.getText()));
	}
	
	public void reset() {
		cin.setText("");
		code.setText("");
	}
	
	public void afficher( ActionEvent e) {
		resetT();
		try { if ( cin.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "SVP entrez les infos complètes !!");
			} else {
				Object[]row=new Object[7];
				DefaultTableModel model=(DefaultTableModel) table_1.getModel();
	        	ResultSet rs=db.querySelectAll("compte","cin LIKE '"+cin.getText()+"' and code='"+code.getText()+"'");
	        	if(!rs.next()) {
	        		JOptionPane.showMessageDialog(this, "COMPTE PAS TROUVÉ","ÉCHEC",JOptionPane.ERROR_MESSAGE);
	        	}
	        	else {
	        			row[0]=rs.getInt("code");
	                	row[1]=rs.getString("type");
	                	row[2]=rs.getDouble("solde");
	                	row[3]=rs.getDouble("decouvertA");
	                	row[4]=rs.getDouble("tauxI");
	                	row[5]=rs.getString("cin");
	                	row[6]=rs.getInt("Agence");
	                	model.addRow(row);
	                	table_1.setModel(model);
	                }
	        	}
	            reset();	
		}
		catch(Exception e1) {
			e1.printStackTrace();
		}
		
	}
}
