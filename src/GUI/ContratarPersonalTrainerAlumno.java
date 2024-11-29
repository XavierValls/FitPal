package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import BLL.Alumno;
import BLL.Contrato;
import BLL.PersonalTrainer;
import DLL.ControllerPersonalTrainer;
import DLL.ControllerContrato;

public class ContratarPersonalTrainerAlumno extends JFrame {
    private JTable tablePersonalTrainers;
    private DefaultTableModel tableModel;
    private JRadioButton rbtnTodos;
    private JRadioButton rbtnContratados;
    private JButton btnContratar;
    private JButton btnVolver;
    private JLabel lblError;
    private Alumno alumno;

    public ContratarPersonalTrainerAlumno(Alumno alumno) {
        this.alumno = alumno;

        setTitle("Contratar Personal Trainer");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(1064, 610);
        setLayout(null);
        setLocationRelativeTo(null);

        lblError = new JLabel();
        lblError.setBounds(50, 10, 700, 30);
        lblError.setForeground(Color.RED);
        lblError.setVisible(false);
        add(lblError);

        rbtnTodos = new JRadioButton("Todos los Personal Trainers");
        rbtnTodos.setBounds(50, 50, 200, 30);
        rbtnTodos.setSelected(true);
        add(rbtnTodos);

        rbtnContratados = new JRadioButton("Mis Personal Trainers");
        rbtnContratados.setBounds(300, 50, 200, 30);
        add(rbtnContratados);

        ButtonGroup group = new ButtonGroup();
        group.add(rbtnTodos);
        group.add(rbtnContratados);

        tableModel = new DefaultTableModel(new String[]{"ID", "Nombre", "Apellido"}, 0);
        tablePersonalTrainers = new JTable(tableModel);
        tablePersonalTrainers.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        JScrollPane scrollPane = new JScrollPane(tablePersonalTrainers);
        scrollPane.setBounds(50, 100, 957, 309);
        add(scrollPane);

        btnContratar = new JButton("Contratar");
        btnContratar.setBounds(50, 450, 150, 30);
        btnContratar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                contratarPersonalTrainer();
            }
        });
        btnContratar.setEnabled(true);
        add(btnContratar);

        btnVolver = new JButton("Volver");
        btnVolver.setBounds(250, 450, 150, 30);
        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PantallaAlumno PantallaAlumno = new PantallaAlumno(alumno);
                PantallaAlumno.setVisible(true);
                dispose();
            }
        });
        add(btnVolver);

        rbtnTodos.addActionListener(e -> {
            cargarPersonalTrainers(true);
            btnContratar.setEnabled(true);
        });

        rbtnContratados.addActionListener(e -> {
            cargarPersonalTrainers(false);
            btnContratar.setEnabled(false);
        });

        tablePersonalTrainers.getSelectionModel().addListSelectionListener(e -> {
            if (rbtnTodos.isSelected()) {
                btnContratar.setEnabled(tablePersonalTrainers.getSelectedRow() != -1);
            }
        });

        cargarPersonalTrainers(true);
    }

    private void cargarPersonalTrainers(boolean todos) {
        tableModel.setRowCount(0);

        if (todos) {
            LinkedList<PersonalTrainer> personalTrainers = ControllerPersonalTrainer.MostrarPersonalTrainers();

            for (PersonalTrainer trainer : personalTrainers) {
                tableModel.addRow(new Object[]{
                        trainer.getIdPersonalTrainer(),
                        trainer.getNombre(),
                        trainer.getApellido()
                });
            }
        } else {
            LinkedList<Contrato> contratos = ControllerContrato.mostrarContratosPorAlumno(alumno.getIdAlumno());

            for (Contrato contrato : contratos) {
                tableModel.addRow(new Object[]{
                        contrato.getIdPersonalTrainer(),
                        contrato.getNombre(),
                        contrato.getApellido()
                });
            }
        }
    }


    private void contratarPersonalTrainer() {
        int selectedRow = tablePersonalTrainers.getSelectedRow();
        if (selectedRow == -1) {
            lblError.setText("Por favor, seleccione un Personal Trainer para contratar.");
            lblError.setVisible(true);
            return;
        }

        lblError.setVisible(false);

        int idTrainer = (int) tableModel.getValueAt(selectedRow, 0);
        String nombre = (String) tableModel.getValueAt(selectedRow, 1);
        String apellido = (String) tableModel.getValueAt(selectedRow, 2);

        int confirm = JOptionPane.showConfirmDialog(
                this,
                "¿Esta seguro de contratar al Personal Trainer " + nombre + " " + apellido + " por 1 mes?",
                "Confirmar Contratacion",
                JOptionPane.YES_NO_OPTION
        );

        if (confirm == JOptionPane.YES_OPTION) {
            ControllerContrato.agregarContrato(idTrainer, alumno.getIdAlumno());
            PantallaAlumno PantallaAlumno = new PantallaAlumno(alumno);
            PantallaAlumno.setVisible(true);
            dispose();


        }
    }

}
