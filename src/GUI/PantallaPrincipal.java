package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BLL.Alumno;
import BLL.Persona;
import BLL.PersonalTrainer;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PantallaPrincipal extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel titulo;
	private JTextField inpEmail;
	private JPasswordField inpPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaPrincipal frame = new PantallaPrincipal();
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
	public PantallaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		titulo = new JPanel();
		titulo.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(titulo);
		titulo.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Loguearse");
		lblNewLabel.setBounds(210, 37, 89, 32);
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		titulo.add(lblNewLabel);
		
		inpEmail = new JTextField();
		inpEmail.setBounds(176, 80, 145, 20);
		titulo.add(inpEmail);
		inpEmail.setColumns(10);
		
		JLabel email = new JLabel("Email");
		email.setBounds(124, 83, 46, 14);
		titulo.add(email);
		
		inpPassword = new JPasswordField();
		inpPassword.setBounds(176, 122, 145, 20);
		titulo.add(inpPassword);
		
		JLabel password = new JLabel("Contraseña");
		password.setBounds(104, 125, 67, 14);
		titulo.add(password);
		
		JButton btnLogin = new JButton("Ingresar");
		btnLogin.setBounds(186, 149, 119, 23);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Persona.Loguearse(inpEmail.getText(), inpPassword.getText()) instanceof PersonalTrainer) {
					PantallaPersonal pantalla = new PantallaPersonal();
					pantalla.setVisible(true);
				} else if(Persona.Loguearse(inpEmail.getText(), inpPassword.getText()) instanceof Alumno) {
					PantallaPersonal pantallaA = new PantallaPersonal();
					pantallaA.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "Error, no se encontro ningun usuario");
				}
				dispose();
				
				
				
			}
		});
		titulo.add(btnLogin);
		
		JButton btnRegister = new JButton("Registrarse");
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				PantallaRegistro pantalla = new PantallaRegistro();
				pantalla.setVisible(true);
				
			}
		});
		btnRegister.setBounds(180, 183, 119, 23);
		titulo.add(btnRegister);
	}
}
