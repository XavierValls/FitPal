package GUI;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.EventQueue;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import BLL.PersonalTrainer;
import BLL.Rutina;
import DLL.ControllerRutina;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;
import java.awt.GridLayout;

public class PantallaPersonal extends JFrame {
	private PersonalTrainer personalTrainer;
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private CardLayout cardLayout;
    private JTable tableRutinas;


    /**
     * Create the frame.
     */
    public PantallaPersonal(PersonalTrainer personalTrainer) {
    	this.personalTrainer = personalTrainer;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1000, 600);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        cardLayout = new CardLayout();
        contentPane.setLayout(cardLayout);

        JPanel mainMenu = new JPanel();
        mainMenu.setLayout(null);

        JLabel lblMenu = new JLabel("Menu de Personal Trainer");
        lblMenu.setBounds(417, 68, 139, 30);
        mainMenu.add(lblMenu);

        JButton btnRutinas = new JButton("Rutinas");
        btnRutinas.setBounds(412, 118, 150, 30);
        btnRutinas.addActionListener(e -> cardLayout.show(contentPane, "RutinasPanel"));
        mainMenu.add(btnRutinas);

        JButton btnAlumnos = new JButton("Alumnos");
        btnAlumnos.setBounds(412, 168, 150, 30);
        btnAlumnos.addActionListener(e -> cardLayout.show(contentPane, "AlumnosPanel"));
        mainMenu.add(btnAlumnos);

        JButton btnDesafios = new JButton("Desafios");
        btnDesafios.setBounds(412, 218, 150, 30);
        btnDesafios.addActionListener(e -> cardLayout.show(contentPane, "DesafiosPanel"));
        mainMenu.add(btnDesafios);

        JButton btnPerfil = new JButton("Mi Perfil");
        btnPerfil.setBounds(412, 268, 150, 30);
        btnPerfil.addActionListener(e -> cardLayout.show(contentPane, "PerfilPanel"));
        mainMenu.add(btnPerfil);

        JButton btnCerrarSesion = new JButton("Cerrar Sesion");
        btnCerrarSesion.setBounds(412, 318, 150, 30);
        btnCerrarSesion.addActionListener(e -> System.exit(0));
        mainMenu.add(btnCerrarSesion);

        contentPane.add(mainMenu, "MainMenu");

        JPanel rutinasPanel = new JPanel();
        rutinasPanel.setLayout(null);
        

        JLabel lblRutinas = new JLabel("Gestion de Rutinas");
        lblRutinas.setBounds(438, 20, 98, 30);
        rutinasPanel.add(lblRutinas);

        JButton btnMisRutinas = new JButton("Mis Rutinas");
        btnMisRutinas.addActionListener(e -> cardLayout.show(contentPane, "misRutinas"));
        btnMisRutinas.setBounds(412, 70, 150, 30);
        rutinasPanel.add(btnMisRutinas);
        

        JButton btnCrearRutina = new JButton("Crear Rutina");
        btnCrearRutina.setBounds(412, 120, 150, 30);
        btnCrearRutina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CrearRutina crearRutina = new CrearRutina(cardLayout, contentPane, personalTrainer);
				contentPane.add(crearRutina, "CrearRutina");
	            cardLayout.show(contentPane, "CrearRutina");
				
			}
		});
        rutinasPanel.add(btnCrearRutina);
        
        

        JButton btnEditarRutina = new JButton("Editar Rutina");
        btnEditarRutina.setBounds(412, 170, 150, 30);
        btnEditarRutina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditarRutina EditarRutina = new EditarRutina(cardLayout, contentPane, personalTrainer);
				contentPane.add(EditarRutina, "EditarRutina");
	            cardLayout.show(contentPane, "EditarRutina");
				
			}
		});
        rutinasPanel.add(btnEditarRutina);

        JButton btnSalirRutinas = new JButton("Volver");
        btnSalirRutinas.setBounds(412, 220, 150, 30);
        btnSalirRutinas.addActionListener(e -> cardLayout.show(contentPane, "MainMenu"));
        rutinasPanel.add(btnSalirRutinas);
        
        JPanel misRutinas = new JPanel();
        misRutinas.setLayout(null);
        contentPane.add(misRutinas, "misRutinas");

        contentPane.add(rutinasPanel, "RutinasPanel");
        
        

        
        
        tableRutinas = new JTable();
        tableRutinas.setToolTipText("");
        tableRutinas.setModel(new DefaultTableModel(
        	new Object[][] {
        	},
        	new String[] {
        		"ID Rutina", "Titulo", "Descripcion", "Nombre Personal Trainer", "Apellido Personal Trainer"
        	}
        ));
        tableRutinas.setBounds(68, 50, 434, 258);
        misRutinas.add(tableRutinas);
        
        JScrollPane scrollPane = new JScrollPane(tableRutinas);
        scrollPane.setBounds(50, 50, 865, 460);
        misRutinas.add(scrollPane);

        JButton btnCargarRutinas = new JButton("Cargar Rutinas");
        btnCargarRutinas.setBounds(305, 521, 150, 30);
        btnCargarRutinas.addActionListener(e -> cargarRutinas());
        misRutinas.add(btnCargarRutinas);
        
        
        
        JLabel lblMisRutinas = new JLabel("Mis Rutinas");
        lblMisRutinas.setBounds(449, 25, 100, 14);
        misRutinas.add(lblMisRutinas);
        
        JButton btnVolverRutinas = new JButton("Volver");
        btnVolverRutinas.addActionListener(e -> cardLayout.show(contentPane, "MainMenu"));
        btnVolverRutinas.setBounds(477, 521, 150, 30);
        misRutinas.add(btnVolverRutinas);

        JPanel alumnosPanel = new JPanel();
        alumnosPanel.setLayout(null);

        JLabel lblAlumnos = new JLabel("Gestion de Alumnos");
        lblAlumnos.setBounds(432, 20, 110, 30);
        alumnosPanel.add(lblAlumnos);

        JButton btnMisAlumnos = new JButton("Mis Alumnos");
        btnMisAlumnos.setBounds(412, 70, 150, 30);
        alumnosPanel.add(btnMisAlumnos);

        JButton btnSalirAlumnos = new JButton("Volver");
        btnSalirAlumnos.setBounds(412, 120, 150, 30);
        btnSalirAlumnos.addActionListener(e -> cardLayout.show(contentPane, "MainMenu"));
        alumnosPanel.add(btnSalirAlumnos);

        contentPane.add(alumnosPanel, "AlumnosPanel");

        JPanel desafiosPanel = new JPanel();
        desafiosPanel.setLayout(null);

        JLabel lblDesafios = new JLabel("Gestion de Desafios");
        lblDesafios.setBounds(435, 20, 103, 30);
        desafiosPanel.add(lblDesafios);

        JButton btnVerDesafios = new JButton("Ver Desafios");
        btnVerDesafios.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		VerDesafios VerDesafios = new VerDesafios(personalTrainer);
        		VerDesafios.setVisible(true);
        		dispose();
        	}
        });
        btnVerDesafios.setBounds(412, 70, 150, 30);
        desafiosPanel.add(btnVerDesafios);

        JButton btnCrearDesafios = new JButton("Crear Desafio");
        btnCrearDesafios.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		CrearDesafio CrearDesafio = new CrearDesafio(personalTrainer);
        		CrearDesafio.setVisible(true);
        		dispose();
        	}
        });
        btnCrearDesafios.setBounds(412, 120, 150, 30);
        desafiosPanel.add(btnCrearDesafios);

        JButton btnSalirDesafios = new JButton("Volver");
        btnSalirDesafios.setBounds(412, 171, 150, 30);
        btnSalirDesafios.addActionListener(e -> cardLayout.show(contentPane, "MainMenu"));
        desafiosPanel.add(btnSalirDesafios);

        contentPane.add(desafiosPanel, "DesafiosPanel");

        JPanel perfilPanel = new JPanel();
        perfilPanel.setLayout(null);

        JLabel lblPerfil = new JLabel("Mi Perfil");
        lblPerfil.setBounds(461, 20, 51, 30);
        perfilPanel.add(lblPerfil);

        JButton btnVerPerfil = new JButton("Ver Perfil");
        btnVerPerfil.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		JOptionPane.showMessageDialog(null, personalTrainer.toString());
        	}
        });
        btnVerPerfil.setBounds(412, 70, 150, 30);
        perfilPanel.add(btnVerPerfil);

        JButton btnSalirPerfil = new JButton("Volver");
        btnSalirPerfil.setBounds(412, 119, 150, 30);
        btnSalirPerfil.addActionListener(e -> cardLayout.show(contentPane, "MainMenu"));
        perfilPanel.add(btnSalirPerfil);

        contentPane.add(perfilPanel, "PerfilPanel");
    }

    private void cargarRutinas() {
        try {
            LinkedList<Rutina> listaOriginal = ControllerRutina.MostrarRutinas(this.personalTrainer.getIdPersonalTrainer());

            Object[][] datosClonados = new Object[listaOriginal.size()][5];
            for (int i = 0; i < listaOriginal.size(); i++) {
                Rutina rutina = listaOriginal.get(i);
                datosClonados[i][0] = rutina.getIdRutina();
                datosClonados[i][1] = rutina.getTitulo();
                datosClonados[i][2] = rutina.getDescripcion();
                datosClonados[i][3] = rutina.getPersonalTrainer().getNombre();
                datosClonados[i][4] = rutina.getPersonalTrainer().getApellido();
            }

            DefaultTableModel model = (DefaultTableModel) tableRutinas.getModel();
            model.setRowCount(0);

            for (Object[] fila : datosClonados) {
                model.addRow(fila);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar rutinas: " + e.getMessage());
        }
    }
}
