package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import BLL.Alumno;
import BLL.Objetivo;
import DLL.ControllerObjetivo;
import java.awt.event.ActionListener;

public class VerObjetivoAlumno extends JFrame {
    private JLabel lblDescripcion;
    private JLabel lblFechaCreacion;
    private JLabel lblFechaObjetivo;
    private JButton btnEditar;
    private JButton btnCrearObjetivo;
    private Alumno alumno;
    private Objetivo objetivo;
    private JLabel lblError;

    public VerObjetivoAlumno(Alumno alumno) {
        this.alumno = alumno;

        setTitle("Ver Objetivo");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(600, 400);
        getContentPane().setLayout(null);
        setLocationRelativeTo(null);

        lblError = new JLabel();
        lblError.setBounds(50, 20, 500, 30);
        lblError.setForeground(Color.RED);
        lblError.setVisible(false);
        getContentPane().add(lblError);

        lblDescripcion = new JLabel("Descripcion: ");
        lblDescripcion.setBounds(118, 80, 500, 30);
        getContentPane().add(lblDescripcion);

        lblFechaCreacion = new JLabel("Fecha de Creacion: ");
        lblFechaCreacion.setBounds(118, 120, 500, 30);
        getContentPane().add(lblFechaCreacion);

        lblFechaObjetivo = new JLabel("Fecha del Objetivo: ");
        lblFechaObjetivo.setBounds(118, 160, 500, 30);
        getContentPane().add(lblFechaObjetivo);

        btnEditar = new JButton("Editar Objetivo");
        btnEditar.setBounds(144, 217, 150, 30);
        btnEditar.setEnabled(false);
        btnEditar.addActionListener(this::editarObjetivo);
        getContentPane().add(btnEditar);

        btnCrearObjetivo = new JButton("Crear Objetivo");
        btnCrearObjetivo.setBounds(144, 258, 150, 30);
        btnCrearObjetivo.addActionListener(this::crearObjetivo);
        getContentPane().add(btnCrearObjetivo);
        
        JButton btnVolver = new JButton("Volver");
        btnVolver.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		PantallaAlumno PantallaAlumno = new PantallaAlumno(alumno);
        		PantallaAlumno.setVisible(true);
        		dispose();
        	}
        });
        btnVolver.setBounds(304, 217, 150, 30);
        getContentPane().add(btnVolver);

        cargarObjetivo();
    }

    private void cargarObjetivo() {
        objetivo = ControllerObjetivo.mostrarObjetivoDelAlumno(alumno.getIdAlumno());

        if (objetivo != null) {
            lblDescripcion.setText("Descripcion: " + objetivo.getDescripcion());
            lblFechaCreacion.setText("Fecha de Creacion: " + objetivo.getFechaCreacion());
            lblFechaObjetivo.setText("Fecha del Objetivo: " + objetivo.getFechaObj());
            btnEditar.setEnabled(true);
            btnCrearObjetivo.setVisible(false);
        } else {
            lblDescripcion.setText("Descripcion: Ningun objetivo asignado.");
            lblFechaCreacion.setText("Fecha de Creacion: N/A");
            lblFechaObjetivo.setText("Fecha del Objetivo: N/A");
            btnEditar.setEnabled(false); 
            btnCrearObjetivo.setVisible(true);
        }
    }

    private void editarObjetivo(ActionEvent e) {
    	EditarObjetivoAlumno EditarObjetivoAlumno = new EditarObjetivoAlumno(alumno);
    	EditarObjetivoAlumno.setVisible(true);
    	dispose();
    }

    private void crearObjetivo(ActionEvent e) {
    	CrearObjetivoAlumno CrearObjetivoAlumno = new CrearObjetivoAlumno(alumno);
    	CrearObjetivoAlumno.setVisible(true);
    	dispose();
    }
}
