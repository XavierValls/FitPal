package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import BLL.PersonalTrainer;
import BLL.Rutina;
import DLL.ControllerPersonalTrainer;
import DLL.ControllerRutina;

public class CrearRutina extends JPanel {
	private PersonalTrainer personalTrainer;
    private JTextField txtTitulo;
    private JTextField txtDescripcion;
    private JRadioButton rbPersonalizada;
    private JRadioButton rbPredeterminada;
    private JComboBox<String> comboAlumnos;
    private JButton btnCrear;
    private JButton btnVolver;
    private JLabel lblError;

    public CrearRutina(CardLayout cardLayout, JPanel contentPane, PersonalTrainer personalTrainer) {
    	this.personalTrainer = personalTrainer;
        setLayout(null);
        
        lblError = new JLabel();
        lblError.setForeground(Color.RED);
        lblError.setBounds(100, 300, 400, 30);
        lblError.setVisible(false);
        add(lblError);

        JLabel lblTitulo = new JLabel("Titulo:");
        lblTitulo.setBounds(100, 50, 150, 30);
        add(lblTitulo);

        txtTitulo = new JTextField();
        txtTitulo.setBounds(300, 50, 234, 30);
        add(txtTitulo);

        JLabel lblDescripcion = new JLabel("Descripcion:");
        lblDescripcion.setBounds(100, 100, 150, 30);
        add(lblDescripcion);

        txtDescripcion = new JTextField();
        txtDescripcion.setBounds(300, 100, 234, 30);
        add(txtDescripcion);

        JLabel lblTipoRutina = new JLabel("Tipo de Rutina:");
        lblTipoRutina.setBounds(100, 150, 150, 30);
        add(lblTipoRutina);

        JPanel tipoPanel = new JPanel();
        tipoPanel.setBounds(300, 150, 234, 59);
        rbPersonalizada = new JRadioButton("Personalizada");
        rbPredeterminada = new JRadioButton("Predeterminada");
        ButtonGroup group = new ButtonGroup();
        group.add(rbPersonalizada);
        group.add(rbPredeterminada);
        tipoPanel.add(rbPersonalizada);
        tipoPanel.add(rbPredeterminada);
        add(tipoPanel);

        JLabel lblSeleccionarAlumno = new JLabel("Seleccionar Alumno:");
        lblSeleccionarAlumno.setBounds(100, 220, 150, 30);
        add(lblSeleccionarAlumno);
        LinkedList<PersonalTrainer> alumnosAc = new LinkedList<>();
        alumnosAc = ControllerPersonalTrainer.MostrarAlumnos(personalTrainer.getIdPersonalTrainer());
        comboAlumnos = new JComboBox<>();
        for (PersonalTrainer personalTrainer2 : alumnosAc) {
			comboAlumnos.addItem(personalTrainer2.getId() +" "+personalTrainer2.getNombre() + " " + personalTrainer2.getApellido());
		}
        comboAlumnos.setVisible(false);
        comboAlumnos.setBounds(300, 220, 234, 30);
        add(comboAlumnos);

        btnCrear = new JButton("Crear Rutina");
        btnCrear.setBounds(100, 270, 150, 30);
        btnCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearRutina();
            }
        });
        add(btnCrear);

        btnVolver = new JButton("Volver");
        btnVolver.setBounds(300, 270, 150, 30);
        btnVolver.addActionListener(e -> cardLayout.show(contentPane, "RutinasPanel"));
        add(btnVolver);
        rbPersonalizada.addActionListener(e -> comboAlumnos.setVisible(true));
        rbPredeterminada.addActionListener(e -> comboAlumnos.setVisible(false));
    }

    private void crearRutina() {
        String titulo = txtTitulo.getText().trim();
        String descripcion = txtDescripcion.getText().trim();
        int tipo;
        String alumnoSeleccionado = "";

        if (titulo.isEmpty() || descripcion.isEmpty()) {
            lblError.setText("Por favor complete todos los campos.");
            lblError.setVisible(true);
            return;
        } else {
            lblError.setVisible(false); 
        }

        if (rbPersonalizada.isSelected()) {
            tipo = 0;
            alumnoSeleccionado = (String) comboAlumnos.getSelectedItem();
            String[] partes = alumnoSeleccionado.split(" ");
	        int idAlumnoSeleccionado = Integer.parseInt(partes[0]);
            ControllerRutina.agregarRutina(new Rutina(descripcion,titulo,tipo,idAlumnoSeleccionado,this.personalTrainer.getIdPersonalTrainer()));
        } else if (rbPredeterminada.isSelected()) {
            tipo = 1;
            alumnoSeleccionado = "";
            ControllerRutina.agregarRutina(new Rutina(descripcion,titulo,tipo,this.personalTrainer.getIdPersonalTrainer()));
        } else {
            lblError.setText("Por favor seleccione un tipo de rutina.");
            lblError.setVisible(true);
            return;
        }

        
    }
}