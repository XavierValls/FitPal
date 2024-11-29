package GUI;

import java.awt.CardLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import BLL.Alumno;
import BLL.PersonalTrainer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent; // Asegúrate de tener la clase PersonalTrainer

public class PantallaAlumno extends JFrame {
    private Alumno alumno;
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private CardLayout cardLayout;

    public PantallaAlumno(Alumno alumno) {
        this.alumno = alumno;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 781, 503);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        cardLayout = new CardLayout();
        contentPane.setLayout(cardLayout);

        JPanel mainMenu = new JPanel();
        mainMenu.setLayout(null);

        JLabel lblMenu = new JLabel("Menu de Alumno");
        lblMenu.setBounds(330, 20, 94, 30);
        mainMenu.add(lblMenu);

        JButton btnRutinas = new JButton("Mis Rutinas");
        btnRutinas.setBounds(302, 70, 150, 30);
        btnRutinas.addActionListener(e -> cardLayout.show(contentPane, "RutinasPanel"));
        mainMenu.add(btnRutinas);

        JButton btnDesafios = new JButton("Desafios");
        btnDesafios.setBounds(302, 120, 150, 30);
        btnDesafios.addActionListener(e -> cardLayout.show(contentPane, "DesafiosPanel"));
        mainMenu.add(btnDesafios);

        JButton btnObjetivos = new JButton("Mi Objetivo");
        btnObjetivos.setBounds(302, 170, 150, 30);
        btnObjetivos.addActionListener(e -> cardLayout.show(contentPane, "ObjetivosPanel"));
        mainMenu.add(btnObjetivos);

        JButton btnPersonalTrainer = new JButton("Personal Trainers");
        btnPersonalTrainer.setBounds(302, 220, 150, 30);
        btnPersonalTrainer.addActionListener(e -> cardLayout.show(contentPane, "PersonalTrainerPanel"));
        mainMenu.add(btnPersonalTrainer);
        
        JButton btnPerfil = new JButton("Mi Perfil");
        btnPerfil.setBounds(302, 270, 150, 30);
        btnPerfil.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		JOptionPane.showMessageDialog(null, alumno.toString());
        	}
        });
        mainMenu.add(btnPerfil);


        JButton btnCerrarSesion = new JButton("Cerrar Sesion");
        btnCerrarSesion.setBounds(302, 320, 150, 30);
        btnCerrarSesion.addActionListener(e -> System.exit(0));
        mainMenu.add(btnCerrarSesion);

        contentPane.add(mainMenu, "MainMenu");

        JPanel rutinasPanel = new JPanel();
        rutinasPanel.setLayout(null);

        JLabel lblRutinas = new JLabel("Mis Rutinas");
        lblRutinas.setBounds(345, 20, 65, 30);
        rutinasPanel.add(lblRutinas);

        JButton btnVerMisRutinas = new JButton("Ver Mis Rutinas");
        btnVerMisRutinas.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		VerRutinasAlumno VerRutinasAlumno = new VerRutinasAlumno(alumno);
        		VerRutinasAlumno.setVisible(true);
        		dispose();
        	}
        });
        btnVerMisRutinas.setBounds(302, 70, 150, 30);
        rutinasPanel.add(btnVerMisRutinas);

        JButton btnSalirRutinas = new JButton("Volver");
        btnSalirRutinas.setBounds(302, 120, 150, 30);
        btnSalirRutinas.addActionListener(e -> cardLayout.show(contentPane, "MainMenu"));
        rutinasPanel.add(btnSalirRutinas);

        contentPane.add(rutinasPanel, "RutinasPanel");

        JPanel desafiosPanel = new JPanel();
        desafiosPanel.setLayout(null);

        JLabel lblDesafios = new JLabel("Mis Desafios");
        lblDesafios.setBounds(340, 20, 75, 30);
        desafiosPanel.add(lblDesafios);

        JButton btnVerMisDesafios = new JButton("Ver Desafios");
        btnVerMisDesafios.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		VerDesafiosAlumno VerDesafiosAlumno = new VerDesafiosAlumno(alumno);
        		VerDesafiosAlumno.setVisible(true);
        		dispose();
        	}
        });
        btnVerMisDesafios.setBounds(302, 70, 150, 30);
        desafiosPanel.add(btnVerMisDesafios);

        JButton btnSalirDesafios = new JButton("Volver");
        btnSalirDesafios.setBounds(302, 120, 150, 30);
        btnSalirDesafios.addActionListener(e -> cardLayout.show(contentPane, "MainMenu"));
        desafiosPanel.add(btnSalirDesafios);

        contentPane.add(desafiosPanel, "DesafiosPanel");

        JPanel objetivosPanel = new JPanel();
        objetivosPanel.setLayout(null);

        JLabel lblObjetivos = new JLabel("Mis Objetivos");
        lblObjetivos.setBounds(341, 20, 73, 30);
        objetivosPanel.add(lblObjetivos);

        JButton btnVerMisObjetivos = new JButton("Ver Mi Objetivo");
        btnVerMisObjetivos.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		VerObjetivoAlumno VerObjetivoAlumno = new VerObjetivoAlumno(alumno);
        		VerObjetivoAlumno.setVisible(true);
        		dispose();
        	}
        });
        btnVerMisObjetivos.setBounds(302, 70, 150, 30);
        objetivosPanel.add(btnVerMisObjetivos);


        JButton btnSalirObjetivos = new JButton("Volver");
        btnSalirObjetivos.setBounds(302, 116, 150, 30);
        btnSalirObjetivos.addActionListener(e -> cardLayout.show(contentPane, "MainMenu"));
        objetivosPanel.add(btnSalirObjetivos);

        contentPane.add(objetivosPanel, "ObjetivosPanel");

        JPanel personalTrainerPanel = new JPanel();
        personalTrainerPanel.setLayout(null);

        JLabel lblPersonalTrainer = new JLabel("Personal Trainers");
        lblPersonalTrainer.setBounds(327, 20, 101, 30);
        personalTrainerPanel.add(lblPersonalTrainer);

        JButton btnContratarPersonalTrainer = new JButton("Ver Personal Trainers");
        btnContratarPersonalTrainer.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		ContratarPersonalTrainerAlumno ContratarPersonalTrainerAlumno = new ContratarPersonalTrainerAlumno(alumno);
        		ContratarPersonalTrainerAlumno.setVisible(true);
        		dispose();
        	}
        });
        btnContratarPersonalTrainer.setBounds(288, 70, 178, 30);
        personalTrainerPanel.add(btnContratarPersonalTrainer);



        JButton btnSalirPersonalTrainer = new JButton("Volver");
        btnSalirPersonalTrainer.setBounds(288, 119, 178, 30);
        btnSalirPersonalTrainer.addActionListener(e -> cardLayout.show(contentPane, "MainMenu"));
        personalTrainerPanel.add(btnSalirPersonalTrainer);

        contentPane.add(personalTrainerPanel, "PersonalTrainerPanel");

        cardLayout.show(contentPane, "MainMenu");
    }
}
