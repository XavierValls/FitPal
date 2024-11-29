package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BLL.Alumno;
import BLL.Persona;
import BLL.PersonalTrainer;
import DLL.ControllerPersonalTrainer;

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
		setBounds(100, 100, 600, 410);
		titulo = new JPanel();
		titulo.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(titulo);
		titulo.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Loguearse");
		lblNewLabel.setBounds(260, 86, 67, 32);
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 13));
		titulo.add(lblNewLabel);
		
		inpEmail = new JTextField();
		inpEmail.setBounds(170, 129, 247, 20);
		titulo.add(inpEmail);
		inpEmail.setColumns(10);
		
		JLabel email = new JLabel("Email");
		email.setBounds(126, 132, 46, 14);
		titulo.add(email);
		
		inpPassword = new JPasswordField();
		inpPassword.setBounds(170, 171, 247, 20);
		titulo.add(inpPassword);
		
		JLabel password = new JLabel("Contraseña");
		password.setBounds(98, 174, 67, 14);
		titulo.add(password);
		
		JButton btnLogin = new JButton("Ingresar");
		btnLogin.setBounds(170, 198, 119, 23);
		btnLogin.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        Persona usuario = (Persona) Persona.Loguearse(inpEmail.getText(), inpPassword.getText());

		        if (usuario instanceof PersonalTrainer) {
		            PersonalTrainer personalTrainer = (PersonalTrainer) usuario;
		            PantallaPersonal pantalla = new PantallaPersonal(personalTrainer);
		            pantalla.setVisible(true);

		            dispose();
		        } else if (usuario instanceof Alumno) {
		            Alumno alumno = (Alumno) usuario;
		            PantallaAlumno pantallaA = new PantallaAlumno(alumno);
		            pantallaA.setVisible(true);

		            dispose();
		        } else {
		            JOptionPane.showMessageDialog(null, "Error, no se encontro ningun usuario");
		        }
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
		btnRegister.setBounds(298, 198, 119, 23);
		titulo.add(btnRegister);
	}
}
