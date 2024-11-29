package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import BLL.Desafio;
import BLL.PersonalTrainer;
import DLL.ControllerDesafio;

public class CrearDesafio extends JFrame {
    private JTextField txtTitulo;
    private JTextField txtDescripcion;
    private JTextField txtFechaExpiracion;
    private JButton btnCrear;
    private JButton btnVolver;
    private JLabel lblError;
    private PersonalTrainer personalTrainer;

    public CrearDesafio(PersonalTrainer personalTrainer) {
        this.personalTrainer = personalTrainer;

        setTitle("Crear Desafio");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(598, 446);
        getContentPane().setLayout(null);
        setLocationRelativeTo(null);

        lblError = new JLabel();
        lblError.setBounds(50, 20, 400, 30);
        lblError.setForeground(Color.RED);
        lblError.setVisible(false);
        getContentPane().add(lblError);

        JLabel lblTitulo = new JLabel("Titulo:");
        lblTitulo.setBounds(50, 70, 150, 30);
        getContentPane().add(lblTitulo);

        txtTitulo = new JTextField();
        txtTitulo.setBounds(200, 70, 294, 30);
        getContentPane().add(txtTitulo);

        JLabel lblDescripcion = new JLabel("Descripcion:");
        lblDescripcion.setBounds(50, 120, 150, 30);
        getContentPane().add(lblDescripcion);

        txtDescripcion = new JTextField();
        txtDescripcion.setBounds(200, 120, 294, 30);
        getContentPane().add(txtDescripcion);

        JLabel lblFechaExpiracion = new JLabel("Fecha de Expiracion (YYYY-MM-DD):");
        lblFechaExpiracion.setBounds(50, 170, 250, 30);
        getContentPane().add(lblFechaExpiracion);

        txtFechaExpiracion = new JTextField();
        txtFechaExpiracion.setBounds(300, 170, 169, 30);
        getContentPane().add(txtFechaExpiracion);

        btnCrear = new JButton("Crear");
        btnCrear.setBounds(108, 250, 150, 30);
        btnCrear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                crearDesafio();
            }
        });
        getContentPane().add(btnCrear);

        btnVolver = new JButton("Volver");
        btnVolver.setBounds(319, 250, 150, 30);
        btnVolver.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		PantallaPersonal PantallaPersonal = new PantallaPersonal(personalTrainer);
        		PantallaPersonal.setVisible(true);
        		dispose();
        	}
        });
        getContentPane().add(btnVolver);
    }

    private void crearDesafio() {
        String titulo = txtTitulo.getText().trim();
        if (titulo.isEmpty()) {
            mostrarError("El titulo no puede estar vacio.");
            return;
        }

        String descripcion = txtDescripcion.getText().trim();
        if (descripcion.isEmpty()) {
            mostrarError("La descripcion no puede estar vacia.");
            return;
        }

        String fechaExpiracionStr = txtFechaExpiracion.getText().trim();
        Date fechaExpiracion;
        try {
            fechaExpiracion = Date.valueOf(fechaExpiracionStr);
            Date fechaActual = new Date(System.currentTimeMillis());
            if (fechaExpiracion.before(fechaActual)) {
                mostrarError("La fecha de expiracion no puede ser anterior a la de hoy.");
                return;
            }
        } catch (IllegalArgumentException ex) {
            mostrarError("El formato de la fecha debe ser YYYY-MM-DD.");
            return;
        }

        lblError.setVisible(false);

        Desafio nuevoDesafio = new Desafio(titulo, descripcion, fechaExpiracion, personalTrainer.getIdPersonalTrainer());
        ControllerDesafio.agregarDesafio(nuevoDesafio);

    }

    private void mostrarError(String mensaje) {
        lblError.setText(mensaje);
        lblError.setVisible(true);
    }
}
