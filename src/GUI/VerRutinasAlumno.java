package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

import BLL.Alumno;
import BLL.Rutina;
import DLL.ControllerRutina;

public class VerRutinasAlumno extends JFrame {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable tableRutinas;
    private DefaultTableModel tableModel;
    private JRadioButton radioMisRutinas;
    private JRadioButton radioRutinasPredeterminadas;
    private JButton btnVolver;
    private Alumno alumno;

    public VerRutinasAlumno(Alumno alumno) {
        this.alumno = alumno;

        setTitle("Ver Rutinas - Alumno");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(825, 500);
        getContentPane().setLayout(null);
        setLocationRelativeTo(null);

        radioMisRutinas = new JRadioButton("Mis Rutinas");
        radioMisRutinas.setBounds(50, 30, 120, 30);
        radioMisRutinas.setSelected(true);

        radioRutinasPredeterminadas = new JRadioButton("Rutinas Predeterminadas");
        radioRutinasPredeterminadas.setBounds(200, 30, 180, 30);

        ButtonGroup group = new ButtonGroup();
        group.add(radioMisRutinas);
        group.add(radioRutinasPredeterminadas);

        getContentPane().add(radioMisRutinas);
        getContentPane().add(radioRutinasPredeterminadas);

        tableRutinas = new JTable();
        tableModel = new DefaultTableModel(new Object[]{"ID", "Titulo", "Descripcion", "Nombre PT", "Apellido PT"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; 
            }
        };
        tableRutinas.setModel(tableModel);
        JScrollPane scrollPane = new JScrollPane(tableRutinas);
        scrollPane.setBounds(50, 80, 700, 309);
        getContentPane().add(scrollPane);

        btnVolver = new JButton("Volver");
        btnVolver.setBounds(50, 400, 100, 30);
        btnVolver.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		PantallaAlumno PantallaAlumno = new PantallaAlumno(alumno);
        		PantallaAlumno.setVisible(true);
        		dispose();
        	}
        });
        getContentPane().add(btnVolver);

        ActionListener filtroListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cargarRutinas();
            }
        };
        radioMisRutinas.addActionListener(filtroListener);
        radioRutinasPredeterminadas.addActionListener(filtroListener);

        cargarRutinas();
    }

    private void cargarRutinas() {
        tableModel.setRowCount(0);

        LinkedList<Rutina> rutinas;
        if (radioMisRutinas.isSelected()) {
            rutinas = ControllerRutina.MostrarRutinas(alumno.getIdAlumno());
        } else {
            rutinas = ControllerRutina.MostrarRutinasPredeterminadas();
        }

        for (Rutina rutina : rutinas) {
            tableModel.addRow(new Object[]{
                rutina.getIdRutina(),
                rutina.getTitulo(),
                rutina.getDescripcion(),
                rutina.getPersonalTrainer().getNombre(),
                rutina.getPersonalTrainer().getApellido()
            });
        }
    }
}
