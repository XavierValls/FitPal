package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import BLL.Alumno;
import BLL.Desafio;
import DLL.ControllerDesafio;

public class VerDesafiosAlumno extends JFrame {
    private JTable tableDesafios;
    private DefaultTableModel tableModel;
    private JRadioButton rbtnMisDesafios;
    private JRadioButton rbtnDesafiosPredeterminados;
    private JButton btnEliminar;
    private JButton btnVolver;
    private JButton btnAnotarme;
    private Alumno alumno;
    private JLabel lblError;

    public VerDesafiosAlumno(Alumno alumno) {
        this.alumno = alumno;

        setTitle("Ver Desafios");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1064, 610);
        getContentPane().setLayout(null);
        setLocationRelativeTo(null);

        lblError = new JLabel();
        lblError.setBounds(50, 20, 700, 30);
        lblError.setForeground(Color.RED);
        lblError.setVisible(false);
        getContentPane().add(lblError);

        rbtnMisDesafios = new JRadioButton("Mis Desafios");
        rbtnMisDesafios.setBounds(50, 60, 150, 30);
        rbtnMisDesafios.setSelected(true);
        getContentPane().add(rbtnMisDesafios);

        rbtnDesafiosPredeterminados = new JRadioButton("Desafios Predeterminados");
        rbtnDesafiosPredeterminados.setBounds(250, 60, 200, 30);
        getContentPane().add(rbtnDesafiosPredeterminados);

        ButtonGroup group = new ButtonGroup();
        group.add(rbtnMisDesafios);
        group.add(rbtnDesafiosPredeterminados);

        tableModel = new DefaultTableModel(new String[]{"ID", "Titulo", "Descripcion", "Fecha de Expiracion"}, 0);
        tableDesafios = new JTable(tableModel);
        tableDesafios.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(tableDesafios);
        scrollPane.setBounds(50, 100, 957, 309);
        getContentPane().add(scrollPane);

        btnVolver = new JButton("Volver");
        btnVolver.setBounds(50, 480, 150, 30);
        btnVolver.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		PantallaAlumno PantallaAlumno = new PantallaAlumno(alumno);
        		PantallaAlumno.setVisible(true);
        		dispose();
        	}
        });
        getContentPane().add(btnVolver);

        btnAnotarme = new JButton("Anotarme");
        btnAnotarme.setBounds(250, 480, 150, 30);
        btnAnotarme.setEnabled(false);
        btnAnotarme.addActionListener(this::anotarmeAlDesafio);
        getContentPane().add(btnAnotarme);

        rbtnMisDesafios.addActionListener(e -> {
            btnAnotarme.setVisible(false);
            cargarDesafios(true);
        });

        rbtnDesafiosPredeterminados.addActionListener(e -> {
            btnAnotarme.setVisible(true);
            cargarDesafios(false);
        });

        tableDesafios.getSelectionModel().addListSelectionListener(e -> {
            boolean seleccionValida = tableDesafios.getSelectedRow() != -1;
            btnAnotarme.setEnabled(seleccionValida && rbtnDesafiosPredeterminados.isSelected());
        });
        cargarDesafios(true);
    }

    private void cargarDesafios(boolean misDesafios) {
        tableModel.setRowCount(0);
        LinkedList<Desafio> desafios;

        if (misDesafios) {
            desafios = ControllerDesafio.MisDesafios(alumno.getIdAlumno());
        } else {
            desafios = ControllerDesafio.desafiosDispo();
        }

        for (Desafio desafio : desafios) {
            tableModel.addRow(new Object[]{
                desafio.getIdDesafio(),
                desafio.getTitulo(),
                desafio.getDescripcion(),
                desafio.getFechaExp()
            });
        }
    }

    private void anotarmeAlDesafio(ActionEvent e) {
        int selectedRow = tableDesafios.getSelectedRow();
        if (selectedRow == -1) {
            lblError.setText("Por favor, seleccione un desafio para anotarse.");
            lblError.setVisible(true);
            return;
        }

        int idDesafio = (int) tableModel.getValueAt(selectedRow, 0);
        String titulo = (String) tableModel.getValueAt(selectedRow, 1);

        int confirm = JOptionPane.showConfirmDialog(
            this,
            "¿Estas seguro que deseas anotarte al desafio: " + titulo + "?",
            "Confirmar Inscripcion",
            JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            ControllerDesafio.inscribirseEnDesafio(alumno.getIdAlumno(), idDesafio);
            JOptionPane.showMessageDialog(null, "Te inscribiste exitosamente.");
        }
    }
}
