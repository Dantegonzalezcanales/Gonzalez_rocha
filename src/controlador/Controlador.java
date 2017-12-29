package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
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
        bteliminar,
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
        TablaEmpleados,
        Btnvolveragregar,
        BtnListamodificar,
        btvolvereliminar,
        btlimpiaragregar,

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

        //acción en  Agregar
        this.vistaAgregar.btagregar.setActionCommand("btagregar");
        this.vistaAgregar.btagregar.addActionListener(this);

        this.vistaAgregar.Btnvolveragregar.setActionCommand("Btnvolveragregar");
        this.vistaAgregar.Btnvolveragregar.addActionListener(this);
        
        this.vistaAgregar.btlimpiaragregar.setActionCommand("btlimpiaragregar");
        this.vistaAgregar.btlimpiaragregar.addActionListener(this);

        //acion en Modificar
        this.vistaModificar.BtnBuscarModificar.setActionCommand("BtnBuscarModificar");
        this.vistaModificar.BtnBuscarModificar.addActionListener(this);

        this.vistaModificar.BtnModificarModificar.setActionCommand("BtnModificarModificar");
        this.vistaModificar.BtnModificarModificar.addActionListener(this);

        this.vistaModificar.BtnvolverModificar.setActionCommand("BtnvolverModificar");
        this.vistaModificar.BtnvolverModificar.addActionListener(this);

        this.vistaModificar.TablaEmpleados.addMouseListener(this);
        this.vistaModificar.TablaEmpleados.setModel(new DefaultTableModel());

        this.vistaModificar.BtnListamodificar.setActionCommand("BtnListamodificar");
        this.vistaModificar.BtnListamodificar.addActionListener(this);

        //acciones vista eliminar
        this.vistaEliminar.bteliminar.setActionCommand("bteliminar");
        this.vistaEliminar.bteliminar.addActionListener(this);

        this.vistaEliminar.btvolvereliminar.setActionCommand("btvolvereliminar");
        this.vistaEliminar.btvolvereliminar.addActionListener(this);

        //acciones vista consulta
        this.vistaConsulta.btconsulta1.setActionCommand("btconsulta1");
        this.vistaConsulta.btconsulta1.addActionListener(this);

        this.vistaConsulta.btconsulta2.setActionCommand("btconsulta2");
        this.vistaConsulta.btconsulta2.addActionListener(this);

        this.vistaConsulta.btconsulta3.setActionCommand("btconsulta3");
        this.vistaConsulta.btconsulta3.addActionListener(this);

        this.vistaConsulta.btconsulta4.setActionCommand("btconsulta4");
        this.vistaConsulta.btconsulta4.addActionListener(this);

    }

    public void actionPerformed(ActionEvent e) {
        switch (AccionMVC.valueOf(e.getActionCommand())) {

            //Acciones de menu de vista Menuinicio
            case MenuAgregar:
                this.vista.setVisible(false);
                this.vistaAgregar.setVisible(true);
                break;

            case MenuConsulta:
                this.vista.setVisible(false);
                this.vistaConsulta.setVisible(true);
                break;
            case MenuEliminar:
                this.vista.setVisible(false);
                this.vistaEliminar.setVisible(true);
                break;

            case MenuModificar:
                this.vista.setVisible(false);
                this.vistaModificar.setVisible(true);
                break;

            case MenuSalir:
                this.vista.dispose();
                System.exit(0);
                break;

            //Acciones de menu de vista Agregar 
            case btlimpiaragregar:
                //Limpiamos textField
                this.vistaAgregar.txtcodigo.setText("");
                this.vistaAgregar.txtrut.setText("");
                this.vistaAgregar.txtnombre.setText("");
                this.vistaAgregar.txtapellido.setText("");
                this.vistaAgregar.txtcelular.setText("");
                this.vistaAgregar.txtemail.setText("");
                this.vistaAgregar.txtsueldo.setText("");
                this.vistaAgregar.CboxEstadocivilAgregar.setSelectedIndex(0);
                this.vistaAgregar.cbdepartamento.setSelectedIndex(0);
                this.vistaAgregar.txtcodigo.grabFocus();
                break;

            case btagregar:
                if (this.modelo.AgregarEmpleado(
                        Integer.parseInt(this.vistaAgregar.txtcodigo.getText()),
                        this.vistaAgregar.txtrut.getText(),
                        this.vistaAgregar.txtnombre.getText(),
                        this.vistaAgregar.txtapellido.getText(),
                        Integer.parseInt(this.vistaAgregar.txtcelular.getText()),
                        this.vistaAgregar.txtemail.getText(),
                        Integer.parseInt(this.vistaAgregar.txtsueldo.getText()),
                        this.vistaAgregar.CboxEstadocivilAgregar.getSelectedItem().toString(),
                        this.vistaAgregar.cbdepartamento.getSelectedItem().toString()
                )) {
                    JOptionPane.showMessageDialog(null, "Producto agregado correctamente");
                }
                break;

            case Btnvolveragregar:
                this.vistaAgregar.setVisible(false);
                this.vista.setVisible(true);
                break;

            //Acciones de menu de vista Modificar 
            case BtnBuscarModificar:

                try {
                    String codigo = this.vistaModificar.txtCodigoModificar.getText();

                    DefaultTableModel modeloT = new DefaultTableModel();
                    vistaModificar.TablaEmpleados.setModel(modeloT);

                    modeloT.addColumn("Codigo");
                    modeloT.addColumn("Rut");
                    modeloT.addColumn("Nombre");
                    modeloT.addColumn("Apellido");
                    modeloT.addColumn("celular");
                    modeloT.addColumn("Email");
                    modeloT.addColumn("Sueldo bruto");
                    modeloT.addColumn("Estado civil");
                    modeloT.addColumn("Departamento");

                    Object[] columna = new Object[9];

                    int numRegistros = modelo.buscarPorCodigo(codigo).size();

                    for (int i = 0; i < numRegistros; i++) {
                        columna[0] = modelo.buscarPorCodigo(codigo).get(i).getCodigo();
                        columna[1] = modelo.buscarPorCodigo(codigo).get(i).getRut();
                        columna[2] = modelo.buscarPorCodigo(codigo).get(i).getNombre();
                        columna[3] = modelo.buscarPorCodigo(codigo).get(i).getApellido();
                        columna[4] = modelo.buscarPorCodigo(codigo).get(i).getCelular();
                        columna[5] = modelo.buscarPorCodigo(codigo).get(i).getEmail();
                        columna[6] = modelo.buscarPorCodigo(codigo).get(i).getSueldo_bruto();
                        columna[7] = modelo.buscarPorCodigo(codigo).get(i).getEstado_civil();
                        columna[8] = modelo.buscarPorCodigo(codigo).get(i).getNombre_departamento();
                        modeloT.addRow(columna);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Empleado no encontrado");
                }

                break;

            case BtnModificarModificar:

                if (this.modelo.modificaEmpleado(
                        Integer.parseInt(this.vistaModificar.txtCodigoModificar.getText()),
                        this.vistaModificar.TxtRutModificar.getText(),
                        this.vistaModificar.TxtNombreModificar.getText(),
                        this.vistaModificar.TxtApellidoModificar.getText(),
                        Integer.parseInt(this.vistaModificar.TxtCelularModificar.getText()),
                        this.vistaModificar.TxtEmailModificar.getText(),
                        Integer.parseInt(this.vistaModificar.TxtSueldoModificar.getText()),
                        this.vistaModificar.CboxEstadosMod.getSelectedItem().toString(),
                        this.vistaModificar.CboxDepartamentomod.getSelectedItem().toString())) {
                    JOptionPane.showMessageDialog(null, "Producto Modificado correctamente");
                }
                break;

            case BtnListamodificar:
                //obtiene del modelo los registros en un DefaultTableModel y lo asigna en la vista
                this.vistaModificar.TablaEmpleados.setModel(this.modelo.ListadoEmpleado());
                break;

            case BtnvolverModificar:
                this.vistaModificar.setVisible(false);
                this.vista.setVisible(true);
                break;

            //Acciones de menu de vista Eliminar
            case bteliminar:
                int codigo1;
                codigo1 = Integer.parseInt(this.vistaEliminar.txteliminar.getText());
                this.modelo.eliminarEmpleado(codigo1);
                break;

            case btvolvereliminar:
                this.vistaEliminar.setVisible(false);
                this.vista.setVisible(true);
                break;

            //Acciones de menu de vista consulta
            case btconsulta1:
                this.vistaAgregar.setVisible(true);
                this.vistaConsulta.setVisible(false);
                //Limpiamos textField
                this.vistaAgregar.txtcodigo.setText("");
                this.vistaAgregar.txtrut.setText("");
                this.vistaAgregar.txtnombre.setText("");
                this.vistaAgregar.txtapellido.setText("");
                this.vistaAgregar.txtcelular.setText("");
                this.vistaAgregar.txtemail.setText("");
                this.vistaAgregar.txtsueldo.setText("");
                this.vistaAgregar.CboxEstadocivilAgregar.setSelectedIndex(0);
                this.vistaAgregar.cbdepartamento.setSelectedIndex(0);
                this.vistaAgregar.txtcodigo.grabFocus();
                break;

            case btconsulta2:
                this.vistaModificar.setVisible(true);
                this.vistaConsulta.setVisible(false);
                try {

                    String Departamento = "Redes";

                    DefaultTableModel modeloT = new DefaultTableModel();
                    vistaModificar.TablaEmpleados.setModel(modeloT);

                    modeloT.addColumn("Codigo");
                    modeloT.addColumn("Rut");
                    modeloT.addColumn("Nombre");
                    modeloT.addColumn("Apellido");
                    modeloT.addColumn("celular");
                    modeloT.addColumn("Email");
                    modeloT.addColumn("Sueldo bruto");
                    modeloT.addColumn("Estado civil");
                    modeloT.addColumn("Departamento");

                    Object[] columna = new Object[9];

                    int numRegistros = modelo.buscarPorDepartamento(Departamento).size();

                    for (int i = 0; i < numRegistros; i++) {
                        columna[0] = modelo.buscarPorDepartamento(Departamento).get(i).getCodigo();
                        columna[1] = modelo.buscarPorDepartamento(Departamento).get(i).getRut();
                        columna[2] = modelo.buscarPorDepartamento(Departamento).get(i).getNombre();
                        columna[3] = modelo.buscarPorDepartamento(Departamento).get(i).getApellido();
                        columna[4] = modelo.buscarPorDepartamento(Departamento).get(i).getCelular();
                        columna[5] = modelo.buscarPorDepartamento(Departamento).get(i).getEmail();
                        columna[6] = modelo.buscarPorDepartamento(Departamento).get(i).getSueldo_bruto();
                        columna[7] = modelo.buscarPorDepartamento(Departamento).get(i).getEstado_civil();
                        columna[8] = modelo.buscarPorDepartamento(Departamento).get(i).getNombre_departamento();
                        modeloT.addRow(columna);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Empleado no encontrado");
                }

                break;

            case btconsulta3:
                int sueldo2 = 120000;
                this.modelo.eliminarSueldo(sueldo2);
                 {
                    JOptionPane.showMessageDialog(null, "Empleados con sueldo igual a 120000 eliminados");
                }
                break;

            case btconsulta4:
                if (this.modelo.modificaEmpleado1());
                 {
                    JOptionPane.showMessageDialog(null, "Los sueldos aumentaron en un 10%");
                }

            case btvolverconsulta:
                this.vistaConsulta.setVisible(false);
                this.vistaModificar.setVisible(true);
                break;
        }
    }

    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == 1)//boton izquierdo
        {
            //Muestro datos de producto a modificar
            int fila = this.vistaModificar.TablaEmpleados.rowAtPoint(e.getPoint());
            if (fila > -1) {
                this.vistaModificar.txtCodigoModificar.setText(String.valueOf(this.vistaModificar.TablaEmpleados.getValueAt(fila, 0)));
                this.vistaModificar.TxtRutModificar.setText(String.valueOf(this.vistaModificar.TablaEmpleados.getValueAt(fila, 1)));
                this.vistaModificar.TxtNombreModificar.setText(String.valueOf(this.vistaModificar.TablaEmpleados.getValueAt(fila, 2)));
                this.vistaModificar.TxtApellidoModificar.setText(String.valueOf(this.vistaModificar.TablaEmpleados.getValueAt(fila, 3)));
                this.vistaModificar.TxtCelularModificar.setText(String.valueOf(this.vistaModificar.TablaEmpleados.getValueAt(fila, 4)));
                this.vistaModificar.TxtEmailModificar.setText(String.valueOf(this.vistaModificar.TablaEmpleados.getValueAt(fila, 5)));
                this.vistaModificar.TxtSueldoModificar.setText(String.valueOf(this.vistaModificar.TablaEmpleados.getValueAt(fila, 6)));
                this.vistaModificar.CboxEstadosMod.setSelectedItem(String.valueOf(this.vistaModificar.TablaEmpleados.getValueAt(fila, 7)));
                this.vistaModificar.CboxDepartamentomod.setSelectedItem(String.valueOf(this.vistaModificar.TablaEmpleados.getValueAt(fila, 8)));
//       

            }
        }
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
