package interfaces;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DB.BDD;
import classes.Client;
import classes.CompteCourant;
import classes.CompteRemunere;
import javafx.scene.effect.InnerShadow;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.JPasswordField;


public class login extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JTextField textField;
	public JButton btnLogin, btnClear, btnExit;
	public JPasswordField passwordField;
	ResultSet rs; 
    String username, password, type;
    public static ArrayList<CompteCourant> listeComptes=new ArrayList<CompteCourant>();
	public static ArrayList<CompteRemunere> listeComptes1=new ArrayList<CompteRemunere>();
	BDD db;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					login frame = new login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @return 
	 * @throws SQLException 
	 */
	public login() {
		db = new BDD("jdbc:mysql://localhost/mini_projet","root","");
		ResultSet rs= db.querySelectAll("compte");
		try {
			while(rs.next()) {
				if(rs.getString("type").equals("Courant")) {
					CompteCourant cptR= new CompteCourant(new Client(rs.getString("cin")),rs.getDouble("decouvertA"),rs.getDouble("solde"),rs.getInt("Agence"));
					cptR.setNum(rs.getInt("code"));
					listeComptes.add(cptR);
				}
				else{
					CompteRemunere cptC= new CompteRemunere(new Client(rs.getString("cin")),rs.getDouble("tauxI"),rs.getDouble("solde"),rs.getInt("Agence"));
					cptC.setNum(rs.getInt("code"));
					listeComptes1.add(cptC);
				}
				
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println(listeComptes);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 896, 515);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		InnerShadow is = new InnerShadow();
		is.setOffsetX(4.0f);
		is.setOffsetY(4.0f);
		
		JLabel lblNewLabel = new JLabel("Login System");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("Verdana",Font.BOLD,80));
		lblNewLabel.setBounds(123, 22, 654, 95);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Username");
		lblNewLabel_1.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(89, 160, 165, 33);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password");
		lblNewLabel_1_1.setFont(new Font("Arial", Font.PLAIN, 20));
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setBounds(89, 244, 165, 33);
		contentPane.add(lblNewLabel_1_1);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setBackground(Color.GREEN);
		btnLogin.setFont(new Font("Arial", Font.BOLD, 19));
		btnLogin.setBounds(169, 369, 111, 33);
		contentPane.add(btnLogin);
		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				jButtonActionPerformed(e);
			}
		});
		
		textField = new JTextField();
		textField.setBounds(392, 157, 184, 36);
		contentPane.add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField(3);
		passwordField.setBounds(392, 244, 184, 29);
		contentPane.add(passwordField);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setForeground(Color.BLACK);
		btnClear.setBackground(Color.GRAY);
		btnClear.setFont(new Font("Arial", Font.BOLD, 19));
		btnClear.setBounds(380, 369, 111, 33);
		contentPane.add(btnClear);
		btnClear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				textField.setText("");
				passwordField.setText("");
			}
		});
		
		JButton btnExit = new JButton("Exit");
		btnExit.setBackground(Color.RED);
		btnExit.setFont(new Font("Arial", Font.BOLD, 19));
		btnExit.setBounds(604, 369, 111, 33);
		contentPane.add(btnExit);
		btnExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});	
	}
	
	public static ArrayList<CompteCourant> listeR(){
		return listeComptes;
	}
	public static ArrayList<CompteRemunere> listeC(){
		return listeComptes1;
	}
	public void jButtonActionPerformed( ActionEvent e) {
		try {
			if(textField.getText().equals("") && "".equals(new String(passwordField.getPassword()))) {
				JOptionPane.showMessageDialog(this,"Saisir les informations demandées"," Erreur d'authentification", JOptionPane.ERROR_MESSAGE);
			}
			else {
				if(textField.getText().equals("directeur") && "123".equals(new String(passwordField.getPassword()))) {
					AdminInfo admin = new AdminInfo();
					admin.setVisible(true);
					setVisible(false);
				}
				else if (textField.getText().equals("employe") && "123".equals(new String(passwordField.getPassword()))) {
					Employé_choix employe = new Employé_choix();
					employe.setVisible(true);
					setVisible(false);
				}
				else if(textField.getText().equals("client") && "123".equals(new String(passwordField.getPassword()))){
					client_info client = new client_info();
					client.setVisible(true);
					setVisible(false);
				}
				else {
					JOptionPane.showMessageDialog(this,"Cet utilisateur n'est pas présent"," Erreur d'authentification", JOptionPane.ERROR_MESSAGE);
					textField.setText("");
					passwordField.setText("");
				}
				
			}
		}
		catch(Exception e1) {
			e1.printStackTrace();
		}
	}
}

