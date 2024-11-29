package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import BLL.Desafio;
import BLL.PersonalTrainer;
import DLL.ControllerDesafio;

public class VerDesafios extends JFrame {
    private JTable tableDesafios;
    private DefaultTableModel tableModel;
    private JRadioButton rbtnMisDesafios;
    private JRadioButton rbtnDesafiosPredeterminados;
    private JButton btnEliminar;
    private JButton btnVolver;
    private PersonalTrainer personalTrainer;
    private JLabel lblError;

    public VerDesafios(PersonalTrainer personalTrainer) {
        this.personalTrainer = personalTrainer;

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


        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(50, 420, 150, 30);
        btnEliminar.setEnabled(false);
        getContentPane().add(btnEliminar);

        btnVolver = new JButton("Volver");
        btnVolver.setBounds(50, 480, 150, 30);
        btnVolver.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		PantallaPersonal PantallaPersonal = new PantallaPersonal(personalTrainer);
        		PantallaPersonal.setVisible(true);
        		dispose();
        	}
        });
        getContentPane().add(btnVolver);

        rbtnMisDesafios.addActionListener(e -> cargarDesafios(true));
        rbtnDesafiosPredeterminados.addActionListener(e -> cargarDesafios(false));

        tableDesafios.getSelectionModel().addListSelectionListener(e -> {
            boolean seleccionValida = tableDesafios.getSelectedRow() != -1;
            btnEliminar.setEnabled(seleccionValida);
        });

        btnEliminar.addActionListener(e -> eliminarDesafio());
        cargarDesafios(true);
    }

    private void cargarDesafios(boolean misDesafios) {
        tableModel.setRowCount(0);
        LinkedList<Desafio> desafios;

        if (misDesafios) {
            desafios = ControllerDesafio.misDesafios(personalTrainer.getIdPersonalTrainer());
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


    private void eliminarDesafio() {
        int selectedRow = tableDesafios.getSelectedRow();
        if (selectedRow != -1) {
            int idDesafio = (int) tableModel.getValueAt(selectedRow, 0);
            int confirm = JOptionPane.showConfirmDialog(
                    this,
                    "¿Esta seguro de que desea eliminar este desafio?",
                    "Confirmar eliminacion",
                    JOptionPane.YES_NO_OPTION
            );
            if (confirm == JOptionPane.YES_OPTION) {
                ControllerDesafio.eliminarDesafio(idDesafio);
                
            }
        }
    }
}
