package interfaces;

import classes.*;
import DB.*;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;


public class Admin_Compte_info extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField cin;
	private JTextField textField_3;
	private JTextField solde;
	private static JTextField textField_5;
	private JTextField decouvertA;
	private JTextField tauxI;
	JRadioButton cpteC;
	JRadioButton cpteR;
	BDD db;
	public static ArrayList<CompteCourant> listeComptes=login.listeR();
	public static ArrayList<CompteRemunere> listeComptes1=login.listeC();
	private JTable table_2;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JTextField agence;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin_Compte_info frame = new Admin_Compte_info();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws SQLException 
	 */
	public Admin_Compte_info() {
		db = new BDD("jdbc:mysql://localhost/mini_projet","root","");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 956, 524);
		getContentPane().setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setForeground(Color.GRAY);
		menuBar.setBackground(Color.GRAY);
		menuBar.setBounds(0, 0, 942, 22);
		getContentPane().add(menuBar);
		
		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);
		
		JMenuItem exit= new JMenuItem("Exit");
		mnNewMenu.add(exit);
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
		
		JMenu mnNewMenu_1 = new JMenu("Edit");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem cltG = new JMenuItem("Gestion Client");
		cltG.setBackground(Color.GRAY);
		mnNewMenu_1.add(cltG);
		cltG.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Admin_Client_info clt = new Admin_Client_info();
				clt.setVisible(true);
				setVisible(false);
			}	
		});
		
		Label label = new Label("Gestion des Comptes");
		label.setFont(new Font("Arial", Font.ITALIC, 17));
		label.setBounds(22, 28, 249, 38);
		getContentPane().add(label);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(24, 72, 369, 290);
		getContentPane().add(scrollPane_1);
		
		Panel panel = new Panel();
		scrollPane_1.setViewportView(panel);
		panel.setLayout(null);
		
		Label label_6 = new Label("Solde initial :");
		label_6.setFont(new Font("Arial Narrow", Font.BOLD, 13));
		label_6.setBounds(23, 67, 108, 21);
		panel.add(label_6);
		
		Label label_6_3 = new Label("CIN :");
		label_6_3.setFont(new Font("Arial Narrow", Font.BOLD, 13));
		label_6_3.setBounds(23, 37, 108, 21);
		panel.add(label_6_3);
		
		cin = new JTextField();
		cin.setColumns(10);
		cin.setBounds(205, 40, 117, 19);
		panel.add(cin);
		
		solde = new JTextField();
		solde.setColumns(10);
		solde.setBounds(205, 69, 117, 19);
		panel.add(solde);
		
		Label label_6_4 = new Label("Si compte est courant :");
		label_6_4.setForeground(Color.RED);
		label_6_4.setFont(new Font("Arial Narrow", Font.BOLD, 13));
		label_6_4.setBounds(4, 118, 189, 21);
		panel.add(label_6_4);
		
		Label label_6_5 = new Label("D\u00E9couvert autoris\u00E9 :");
		label_6_5.setFont(new Font("Arial Narrow", Font.BOLD, 13));
		label_6_5.setBounds(23, 145, 154, 21);
		panel.add(label_6_5);
		
		Label label_6_6 = new Label("Si compte est r\u00E9mun\u00E9r\u00E9 :");
		label_6_6.setForeground(Color.RED);
		label_6_6.setFont(new Font("Arial Narrow", Font.BOLD, 13));
		label_6_6.setBounds(4, 175, 189, 21);
		panel.add(label_6_6);
		
		Label label_6_7 = new Label("Taux d'int\u00E9r\u00EAt :");
		label_6_7.setFont(new Font("Arial Narrow", Font.BOLD, 13));
		label_6_7.setBounds(23, 202, 129, 21);
		panel.add(label_6_7);
		
		decouvertA = new JTextField();
		decouvertA.setEditable(false);
		decouvertA.setColumns(10);
		decouvertA.setBounds(205, 147, 117, 19);
		panel.add(decouvertA);
		
		tauxI = new JTextField();
		tauxI.setEditable(false);
		tauxI.setColumns(10);
		tauxI.setBounds(205, 204, 117, 19);
		panel.add(tauxI);
		
		JButton add = new JButton("Cr\u00E9er");
		add.setFont(new Font("Tahoma", Font.BOLD, 12));
		add.setBounds(256, 245, 85, 21);
		panel.add(add);

		
		cpteC = new JRadioButton("Compte Courant");
		cpteC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tauxI.setEditable(false);
				decouvertA.setEditable(true);
			}
		});
		cpteC.setFont(new Font("Arial", Font.BOLD, 13));
		cpteC.setBounds(9, 10, 143, 21);
		buttonGroup.add(cpteC);
		panel.add(cpteC);
		
		cpteR = new JRadioButton("Compte R\u00E9mun\u00E9r\u00E9");
		cpteR.setFont(new Font("Arial", Font.BOLD, 13));
		cpteR.setBounds(179, 10, 143, 21);
		buttonGroup.add(cpteR);
		panel.add(cpteR);
		
		Label label_6_1 = new Label("Agence :");
		label_6_1.setFont(new Font("Arial Narrow", Font.BOLD, 13));
		label_6_1.setBounds(23, 94, 108, 21);
		panel.add(label_6_1);
		
		agence = new JTextField();
		agence.setColumns(10);
		agence.setBounds(205, 98, 117, 19);
		panel.add(agence);
		cpteR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				decouvertA.setEditable(false);
				tauxI.setEditable(true);
			}
		});
		
		add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ajouter(e);
			}
			
		});
		
		Label label_1 = new Label("Op\u00E9rations");
		label_1.setFont(new Font("Calibri", Font.PLAIN, 14));
		label_1.setBounds(22, 369, 264, 22);
		getContentPane().add(label_1);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(29, 395, 354, 69);
		getContentPane().add(scrollPane_2);
		
		Panel panel_1 = new Panel();
		scrollPane_2.setViewportView(panel_1);
		panel_1.setLayout(null);
		
		Label label_6_8 = new Label("Num\u00E9ro du compte:");
		label_6_8.setFont(new Font("Arial Narrow", Font.BOLD, 13));
		label_6_8.setBounds(9, 8, 155, 21);
		panel_1.add(label_6_8);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(84, 37, 117, 19);
		panel_1.add(textField_5);
		
		JButton btnNewButton_3_1 = new JButton("Ok");
		btnNewButton_3_1.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnNewButton_3_1.setBackground(Color.WHITE);
		btnNewButton_3_1.setBounds(257, 35, 85, 21);
		panel_1.add(btnNewButton_3_1);
		btnNewButton_3_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ResultSet rs=db.querySelectAll("compte", "code='"+textField_5.getText()+"'");
				try {
					if(rs.next()) {
						Admin_Compte_info_2 cpt2= new Admin_Compte_info_2();
						cpt2.setVisible(true);	
					}
					else JOptionPane.showMessageDialog(null, "COMPTE PAS TROUVÉ","Échec",JOptionPane.ERROR_MESSAGE);
				} catch (HeadlessException e1) {
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}	
		});
		
		Label label_2 = new Label("Recherche des Comptes");
		label_2.setFont(new Font("Cambria", Font.ITALIC, 14));
		label_2.setBounds(428, 28, 204, 31);
		getContentPane().add(label_2);
		
		Label label_3 = new Label("Rechercher Compte :");
		label_3.setFont(new Font("Dialog", Font.BOLD, 13));
		label_3.setBounds(442, 72, 157, 31);
		getContentPane().add(label_3);
		
		JButton ok = new JButton("Ok");
		ok.setBounds(831, 82, 85, 21);
		ok.setBackground(Color.WHITE);
		ok.setFont(new Font("SansSerif", Font.BOLD, 13));
		getContentPane().add(ok);
		ok.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				rechercher(e);
			}
			
		});
		
		JButton actualiser = new JButton("Actualiser");
		actualiser.setBounds(501, 113, 97, 21);
		actualiser.setBackground(Color.WHITE);
		actualiser.setFont(new Font("SansSerif", Font.BOLD, 13));
		getContentPane().add(actualiser);
		actualiser.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					table();	
			}
			
		});
        
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(636, 84, 141, 19);
		getContentPane().add(textField_3);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(442, 174, 490, 303);
		getContentPane().add(scrollPane);
		
		table_2 = new JTable();
		resetT();
		scrollPane.setViewportView(table_2);
		
		
		
		
	}
	
	static int getCode() {
		return Integer.parseInt(textField_5.getText());
	}
	static ArrayList<CompteCourant> getListC() {
		return listeComptes;
	}
	static ArrayList<CompteRemunere> getListR() {
		return listeComptes1;
	}

	public void reset() {
		cin.setText("");
		decouvertA.setText("");
		tauxI.setText("");
		solde.setText("");
		agence.setText("");
	}
	
	public void resetT() {
		table_2.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
					"code", "type", "solde", "decouvertA", "tauxI", "cin","Agence"
				}
			));
	}
	 
	public void table()  {
		resetT();
		Object[]row=new Object[7];
		DefaultTableModel model=(DefaultTableModel) table_2.getModel();
		ResultSet rs= db.querySelectAll("compte");
		try {
			while(rs.next()) {
				row[0]=rs.getInt("code");
	        	row[1]=rs.getString("type");
	        	row[2]=rs.getDouble("solde");
	        	row[3]=rs.getDouble("DecouvertA");
	        	row[4]=rs.getDouble("tauxI");
	        	row[5]=rs.getString("cin");
	        	row[6]=rs.getInt("Agence");
	        	model.addRow(row);
	        	table_2.setModel(model);
	        }
		}
		catch(SQLException e1) {
			e1.printStackTrace();
		}
	}
	private void ajouter(ActionEvent e) {
		if ( (!cpteC.isSelected() || !cpteR.isSelected()) && (solde.getText().equals("") || cin.getText().equals("")) || agence.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "SVP entrez les informations complètes");
			} else {
					if(cpteC.isSelected()) {
							try {
								ResultSet rs = db.querySelectAll("client","cin= '"+cin.getText()+"' ");
								ResultSet rs3=db.querySelectAll("agence","id= '"+agence.getText()+"' ");
									if(rs.next() && rs3.next()) {
										ResultSet rs4=db.querySelectAll("compte","cin='"+cin.getText()+"'");
										if(!rs4.next()) { Client clt = new Client(cin.getText());
										clt.setNum(rs.getInt("code"));
										CompteCourant cptC = new CompteCourant(clt,Double.parseDouble(decouvertA.getText()),Double.parseDouble(solde.getText()),Integer.parseInt(agence.getText()));
										ResultSet rs1=db.querySelect(new String[] {"code"}, "compte");
										String[] colon = {"type","solde","decouvertA","tauxI","cin","agence"};
										if(rs1.next()) {
											rs1.last();
											cptC.setNum(rs1.getInt("code")+1);
			   	   			            	System.out.println(cptC.getNumeroCompte());
			   	   			            	getListC().add(cptC);
											}
			    			            Object[] infos = { "Courant", cptC.getSolde(),cptC.getMontantDecouvertAutorise(),"0",clt.getCIN(),cptC.getAgence().getNum()};
			   	    			        System.out.println(db.queryInsert("compte", colon, infos));
			   	    			        JOptionPane.showMessageDialog(this, "Inserted successfully","Succès",JOptionPane.PLAIN_MESSAGE);
			   	    			        reset();
										}
			   	    			        else {		
											ResultSet rs2=db.querySelectAll("compte","cin='"+cin.getText()+"' and Agence='"+agence.getText()+"'");
											if(rs2.next()) {
												Client clt = new Client(cin.getText());
												clt.setNum(rs.getInt("code"));
												CompteCourant cptC = new CompteCourant(clt,Double.parseDouble(decouvertA.getText()),Double.parseDouble(solde.getText()),Integer.parseInt(agence.getText()));
												ResultSet rs1=db.querySelect(new String[] {"code"}, "compte");
												String[] colon = {"type","solde","decouvertA","tauxI","cin","agence"};
												if(rs1.next()) {
													rs1.last();
													cptC.setNum(rs1.getInt("code")+1);
					   	   			            	System.out.println(cptC.getNumeroCompte());
					   	   			            	getListC().add(cptC);
													}
					    			            Object[] infos = { "Courant", cptC.getSolde(),cptC.getMontantDecouvertAutorise(),"0",clt.getCIN(),cptC.getAgence().getNum()};
					   	    			        System.out.println(db.queryInsert("compte", colon, infos));
					   	    			        JOptionPane.showMessageDialog(this, "Inserted successfully","Succès",JOptionPane.PLAIN_MESSAGE);
					   	    			        reset(); 
											}
											else {  JOptionPane.showMessageDialog(this, "Impossible de créer un compte dans une autre agence ","Opération Impossible",JOptionPane.ERROR_MESSAGE);}
											
										}
									}
									else {JOptionPane.showMessageDialog(this, "CIN PAS TROUVÉ ou AGENCE PAS TROUVÉE","Erreur d'authentification",JOptionPane.ERROR_MESSAGE);}
								}
								catch(Exception e1) {
									e1.printStackTrace();
								}	
						}
					else if(cpteR.isSelected()) {
							try{
								ResultSet rs = db.querySelectAll("client","cin= '"+cin.getText()+"' ");
								ResultSet rs3=db.querySelectAll("agence","id= '"+agence.getText()+"' ");
								if(rs.next() && rs3.next()) {
									ResultSet rs4=db.querySelectAll("compte","cin='"+cin.getText()+"'");
									if(!rs4.next()) {Client clt = new Client(cin.getText());
										clt.setNum(rs.getInt("code"));
										CompteRemunere cptR = new CompteRemunere(clt,Double.parseDouble(tauxI.getText()),Double.parseDouble(solde.getText()),Integer.parseInt(agence.getText()));
										ResultSet rs1=db.querySelect(new String[] {"code"}, "compte");
										String[] colon = {"type","solde","decouvertA","tauxI","cin","agence"};
										if(rs1.next()) {
											rs1.last();
											cptR.setNum(rs1.getInt("code")+1);
			   	   			            	System.out.println(cptR.getNumeroCompte());
			   	   			            	getListR().add(cptR);
											}
			    			            Object[] infos = { "Remunere", cptR.getSolde(),"0",cptR.getTauxInterets(),clt.getCIN(),cptR.getAgence().getNum()};
			   	    			        System.out.println(db.queryInsert("compte", colon, infos));
			   	    			        JOptionPane.showMessageDialog(this, "Inserted successfully","Succès",JOptionPane.PLAIN_MESSAGE);
			   	    			        reset(); 	
			   	    			    }
									else {
										ResultSet rs2=db.querySelectAll("compte","cin='"+cin.getText()+"' and Agence='"+agence.getText()+"'");
										if(rs2.next()) {
											Client clt = new Client(cin.getText());
											clt.setNum(rs.getInt("code"));
											CompteRemunere cptR = new CompteRemunere(clt,Double.parseDouble(tauxI.getText()),Double.parseDouble(solde.getText()),Integer.parseInt(agence.getText()));
											ResultSet rs1=db.querySelect(new String[] {"code"}, "compte");
											String[] colon = {"type","solde","decouvertA","tauxI","cin","agence"};
											if(rs1.next()) {
												rs1.last();
												cptR.setNum(rs1.getInt("code")+1);
				   	   			            	System.out.println(cptR.getNumeroCompte());
				   	   			            	getListR().add(cptR);
												}
				    			            Object[] infos = { "Remunere", cptR.getSolde(),"0",cptR.getTauxInterets(),clt.getCIN(),cptR.getAgence().getNum()};
				   	    			        System.out.println(db.queryInsert("compte", colon, infos));
				   	    			        JOptionPane.showMessageDialog(this, "Inserted successfully","Succès",JOptionPane.PLAIN_MESSAGE);
				   	    			        reset(); 
										}	
										else {  JOptionPane.showMessageDialog(this, "Impossible de créer un compte dans une autre agence ","Opération Impossible",JOptionPane.ERROR_MESSAGE);}
											
										}
									}
								else {JOptionPane.showMessageDialog(this,"CIN PAS TROUVÉ ou AGENCE PAS TROUVÉE","Erreur d'authentification",JOptionPane.ERROR_MESSAGE);}
							}
							catch(Exception e1) {
								e1.printStackTrace();
							}
						}
					else {
						JOptionPane.showMessageDialog(this, "SVP entrez les informations complètes");
					}  		
			}
	}
	
	private void rechercher(ActionEvent e) {
		resetT();
		try { if (textField_3.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "SVP entrez quelque chose");
			} else {
				Object[]row=new Object[7];
				DefaultTableModel model=(DefaultTableModel) table_2.getModel();
	        	String id= textField_3.getText();
	            ResultSet rs = db.querySelectAll("compte","code='"+id+"'" );
		    	if(rs.next())  {
		    		row[0]=rs.getInt("code");
	               	row[1]=rs.getString("type");
	               	row[2]=rs.getDouble("solde");
	               	row[3]=rs.getDouble("decouvertA");
	               	row[4]=rs.getDouble("tauxI");
	               	row[5]=rs.getString("cin");
	               	row[6]=rs.getInt("Agence");
	               	model.addRow(row);
		        	table_2.setModel(model);
		        }
		    	else
	        		JOptionPane.showMessageDialog(this, "COMPTE PAS TROUVÉ","ÉCHEC",JOptionPane.ERROR_MESSAGE);
	            reset();
			}	
		}
		catch(Exception e1) {
			e1.printStackTrace();
		}
	}
}
