/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Duoc UC
 */
public class Modelo extends Conexion{
    public boolean AgregarEmpleado(int codigo, String rut , String nombre, String apellido, int celular,String email,int sueldo_bruto,String estado_civil,String nombre_departamento) {
//         ----> Validaciones que deben agregarse posteriormente. <--------
        if (valida_datos(codigo, rut, nombre, apellido,celular,email,sueldo_bruto, estado_civil,nombre_departamento)) {

//            Se arma la consulta
            String q = " INSERT INTO pelicula (codigo, rut,nombre,apellido,celular,email,sueldo_bruto,estado_civil,nombre_departamento) "
                    + "VALUES ( " + codigo + ",'" + rut + "', '" + nombre
                    + "','" + apellido + "'," + celular + ",'" +email+ "'," +sueldo_bruto+ ",'" +estado_civil+ "','" +nombre_departamento+"') ";
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
    private boolean valida_datos(int codigo, String rut , String nombre, String apellido, int celular,String email,int sueldo_bruto,String estado_civil,String nombre_departamento) {
        
        if (codigo > 0 && codigo <= 100) {
            JOptionPane.showMessageDialog(null, "Codigo no esta dentro del Rango");
            return false;
            
        } else if (celular <9 && celular>9) {
         JOptionPane.showMessageDialog(null, "El numero de celular no corresponde");
         return false;
        }
            else if(rut.equals("")){
            JOptionPane.showMessageDialog(null, "Debe ingresar N° de RUT");       
             return false;
        } else if(nombre.equals("")){
                JOptionPane.showMessageDialog(null, "Debe ingresar un Nombre");       
             return false;
        }
           else if(apellido.equals("")){
                JOptionPane.showMessageDialog(null, "Debe ingresar un Apellido");       
             return false;
           }   
             else if (sueldo_bruto>=120000) {
         JOptionPane.showMessageDialog(null, "El sueldo no es correcto");
         return false;
             }
             else if (estado_civil.equals("C")||estado_civil.equals("S")||estado_civil.equals("V")){
                  JOptionPane.showMessageDialog(null, "Debe seleccionar estado civil");
             }
            return false;
        }
    
     
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
     
         //Modificar producto seleccionado
    public boolean modificaEmpleado(int codigo, String rut, String nombre, String apellido, int celular,String email,int sueldo_bruto,String estado_civil,String nombre_departamento) {
        String q = "UPDATE empresa.empleados SET codigo=" + codigo + ", rut=" + rut + ", nombre='" + nombre
                + "', apellido='" +apellido+  "', celular=" +celular + ", email='" +email 
                +"', sueldo_bruto="+sueldo_bruto+", estado_civil='"+estado_civil+"', nombre_departamento='"+
        nombre_departamento+"' WHERE codigo='" + codigo + "' ";
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

}
