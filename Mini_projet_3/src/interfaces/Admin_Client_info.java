package interfaces;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import DB.*;
import classes.Client;

import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;

public class Admin_Client_info extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField cin;
	private JTextField nom;
	private JTextField prenom;
	private JTable table;
	image icon;
	BDD db;
	File f;
	private int code;
	private JLabel image;
	ResultSet rs;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin_Client_info frame = new Admin_Client_info();
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
	public Admin_Client_info() {
		db = new BDD("jdbc:mysql://localhost/mini_projet","root","");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 956, 524);
		getContentPane().setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setForeground(Color.GRAY);
		menuBar.setBackground(Color.GRAY);
		menuBar.setBounds(0, 0, 942, 22);
		getContentPane().add(menuBar);
		
		JMenu menu = new JMenu("File");
		menuBar.add(menu);
		
		JMenuItem exit = new JMenuItem("Exit");
		menu.add(exit);
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
		
		JMenuItem compteG = new JMenuItem("Gestion compte");
		menu2.add(compteG);
		compteG.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Admin_Compte_info cpt= new Admin_Compte_info();
					cpt.setVisible(true);
					setVisible(false);
				
			}	
		});
		
		Label label = new Label("Gestion des Clients");
		label.setFont(new Font("Arial", Font.ITALIC, 17));
		label.setBounds(22, 28, 249, 38);
		getContentPane().add(label);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(24, 72, 359, 224);
		getContentPane().add(scrollPane_1);
		
		Panel panel = new Panel();
		scrollPane_1.setViewportView(panel);
		panel.setLayout(null);
		
		Label label_6 = new Label("Photo :");
		label_6.setFont(new Font("Arial Narrow", Font.BOLD, 13));
		label_6.setBounds(103, 175, 108, 21);
		panel.add(label_6);
		
		Label label_6_1 = new Label("CIN :");
		label_6_1.setFont(new Font("Arial Narrow", Font.BOLD, 13));
		label_6_1.setBounds(103, 94, 108, 21);
		panel.add(label_6_1);
		
		Label label_6_2 = new Label("Nom :");
		label_6_2.setFont(new Font("Arial Narrow", Font.BOLD, 13));
		label_6_2.setBounds(103, 121, 108, 21);
		panel.add(label_6_2);
		
		Label label_6_3 = new Label("Prenom");
		label_6_3.setFont(new Font("Arial Narrow", Font.BOLD, 13));
		label_6_3.setBounds(103, 148, 108, 21);
		panel.add(label_6_3);
		
		JButton photo = new JButton("Ajouter photo");
		photo.setFont(new Font("SansSerif", Font.BOLD, 13));
		photo.setBackground(Color.GRAY);
		photo.setBounds(217, 175, 117, 27);
		panel.add(photo);
		photo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JFileChooser choix = new JFileChooser("D:\\Photos\\Photos Cam");
				choix.showOpenDialog(null) ;
				f = choix.getSelectedFile();
				image(f);
			}
			
		});
		
		cin = new JTextField();
		cin.setBounds(217, 94, 117, 19);
		panel.add(cin);
		cin.setColumns(10);
		
		nom = new JTextField();
		nom.setColumns(10);
		nom.setBounds(217, 121, 117, 19);
		panel.add(nom);
		
		prenom = new JTextField();
		prenom.setColumns(10);
		prenom.setBounds(217, 148, 117, 19);
		panel.add(prenom);
		
		image = new JLabel("");
		image.setBounds(10, 10, 88, 75);
		panel.add(image);
		
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
		
		JButton ajouter = new JButton("Ajouter");
		ajouter.setBackground(Color.WHITE);
		ajouter.setFont(new Font("SansSerif", Font.BOLD, 13));
		panel_1.add(ajouter);
		ajouter.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ajouter(e);
			}
			
		});
		
		JButton modifier = new JButton("Modifier");
		modifier.setBackground(Color.WHITE);
		modifier.setFont(new Font("SansSerif", Font.BOLD, 13));
		panel_1.add(modifier);
		modifier.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				modifier(e);
			}
			
		});
		
		JButton supprimer = new JButton("Supprimer");
		supprimer.setBackground(Color.WHITE);
		supprimer.setFont(new Font("SansSerif", Font.BOLD, 13));
		panel_1.add(supprimer);
		supprimer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				supprimer(e);
			}
			
		});
		
		Label label_2 = new Label("Recherche des Clients");
		label_2.setFont(new Font("Cambria", Font.ITALIC, 14));
		label_2.setBounds(428, 28, 204, 31);
		getContentPane().add(label_2);
		
		Label label_3 = new Label("Rechercher Client :");
		label_3.setFont(new Font("Dialog", Font.BOLD, 13));
		label_3.setBounds(442, 72, 157, 31);
		getContentPane().add(label_3);
		
		textField = new JTextField();
		textField.setBounds(627, 84, 182, 19);
		getContentPane().add(textField);
		textField.setColumns(10);
		
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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(424, 150, 492, 327);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		resetT();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row=table.getSelectedRow();
	        	int id = Integer.parseInt(table.getModel().getValueAt(row,0).toString());
	        	cin.setText(table.getModel().getValueAt(row,1).toString());
	        	nom.setText(table.getModel().getValueAt(row,2).toString());
	        	prenom.setText(table.getModel().getValueAt(row,3).toString());
				try {
					ResultSet rs1=db.querySelectAll("client","code= '"+id+"'");
					while(rs1.next()) {
					image.setIcon(new ImageIcon(new ImageIcon("D:\\Photos\\Photos Cam\\"+rs1.getString("image")).getImage().getScaledInstance(88, 75, Image.SCALE_DEFAULT)));
					icon=new image("D:\\Photos\\Photos Cam\\",rs1.getString("image"));
					}
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
				code=id;
			}	
		});
		scrollPane.setViewportView(table);
	}
	
	public void reset1() {
		cin.setText("");
		nom.setText("");
		prenom.setText("");
	}
	
	public void resetT() {
		table.setModel(new DefaultTableModel(
				new Object[][] {
				},
				new String[] {
						"Code", "Cin", "Nom", "Prenom"
				}
			));
	}
	
	public void table() {
		resetT();
		try{
			Object[]row=new Object[5];
			DefaultTableModel model= (DefaultTableModel) table.getModel();
			rs= db.querySelectAll("client");
	    	while(rs.next())  {
	    		row[0]=rs.getInt("code");
               	row[1]=rs.getString("cin");
               	row[2]=rs.getString("nom");
               	row[3]=rs.getString("prenom");
	            model.addRow(row);
	            table.setModel(model);
	        }
	    	}
		catch(Exception e1) {
			e1.printStackTrace();
		}
	}
       
	public String image(File f) {
		icon=new image(f.getPath(),f.getName());
		image.setIcon(new ImageIcon(new ImageIcon(icon.getUrl()).getImage().getScaledInstance(88, 75, Image.SCALE_DEFAULT)));
		return f.getName();
	}
	
	private void ajouter(ActionEvent e) {
		if (cin.getText().equals("") || nom.getText().equals("") || prenom.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "SVP entrez les informations complètes");
        } else {
        	try {
        		ResultSet rs1= db.querySelect(new String[] {"cin","image"},"client");
        		while(rs1.next()) {
        			if(rs1.getString("cin").equals(cin.getText())) {
        				JOptionPane.showMessageDialog(this, "CIN déjà utilisé","Échec",JOptionPane.ERROR_MESSAGE);
        				break;
        			}
        			else if(rs1.getString("image").equals(this.image(f))) {
        				JOptionPane.showMessageDialog(this, "Photo invalide ","Échec",JOptionPane.ERROR_MESSAGE);
        				break;
        			}
        			else continue;
        		}
        		if(rs1.isAfterLast() || !rs1.next()) {
		            String[] colon = { "cin","nom","prenom","image"};
		            Client clt = new Client(cin.getText(),nom.getText(),prenom.getText(),this.image(f));
		            String[] infos = { clt.getCIN(), clt.getName(),clt.getPrenom(),clt.getImage()};
		            System.out.println(db.queryInsert("client", colon, infos));
		            JOptionPane.showMessageDialog(this, "Inserted successfully","Succès",JOptionPane.PLAIN_MESSAGE);
		            reset1();
		            image.setIcon(null);
        		}
        	}
        	catch(Exception e1) {
        		 JOptionPane.showMessageDialog(this, "Inserted failed","Perte",JOptionPane.ERROR_MESSAGE);
        		 e1.printStackTrace();
        	}
        }
	}
	
	private void modifier(ActionEvent e) {
		try { if (cin.getText().equals("") || nom.getText().equals("") || prenom.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "SVP entrez les informations complètes");
			} else {
				ResultSet rs1= db.querySelectAll("client","cin NOT LIKE '"+code+"'");
        		while(rs1.next()) {
        			if(rs1.getString("cin").equals(cin.getText())) {
        				JOptionPane.showMessageDialog(this, "CIN déjà utilisé","Échec",JOptionPane.ERROR_MESSAGE);
        				break;
        			}
        			else if(rs1.getString("image").equals(icon.getName())) {
        				JOptionPane.showMessageDialog(this, "Photo déjà utilisée","Échec",JOptionPane.ERROR_MESSAGE);
        				break;
        			}
        			else continue;
        		}
	        		if(rs1.isAfterLast()) {
		            String[] colon = { "cin","nom","prenom","image"};
		            Object[] infos = {cin.getText(),nom.getText(),prenom.getText(),icon.getName()};
		            System.out.println(db.queryUpdate("client", colon, infos,"code='"+ code+"'"));
		            JOptionPane.showMessageDialog(this, "Update successfully","Succès",JOptionPane.PLAIN_MESSAGE);
		            reset1();
		            image.setIcon(null);
        		}	
			}
		}
		catch(Exception e1) {
			JOptionPane.showMessageDialog(this, "Update failed","Perte",JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void supprimer(ActionEvent e) {
		String id = String.valueOf(table.getValueAt(table.getSelectedRow(), 0));
        if (JOptionPane.showConfirmDialog(this, "est ce que tu es sûr que tu veux supprimer", "attention!!!", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
            db.queryDelete("client", "code=" + id);
            JOptionPane.showMessageDialog(this, "Deleted successfully","Succès",JOptionPane.PLAIN_MESSAGE);
        } else {
        	JOptionPane.showMessageDialog(this, "Delete failed","Perte",JOptionPane.ERROR_MESSAGE);
        }
        reset1();
        image.setIcon(null);
	}
	
	private void rechercher(ActionEvent e) {
		resetT();
		try { if (textField.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "SVP entrez quelque chose");
			} else {
				Object[] row=new Object[5];
				DefaultTableModel model=(DefaultTableModel) table.getModel();
	        	String id= textField.getText();
	            rs = db.querySelectAll("client","code="+id);
	        	if(rs.next())  {
	        		row[0]=rs.getInt("code");
	               	row[1]=rs.getString("cin");
	               	row[2]=rs.getString("nom");
	               	row[3]=rs.getString("prenom");
	               	model.addRow(row);
	               	table.setModel(model);
	               }
	        	else
	        		JOptionPane.showMessageDialog(this, "CLIENT PAS TROUVÉ","ÉCHEC",JOptionPane.ERROR_MESSAGE);
	       	}	
		}
		catch(Exception e1) {
			e1.printStackTrace();
		}
	}
}

