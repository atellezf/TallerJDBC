package dev.atellezf.nomina.sql;

import dev.atellezf.nomina.beans.Empleado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmpleadoDAO implements EmpleadoDML {

    public List<Empleado> obtenerLista(int departamento) {
        ArrayList<Empleado> empleados = new ArrayList<>();
        try (Connection dbcon = DataSource.getConnection()) {
            PreparedStatement pstm = dbcon.prepareStatement(SQL_SELECT_EMPLEADO_POR_DEPTO);
            pstm.setInt(1, departamento);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                empleados.add( crearEmpleado(rs) );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return empleados;
    }

    public Empleado obtener(int id) {
        try (Connection dbcon = DataSource.getConnection()) {
            PreparedStatement pstm = dbcon.prepareStatement(SQL_SELECT_EMPLEADO_POR_ID);
            pstm.setInt(1, id);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) return crearEmpleado(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int eliminar(  int id ) {
        try (Connection dbcon = DataSource.getConnection()) {
            PreparedStatement pstm = dbcon.prepareStatement(SQL_DELETE_EMPLEADO);
            pstm.setInt(1, id);
            return pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int actualizar( Empleado emp ) {
        try (Connection dbcon = DataSource.getConnection()) {
            PreparedStatement pstm = dbcon.prepareStatement(SQL_UPDATE_EMPLEADO);
            pstm.setString(1, emp.getNombre());
            pstm.setString(2, emp.getApellidos());
            pstm.setString(3, emp.getEmail());
            pstm.setFloat(4, emp.getSalario());
            pstm.setInt(5, emp.getDepartamento());
            pstm.setInt(6, emp.getId());
            return pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int insertar( Empleado emp ) {
        try (Connection dbcon = DataSource.getConnection()) {
            PreparedStatement pstm = dbcon.prepareStatement(SQL_INSERT_EMPLEADO);
            pstm.setString(1, emp.getNombre());
            pstm.setString(2, emp.getApellidos());
            pstm.setString(3, emp.getEmail());
            pstm.setFloat(4, emp.getSalario());
            pstm.setInt(5, emp.getDepartamento());
            return pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private Empleado crearEmpleado(ResultSet rs) throws SQLException {
        int id = rs.getInt("id_emp");
        String nombre = rs.getString("nombre");
        String apellidos = rs.getString("apellidos");
        String email = rs.getString("email");
        float salario = rs.getFloat("salario");
        int depto = rs.getInt("departamento");
        return new Empleado(id, nombre, apellidos, email, salario, depto);
    }

}
