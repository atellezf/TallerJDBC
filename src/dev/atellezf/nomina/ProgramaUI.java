package dev.atellezf.nomina;

import dev.atellezf.nomina.beans.Empleado;
import dev.atellezf.nomina.sql.EmpleadoDAO;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProgramaUI {
    private JPanel panel1;
    private JButton btnCancelar;
    private JButton btnGuardar;
    private JTextField cmpId;
    private JTextField cmpNombre;
    private JTextField cmpEmail;
    private JTextField cmpSalario;
    private JTextField cmpApellidos;
    private JComboBox cmpDepto;
    private JButton btnBuscar;

    private EmpleadoDAO dao;
    private Empleado empleadoActual;

    public ProgramaUI() {
        dao = new EmpleadoDAO();
        btnBuscar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(cmpId.getText());
                setEmpleadoActual( dao.obtener(id) );
            }
        });
    }

    private void setEmpleadoActual(Empleado emp) {
        if( emp != null ) {
            cmpId.setEnabled(true);
            btnBuscar.setEnabled(false);
            btnGuardar.setEnabled(true);
            setData(emp);
        } else {
            cmpId.setEnabled(false);
            btnBuscar.setEnabled(true);
            btnGuardar.setEnabled(false);
            limpiarCampos();
        }
        empleadoActual = emp;
    }

    private void limpiarCampos() {
        cmpId.setText("");
        cmpId.setEnabled(true);
        cmpNombre.setText("");
        cmpApellidos.setText("");
        cmpEmail.setText("");
        cmpSalario.setText("");
        cmpDepto.setSelectedIndex(-1);
    }

    private void setData(Empleado emp) {
        cmpNombre.setText(emp.getNombre());
        cmpApellidos.setText(emp.getApellidos());
        cmpEmail.setText(emp.getEmail());
        cmpSalario.setText( String.valueOf( emp.getSalario() ) );
        cmpDepto.setSelectedIndex( emp.getDepartamento() - 1 );
    }

    private void getData(Empleado emp) {
        emp.setNombre(cmpNombre.getText());
        emp.setApellidos(cmpApellidos.getText());
        emp.setEmail(cmpEmail.getText());
        emp.setSalario( Float.parseFloat(cmpSalario.getText()) );
        emp.setDepartamento(cmpDepto.getSelectedIndex() + 1);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Empleados");
        frame.setContentPane(new ProgramaUI().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
