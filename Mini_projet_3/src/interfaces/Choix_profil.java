package interfaces;


import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

public class Choix_profil extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public JButton btnAdmin = new JButton("ADMIN");
	public JButton btnClient = new JButton("CLIENT");
	public JButton btnEmploye = new JButton("EMPLOYÉ");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Choix_profil frame = new Choix_profil();
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
	public Choix_profil() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 709, 401);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("WELCOME");
		lblNewLabel.setFont(new Font("Dubai", Font.BOLD, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(203, 10, 291, 41);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Choose your field");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 29));
		lblNewLabel_1.setBounds(69, 81, 559, 66);
		contentPane.add(lblNewLabel_1);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(39, 174, 619, 10);
		contentPane.add(separator);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(39, 61, 619, 10);
		contentPane.add(separator_1);
		
		btnAdmin.setBackground(Color.WHITE);
		btnAdmin.setForeground(Color.RED);
		btnAdmin.setFont(new Font("Arial Black", Font.PLAIN, 16));
		btnAdmin.setBounds(69, 230, 120, 41);
		contentPane.add(btnAdmin);
		btnAdmin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				login admin = new login();
				admin.setVisible(true);
			}
		});
		
		
		btnEmploye.setForeground(Color.RED);
		btnEmploye.setBackground(Color.WHITE);
		btnEmploye.setFont(new Font("Arial Black", Font.PLAIN, 16));
		btnEmploye.setBounds(287, 230, 120, 41);
		contentPane.add(btnEmploye);
		btnEmploye.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				login admin = new login();
				admin.setVisible(true);
				if(admin.textField.getText().equals("employe") && Arrays.equals(admin.passwordField.getPassword(),new char[] {'1','2','3'})) {
					admin.btnLogin.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							AdminInfo admin2 = new AdminInfo();
							admin2.setVisible(true);
							admin.dispose();
						}
					});		
				}
			}
		});
		
		
		btnClient.setBackground(Color.WHITE);
		btnClient.setForeground(Color.RED);
		btnClient.setFont(new Font("Arial Black", Font.PLAIN, 16));
		btnClient.setBounds(506, 230, 108, 41);
		contentPane.add(btnClient);
		btnClient.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				login admin = new login();
				admin.setVisible(true);
				if(admin.textField.getText().equals("client") && Arrays.equals(admin.passwordField.getPassword(),new char[] {'1','2','3'}) && admin.btnLogin.isEnabled()) {
						AdminInfo admin2 = new AdminInfo();
						admin2.setVisible(true);
						admin.dispose();
				}
			}
		});
	}
}

