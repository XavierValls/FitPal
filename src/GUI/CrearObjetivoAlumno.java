package GUI;

import javax.swing.*;

import BLL.Alumno;
import DLL.ControllerObjetivo;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class CrearObjetivoAlumno extends JFrame {
	private Alumno alumno;
    private JTextField txtDescripcion;
    private JTextField txtFechaObjetivo;
    private JButton btnCrear;
    private JButton btnCancelar;
    private JLabel lblError;

    public CrearObjetivoAlumno(Alumno alumno) {
    	this.alumno = alumno;
        setTitle("Crear Objetivo");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(524, 355);
        getContentPane().setLayout(null);
        setLocationRelativeTo(null);

        lblError = new JLabel();
        lblError.setBounds(50, 10, 300, 30);
        lblError.setForeground(Color.RED);
        lblError.setVisible(false);
        getContentPane().add(lblError);

        JLabel lblDescripcion = new JLabel("Descripcion:");
        lblDescripcion.setBounds(110, 80, 100, 30);
        getContentPane().add(lblDescripcion);

        txtDescripcion = new JTextField();
        txtDescripcion.setBounds(200, 80, 200, 30);
        getContentPane().add(txtDescripcion);

        JLabel lblFechaObjetivo = new JLabel("Fecha del Objetivo (YYYY-MM-DD):");
        lblFechaObjetivo.setBounds(10, 140, 200, 30);
        getContentPane().add(lblFechaObjetivo);

        txtFechaObjetivo = new JTextField();
        txtFechaObjetivo.setBounds(200, 140, 200, 30);
        getContentPane().add(txtFechaObjetivo);

        btnCrear = new JButton("Crear");
        btnCrear.setBounds(190, 200, 100, 30);
        btnCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearObjetivo();
            }
        });
        getContentPane().add(btnCrear);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(300, 200, 100, 30);
        btnCancelar.addActionListener(e -> dispose());
        getContentPane().add(btnCancelar);
    }

    private void crearObjetivo() {
        String descripcion = txtDescripcion.getText().trim();
        String fechaStr = txtFechaObjetivo.getText().trim();

        if (descripcion.isEmpty()) {
            lblError.setText("La descripcion no puede estar vacia.");
            lblError.setVisible(true);
            return;
        }

        LocalDate fechaObjetivo;
        try {
            fechaObjetivo = LocalDate.parse(fechaStr);
        } catch (DateTimeParseException e) {
            lblError.setText("Formato de fecha invalido. Debe ser YYYY-MM-DD.");
            lblError.setVisible(true);
            return;
        }

        LocalDate hoy = LocalDate.now();
        if (fechaObjetivo.isBefore(hoy)) {
            lblError.setText("La fecha del objetivo no puede ser menor a la fecha actual.");
            lblError.setVisible(true);
            return;
        }

        ControllerObjetivo.AgregarObjetivo(descripcion, fechaObjetivo, alumno.getIdAlumno());
    	PantallaAlumno PantallaAlumno = new PantallaAlumno(alumno);
    	PantallaAlumno.setVisible(true);
        dispose();
    }

}
