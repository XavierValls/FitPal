package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import BLL.PersonalTrainer;
import BLL.Rutina;
import DLL.ControllerRutina;

public class EditarRutina extends JPanel {
    private PersonalTrainer personalTrainer;
    private JComboBox<String> comboRutinas;
    private JTextField txtTituloActual;
    private JTextField txtDescripcionActual;
    private JTextField txtNuevoTitulo;
    private JTextField txtNuevaDescripcion;
    private JButton btnEditar;
    private JButton btnVolver;
    private JLabel lblError;

    public EditarRutina(CardLayout cardLayout, JPanel contentPane, PersonalTrainer personalTrainer) {
        this.personalTrainer = personalTrainer;
        setLayout(null);

        lblError = new JLabel();
        lblError.setForeground(Color.RED);
        lblError.setBounds(100, 320, 400, 30);
        lblError.setVisible(false);
        add(lblError);

        JLabel lblSeleccionarRutina = new JLabel("Seleccionar Rutina:");
        lblSeleccionarRutina.setBounds(100, 50, 150, 30);
        add(lblSeleccionarRutina);

        comboRutinas = new JComboBox<>();
        comboRutinas.setBounds(300, 50, 260, 30);
        cargarRutinas();
        add(comboRutinas);

        JLabel lblTituloActual = new JLabel("Titulo Actual:");
        lblTituloActual.setBounds(100, 100, 150, 30);
        add(lblTituloActual);

        txtTituloActual = new JTextField();
        txtTituloActual.setBounds(300, 100, 260, 30);
        txtTituloActual.setEditable(false);
        add(txtTituloActual);

        JLabel lblDescripcionActual = new JLabel("Descripcion Actual:");
        lblDescripcionActual.setBounds(100, 150, 150, 30);
        add(lblDescripcionActual);

        txtDescripcionActual = new JTextField();
        txtDescripcionActual.setBounds(300, 150, 260, 30);
        txtDescripcionActual.setEditable(false);
        add(txtDescripcionActual);

        JLabel lblNuevoTitulo = new JLabel("Nuevo Titulo:");
        lblNuevoTitulo.setBounds(100, 200, 150, 30);
        add(lblNuevoTitulo);

        txtNuevoTitulo = new JTextField();
        txtNuevoTitulo.setBounds(300, 200, 260, 30);
        add(txtNuevoTitulo);

        JLabel lblNuevaDescripcion = new JLabel("Nueva Descripcion:");
        lblNuevaDescripcion.setBounds(100, 250, 150, 30);
        add(lblNuevaDescripcion);

        txtNuevaDescripcion = new JTextField();
        txtNuevaDescripcion.setBounds(300, 250, 260, 30);
        add(txtNuevaDescripcion);

        btnEditar = new JButton("Editar Rutina");
        btnEditar.setBounds(124, 300, 150, 30);
        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editarRutina();
            }
        });
        add(btnEditar);

        btnVolver = new JButton("Volver");
        btnVolver.setBounds(350, 300, 150, 30);
        btnVolver.addActionListener(e -> cardLayout.show(contentPane, "RutinasPanel"));
        add(btnVolver);

        comboRutinas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarDatosRutinaSeleccionada();
            }
        });
    }

    private void cargarRutinas() {
        LinkedList<Rutina> rutinas = ControllerRutina.MostrarRutinas(personalTrainer.getIdPersonalTrainer());
        comboRutinas.removeAllItems();
        for (Rutina rutina : rutinas) {
            comboRutinas.addItem(rutina.getIdRutina() + " - " + rutina.getTitulo() + " - " + rutina.getDescripcion());
        }
    }
    
    private void mostrarDatosRutinaSeleccionada() {
        String rutinaSeleccionada = (String) comboRutinas.getSelectedItem();
        if (rutinaSeleccionada != null) {
            String[] partes = rutinaSeleccionada.split(" - ");
            if (partes.length >= 3) {
                txtTituloActual.setText(partes[1]);
                txtDescripcionActual.setText(partes[2]);
            }
        }
    }


    private void editarRutina() {
        String rutinaSeleccionada = (String) comboRutinas.getSelectedItem();
        if (rutinaSeleccionada == null) {
            lblError.setText("Por favor seleccione una rutina.");
            lblError.setVisible(true);
            return;
        }
        String[] partes = rutinaSeleccionada.split(" - ");
        int idRutina = Integer.parseInt(partes[0]);

        String nuevoTitulo = txtNuevoTitulo.getText().trim();
        String nuevaDescripcion = txtNuevaDescripcion.getText().trim();

        if (nuevoTitulo.isEmpty() && nuevaDescripcion.isEmpty()) {
            lblError.setText("Debe ingresar al menos un cambio en el titulo o descripcion.");
            lblError.setVisible(true);
            return;
        }
        lblError.setVisible(false);

        nuevoTitulo = nuevoTitulo.isEmpty() ? txtTituloActual.getText() : nuevoTitulo;
        nuevaDescripcion = nuevaDescripcion.isEmpty() ? txtDescripcionActual.getText() : nuevaDescripcion;

       ControllerRutina.actualizarRutina(new Rutina(idRutina,nuevaDescripcion,nuevoTitulo));
        
    }
}
