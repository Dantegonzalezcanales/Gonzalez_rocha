
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import modelo.Empleado;
import modelo.Modelo;
import vista.Agregar;
import vista.Consultas;
import vista.Eliminar;
import vista.Menuinicio;
import vista.Modificar;



public class Controlador implements ActionListener, MouseListener {
 /**
     * instancia a nuestra interfaz de usuario
     */ 
    Menuinicio vista;
    Agregar vistaAgregar = new Agregar();            
    Consultas vistaConsulta = new Consultas();
    Eliminar vistaEliminar = new Eliminar();
    Modificar vistaModificar = new Modificar();
    
     /**
     * instancia a nuestro modelo
     */
    Modelo modelo = new Modelo();
    Empleado empleado = new Empleado();
    
    /**
     * Se declaran en un ENUM las acciones que se realizan desde la interfaz de
     * usuario VISTA y posterior ejecución desde el controlador
     */
    public enum AccionMVC {
        btagregar,
        buttonGroup1,
        rbcasado,
        rbsoltero,
        rbviudo,
        BtnEliminareliminar,
        btconsulta1,
        btconsulta2,
        btconsulta3,
        btconsulta4,
        btvolverconsulta,
        MenuAgregar,
        MenuConsulta,
        MenuEliminar,
        MenuModificar,
        MenuSalir,
        BtnBuscarModificar,
        BtnModificarModificar,
        BtnvolverModificar,
        RbtCasadoModificar,
        RbtSolteroModificar,
        RbtViudoModificar,
        TablaEmpleados,
        BTGestadocivilagregar
        
    }
    
    
    /**
     * Constrcutor de clase
     *
     * @param vista Instancia de clase interfaz
     */
    public Controlador(Menuinicio vista) {
        this.vista = vista;
    }

    /**
     * Inicia el skin y las diferentes variables que se utilizan
     */
    public void iniciar() {
        // Skin tipo WINDOWS
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
            SwingUtilities.updateComponentTreeUI(vista);
            vista.setVisible(true);
        } catch (UnsupportedLookAndFeelException ex) {
        } catch (ClassNotFoundException ex) {
        } catch (InstantiationException ex) {
        } catch (IllegalAccessException ex) {
        }
        
        //declara una acción y añade un escucha al evento producido por el componente
        this.vista.MenuAgregar.setActionCommand("MenuAgregar");
        this.vista.MenuAgregar.addActionListener(this);
        
        this.vista.MenuConsulta.setActionCommand("MenuConsulta");
        this.vista.MenuConsulta.addActionListener(this);
      
        this.vista.MenuEliminar.setActionCommand("MenuEliminar");
        this.vista.MenuEliminar.addActionListener(this);
      
        this.vista.MenuModificar.setActionCommand("MenuModificar");
        this.vista.MenuModificar.addActionListener(this);
        
        this.vista.MenuSalir.setActionCommand("MenuSalir");
        this.vista.MenuSalir.addActionListener(this);
        
        this.vistaAgregar.btagregar.setActionCommand("btagregar");
        this.vistaAgregar.btagregar.addActionListener(this);
        
        this.vistaAgregar.rbcasado.setActionCommand("casado");
        this.vistaAgregar.rbsoltero.setActionCommand("soltero");
        this.vistaAgregar.rbviudo.setActionCommand("viudo");
      
        
    }
    
    
    
    public void actionPerformed(ActionEvent e) {
            switch (AccionMVC.valueOf(e.getActionCommand())) {

                case btagregar:
                     if (this.modelo.AgregarEmpleado(
                             
                        Integer.parseInt(this.vistaAgregar.txtcodigo.getText()),
                        this.vistaAgregar.txtrut.getText(),
                        this.vistaAgregar.txtnombre.getText(),
                        this.vistaAgregar.txtapellido.getText(),
                        Integer.parseInt(this.vistaAgregar.txtcelular.getText()),
                        this.vistaAgregar.txtemail.getText(),
                        Integer.parseInt(this.vistaAgregar.txtsueldo.getText()),
                         this.vistaAgregar.BTGestadocivilagregar.getSelection().getActionCommand(),
                        this.vistaAgregar.cbdepartamento.getSelectedItem().toString()
                        )) {
                    JOptionPane.showMessageDialog(null, "Producto agregado correctamente");
                }
    }
    }
    


    public void mouseClicked(MouseEvent e) {
    }

  
    public void mousePressed(MouseEvent e) {
    }

    
    public void mouseReleased(MouseEvent e) {
    }

    
    public void mouseEntered(MouseEvent e) {
    }

    
    public void mouseExited(MouseEvent e) {
    }

   
}
