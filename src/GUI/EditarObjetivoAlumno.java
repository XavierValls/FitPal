package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import BLL.Alumno;
import BLL.Objetivo;
import DLL.ControllerObjetivo;

public class EditarObjetivoAlumno extends JFrame {
    private JTextField txtDescripcion;
    private JTextField txtFechaObjetivo;
    private JButton btnGuardar;
    private JButton btnCancelar;
    private JLabel lblError;
    private Alumno alumno;

    public EditarObjetivoAlumno(Alumno alumno) {
        this.alumno = alumno;
        Objetivo objetivo = ControllerObjetivo.mostrarObjetivoDelAlumno(alumno.getIdAlumno());
        setTitle("Editar Objetivo");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(700, 450);
        getContentPane().setLayout(null);
        setLocationRelativeTo(null);

        lblError = new JLabel();
        lblError.setBounds(50, 10, 300, 30);
        lblError.setForeground(Color.RED);
        lblError.setVisible(false);
        getContentPane().add(lblError);

        JLabel lblDescripcion = new JLabel("Descripcion:");
        lblDescripcion.setBounds(131, 111, 100, 30);
        getContentPane().add(lblDescripcion);

        txtDescripcion = new JTextField(objetivo.getDescripcion());
        txtDescripcion.setBounds(227, 111, 328, 30);
        getContentPane().add(txtDescripcion);

        JLabel lblFechaObjetivo = new JLabel("Fecha del Objetivo (YYYY-MM-DD):");
        lblFechaObjetivo.setBounds(10, 203, 200, 30);
        getContentPane().add(lblFechaObjetivo);

        txtFechaObjetivo = new JTextField(objetivo.getFechaObj().toString());
        txtFechaObjetivo.setBounds(227, 203, 328, 30);
        getContentPane().add(txtFechaObjetivo);

        btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(268, 244, 100, 30);
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarCambios();
            }
        });
        getContentPane().add(btnGuardar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(424, 244, 100, 30);
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	PantallaAlumno PantallaAlumno = new PantallaAlumno(alumno);
                PantallaAlumno.setVisible(true);
                dispose();
            }
        });
        
        getContentPane().add(btnCancelar);
    }

    private void guardarCambios() {
    	Objetivo objetivo = ControllerObjetivo.mostrarObjetivoDelAlumno(alumno.getIdAlumno());
        String nuevaDescripcion = txtDescripcion.getText().trim();
        String nuevaFechaStr = txtFechaObjetivo.getText().trim();

        if (nuevaDescripcion.isEmpty()) {
            lblError.setText("La descripcion no puede estar vacia.");
            lblError.setVisible(true);
            return;
        }

        LocalDate nuevaFechaObjetivo;
        try {
            nuevaFechaObjetivo = LocalDate.parse(nuevaFechaStr);
        } catch (DateTimeParseException e) {
            lblError.setText("Formato de fecha invalido. Debe ser YYYY-MM-DD.");
            lblError.setVisible(true);
            return;
        }

        LocalDate hoy = LocalDate.now();
        if (nuevaFechaObjetivo.isBefore(hoy)) {
            lblError.setText("La fecha del objetivo no puede ser menor a la fecha actual.");
            lblError.setVisible(true);
            return;
        }

        boolean cambiosRealizados = false;

        if (!nuevaDescripcion.equals(objetivo.getDescripcion())) {
            objetivo.setDescripcion(nuevaDescripcion);
            cambiosRealizados = true;
        }

        if (!nuevaFechaObjetivo.equals(objetivo.getFechaObj())) {
            objetivo.setFechaObj(nuevaFechaObjetivo);
            cambiosRealizados = true;
        }

        if (!cambiosRealizados) {
            lblError.setText("No se realizaron cambios.");
            lblError.setVisible(true);
            return;
        }

        ControllerObjetivo.actualizarObjetivo(objetivo);
        PantallaAlumno PantallaAlumno = new PantallaAlumno(alumno);
        PantallaAlumno.setVisible(true);
        dispose();
        
    }

}
