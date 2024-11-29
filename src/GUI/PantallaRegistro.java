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
import javax.swing.DefaultComboBoxModel;
import java.awt.Color;

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
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblnombre = new JLabel("Nombre");
		lblnombre.setBounds(118, 90, 74, 14);
		contentPane.add(lblnombre);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(118, 122, 74, 14);
		contentPane.add(lblApellido);
		
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setBounds(118, 147, 74, 14);
		contentPane.add(lblEmail);
		
		JLabel lblContrase = new JLabel("Contraseña");
		lblContrase.setBounds(92, 177, 74, 14);
		contentPane.add(lblContrase);
		
		JLabel lblRol = new JLabel("Rol");
		lblRol.setBounds(129, 205, 74, 14);
		contentPane.add(lblRol);
		
		inpNombre = new JTextField();
		inpNombre.setBounds(166, 90, 278, 20);
		contentPane.add(inpNombre);
		inpNombre.setColumns(10);
		
		inpApellido = new JTextField();
		inpApellido.setColumns(10);
		inpApellido.setBounds(166, 118, 278, 20);
		contentPane.add(inpApellido);
		
		inpEmail = new JTextField();
		inpEmail.setColumns(10);
		inpEmail.setBounds(166, 146, 278, 20);
		contentPane.add(inpEmail);
		
		inpPassword = new JPasswordField();
		inpPassword.setBounds(166, 174, 278, 20);
		contentPane.add(inpPassword);
		
		JComboBox inpRol = new JComboBox();
		inpRol.setModel(new DefaultComboBoxModel(new String[] {"PersonalTrainer", "Alumno"}));
		inpRol.setBounds(166, 202, 278, 20);
		contentPane.add(inpRol);
		
		JLabel lblErrorMessage = new JLabel("");
		lblErrorMessage.setForeground(new Color(255, 0, 0)); 
		lblErrorMessage.setBounds(125, 220, 300, 20);
		contentPane.add(lblErrorMessage);
		
		JButton btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.addActionListener(new ActionListener() {
			String nombre = inpNombre.getText();
			String apellido = inpApellido.getText();
			String email = inpEmail.getText();
			String password = inpPassword.getText();
			int rolSeleccionado = inpRol.getSelectedIndex() + 1;
			public void actionPerformed(ActionEvent e) {
				 if (nombre.isEmpty() || apellido.isEmpty() ||email.isEmpty() ||password.isEmpty()) {
				        lblErrorMessage.setText("Por favor, completa todos los campos.");
				    } else {
				        lblErrorMessage.setText("");
				        Persona.Registrarse(inpNombre.getText(), inpApellido.getText(), inpEmail.getText(), inpPassword.getText(), rolSeleccionado);
				        dispose();
				        PantallaPrincipal pantalla = new PantallaPrincipal();
						pantalla.setVisible(true);
				    }
			}
		});
		btnRegistrarse.setBounds(189, 230, 112, 23);
		contentPane.add(btnRegistrarse);
		
		JLabel lblRegistro = new JLabel("Registro");
		lblRegistro.setBounds(267, 68, 49, 14);
		contentPane.add(lblRegistro);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PantallaPrincipal PantallaPrincipal = new PantallaPrincipal();
				PantallaPrincipal.setVisible(true);
				dispose();
			}
		});
		btnVolver.setBounds(313, 230, 118, 23);
		contentPane.add(btnVolver);
	}
}
