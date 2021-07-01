package interfaces;

import DB.*;
import classes.*;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.FlowLayout;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Font;

public class AdminInfo extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTable table;
	private JTextField ville;
	private JTextField nom;
	BDD db;
	Object[][] datas;
	private String id;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminInfo frame = new AdminInfo();
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
	public AdminInfo() {
		
		db = new BDD("jdbc:mysql://localhost:3306/mini_projet","root","");
		getContentPane().setBackground(Color.LIGHT_GRAY);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 956, 524);
		getContentPane().setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setForeground(Color.GRAY);
		menuBar.setBackground(Color.GRAY);
		menuBar.setBounds(0, 0, 942, 22);
		getContentPane().add(menuBar);
		
		JMenu menu1 = new JMenu("File");
		menuBar.add(menu1);
		
		JMenuItem exit = new JMenuItem("Exit");
		menu1.add(exit);
		exit.addActionListener(new ActionListener () {
			@Override
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(
			            null,
			            "Are you sure you want to exit the application?",
			            "Exit Application",
			            JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					setVisible(false);
					login lg=new login();
					lg.setVisible(true);
				}	
			}
		});
		
		JMenu menu2 = new JMenu("Edit");
		menuBar.add(menu2);
		
		JMenuItem clientG = new JMenuItem("Gestion client");
		menu2.add(clientG);
		clientG.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Admin_Client_info clt = new Admin_Client_info();
				clt.setVisible(true);
				setVisible(false);
			}	
		});
		
		JMenuItem compteG = new JMenuItem("Gestion Compte");
		compteG.setBackground(Color.GRAY);
		menu2.add(compteG);
		compteG.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Admin_Compte_info cpt = new Admin_Compte_info();
					cpt.setVisible(true);
					setVisible(false);
				
			}
			
		});
		
		Label label = new Label("Gestion des Agences");
		label.setFont(new Font("Arial", Font.ITALIC, 17));
		label.setBounds(22, 28, 249, 38);
		getContentPane().add(label);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(24, 72, 359, 224);
		getContentPane().add(scrollPane_1);
		
		Panel panel = new Panel();
		scrollPane_1.setViewportView(panel);
		panel.setLayout(null);
		
		Label label_4 = new Label("BANK ENSAM");
		label_4.setAlignment(Label.CENTER);
		label_4.setFont(new Font("Arial Black", Font.BOLD, 19));
		label_4.setBounds(36, 10, 235, 60);
		panel.add(label_4);
		
		Label label_5 = new Label("Ville Agence :");
		label_5.setFont(new Font("Arial Narrow", Font.BOLD, 13));
		label_5.setBounds(10, 127, 124, 21);
		panel.add(label_5);
		
		Label label_6 = new Label("Nom Agence :");
		label_6.setFont(new Font("Arial Narrow", Font.BOLD, 13));
		label_6.setBounds(10, 173, 124, 21);
		panel.add(label_6);
		
		ville = new JTextField();
		ville.setBounds(164, 129, 159, 19);
		panel.add(ville);
		ville.setColumns(10);
		
		nom = new JTextField();
		nom.setColumns(10);
		nom.setBounds(164, 173, 159, 19);
		panel.add(nom);
		
		Label label_1 = new Label("Op\u00E9rations");
		label_1.setFont(new Font("Calibri", Font.PLAIN, 14));
		label_1.setBounds(27, 324, 264, 38);
		getContentPane().add(label_1);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(29, 374, 354, 73);
		getContentPane().add(scrollPane_2);
		
		Panel panel_1 = new Panel();
		scrollPane_2.setViewportView(panel_1);
		FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
		flowLayout.setVgap(20);
		
		JButton add = new JButton("Ajouter");
		add.setBackground(Color.GRAY);
		add.setFont(new Font("SansSerif", Font.BOLD, 13));
		panel_1.add(add);
		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ajouter(e);
			}
			
		});
		
		JButton update = new JButton("Modifier");
		update.setBackground(Color.GRAY);
		update.setFont(new Font("SansSerif", Font.BOLD, 13));
		panel_1.add(update);
		update.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				modifier(e);
			}
			
		});
		
		JButton delete = new JButton("Supprimer");
		delete.setBackground(Color.GRAY);
		delete.setFont(new Font("SansSerif", Font.BOLD, 13));
		panel_1.add(delete);
		delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				supprimer(e);
			}
			
		});
		
		Label label_2 = new Label("Recherche des Agences");
		label_2.setFont(new Font("Cambria", Font.ITALIC, 14));
		label_2.setBounds(428, 28, 204, 31);
		getContentPane().add(label_2);
		
		Label label_3 = new Label("Rechercher Agence :");
		label_3.setFont(new Font("Dialog", Font.BOLD, 13));
		label_3.setBounds(441, 77, 157, 31);
		getContentPane().add(label_3);
		
		textField = new JTextField();
		textField.setBounds(623, 86, 182, 19);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton ok = new JButton("Ok");
		ok.setBounds(831, 87, 85, 21);
		ok.setBackground(Color.GRAY);
		ok.setFont(new Font("SansSerif", Font.BOLD, 13));
		getContentPane().add(ok);
		ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				rechercher(e);
			}
			
		});

		JButton actualiser = new JButton("Actualiser");
		actualiser.setBounds(501, 133, 97, 21);
		actualiser.setBackground(Color.GRAY);
		actualiser.setFont(new Font("SansSerif", Font.BOLD, 13));
		getContentPane().add(actualiser);
		actualiser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				actualiser(e);
			}
			
		});
		
		JButton btnNewButton_5 = new JButton("Download");
		btnNewButton_5.setForeground(Color.BLACK);
		btnNewButton_5.setBounds(739, 133, 97, 21);
		btnNewButton_5.setBackground(Color.LIGHT_GRAY);
		btnNewButton_5.setFont(new Font("SansSerif", Font.BOLD, 13));
		getContentPane().add(btnNewButton_5);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(424, 184, 492, 293);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		resetT();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row=table.getSelectedRow();
	        	id = (table.getModel().getValueAt(row,0).toString());
	        	nom.setText(table.getModel().getValueAt(row,1).toString());
	        	ville.setText(table.getModel().getValueAt(row,2).toString());
			}
				
		});
		scrollPane.setViewportView(table);
	}
	
	public void resetT() {
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"id","nom","city"
				}
			));
	}
	
	public void actualiser(ActionEvent e) {
		resetT();
		datas=new Object[100][3];
		ResultSet rs= db.querySelectAll("agence");
		Object[] row = new Object[3];
		DefaultTableModel model=(DefaultTableModel) table.getModel();
		try {
			while(rs.next()) {
				row[0]=rs.getInt("id");
				row[1]=rs.getString("name");
				row[2]=rs.getString("city");
				model.addRow(row);
				table.setModel(model);
			}
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		reset();
	}
	
	public void reset() {
		ville.setText("");
		nom.setText("");
	}
	
	private void ajouter(ActionEvent e) {
		if (ville.getText().equals("") || nom.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "SVP entrez les informations complètes");
        } else {
        	try {
	            String[] colon = {"name", "city"};
	            Agence agence1 = new Agence(ville.getText(),nom.getText());
	            Object[] infos = { agence1.getNom(),agence1.getVille()};
	            System.out.println(db.queryInsert("agence", colon, infos));
	            JOptionPane.showMessageDialog(this, "Inserted successfully","Succès",JOptionPane.PLAIN_MESSAGE);
	            reset();
        	}
        	catch(Exception e1) {
        		JOptionPane.showMessageDialog(this, "Insertion failed","Perte",JOptionPane.ERROR_MESSAGE);
        		e1.printStackTrace();
        	}
        }
	}
	
	private void modifier(ActionEvent e) {
		try { if (ville.getText().equals("") || nom.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "SVP entrez les informations complètes");
			} else {
	            String[] colon = { "name", "city"};
	            String[] infos = { nom.getText(),ville.getText()};
	            System.out.println(db.queryUpdate("agence", colon, infos,"id='"+ id+"'"));
	            JOptionPane.showMessageDialog(this, "Update successfully","Succès",JOptionPane.PLAIN_MESSAGE);
	            reset();
			}	
		}
		catch(Exception e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(this, "Update failed","Perte",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void supprimer(ActionEvent e) {
		String id = String.valueOf(table.getValueAt(table.getSelectedRow(), 0));
        if (JOptionPane.showConfirmDialog(this, "est ce que tu es sûr que tu veux supprimer", "attention!!!", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
            db.queryDelete("agence", "id=" + id);
            JOptionPane.showMessageDialog(this, "Deleted successfully","Succès",JOptionPane.PLAIN_MESSAGE);
        } else {
        	JOptionPane.showMessageDialog(this, "Delete failed","Perte",JOptionPane.ERROR_MESSAGE);
        }
	}
	
	private void rechercher(ActionEvent e) {
		resetT();
		try { if (textField.getText().equals("") ) {
            JOptionPane.showMessageDialog(this, "SVP entrez quelque chose");
			} else {
				Object[] row=new Object[3];
				DefaultTableModel model=(DefaultTableModel) table.getModel();
	        	String id= textField.getText();
	            ResultSet rs = db.querySelectAll("agence","id='"+id+"'" );
		    	if(rs.next())  {
		    		row[0]=rs.getInt("id");
	               	row[1]=rs.getString("name");
	               	row[2]=rs.getString("city");
	               	model.addRow(row);
	               	table.setModel(model);
		        }
		    	else
	        		JOptionPane.showMessageDialog(this, "AGENCE PAS TROUVÉE","ÉCHEC",JOptionPane.ERROR_MESSAGE);
	            reset();
			}	
		}
		catch(Exception e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(this, "Cette agence n'existe pas","Perte",JOptionPane.ERROR_MESSAGE);
		}
	}
}
