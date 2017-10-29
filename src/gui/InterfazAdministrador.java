/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import controladores.ControladorEmpleado;
import controladores.ControladorSede;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

/**
 *
 * @author diegoruiz
 */
public class InterfazAdministrador extends JFrame implements ActionListener{
    Reportes reportes;
    PanelCrearUsuario panelCrearUsuario;
    PanelBienvenido panelBienvenido;
    PanelBuscar panelBuscar;
    JButton crear_usuario;
    JButton modificar_usuario;
    JButton listar_usuarios;
    
    /**
     * 
     * @param id_empleado 
     */
    public InterfazAdministrador(String id_empleado){
        panelBuscar = new PanelBuscar();
        configurarPanelBienvenida();
        configurarUserPanel(); // configuramos la ventana
        configurarBotones();
        mostrarPanelEmpleado("Crear Usuario", null,false);
    }
    private void configurarPanelBienvenida(){
        panelBienvenido = new PanelBienvenido();
        panelBienvenido.setBounds(370, 10, 420, 550);
        this.add(panelBienvenido);
    }
    private void configurarUserPanel() {
        this.setTitle("Panel de usuario");                      // colocamos titulo a la ventana
        this.setSize(800, 600);                                 // colocamos tamanio a la ventana (ancho, alto)
        this.setLocationRelativeTo(null);                       // centramos la ventana en la pantalla
        this.setLayout(null);                                   // no usamos ningun layout, solo asi podremos dar posiciones a los componentes
        this.setResizable(false);                               // hacemos que la ventana no sea redimiensionable
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    // hacemos que cuando se cierre la ventana termina todo proceso
    }
    
    public void configurarBotones(){
        crear_usuario = new JButton("Crear Usuario");
        modificar_usuario = new JButton("Modificar Usuario");
        listar_usuarios = new JButton("Listar Usuarios");
        
        crear_usuario.setBounds(20, 20, 200, 100);
        modificar_usuario.setBounds(20, 140, 200, 100);
        listar_usuarios.setBounds(20, 260, 200, 100);
        
        crear_usuario.addActionListener(this);
        listar_usuarios.addActionListener(this);
        modificar_usuario.addActionListener(this);
        
        this.add(crear_usuario);
        this.add(modificar_usuario);
        this.add(listar_usuarios);
    }
    public void mostrarPanelEmpleado(String accion, String[] datos_empleado, boolean visible){
        panelCrearUsuario = new PanelCrearUsuario(accion, datos_empleado);
        panelCrearUsuario.setBounds(370, 10, 420, 550);
        this.add(panelCrearUsuario);
        panelCrearUsuario.setVisible(visible);
    }
    
    public void removerPaneles(){
        if(panelCrearUsuario != null){
            this.remove(panelCrearUsuario);
        }
        if(panelBuscar != null){
            this.remove(panelBuscar);
        }
        if(panelBienvenido != null){
            this.remove(panelBienvenido);
        }
        this.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(crear_usuario)){
            removerPaneles();
            mostrarPanelEmpleado("Crear Usuario", null, true);
            this.repaint();
            panelCrearUsuario.setCombobox(null);
        }else if(e.getSource().equals(modificar_usuario)){
            removerPaneles();
            panelBuscar = new PanelBuscar();
            panelBuscar.setBounds(370, 10, 420, 550);
            this.add(panelBuscar);
            this.repaint();
        }
    }
    
    /**
     * InnerClase que contiene el panel con el formulario para buscar un usuario
     */
    public class PanelBuscar extends JPanel{
        JLabel label_buscar;
        JLabel label_alerta;
        JTextField txt_buscar;
        JButton btn_buscar;
        JButton btn_cancelar;
        String id_empleado;
        Keylistener keylistener; //Objeto para menjar evento de teclado en este panel
        Mouselistener mouselistener; //Objeto para menjar evento de mouse en este panel
        public PanelBuscar() {
            setTitle("Buscar Usuario");                        // colocamos titulo a la ventana
            setSize(420, 550);                                 // colocamos tamanio a la ventana (ancho, alto)
            setLocationRelativeTo(null);                       // centramos la ventana en la pantalla
            setLayout(null);                                   // no usamos ningun layout, solo asi podremos dar posiciones a los componentes
            setResizable(false);                               // hacemos que la ventana no sea redimiensionable
            Border lowerEtched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
            TitledBorder title = BorderFactory.createTitledBorder(lowerEtched, "Buscar Usuario");
            Font titleFont = UIManager.getFont("TitledBorder.font");
            title.setTitleFont( titleFont.deriveFont(Font.ITALIC + Font.BOLD, 20) );
            setBorder(title);
          
            label_buscar = new JLabel("Identificación: ", SwingConstants.RIGHT);
            label_alerta = new JLabel("<html><font color='red'></font></html>", SwingConstants.CENTER);
            txt_buscar = new JTextField();
            btn_buscar = new JButton("Buscar");
            btn_cancelar = new JButton("Cancelar");
            keylistener = new Keylistener();
            mouselistener = new Mouselistener();
            
            label_buscar.setBounds(10, 50, 100, 35);
            txt_buscar.setBounds(125, 50, 250, 35);
            btn_buscar.setBounds(125, 90, 120, 35);
            btn_cancelar.setBounds(255, 90, 120, 35);
            label_alerta.setBounds(10, 130, 400, 32);
            txt_buscar.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
            
            btn_buscar.addMouseListener(mouselistener);
            btn_cancelar.addMouseListener(mouselistener);
            txt_buscar.addKeyListener(keylistener);
            
            add(label_buscar);
            add(label_alerta);
            add(txt_buscar);
            add(btn_buscar);
            add(btn_cancelar);
        }
        
        public void buscarUsuario(String id_empleado){
            ControladorEmpleado controladorEmpleado = new ControladorEmpleado();
            if(controladorEmpleado.consultarEmpleado(id_empleado)){
                removerPaneles();
                String[] datos_empleado = controladorEmpleado.extraerEmpleado(id_empleado);
                mostrarPanelEmpleado("Modificar Usuario", datos_empleado,true);
                this.repaint();
                String[] temp = {datos_empleado[1], datos_empleado[2], datos_empleado[11]}; //Setear combobox
                System.out.println("Array set combox: "+Arrays.toString(temp));
                panelCrearUsuario.setCombobox(temp);
            }else{
                txt_buscar.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
                label_alerta.setText("<html><font color='red' size='4'>!!! No Existe el usuario con esa identificación!!!</font></html>");
            }
        }
        
        /*Clase exclusiva para manejar eventos del teclado en este panel*/
        public class Keylistener implements KeyListener{

            @Override
            public void keyTyped(KeyEvent e) {
                if(e.getSource().equals(txt_buscar)){
                    label_alerta.setText("");
                    txt_buscar.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));
                }else if(e.getSource().equals(btn_cancelar)){
                    
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        
        }//Fin de clase keyListener
        
        /*Clase exclusiva para manejar eventos del mouse en este panel*/
        public class Mouselistener implements MouseListener{

            @Override
            public void mouseClicked(MouseEvent e) {
                if(e.getSource().equals(btn_buscar)){
                    id_empleado = txt_buscar.getText();
                    buscarUsuario(id_empleado);
                }else if(e.getSource().equals(btn_cancelar)){
                    setVisible(false);
                    removerPaneles();
                    configurarPanelBienvenida();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        
        }
    }
    
    /**
     * InnerClase que contiene el panel de bienvenida para el usuario
     */
    public class PanelBienvenido extends JPanel{
        public PanelBienvenido(){
            setTitle("Bienvenido");                        // colocamos titulo a la ventana
            setSize(420, 550);                                 // colocamos tamanio a la ventana (ancho, alto)
            setLocationRelativeTo(null);                       // centramos la ventana en la pantalla
            setLayout(null);                                   // no usamos ningun layout, solo asi podremos dar posiciones a los componentes
            setResizable(false);                               // hacemos que la ventana no sea redimiensionable
            Border lowerEtched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
            TitledBorder title = BorderFactory.createTitledBorder(lowerEtched, "Bienvenido");
            Font titleFont = UIManager.getFont("TitledBorder.font");
            title.setTitleFont( titleFont.deriveFont(Font.ITALIC + Font.BOLD, 20) );
            setBorder(title);
        }
        
    }
    
    
    /**
     * InnerClase que contiene un formulario para crear, visualizar y editar un usuario.
     */
    private class PanelCrearUsuario extends JPanel {
        //Fields
        JTextField txt_id;
        JComboBox comb_id_sede;
        JComboBox comb_rol;
        JTextField txt_primer_nombre;
        JTextField txt_segundo_nombre;
        JTextField txt_primer_apellido;
        JTextField txt_segundo_appelido;
        JTextField txt_direccion;
        JTextField txt_telefono;
        JTextField txt_email;
        JTextField txt_password;
        JComboBox comb_estado;
        //Labels
        JLabel label_id;
        JLabel label_id_sede;
        JLabel label_rol;
        JLabel label_primer_nombre;
        JLabel label_segundo_nombre;
        JLabel label_prime_apellido;
        JLabel label_segundo_apellido;
        JLabel label_direccion;
        JLabel label_telefono;
        JLabel label_email;
        JLabel label_password;
        JLabel label_estado;
        JLabel label_alert;

        //JButtons
        JButton btn_cancelar;
        JButton btn_enviar;

        ChangeListener changeListener;
        Actionlistenr actionlistener;

        //Array Para alertas
        String[] alertas ={"Ingrese un número si caracteres especiales", 
                           "Debe Seleccionar una sede para el usuario", 
                           "Debe seleccionar un rol para el usuario", 
                           "Ingrese El nombre sin caracteres especiales",
                           "No ingrese palabras reservas ni espacios", 
                           "Ingrese el apellido sin caracteres especiales", 
                           "", 
                           "Ingrese una dirección correcta", 
                           "Digite un número de teléfono correcto", 
                           "Debe indicar una dirección de correo válida", 
                           "EL password debe contener al menos 5 caracteres", 
                           "Debe seleccionar un estado al para el usuario",
                           "Ya existe un usuario con esa identificación",
                           "No se pudo crear el usuario, verifique la base de datos"};

        //variables auxiliares
        String accion;
        String[] sedes_id;
        String[] sedes_idonly;

        public PanelCrearUsuario(String accion, String[] datos_usuario){
            setLayout(null);
            this.setSize(420, 550);
            this.accion = accion;
            Border lowerEtched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
            TitledBorder title = BorderFactory.createTitledBorder(lowerEtched, this.accion);
            Font titleFont = UIManager.getFont("TitledBorder.font");
            title.setTitleFont( titleFont.deriveFont(Font.ITALIC + Font.BOLD, 20) );
            this.setBorder(title);
            changeListener = new ChangeListener();
            actionlistener = new Actionlistenr();
            configurarEtiquetas();
            configurarCampos();
            configurarBotones();
            if(datos_usuario == null){
            }else{
                completarCampos(datos_usuario);
                //this.repaint();
            }
        }

        //ConfigurarEtiquetas()
        public void configurarEtiquetas(){
            //Declaración de etiquetas
            label_id = new JLabel("* Identificación: ", SwingConstants.RIGHT);
            label_id_sede = new JLabel("* Id sede: ", SwingConstants.RIGHT);
            label_rol = new JLabel("* Cargo: ", SwingConstants.RIGHT);
            label_primer_nombre = new JLabel("* Primer nombre: ", SwingConstants.RIGHT);
            label_segundo_nombre = new JLabel("  Segundo nombre: ", SwingConstants.RIGHT);
            label_prime_apellido = new JLabel("* Primer apellido: ", SwingConstants.RIGHT);
            label_segundo_apellido = new JLabel("  Segundo apellido: ", SwingConstants.RIGHT);
            label_direccion = new JLabel("* Dirección: ", SwingConstants.RIGHT);
            label_telefono = new JLabel("* Teléfono: ", SwingConstants.RIGHT);
            label_email = new JLabel("* email: ", SwingConstants.RIGHT);
            label_password = new JLabel("* Password: ", SwingConstants.RIGHT);
            label_estado = new JLabel("* Estado: ", SwingConstants.RIGHT);
            label_alert = new JLabel("<html><font color='red'></font></html>", SwingConstants.CENTER);

            //Ubicación de etiquetas
            label_id.setBounds(5, 30, 150, 35);
            label_id_sede.setBounds(5, 67, 150, 35);
            label_rol.setBounds(5, 104, 150, 35);
            label_primer_nombre.setBounds(5, 141, 150, 35);
            label_segundo_nombre.setBounds(5, 178, 150, 35);
            label_prime_apellido.setBounds(5, 215, 150, 35);
            label_segundo_apellido.setBounds(5, 252, 150, 35);
            label_direccion.setBounds(5, 289, 150, 35);
            label_telefono.setBounds(5, 326, 150, 35);
            label_email.setBounds(5, 363, 150, 35);
            label_password.setBounds(5, 400, 150, 35);
            label_estado.setBounds(5, 437, 150, 35);
            label_alert.setBounds(10, 472, 400, 25);

            //label_alert.setOpaque(true);
            //label_alert.setBackground(Color.RED);

            //Add labels
            //this.add(label_panel_title);
            add(label_id);
            add(label_id_sede);
            add(label_rol);
            add(label_primer_nombre);
            add(label_segundo_nombre);
            add(label_prime_apellido);
            add(label_segundo_apellido);
            add(label_direccion);
            add(label_telefono);
            add(label_email);
            add(label_password);
            add(label_estado);
            add(label_alert);
        }//Fin de configurar etiquetas

        //configurarCampos()
        public void configurarCampos(){
            //Arreglos auxiliares
            //Objeto temporal de tipo ControladorSede
            ControladorSede controladorSede = new ControladorSede();
            String[] sedes_id_temp = controladorSede.listarIdNombreSedes();
            if(sedes_id_temp.length > 0){
                sedes_id = new String[sedes_id_temp.length+1];
                sedes_idonly = new String[sedes_id_temp.length+1];
                sedes_id[0] = "Seleccionar";
                sedes_idonly[0] = "xyzxyzxyzxyzxyz";
                for(int i=0; i<sedes_id_temp.length;i++){
                    sedes_id[i+1] = sedes_id_temp[i];
                    sedes_idonly[i+1] = sedes_id_temp[i].split(" - ")[0];
                }

            }
            String[] roles = {"Seleccione","Administrador", "Gerente", "Operador"};
            String[] estados = {"Seleccione", "Activo", "Inactivo"};

            //Inicializar campos
            txt_id = new JTextField();
            comb_id_sede = new JComboBox(sedes_id);
            comb_rol = new JComboBox(roles);
            txt_primer_nombre = new JTextField();
            txt_segundo_nombre = new JTextField();
            txt_primer_apellido = new JTextField();
            txt_segundo_appelido = new JTextField();
            txt_direccion = new JTextField();
            txt_telefono = new JTextField();
            txt_email = new JTextField();
            txt_password = new JTextField();
            comb_estado = new JComboBox(estados);

            //Posicionar Campos
            txt_id.setBounds(165, 30, 250, 35);
            comb_id_sede.setBounds(165, 67, 250, 35);
            comb_rol.setBounds(165, 104, 250, 35);
            txt_primer_nombre.setBounds(165, 141, 250, 35);
            txt_segundo_nombre.setBounds(165, 178, 250, 35);
            txt_primer_apellido.setBounds(165, 215, 250, 35);
            txt_segundo_appelido.setBounds(165, 252, 250, 35);
            txt_direccion.setBounds(165, 289, 250, 35);
            txt_telefono.setBounds(165, 326, 250, 35);
            txt_email.setBounds(165, 363, 250, 35);
            txt_password.setBounds(165, 400, 250, 35);
            comb_estado.setBounds(165, 437, 250, 35);

            //evntos
            txt_id.addKeyListener(changeListener);
            comb_id_sede.addActionListener(actionlistener);
            comb_rol.addActionListener(actionlistener);
            txt_primer_nombre.addKeyListener(changeListener);
            txt_segundo_nombre.addKeyListener(changeListener);
            txt_primer_apellido.addKeyListener(changeListener);
            txt_segundo_appelido.addKeyListener(changeListener);
            txt_direccion.addKeyListener(changeListener);
            txt_telefono.addKeyListener(changeListener);
            txt_email.addKeyListener(changeListener);
            txt_password.addKeyListener(changeListener);
            comb_estado.addActionListener(actionlistener);

            //Add Campos
            add(txt_id);
            add(comb_id_sede);
            add(comb_rol);
            add(txt_primer_nombre);
            add(txt_segundo_nombre);
            add(txt_primer_apellido);
            add(txt_segundo_appelido);
            add(txt_direccion);
            add(txt_telefono);
            add(txt_email);
            add(txt_password);
            add(comb_estado);
        }//Fin de configurar campos

        public void setCombobox(String[] values){
            if(values != null){
                int index = Arrays.asList(sedes_idonly).indexOf(values[0]);
                comb_id_sede.setSelectedIndex(index);
                comb_rol.setSelectedItem(values[1]);
                comb_estado.setSelectedItem(values[2]);
            }else{
                comb_id_sede.setSelectedIndex(0);
                comb_rol.setSelectedIndex(0);
                comb_estado.setSelectedIndex(0);
            }
        }

        //Configurar botones
        public void configurarBotones(){
            btn_enviar = new JButton("Enviar");
            btn_cancelar = new JButton("Cancelar");

            btn_enviar.setBounds(165, 502, 120, 35);
            btn_cancelar.setBounds(295, 502, 120, 35);

            btn_enviar.addActionListener(actionlistener);
            btn_cancelar.addActionListener(actionlistener);

            this.add(btn_enviar);
            this.add(btn_cancelar);
        }//fin de configurar botones

        /**
         * Método para llenar el formulario para la modificación de un empleado.
         * @param datos_empleado 
         */
        public void completarCampos(String[] datos_empleado){
            txt_id.setText(datos_empleado[0]);
            txt_id.setEditable(false);
            txt_id.setEnabled(false);
            txt_primer_nombre.setText(datos_empleado[3]);
            txt_segundo_nombre.setText(datos_empleado[4]);
            txt_primer_apellido.setText(datos_empleado[5]);
            txt_segundo_appelido.setText(datos_empleado[6]);
            txt_direccion.setText(datos_empleado[7]);
            txt_telefono.setText(datos_empleado[8]);
            txt_email.setText(datos_empleado[9]);
            txt_password.setText(datos_empleado[10]);
        }

        //crear usuario
        public void crearUsuario(){
            ControladorEmpleado controladorEmpleado = new ControladorEmpleado();
            String identificacion = txt_id.getText();
            String id_sede = comb_id_sede.getSelectedItem().toString().split(" - ")[0];
            String rol = comb_rol.getSelectedItem().toString();
            String primer_nombre = txt_primer_nombre.getText();
            String segundo_nombre = txt_segundo_nombre.getText();
            String primer_apellido = txt_primer_apellido.getText();
            String segundo_apellido = txt_segundo_appelido.getText();
            String direccion = txt_direccion.getText();
            String telefono = txt_telefono.getText();
            String email = txt_email.getText();
            String password = txt_password.getText();
            String estado = comb_estado.getSelectedItem().toString();
            int validar_campos = validarCampos();
            if(validar_campos == 0){
                setBordes(null, null);
                label_alert.setText("");
                int insertar_modificar_usurio;
                String msg_confirmacion;
                if(accion.equals("Crear Usuario")){
                  insertar_modificar_usurio = controladorEmpleado.insertarEmpleado(identificacion, id_sede, rol, primer_nombre, segundo_nombre, primer_apellido, segundo_apellido, direccion, telefono, email, password, estado);              
                  msg_confirmacion = "El Usuario "+primer_nombre+" ha sido creado correctamente";
                }else{
                   insertar_modificar_usurio = controladorEmpleado.editarEmpleado(identificacion, id_sede, rol, primer_nombre, segundo_nombre, primer_apellido, segundo_apellido, direccion, telefono, email, password, estado);                             
                   msg_confirmacion = "El Usuario "+primer_nombre+" ha sido Actualizado correctamente";
                }
                if(insertar_modificar_usurio >= 1){
                    int res = JOptionPane.showOptionDialog(null, msg_confirmacion, "Test", JOptionPane.DEFAULT_OPTION,
                            JOptionPane.INFORMATION_MESSAGE, null, null, null);
                    if( res == 0 || res == -1){
                        this.setVisible(false);
                    }
                }else{
                    setBordes(txt_id, null);
                    label_alert.setText("<html><font color='red' size='4'>!!!"+alertas[12]+"!!!</font></html>");
                }
            }else{
                switch (validar_campos){
                    case 1: setBordes(txt_id, null);
                            label_alert.setText("<html><font color='red' size='4'>!!!"+alertas[0]+"!!!</font></html>");
                            break;
                    case 2: setBordes(null, comb_id_sede);
                            label_alert.setText("<html><font color='red' size='4'>!!!"+alertas[1]+"!!!</font></html>");
                            break;
                    case 3: setBordes(null, comb_rol);
                            label_alert.setText("<html><font color='red' size='4'>!!!"+alertas[2]+"!!!</font></html>");
                            break;
                    case 4: setBordes(txt_primer_nombre, null);
                            label_alert.setText("<html><font color='red' size='4'>!!!"+alertas[3]+"!!!</font></html>");
                            break;
                    case 5: setBordes(txt_segundo_nombre, null);
                            label_alert.setText("<html><font color='red' size='4'>!!!"+alertas[4]+"!!!</font></html>");
                            break;
                    case 6: setBordes(txt_primer_apellido, null);
                            label_alert.setText("<html><font color='red' size='4'>!!!"+alertas[5]+"!!!</font></html>");
                            break;
                    case 7: setBordes(txt_segundo_appelido, null);
                            label_alert.setText("<html><font color='red' size='4'>!!!"+alertas[6]+"!!!</font></html>");
                            break;
                    case 8: setBordes(txt_direccion, null);
                            label_alert.setText("<html><font color='red' size='4'>!!!"+alertas[7]+"!!!</font></html>");
                            break;
                    case 9: setBordes(txt_telefono, null);
                            label_alert.setText("<html><font color='red' size='4'>!!!"+alertas[8]+"!!!</font></html>");
                            break;
                    case 10: setBordes(txt_email, null);
                            label_alert.setText("<html><font color='red' size='4'>!!!"+alertas[9]+"!!!</font></html>");
                             break;
                    case 11: setBordes(txt_password, null);
                            label_alert.setText("<html><font color='red' size='4'>!!!"+alertas[10]+"!!!</font></html>");
                             break;
                    case 12: setBordes(null, comb_estado);
                            label_alert.setText("<html><font color='red' size='4'>!!!"+alertas[11]+"!!!</font></html>");
                             break;
                    default:
                             break;

                }
            }

        }//Fin de crear usuario

        public int validarCampos(){
            String identificacion = txt_id.getText();
            int id_sede = comb_id_sede.getSelectedIndex();
            int  rol = comb_rol.getSelectedIndex();
            String primer_nombre = txt_primer_nombre.getText();
            String segundo_nombre = txt_segundo_nombre.getText();
            String primer_apellido = txt_primer_apellido.getText();
            String segundo_apellido = txt_segundo_appelido.getText();
            String direccion = txt_direccion.getText();
            String telefono = txt_telefono.getText();
            String email = txt_email.getText();
            String password = txt_password.getText();
            int estado = comb_estado.getSelectedIndex();
            if(identificacion.equals("") || !Pattern.matches("[0-9]+", identificacion)){
                return 1;
            }else if(id_sede == 0){
                return 2;
            }else if(rol == 0){
                return 3;
            }else if(primer_nombre.equals("") || !Pattern.matches("[a-zA-Z]+", primer_nombre)){
                System.err.println("acá");
                return 4;
            }else if(!Pattern.matches("[a-zA-Z]*", segundo_nombre)){
                return 5;
            }else if(!Pattern.matches("[a-zA-Z]+", primer_apellido)){
                return 6;
            }else if(!Pattern.matches("[a-zA-Z]*", segundo_apellido)){
                return 7;
            }else if(direccion.equals("") || direccion.contains("*") || direccion.contains("=") || direccion.toUpperCase().contains("SELECT") || 
                     direccion.toUpperCase().contains("INSERT") || direccion.toUpperCase().contains("DROP") || 
                     direccion.toUpperCase().contains("DELETE") ){
                return 8;

            }else if(telefono.equals("") || telefono.contains("*") || telefono.contains("=") || telefono.toUpperCase().contains("SELECT") || 
                     telefono.toUpperCase().contains("INSERT") || telefono.toUpperCase().contains("DROP") || 
                     telefono.toUpperCase().contains("DELETE")){
                return 9;
            }else if(!validarCaracteres(email, 10)){
                return  10;
            }else if(!validarCaracteres(password, 11)){
                return 11;
            }else if(estado == 0){
                return 12;
            }else{
                return 0;
            }
        }

        public void setBordes(JTextField campo, JComboBox select){
            label_alert.setText("");
            txt_id.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
            comb_id_sede.setBorder(null);
            comb_rol.setBorder(null);
            txt_primer_nombre.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
            txt_segundo_nombre.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
            txt_primer_apellido.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
            txt_segundo_appelido.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
            txt_direccion.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
            txt_telefono.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
            txt_email.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
            txt_password.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
            comb_estado.setBorder(null);

            if(campo != null){
                campo.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
            }else if(select != null){
                select.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
            }
        }

        public boolean validarCaracteres(String contenido_campo, int campo){
            if(campo == 10){
                String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
                Pattern pattern = Pattern.compile(PATTERN_EMAIL);
                Matcher matcher = pattern.matcher(contenido_campo);
                return matcher.matches();
            }else if(campo == 11){
                String PATTERN_PASSWORD = "(^[_A-Za-z0-9-\\\\+]+(\\\\.[_A-Za-z0-9-]+)*.{5,20})";
                Pattern pattern = Pattern.compile(PATTERN_PASSWORD);
                Matcher matcher = pattern.matcher(contenido_campo);
                return matcher.matches();
            }else{
                return false;
            }
        }

        public class ChangeListener  implements KeyListener{
            @Override
            public void keyTyped(KeyEvent e) {
                setBordes(null, null);
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        } 
        public class Actionlistenr implements ActionListener{

                @Override
                public void actionPerformed(ActionEvent e) {
                    if(e.getSource().equals(btn_cancelar)){
                    setVisible(false);

                    }else if(e.getSource().equals(btn_enviar)){
                        crearUsuario();
                    }else if(e.getSource().equals(txt_id) || e.getSource().equals(comb_id_sede) || e.getSource().equals(comb_rol) || e.getSource().equals(txt_primer_nombre)){
                        setBordes(null, null);
                    }
                }  
        }
    }//Fin class TestPanelCreateUsuario

    
   
    
}//Fin clase InterfazAdministrador