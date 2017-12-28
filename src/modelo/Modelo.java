/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Duoc UC
 */
public class Modelo extends Conexion {

    public boolean AgregarEmpleado(int codigo, String rut, String nombre, String apellido, int celular, String email, int sueldo_bruto, String estado_civil, String nombre_departamento) {
//         ----> Validaciones que deben agregarse posteriormente. <--------
        if (valida_datos(codigo, rut, nombre, apellido, celular, email, sueldo_bruto, estado_civil, nombre_departamento)) {

//            Se arma la consulta
            String q = " INSERT INTO empleados (codigo, rut,nombre,apellido,celular,email,sueldo_bruto,estado_civil,nombre_departamento) "
                    + "VALUES ( " + codigo + ",'" + rut + "', '" + nombre
                    + "','" + apellido + "'," + celular + ",'" + email + "'," + sueldo_bruto + ",'" + estado_civil + "','" + nombre_departamento + "') ";
//            se ejecuta la consulta

            try {
                PreparedStatement pstm = this.getConexion().prepareStatement(q);
                pstm.execute();
                pstm.close();
                return true;

            } catch (SQLException e) {
                System.err.println(e.getMessage());

            }
            return false;
        } else {
            JOptionPane.showMessageDialog(null, "Ingrese datos correctamente");
        }
        return false;
    }

    //Metodo para validar datos
    private boolean valida_datos(int codigo, String rut, String nombre, String apellido, int celular, String email, int sueldo_bruto, String estado_civil, String nombre_departamento) {

        if (codigo < 0 && codigo >= 100) {
            JOptionPane.showMessageDialog(null, "Codigo no esta dentro del Rango");
            return false;

        } else if (celular < 9 && celular > 9) {
            JOptionPane.showMessageDialog(null, "El numero de celular no corresponde");
            return false;
        } else if (rut.equals("")) {
            JOptionPane.showMessageDialog(null, "Debe ingresar N° de RUT");
            return false;
        } else if (nombre.equals("")) {
            JOptionPane.showMessageDialog(null, "Debe ingresar un Nombre");
            return false;
        } else if (apellido.equals("")) {
            JOptionPane.showMessageDialog(null, "Debe ingresar un Apellido");
            return false;
        } else if (sueldo_bruto <= 120000) {
            JOptionPane.showMessageDialog(null, "El sueldo no es correcto");
            return false;
        } else if (nombre_departamento.equals("Departamentos")) {
            JOptionPane.showMessageDialog(null, "Debe ingresar un Departamento");
            return false;
        } else if (estado_civil.equals("C") || estado_civil.equals("S") || estado_civil.equals("V")) 
            return true;
        else  {
            JOptionPane.showMessageDialog(null, "Debe seleccionar estado civil");
            return false;
        }    }

    public boolean eliminarEmpleado(int codigo) {
        boolean res = false;
        String q = " DELETE FROM empresa.empleados WHERE codigo=" + codigo + " ";
        try {
            PreparedStatement pstm = getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
            res = true;    
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return res;
    }

//     private boolean valida_datos(int codigo){
//        String codigo1 = String.valueOf("");
//         if(codigo1.equals("")){JOptionPane.showMessageDialog(null, "Debe seleccionar estado civil");
//     }
    
    //Modificar producto seleccionado
    public boolean modificaEmpleado(int codigo, String rut, String nombre, String apellido, int celular, String email, int sueldo_bruto, String estado_civil, String nombre_departamento) {
        String q = "UPDATE empresa.empleados SET codigo=" + codigo + ", rut=" + rut + ", nombre='" + nombre
                + "', apellido='" + apellido + "', celular=" + celular + ", email='" + email
                + "', sueldo_bruto=" + sueldo_bruto + ", estado_civil='" + estado_civil + "', nombre_departamento='"
                + nombre_departamento + "' WHERE codigo='" + codigo + "' ";
        try {
            PreparedStatement pstm = this.getConexion().prepareStatement(q);
            pstm.execute();
            pstm.close();
            return true;
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    public ArrayList<Empleado> buscarPorCodigo(String codigo) {
        ArrayList<Empleado> listaEmpleado = new ArrayList<Empleado>();
        try {
            Connection conexion = getConexion();
            String query = "SELECT codigo, rut,nombre,apellido,celular,email,sueldo_bruto,estado_civil,nombre_departamento From empleados where codigo=?";
            PreparedStatement buscarPorCodigo = conexion.prepareStatement(query);
            buscarPorCodigo.setString(1, codigo);
            ResultSet rs = buscarPorCodigo.executeQuery();
            while (rs.next()) {
                Empleado empleado = new Empleado();
                empleado.setCodigo(rs.getInt("codigo"));
                empleado.setRut(rs.getString("rut"));
                empleado.setNombre(rs.getString("nombre"));
                empleado.setApellido(rs.getString("apellido"));
                empleado.setCelular(rs.getInt("celular"));
                empleado.setEmail(rs.getString("email"));
                empleado.setSueldo_bruto(rs.getInt("sueldo_bruto"));
                empleado.setEstado_civil(rs.getString("estado_civil"));
                empleado.setNombre_departamento(rs.getString("Nombre_departamento"));
                listaEmpleado.add(empleado);
            }
        } catch (SQLException s) {
            System.out.println("Error SQL al listar pelicula" + s.getMessage());
        } catch (Exception e) {
            System.out.println("Error al listar pelicula" + e.getMessage());
        }
        return listaEmpleado;
    }

    //Metodo para listar producto
    public DefaultTableModel ListadoEmpleado() {
        DefaultTableModel tablemodel = new DefaultTableModel();
        int registros = 0;
        String[] columNames = {"Codigo", "Rut", "Nombre", "Apellido", "Celular", "Email", "sueldo bruto", "Estado Civil", "Nombre departamento"};
        try {
            PreparedStatement pstm = getConexion().prepareStatement("SELECT count(*) as total FROM empleados");
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        Object[][] data = new String[registros][9];
        try {
            PreparedStatement pstm = getConexion().prepareStatement("SELECT * FROM empleados");
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while (res.next()) {
                data[i][0] = res.getString("codigo");
                data[i][1] = res.getString("rut");
                data[i][2] = res.getString("nombre");
                data[i][3] = res.getString("apellido");
                data[i][4] = res.getString("celular");
                data[i][5] = res.getString("email");
                data[i][6] = res.getString("sueldo_bruto");
                data[i][7] = res.getString("estado_civil");
                data[i][8] = res.getString("nombre_departamento");
                i++;
            }
            res.close();
            tablemodel.setDataVector(data, columNames);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return tablemodel;
    }

}
