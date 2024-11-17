package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BLL.Persona;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PantallaRegistro extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField inpNombre;
	private JTextField inpApellido;
	private JTextField inpEmail;
	private JPasswordField inpPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaRegistro frame = new PantallaRegistro();
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
	public PantallaRegistro() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblnombre = new JLabel("Nombre");
		lblnombre.setBounds(23, 26, 74, 14);
		contentPane.add(lblnombre);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(23, 51, 74, 14);
		contentPane.add(lblApellido);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(23, 76, 74, 14);
		contentPane.add(lblEmail);
		
		JLabel lblContrase = new JLabel("Contraseña");
		lblContrase.setBounds(23, 101, 74, 14);
		contentPane.add(lblContrase);
		
		JLabel lblRol = new JLabel("Rol");
		lblRol.setBounds(23, 126, 74, 14);
		contentPane.add(lblRol);
		
		inpNombre = new JTextField();
		inpNombre.setBounds(80, 23, 86, 20);
		contentPane.add(inpNombre);
		inpNombre.setColumns(10);
		
		inpApellido = new JTextField();
		inpApellido.setColumns(10);
		inpApellido.setBounds(80, 48, 86, 20);
		contentPane.add(inpApellido);
		
		inpEmail = new JTextField();
		inpEmail.setColumns(10);
		inpEmail.setBounds(80, 73, 86, 20);
		contentPane.add(inpEmail);
		
		inpPassword = new JPasswordField();
		inpPassword.setBounds(90, 101, 74, 20);
		contentPane.add(inpPassword);
		
		JComboBox inpRol = new JComboBox();
		inpRol.setBounds(80, 126, 86, 20);
		contentPane.add(inpRol);
		
		JButton btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Persona.Registrarse(inpNombre.getText(), inpApellido.getText(), inpEmail.getText(), inpPassword.getText(), inpRol.getX());
			}
		});
		btnRegistrarse.setBounds(77, 151, 89, 23);
		contentPane.add(btnRegistrarse);
	}
}
