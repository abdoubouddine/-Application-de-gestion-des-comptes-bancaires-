package interfaces;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JSeparator;

public class Employé_choix extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Employé_choix frame = new Employé_choix();
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
	public Employé_choix() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 709, 401);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("BIENVENUE");
		lblNewLabel.setFont(new Font("Dubai", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(203, 10, 291, 41);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("GESTION BANCAIRE");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 29));
		lblNewLabel_1.setBounds(69, 71, 559, 66);
		contentPane.add(lblNewLabel_1);
		
		JButton btnNewButton = new JButton("Gestion des Clients");
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setFont(new Font("Arial Black", Font.PLAIN, 17));
		btnNewButton.setBounds(185, 182, 325, 51);
		contentPane.add(btnNewButton);
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Admin_Client_info clt= new Admin_Client_info();
				clt.setVisible(true);
				setVisible(false);
			}
			
		});
		
		JButton btnGestionDesComptes = new JButton("Gestion des Comptes");
		btnGestionDesComptes.setBackground(Color.WHITE);
		btnGestionDesComptes.setFont(new Font("Arial Black", Font.PLAIN, 17));
		btnGestionDesComptes.setBounds(185, 257, 325, 51);
		contentPane.add(btnGestionDesComptes);
		btnGestionDesComptes.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Admin_Compte_info cpt = new Admin_Compte_info();
				cpt.setVisible(true);
				setVisible(false);
			}
			
		});
		
		JSeparator separator = new JSeparator();
		separator.setBounds(39, 147, 619, 10);
		contentPane.add(separator);
	}
}
