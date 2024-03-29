/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.toedter.calendar.JDateChooser;
import controladores.ControladorAsistente;
import controladores.ControladorEvento;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import javax.swing.JFileChooser;
import reportes.Reportes;

/**
 *
 * @author diegoruiz
 */
public class InterfazOperador extends JFrame implements ActionListener {

    PanelBienvenido panelBienvenido;
    PanelCrearAsistente panelCrearAsistente;
    PanelListarAsistentes panelListarAsistentes;
    Reportes reportes;
    PanelBuscarFiltrarPagarRegistrar panelBuscarFiltrarPagarRegistrar;
    JButton btn_crear_asistente;
    JButton btn_modificar_asistente;
    JButton btn_listar_asistentes;
    JButton btn_registrar_asistente_evento;
    JButton btn_registrar_pago;
    JButton btn_listar_eventos;
    JButton btn_generar_certificados;
    JButton btn_reportes;
    JButton btn_salir;
    String[] datos_empleado;

    public InterfazOperador(String[] datos_empleado) {
        this.datos_empleado=datos_empleado;
        panelCrearAsistente = new PanelCrearAsistente("none", null);
        panelListarAsistentes = new PanelListarAsistentes();
        panelBuscarFiltrarPagarRegistrar = new PanelBuscarFiltrarPagarRegistrar("");
        configurarBotones();
        configurarUserPanel();
        
        configurarPanelBienvenida();
        
      
    }
    
        public class PanelLateral extends JPanel{
        public PanelLateral(){
            configurarBotones();
            this.setLayout(null);
        }
        
        public void configurarBotones(){  
            btn_crear_asistente.setBounds(20,10,150,50);
            btn_modificar_asistente.setBounds(20,65,150,50);
            btn_listar_asistentes.setBounds(20,120,150,50);
            btn_registrar_asistente_evento.setBounds(20,175,150,50);
            btn_registrar_pago.setBounds(20,230,150,50);
            btn_listar_eventos.setBounds(20,285,150,50);
            btn_generar_certificados.setBounds(20,340,150,50);
            btn_reportes.setBounds(20,395,150,50);
            btn_salir.setBounds(20,450,150,50);
            
            add(btn_crear_asistente);
            add(btn_modificar_asistente);
            add(btn_listar_asistentes);
            this.add(btn_registrar_asistente_evento);
            this.add(btn_registrar_pago);
            this.add(btn_listar_eventos);
            this.add(btn_generar_certificados);
            this.add(btn_reportes);
            this.add(btn_salir);
 
        }
        
    }

    private void configurarUserPanel() {
        this.setTitle("Panel de usuario");                      // colocamos titulo a la ventana
        this.setSize(800, 600);                                 // colocamos tamanio a la ventana (ancho, alto)
        this.setLocationRelativeTo(null);                       // centramos la ventana en la pantalla
        this.setLayout(null);                                   // no usamos ningun layout, solo asi podremos dar posiciones a los componentes
        this.setResizable(false);                               // hacemos que la ventana no sea redimiensionable
        this.getContentPane().setBackground(new java.awt.Color (35,47,65));
        
        
        //PANEL AZUL CLARO
        PanelLateral  jPanelAzulClaro = new PanelLateral();
        jPanelAzulClaro.setBounds(0,0,240,600) ;
        jPanelAzulClaro.setBackground(new java.awt.Color(97, 212, 195));
        //jPanelAzulClaro.add(jLabelABC); 
       
       
        /*btn_crear_asistente.setBounds(20,50,70,60);
        btn_modificar_asistente.setBounds(20,120,70,60);
        btn_listar_asistentes.setBounds(20,190,70,60);
        btn_registrar_asistente_evento.setBounds(20,260,70,60);
        btn_registrar_pago.setBounds(20,330,70,60);
        btn_listar_eventos.setBounds(20,400,70,60);
        btn_generar_certificados.setBounds(20,470,70,60);
        btn_reportes.setBounds(20,540,70,60);
        btn_salir.setBounds(20,610,70,60);*/
        
        jPanelAzulClaro.add(btn_crear_asistente);
        jPanelAzulClaro.add(btn_modificar_asistente);
        jPanelAzulClaro.add(btn_listar_asistentes);
        jPanelAzulClaro.add(btn_registrar_asistente_evento);
        jPanelAzulClaro.add(btn_registrar_pago);
        jPanelAzulClaro.add(btn_listar_eventos);
        jPanelAzulClaro.add(btn_generar_certificados);
        jPanelAzulClaro.add(btn_reportes);
        jPanelAzulClaro.add(btn_salir);
        this.getContentPane().add(jPanelAzulClaro); 
        
        
        
            
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    // hacemos que cuando se cierre la ventana termina todo proceso
   
    }

    public void configurarBotones() {
        btn_crear_asistente = new JButton("Crear Asistente");
        btn_modificar_asistente = new JButton("Modificar Asistente");
        btn_listar_asistentes = new JButton("Asistentes");
        btn_registrar_asistente_evento = new JButton("Registro");
        btn_registrar_pago = new JButton("Pagos");
        btn_listar_eventos = new JButton("Eventos");
        btn_generar_certificados = new JButton("Certificados");
        btn_reportes = new JButton("Reportes");
        btn_salir = new JButton("Salir");


        btn_crear_asistente.addActionListener(this);
        btn_listar_asistentes.addActionListener(this);
        btn_modificar_asistente.addActionListener(this);
        btn_registrar_asistente_evento.addActionListener(this);
        btn_registrar_pago.addActionListener(this);
        btn_listar_eventos.addActionListener(this);
        btn_generar_certificados.addActionListener(this);
        btn_reportes.addActionListener(this);
        btn_salir.addActionListener(this);
        
        
        
    }

    private void configurarPanelBienvenida() {
        panelBienvenido = new PanelBienvenido();
        panelBienvenido.setBounds(240, 10, 560, 550);
        panelBienvenido.setBackground(new java.awt.Color (35,47,65));
        
        this.add(panelBienvenido);
    }

    public void mostrarPanelAsistente(String accion, String[] datos_empleado, boolean visible) {
        panelCrearAsistente = new PanelCrearAsistente(accion, datos_empleado);
        panelCrearAsistente.setBounds(240, 10, 560, 550);
        panelCrearAsistente.setBackground(new java.awt.Color (35,47,65));
        this.add(panelCrearAsistente);
        panelCrearAsistente.setVisible(visible);
    }

    public void mostrarPanelBuscarFiltarPagarInscribir(String accion, boolean visible) {
        panelBuscarFiltrarPagarRegistrar = new PanelBuscarFiltrarPagarRegistrar(accion);
        panelBuscarFiltrarPagarRegistrar.setBounds(240, 10, 560, 550);
        panelBuscarFiltrarPagarRegistrar.setBackground(new java.awt.Color (35,47,65));
        this.add(panelBuscarFiltrarPagarRegistrar);
        panelBuscarFiltrarPagarRegistrar.setVisible(visible);
    }

    public void mostrarPanelListar(String operacion, int evento_id, String asistente_id) {
        if (operacion.equals("Listar Asistentes")) {
            panelListarAsistentes = new PanelListarAsistentes(operacion, evento_id);
        } else if (operacion.equals("Estado Pagos Asistente")) {
            panelListarAsistentes = new PanelListarAsistentes(operacion, asistente_id);
        }
        panelListarAsistentes.setBounds(240, 10, 560, 550);
        this.add(panelListarAsistentes);
        panelListarAsistentes.setVisible(true);
    }

    public void mostrarPanelListarEventosCampos(String operacion, String campo, String contenido, String fecha1, String fecha2) {
        panelListarAsistentes = new PanelListarAsistentes(operacion, campo, contenido, fecha1, fecha2);
        panelListarAsistentes.setBounds(240, 10, 560, 550);
        this.add(panelListarAsistentes);
        panelListarAsistentes.setVisible(true);
    }

    public void removerPaneles() {
        if (panelCrearAsistente != null) {
            this.remove(panelCrearAsistente);
        }
        if (panelBienvenido != null) {
            this.remove(panelBienvenido);
        }
        if (panelBuscarFiltrarPagarRegistrar != null) {
            this.remove(panelBuscarFiltrarPagarRegistrar);
        }
        if (panelListarAsistentes != null) {
            this.remove(panelListarAsistentes);
        }
        this.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(btn_crear_asistente)) {
            removerPaneles();
            mostrarPanelAsistente("Crear Asistente", null, true);
            this.repaint();
        } else if (e.getSource().equals(btn_modificar_asistente)) {
            removerPaneles();
            mostrarPanelBuscarFiltarPagarInscribir("Modificar Asistente", true);
            this.repaint();
        } else if (e.getSource().equals(btn_generar_certificados)) {
            removerPaneles();
            mostrarPanelBuscarFiltarPagarInscribir("Imprimir Diplomas", true);
            this.repaint();
        } else if (e.getSource().equals(btn_listar_asistentes)) {
            removerPaneles();
            mostrarPanelBuscarFiltarPagarInscribir("Listar Asistentes", true);
            panelBuscarFiltrarPagarRegistrar.setComboBox("Listar Asistentes", null);
            this.repaint();
        } else if (e.getSource().equals(btn_registrar_asistente_evento)) {
            removerPaneles();
            mostrarPanelBuscarFiltarPagarInscribir("Registrar Asistente Evento", true);
            this.repaint();
        } else if (e.getSource().equals(btn_registrar_pago)) {
            removerPaneles();
            mostrarPanelBuscarFiltarPagarInscribir("Pagos", true);
            panelBuscarFiltrarPagarRegistrar.setComboBox("Pagos Inicial", null);
            this.repaint();
        } else if (e.getSource().equals(btn_listar_eventos)) {
            removerPaneles();
            mostrarPanelBuscarFiltarPagarInscribir("Eventos", true);
            panelBuscarFiltrarPagarRegistrar.setComboBox("Eventos", null);
            this.repaint();
        }else if(e.getSource().equals(btn_reportes)){
            reportes = new Reportes(datos_empleado);
            this.dispose();
        }else if(e.getSource().equals(btn_salir)){
            System.exit(1);
        }
    }

    /**
     * InnerClase que contiene el panel de bienvenida para el usuario
     */
    private class PanelBienvenido extends JPanel {

        public PanelBienvenido() {
           // setTitle("Bienvenido");  
            javax.swing.JLabel titulo = new javax.swing.JLabel();
            titulo.setText("BIENVENIDO"); 
            titulo.setBounds (0,0,115,50);
            titulo.setFont(new java.awt.Font("Arial 18 Plain", Font.PLAIN, 18)); // NOI18N
            titulo.setForeground(new java.awt.Color(254, 254, 254));
            titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            this.add(titulo);
// colocamos titulo a la ventana
            setFont(new java.awt.Font("Arial 18 Plain",Font.PLAIN, 18)); // NOI18N
            setForeground(new java.awt.Color(254, 254, 254));
            setSize(420, 550);                                 // colocamos tamanio a la ventana (ancho, alto)
            setLocationRelativeTo(null);                       // centramos la ventana en la pantalla
            setLayout(null);                                   // no usamos ningun layout, solo asi podremos dar posiciones a los componentes
            setResizable(false);                               // hacemos que la ventana no sea redimiensionable
            Border lowerEtched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
      //      TitledBorder title = BorderFactory.createTitledBorder(lowerEtched, "Bienvenido");
            Font titleFont = UIManager.getFont("TitledBorder.font");
        //    title.setTitleFont(titleFont.deriveFont(Font.ITALIC + Font.BOLD, 20));
          //  setBorder(title);
        }

    }

    /**
     * InnerClase que contiene un formulario para crear, visualizar y editar un
     * usuario.
     */
    private class PanelCrearAsistente extends JPanel {

        //Fields
        JTextField txt_id;
        JTextField txt_primer_nombre;
        JTextField txt_segundo_nombre;
        JTextField txt_primer_apellido;
        JTextField txt_segundo_appelido;
        JTextField txt_direccion;
        JTextField txt_telefono;
        JTextField txt_email;
        JComboBox eventosBox;

        //Labels
        JLabel label_id;
        JLabel label_primer_nombre;
        JLabel label_segundo_nombre;
        JLabel label_prime_apellido;
        JLabel label_segundo_apellido;
        JLabel label_direccion;
        JLabel label_telefono;
        JLabel label_email;
        JLabel label_alert;

        //JButtons
        JButton btn_cancelar;
        JButton btn_enviar;
        JButton btn_imprimir;
        JButton btn_guardar;
        JRadioButton btn_escarapela;
        JRadioButton btn_diploma;
        
        ChangeListener changeListener;
        Actionlistenr actionlistener;

        //Array Para alertas
        String[] alertas = {"Ingrese un número si caracteres especiales",
            "Ingrese El nombre sin caracteres especiales",
            "No ingrese palabras reservas ni espacios",
            "Ingrese el apellido sin caracteres especiales",
            "",
            "Ingrese una dirección correcta",
            "Digite un número de teléfono correcto",
            "Debe indicar una dirección de correo válida",
            "Ya existe un usuario con esa identificación",
            "No se pudo crear el usuario, verifique la base de datos"};

        //variables auxiliares
        String accion;
        String[] sedes_id;
        String[] sedes_idonly;
        String[] datos_pdf;
        
        public PanelCrearAsistente(String accion, String[] datos_usuario) {
            javax.swing.JLabel titulo = new javax.swing.JLabel();
            titulo.setText(accion); 
            titulo.setBounds (0,0,200,50);
            titulo.setFont(new java.awt.Font("Arial 18 Plain", Font.PLAIN, 18)); // NOI18N
            titulo.setForeground(new java.awt.Color(254, 254, 254));
            titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            this.add(titulo);
            
            datos_pdf = datos_usuario;
            setLayout(null);
           // this.setBounds(240,10,420 , 550); 
            this.setSize(420, 550);
            this.accion = accion;
            Border lowerEtched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
            TitledBorder title = BorderFactory.createTitledBorder(lowerEtched, this.accion);
          //  Font titleFont = UIManager.getFont("TitledBorder.font");
            //title.setTitleFont(titleFont.deriveFont(Font.ITALIC + Font.BOLD, 20));
            //this.setBorder(title);
            
            changeListener = new ChangeListener();
            actionlistener = new Actionlistenr();
            
            configurarEtiquetas();
            configurarCampos();
            configurarBotones();
            if (datos_usuario == null) {
            } else if (accion.equals("Imprimir Diplomas")) {

                completarCamposImprimir(datos_usuario);
            } else {
                completarCampos(datos_usuario);
            }
        }

        //ConfigurarEtiquetas()
        public void configurarEtiquetas() {
            //Declaración de etiquetas
            label_id = new JLabel("* Identificación: ", SwingConstants.RIGHT);
            label_primer_nombre = new JLabel("* Primer nombre: ", SwingConstants.RIGHT);
            label_segundo_nombre = new JLabel("  Segundo nombre: ", SwingConstants.RIGHT);
            label_prime_apellido = new JLabel("* Primer apellido: ", SwingConstants.RIGHT);
            label_segundo_apellido = new JLabel("  Segundo apellido: ", SwingConstants.RIGHT);
            label_direccion = new JLabel("* Dirección: ", SwingConstants.RIGHT);
            label_telefono = new JLabel("* Teléfono: ", SwingConstants.RIGHT);
            label_email = new JLabel("* email: ", SwingConstants.RIGHT);
            label_alert = new JLabel("<html><font color='red'></fohtmlnt></html>", SwingConstants.CENTER);

            //Ubicación de etiquetas
            label_id.setBounds(5, 70, 150, 35);
            label_id.setFont(new java.awt.Font("Arial 18 Plain", Font.PLAIN, 12)); // NOI18N
            label_id.setForeground(new java.awt.Color(254, 254, 254));
            label_primer_nombre.setBounds(5, 107, 150, 35);
            label_primer_nombre.setFont(new java.awt.Font("Arial 18 Plain", Font.PLAIN, 12)); // NOI18N
            label_primer_nombre.setForeground(new java.awt.Color(254, 254, 254));
            label_segundo_nombre.setBounds(5, 142, 150, 35);
            label_segundo_nombre.setFont(new java.awt.Font("Arial 18 Plain", Font.PLAIN, 12)); // NOI18N
            label_segundo_nombre.setForeground(new java.awt.Color(254, 254, 254));
            label_prime_apellido.setBounds(5, 179, 150, 35);
            label_prime_apellido.setFont(new java.awt.Font("Arial 18 Plain", Font.PLAIN, 12)); // NOI18N
            label_prime_apellido.setForeground(new java.awt.Color(254, 254, 254));
            label_segundo_apellido.setBounds(5, 216, 150, 35);
            label_segundo_apellido.setFont(new java.awt.Font("Arial 18 Plain", Font.PLAIN, 12)); // NOI18N
            label_segundo_apellido.setForeground(new java.awt.Color(254, 254, 254));
            label_direccion.setBounds(5, 253, 150, 35);
            label_direccion.setFont(new java.awt.Font("Arial 18 Plain", Font.PLAIN, 12)); // NOI18N
            label_direccion.setForeground(new java.awt.Color(254, 254, 254));
            label_telefono.setBounds(5, 290, 150, 35);
            label_telefono.setFont(new java.awt.Font("Arial 18 Plain", Font.PLAIN, 12)); // NOI18N
            label_telefono.setForeground(new java.awt.Color(254, 254, 254));
            label_email.setBounds(5, 327, 150, 35);
            label_email.setFont(new java.awt.Font("Arial 18 Plain", Font.PLAIN, 12)); // NOI18N
            label_email.setForeground(new java.awt.Color(254, 254, 254));
            label_alert.setBounds(10, 364, 450, 25);
            label_alert.setFont(new java.awt.Font("Arial 18 Plain", Font.PLAIN, 12)); // NOI18N
            label_alert.setForeground(new java.awt.Color(254, 254, 254));

            //Add labels
            add(label_id);
            add(label_primer_nombre);
            add(label_segundo_nombre);
            add(label_prime_apellido);
            add(label_segundo_apellido);
            add(label_direccion);
            add(label_telefono);
            add(label_email);
            add(label_alert);
        }//Fin de configurar etiquetas

        //configurarCampos()
        public void configurarCampos() {
            //Inicializar campos
            txt_id = new JTextField();
            txt_primer_nombre = new JTextField();
            txt_segundo_nombre = new JTextField();
            txt_primer_apellido = new JTextField();
            txt_segundo_appelido = new JTextField();
            txt_direccion = new JTextField();
            txt_telefono = new JTextField();
            txt_email = new JTextField();
            eventosBox = new JComboBox<String>();
            //Posicionar Campos
            txt_id.setBounds(165, 70, 300, 35);
            txt_primer_nombre.setBounds(165, 107, 300, 35);
            txt_segundo_nombre.setBounds(165, 142, 300, 35);
            txt_primer_apellido.setBounds(165, 179, 300, 35);
            txt_segundo_appelido.setBounds(165, 216, 300, 35);
            txt_direccion.setBounds(165, 253, 300, 35);
            txt_telefono.setBounds(165, 290, 300, 35);
            txt_email.setBounds(165, 327, 300, 35);
            eventosBox.setBounds(165, 364, 300, 35);

            //evntos
            txt_id.addKeyListener(changeListener);
            txt_primer_nombre.addKeyListener(changeListener);
            txt_segundo_nombre.addKeyListener(changeListener);
            txt_primer_apellido.addKeyListener(changeListener);
            txt_segundo_appelido.addKeyListener(changeListener);
            txt_direccion.addKeyListener(changeListener);
            txt_telefono.addKeyListener(changeListener);
            txt_email.addKeyListener(changeListener);

            //Add Campos
            add(txt_id);
            add(txt_primer_nombre);
            add(txt_segundo_nombre);
            add(txt_primer_apellido);
            add(txt_segundo_appelido);
            add(txt_direccion);
            add(txt_telefono);
            add(txt_email);
        }//Fin de configurar campos

        //Configurar botones
        public void configurarBotones() {
            btn_enviar = new JButton("Enviar");
            btn_cancelar = new JButton("Cancelar");
            btn_imprimir = new JButton("Imprmir");
            btn_guardar = new JButton("guardar");
            
            btn_escarapela = new JRadioButton("Escarapela");
            btn_diploma = new JRadioButton("Diploma");
            btn_enviar.setBounds(165, 400, 145, 35);
            btn_imprimir.setBounds(165, 400, 145, 35);
            btn_cancelar.setBounds(320, 400, 145, 35);
            btn_guardar.setBounds(320, 400, 145, 35);
            
            btn_escarapela.setBounds(165, 437, 145, 35);
            btn_diploma.setBounds(320, 437, 145, 35);
            btn_enviar.addActionListener(actionlistener);
            btn_cancelar.addActionListener(actionlistener);
            btn_imprimir.addActionListener(actionlistener);
            btn_guardar.addActionListener(actionlistener);
            btn_escarapela.addActionListener(actionlistener);
            btn_diploma.addActionListener(actionlistener);
            
            this.add(btn_enviar);
            
            this.add(btn_cancelar);
        }//fin de configurar botones

        /**
         * Método para llenar el formulario para la modificación de un empleado.
         *
         * @param datos_asistente
         */
        public void completarCampos(String[] datos_asistente) {
            txt_id.setText(datos_asistente[0]);
            txt_id.setEditable(false);
            txt_id.setEnabled(false);
            txt_primer_nombre.setText(datos_asistente[1]);
            txt_segundo_nombre.setText(datos_asistente[2]);
            txt_primer_apellido.setText(datos_asistente[3]);
            txt_segundo_appelido.setText(datos_asistente[4]);
            txt_direccion.setText(datos_asistente[5]);
            txt_telefono.setText(datos_asistente[6]);
            txt_email.setText(datos_asistente[7]);
        }

        public void completarCamposImprimir(String[] datos_asistente) {
            
            txt_id.setText(datos_asistente[0]);
            txt_id.setEditable(false);
            txt_id.setEnabled(false);
            txt_primer_nombre.setText(datos_asistente[1]);
            txt_primer_nombre.setEditable(false);
            txt_primer_nombre.setEnabled(false);
            txt_segundo_nombre.setText(datos_asistente[2]);
            txt_segundo_nombre.setEditable(false);
            txt_segundo_nombre.setEnabled(false);
            txt_primer_apellido.setText(datos_asistente[3]);
            txt_primer_apellido.setEditable(false);
            txt_primer_apellido.setEnabled(false);
            txt_segundo_appelido.setText(datos_asistente[4]);
            txt_segundo_appelido.setEditable(false);
            txt_segundo_appelido.setEnabled(false);
            txt_direccion.setText(datos_asistente[5]);
            txt_direccion.setEditable(false);
            txt_direccion.setEnabled(false);
            txt_telefono.setText(datos_asistente[6]);
            txt_telefono.setEditable(false);
            txt_telefono.setEnabled(false);
            txt_email.setText(datos_asistente[7]);
            txt_email.setEditable(false);
            txt_email.setEnabled(false);
            btn_enviar.setVisible(false);
            btn_cancelar.setVisible(false);
            this.add(eventosBox);
            this.add(btn_imprimir);
            this.add(btn_guardar);
            this.add(btn_diploma);
            this.add(btn_escarapela);
            label_alert.setBounds(10, 480, 450, 25);
            String[] eventos_nombre;
            ControladorEvento controladorEvento = new ControladorEvento();
            eventos_nombre = controladorEvento.listarIdNombreEventosAsistentePago(datos_asistente[0]);
            eventosBox.addItem("Seleccione");
            
                for (int i = 0; i < eventos_nombre.length; i++) {
                    
                    eventosBox.addItem(eventos_nombre[i].split(" - ")[0]+"-"+eventos_nombre[i].split(" - ")[1]);
                }
                eventosBox.setSelectedIndex(0);
            
        }

        //crear usuario
        public void crearAsistente() {
            ControladorAsistente controladorAsistente = new ControladorAsistente();
            String identificacion = txt_id.getText();
            String primer_nombre = txt_primer_nombre.getText();
            String segundo_nombre = txt_segundo_nombre.getText();
            String primer_apellido = txt_primer_apellido.getText();
            String segundo_apellido = txt_segundo_appelido.getText();
            String direccion = txt_direccion.getText();
            String telefono = txt_telefono.getText();
            String email = txt_email.getText();
            int validar_campos = validarCampos();
            if (validar_campos == 0) {
                setBordes(null, null);
                label_alert.setText("");
                int insertar_modificar_asistente;
                String msg_confirmacion;
                if (accion.equals("Crear Asistente")) {
                    insertar_modificar_asistente = controladorAsistente.insertarAsistente(identificacion, primer_nombre, segundo_nombre, primer_apellido, segundo_apellido, direccion, telefono, email);
                    msg_confirmacion = "El Asistente " + primer_nombre + " ha sido creado correctamente";
                } else {
                    insertar_modificar_asistente = controladorAsistente.editarAsistente(identificacion, primer_nombre, segundo_nombre, primer_apellido, segundo_apellido, direccion, telefono, email);
                    msg_confirmacion = "El Asistente " + primer_nombre + " ha sido Actualizado correctamente";
                }
                if (insertar_modificar_asistente >= 1) {
                    int res = JOptionPane.showOptionDialog(null, msg_confirmacion, "Test", JOptionPane.DEFAULT_OPTION,
                            JOptionPane.INFORMATION_MESSAGE, null, null, null);
                    if (res == 0 || res == -1) {
                        this.setVisible(false);
                        removerPaneles();
                        mostrarPanelAsistente(accion, null, true);
                    }
                } else {
                    setBordes(txt_id, null);
                    label_alert.setText("<html><font color='red' size='4'>!!!" + alertas[12] + "!!!</font></html>");
                }
            } else {
                switch (validar_campos) {
                    case 1:
                        setBordes(txt_id, null);
                        label_alert.setText("<html><font color='red' size='4'>!!!" + alertas[0] + "!!!</font></html>");
                        break;
                    case 2:
                        setBordes(txt_primer_nombre, null);
                        label_alert.setText("<html><font color='red' size='4'>!!!" + alertas[1] + "!!!</font></html>");
                        break;
                    case 3:
                        setBordes(txt_segundo_nombre, null);
                        label_alert.setText("<html><font color='red' size='4'>!!!" + alertas[2] + "!!!</font></html>");
                        break;
                    case 4:
                        setBordes(txt_primer_apellido, null);
                        label_alert.setText("<html><font color='red' size='4'>!!!" + alertas[3] + "!!!</font></html>");
                        break;
                    case 5:
                        setBordes(txt_segundo_appelido, null);
                        label_alert.setText("<html><font color='red' size='4'>!!!" + alertas[4] + "!!!</font></html>");
                        break;
                    case 6:
                        setBordes(txt_direccion, null);
                        label_alert.setText("<html><font color='red' size='4'>!!!" + alertas[5] + "!!!</font></html>");
                        break;
                    case 7:
                        setBordes(txt_telefono, null);
                        label_alert.setText("<html><font color='red' size='4'>!!!" + alertas[6] + "!!!</font></html>");
                        break;
                    case 8:
                        setBordes(txt_email, null);
                        label_alert.setText("<html><font color='red' size='4'>!!!" + alertas[7] + "!!!</font></html>");
                        break;
                    default:
                        break;
                }
            }

        }//Fin de crear usuario

        public int validarCampos() {
            String identificacion = txt_id.getText();
            String primer_nombre = txt_primer_nombre.getText();
            String segundo_nombre = txt_segundo_nombre.getText();
            String primer_apellido = txt_primer_apellido.getText();
            String segundo_apellido = txt_segundo_appelido.getText();
            String direccion = txt_direccion.getText();
            String telefono = txt_telefono.getText();
            String email = txt_email.getText();
            if (identificacion.equals("") || !Pattern.matches("[0-9]+", identificacion)) {
                return 1;
            } else if (primer_nombre.equals("") || !Pattern.matches("[a-zA-Z]+", primer_nombre)) {
                return 2;
            } else if (!Pattern.matches("[a-zA-Z]*", segundo_nombre)) {
                return 3;
            } else if (!Pattern.matches("[a-zA-Z]+", primer_apellido)) {
                return 4;
            } else if (!Pattern.matches("[a-zA-Z]*", segundo_apellido)) {
                return 5;
            } else if (direccion.equals("") || direccion.contains("*") || direccion.contains("=") || direccion.toUpperCase().contains("SELECT")
                    || direccion.toUpperCase().contains("INSERT") || direccion.toUpperCase().contains("DROP")
                    || direccion.toUpperCase().contains("DELETE")) {
                return 6;

            } else if (telefono.equals("") || telefono.contains("*") || telefono.contains("=") || telefono.toUpperCase().contains("SELECT")
                    || telefono.toUpperCase().contains("INSERT") || telefono.toUpperCase().contains("DROP")
                    || telefono.toUpperCase().contains("DELETE")) {
                return 7;
            } else if (!validarCaracteres(email, 8)) {
                return 8;
            } else {
                return 0;
            }
        }

        public void setBordes(JTextField campo, JComboBox select) {
            label_alert.setText("");
            txt_id.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
            txt_primer_nombre.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
            txt_segundo_nombre.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
            txt_primer_apellido.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
            txt_segundo_appelido.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
            txt_direccion.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
            txt_telefono.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
            txt_email.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));

            if (campo != null) {
                campo.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
                campo.requestFocus();
            } else if (select != null) {
                select.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
            }
        }

        public boolean validarCaracteres(String contenido_campo, int campo) {
            if (campo == 8) {
                String PATTERN_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
                Pattern pattern = Pattern.compile(PATTERN_EMAIL);
                Matcher matcher = pattern.matcher(contenido_campo);
                return matcher.matches();
            } else {
                return false;
            }
        }

        public class ChangeListener implements KeyListener {

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

        public class Actionlistenr implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(btn_cancelar)) {
                    setVisible(false);
                    removerPaneles();
                    mostrarPanelAsistente(accion, null, true);
                }else if (e.getSource().equals(btn_imprimir)) {
                    
                    if(btn_diploma.isSelected())
                    {
                        btn_escarapela.setSelected(false);
                        String nombre_evento = eventosBox.getSelectedItem().toString().split(" - ")[0];
                        GenerarPdf gp= new GenerarPdf(datos_pdf, nombre_evento);
                        gp.imprimirPdf();
                    
                    }else if(btn_escarapela.isSelected())
                    {
                        btn_diploma.setSelected(false);
                        String nombre_evento = eventosBox.getSelectedItem().toString().split(" - ")[0];
                        GenerarPdf gp= new GenerarPdf(datos_pdf, nombre_evento);
                        gp.imprimirPdf();
                    
                    }
                    else {
                        btn_diploma.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
                        btn_escarapela.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
                        label_alert.setText("<html><font color='red' size='4'>!!! Selecciona una opción!!!</font></html>");
                        btn_diploma.requestFocus();
                        btn_escarapela.requestFocus();
                    
                    
                    }
                    
                    
                 
                }
                else if (e.getSource().equals(btn_diploma)) {
                         
                            btn_escarapela.setSelected(false);
                         }
                
                 else if (e.getSource().equals(btn_escarapela)) {
                         
                            btn_diploma.setSelected(false);
                         }
                
                
                
                else if (e.getSource().equals(btn_guardar)) {
                    
                    if(btn_diploma.isSelected())
                    {
                        
                        String nombre_evento = eventosBox.getSelectedItem().toString().split(" - ")[1];
                        GenerarPdf gp= new GenerarPdf(datos_pdf, nombre_evento);
                        gp.generarPdf();
                    
                    }else if(btn_escarapela.isSelected())
                    {
                        
                        String nombre_evento = eventosBox.getSelectedItem().toString().split(" - ")[1];
                        GenerarPdf gp= new GenerarPdf(datos_pdf, nombre_evento);
                        gp.generarPdf();
                    
                    }
                    else {
                        btn_diploma.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
                        btn_escarapela.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
                        label_alert.setText("<html><font color='red' size='4'>!!! Selecciona una opción!!!</font></html>");
                        btn_diploma.requestFocus();
                        btn_escarapela.requestFocus();
                    
                    }
                    
                    
                    
                    
                   
                  
                    
                
                }
                
                
                else if (e.getSource().equals(btn_enviar)) {
                    crearAsistente();
                }
            }
        }
    }//Fin class TestPanelCreateUsuario 

    /**
     * InnerClase que contiene el panel con el formulario para buscar un
     * asistente
     */
    private class PanelBuscarFiltrarPagarRegistrar extends JPanel {

        JLabel label_buscar;
        JLabel label_alerta;
        JTextField txt_buscar;
        JButton btn_confirmar;
        JButton btn_buscar;
        JButton btn_cancelar;
        JButton btn_imprimir_diploma;
        String id_asistente;
        Keylistener keylistener; //Objeto para menjar evento de teclado en este panel
        Actionlistener actionlistener; //Objeto para menjar evento de mouse en este panel
        String accion;
        String empleado_id;
        String[] costos;
        JRadioButton radio_estado;
        JRadioButton radio_cupos;

        /*
            Para filtrar 
         */
        JLabel label_seleccion_operacion;
        JLabel label_eventos_id;
        JLabel label_metodos_pago;
        JLabel label_valor;
        JTextField txt_valor;
        JComboBox comb_eventos_id;
        JComboBox comb_metodos_pago;
        JComboBox comb_seleccion_operacion;
        JDateChooser fecha_inicial;
        JDateChooser fecha_final;

        public PanelBuscarFiltrarPagarRegistrar(String accion) {
            javax.swing.JLabel titulo = new javax.swing.JLabel();
            titulo.setText(accion); 
            titulo.setBounds (0,0,250,50);
            titulo.setFont(new java.awt.Font("Arial 18 Plain", Font.PLAIN, 18)); // NOI18N
            titulo.setForeground(new java.awt.Color(254, 254, 254));
            titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            this.add(titulo); 
            
            setTitle(accion);                        // colocamos titulo a la ventana
            setSize(420, 550);                                 // colocamos tamanio a la ventana (ancho, alto)
            setLocationRelativeTo(null);                       // centramos la ventana en la pantalla
            setLayout(null);                                   // no usamos ningun layout, solo asi podremos dar posiciones a los componentes
            setResizable(false);                               // hacemos que la ventana no sea redimiensionable
            Border lowerEtched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
            TitledBorder title = BorderFactory.createTitledBorder(lowerEtched, accion);
            Font titleFont = UIManager.getFont("TitledBorder.font");
            title.setTitleFont(titleFont.deriveFont(Font.ITALIC + Font.BOLD, 20));
            setBorder(title);
            this.accion = accion;
            empleado_id = datos_empleado[0];
            if (accion.equals("Listar Asistentes")) {
                configurarComponentesFiltrar();
            } else if (accion.equals("Modificar Asistente")) {
                configurarComponentesModificar();
            } else if (accion.equals("Registrar Asistente Evento")) {
                configurarComponentesAsistenteEvento();
            } else if (accion.equals("Pagos")) {
                configurarComponentesPagoEvento();
            } else if (accion.equals("Eventos")) {
                configurarComponentesListarEventosCampo();
            } else if (accion.equals("Imprimir Diplomas")) {
                configurarComponentesModificar();
            }
        }

        public void configurarComponentesModificar() {
            label_alerta = new JLabel("<html><font color='red'></font></html>", SwingConstants.CENTER);
            label_buscar = new JLabel("Identificación: ");
            txt_buscar = new JTextField();
            btn_buscar = new JButton("Buscar");
            btn_cancelar = new JButton("Cancelar");
            keylistener = new Keylistener();
            actionlistener = new Actionlistener();

            label_buscar.setBounds(10, 50, 100, 35);
            label_buscar.setFont(new java.awt.Font("Arial 18 Plain", Font.PLAIN, 12)); // NOI18N
            label_buscar.setForeground(new java.awt.Color(254, 254, 254));

            txt_buscar.setBounds(125, 50, 300, 35);
            
            
            btn_buscar.setBounds(125, 90, 145, 35);
          //  label_eventos_id.setFont(new java.awt.Font("Arial 18 Plain", Font.PLAIN, 12)); // NOI18N
         //   label_eventos_id.setForeground(new java.awt.Color(254, 254, 254));

            btn_cancelar.setBounds(280, 90, 145, 35);
            label_alerta.setBounds(10, 130, 400, 32);
            label_alerta.setFont(new java.awt.Font("Arial 18 Plain", Font.PLAIN, 12)); // NOI18N
            label_alerta.setForeground(new java.awt.Color(254, 254, 254));

            
            txt_buscar.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));

            btn_buscar.addActionListener(actionlistener);
            btn_cancelar.addActionListener(actionlistener);
            txt_buscar.addKeyListener(keylistener);

            add(label_buscar);
            add(label_alerta);
            add(txt_buscar);
            add(btn_buscar);
            add(btn_cancelar);
        }

        public void configurarComponentesFiltrar() {
            label_alerta = new JLabel("<html><font color='red'></font></html>", SwingConstants.CENTER);
            label_eventos_id = new JLabel("Evento: ", SwingConstants.RIGHT);
            comb_eventos_id = new JComboBox();
            btn_buscar = new JButton("Buscar");
            btn_cancelar = new JButton("Cancelar");
            keylistener = new Keylistener();
            actionlistener = new Actionlistener();

            label_eventos_id.setBounds(10, 50, 100, 35);
            label_eventos_id.setFont(new java.awt.Font("Arial 18 Plain", Font.PLAIN, 12)); // NOI18N
            label_eventos_id.setForeground(new java.awt.Color(254, 254, 254));

            comb_eventos_id.setBounds(125, 50, 300, 35);
            
            btn_buscar.setBounds(125, 87, 145, 35);
            
            btn_cancelar.setBounds(280, 87, 145, 35);
           
            label_alerta.setBounds(10, 135, 400, 32);
            label_alerta.setFont(new java.awt.Font("Arial 18 Plain", Font.PLAIN, 12)); // NOI18N
            label_alerta.setForeground(new java.awt.Color(254, 254, 254));

            comb_eventos_id.addActionListener(actionlistener);
            btn_buscar.addActionListener(actionlistener);
            btn_cancelar.addActionListener(actionlistener);

            add(label_alerta);
            add(label_eventos_id);
            add(comb_eventos_id);
            add(btn_buscar);
            add(btn_cancelar);
        }

        public void configurarComponentesAsistenteEvento() {
            label_alerta = new JLabel("<html><font color='red'></font></html>", SwingConstants.CENTER);
            label_buscar = new JLabel("Identificación: ");
            txt_buscar = new JTextField();
            label_eventos_id = new JLabel("Evento");
            comb_eventos_id = new JComboBox();
            btn_buscar = new JButton("Buscar");
            btn_cancelar = new JButton("Cancelar");
            btn_confirmar = new JButton("Enviar");
            keylistener = new Keylistener();
            actionlistener = new Actionlistener();

            label_buscar.setBounds(10, 50, 100, 35);
            label_buscar.setFont(new java.awt.Font("Arial 18 Plain", Font.PLAIN, 12)); // NOI18N
            label_buscar.setForeground(new java.awt.Color(254, 254, 254));

            
            txt_buscar.setBounds(125, 50, 300, 35);
            btn_buscar.setBounds(125, 90, 145, 35);
            btn_cancelar.setBounds(280, 90, 145, 35);
            label_alerta.setBounds(10, 130, 400, 32);
            label_alerta.setFont(new java.awt.Font("Arial 18 Plain", Font.PLAIN, 12)); // NOI18N
            label_alerta.setForeground(new java.awt.Color(254, 254, 254));

            txt_buscar.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
            btn_buscar.addActionListener(actionlistener);
            btn_cancelar.addActionListener(actionlistener);
            btn_confirmar.addActionListener(actionlistener);
            txt_buscar.addKeyListener(keylistener);
            comb_eventos_id.addActionListener(actionlistener);

            add(label_buscar);
            add(label_alerta);
            add(btn_confirmar);
            add(txt_buscar);
            add(label_eventos_id);
            add(comb_eventos_id);
            add(btn_buscar);
            add(btn_cancelar);
        }

        public void configurarComponentesPagoEvento() {
            label_alerta = new JLabel("<html><font color='red'></font></html>", SwingConstants.CENTER);
             label_alerta.setFont(new java.awt.Font("Arial 18 Plain", Font.PLAIN, 12)); // NOI18N
            label_alerta.setForeground(new java.awt.Color(254, 254, 254));

            label_buscar = new JLabel("Identificación: ", SwingConstants.RIGHT);
             label_buscar.setFont(new java.awt.Font("Arial 18 Plain", Font.PLAIN, 12)); // NOI18N
            label_buscar.setForeground(new java.awt.Color(254, 254, 254));

            txt_buscar = new JTextField();
            label_eventos_id = new JLabel("Evento", SwingConstants.RIGHT);
            label_eventos_id.setFont(new java.awt.Font("Arial 18 Plain", Font.PLAIN, 12)); // NOI18N
            label_eventos_id.setForeground(new java.awt.Color(254, 254, 254));

            label_valor = new JLabel("Total: ", SwingConstants.RIGHT);
            label_valor.setFont(new java.awt.Font("Arial 18 Plain", Font.PLAIN, 12)); // NOI18N
            label_valor.setForeground(new java.awt.Color(254, 254, 254));

            
            txt_valor = new JTextField("0");
            txt_valor.setEditable(false);
            label_metodos_pago = new JLabel("Metodo Pago", SwingConstants.RIGHT);
            label_metodos_pago.setFont(new java.awt.Font("Arial 18 Plain", Font.PLAIN, 12)); // NOI18N
            label_metodos_pago.setForeground(new java.awt.Color(254, 254, 254));

            comb_eventos_id = new JComboBox();
            label_seleccion_operacion = new JLabel("Operación", SwingConstants.RIGHT);
            label_seleccion_operacion.setFont(new java.awt.Font("Arial 18 Plain", Font.PLAIN, 12)); // NOI18N
            label_seleccion_operacion.setForeground(new java.awt.Color(254, 254, 254));

            comb_seleccion_operacion = new JComboBox();
            String[] opciones = {"Seleccione", "Tarjeta Crédito", "Efectivo", "Cheque"};
            comb_metodos_pago = new JComboBox(opciones);
            comb_metodos_pago.setEnabled(false);
            btn_buscar = new JButton("Buscar");
            btn_cancelar = new JButton("Cancelar");
            btn_confirmar = new JButton("Enviar");
            keylistener = new Keylistener();
            actionlistener = new Actionlistener();
            label_seleccion_operacion.setBounds(10, 50, 100, 35);
            label_seleccion_operacion.setFont(new java.awt.Font("Arial 18 Plain", Font.PLAIN, 12)); // NOI18N
            label_seleccion_operacion.setForeground(new java.awt.Color(254, 254, 254));

            comb_seleccion_operacion.setBounds(125, 50, 300, 35);
            label_buscar.setBounds(10, 90, 100, 35);
            label_buscar.setFont(new java.awt.Font("Arial 18 Plain", Font.PLAIN, 12)); // NOI18N
            label_buscar.setForeground(new java.awt.Color(254, 254, 254));

            txt_buscar.setBounds(125, 90, 300, 35);
            btn_buscar.setBounds(125, 130, 145, 35);
            btn_cancelar.setBounds(280, 130, 145, 35);
            label_alerta.setBounds(10, 170, 400, 32);
            label_alerta.setFont(new java.awt.Font("Arial 18 Plain", Font.PLAIN, 12)); // NOI18N
            label_alerta.setForeground(new java.awt.Color(254, 254, 254));

            txt_buscar.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));

            btn_buscar.addActionListener(actionlistener);
            btn_cancelar.addActionListener(actionlistener);
            btn_confirmar.addActionListener(actionlistener);
            txt_buscar.addKeyListener(keylistener);
            comb_eventos_id.addActionListener(actionlistener);
            comb_metodos_pago.addActionListener(actionlistener);
            comb_seleccion_operacion.addActionListener(actionlistener);

            add(label_buscar);
            add(label_alerta);
            add(label_metodos_pago);
            add(label_valor);
            add(btn_confirmar);
            add(label_seleccion_operacion);
            add(comb_seleccion_operacion);
            add(txt_buscar);
            add(txt_valor);
            add(label_eventos_id);
            add(comb_eventos_id);
            add(comb_metodos_pago);
            add(btn_buscar);
            add(btn_cancelar);
        }

        public void configurarComponentesListarEventosCampo() {
            label_alerta = new JLabel("<html><font color='red'></font></html>", SwingConstants.CENTER);
            radio_estado = new JRadioButton("Estado");
            radio_cupos = new JRadioButton("Cupos");
            label_eventos_id = new JLabel("Opcion", SwingConstants.RIGHT);
            comb_eventos_id = new JComboBox(); //Usar este para filtrar estados
            btn_buscar = new JButton("Buscar");
            btn_cancelar = new JButton("Cancelar");
            keylistener = new Keylistener();
            actionlistener = new Actionlistener();
            label_metodos_pago = new JLabel("Fecha Inicial", SwingConstants.RIGHT);
            fecha_inicial = new JDateChooser();
            fecha_inicial.getJCalendar().setPreferredSize(new Dimension(500, 200));
            fecha_inicial.setFont(new Font("Dialog", Font.PLAIN, 10));
            label_seleccion_operacion = new JLabel("Fecha Final", SwingConstants.RIGHT);
            
            fecha_final = new JDateChooser();
            fecha_final.getJCalendar().setPreferredSize(new Dimension(500, 200));
            fecha_final.setFont(new Font("Dialog", Font.PLAIN, 10));
            radio_estado.setBounds(200, 50, 100, 25);
            radio_cupos.setBounds(300, 50, 100, 25);
            label_eventos_id.setBounds(10, 80, 100, 35);
            label_eventos_id.setFont(new java.awt.Font("Arial 18 Plain", Font.PLAIN, 12)); // NOI18N
            label_eventos_id.setForeground(new java.awt.Color(254, 254, 254));

            comb_eventos_id.setBounds(125, 80, 400, 35);
            label_metodos_pago.setBounds(10, 120, 100, 35);
            label_metodos_pago.setFont(new java.awt.Font("Arial 18 Plain", Font.PLAIN, 12)); // NOI18N
            label_metodos_pago.setForeground(new java.awt.Color(254, 254, 254));

            fecha_inicial.setBounds(125, 120, 400, 35);
            label_seleccion_operacion.setBounds(10, 160, 100, 35);
            label_seleccion_operacion.setFont(new java.awt.Font("Arial 18 Plain", Font.PLAIN, 12)); // NOI18N
            label_seleccion_operacion.setForeground(new java.awt.Color(254, 254, 254));

            fecha_final.setBounds(125, 160, 400, 35);
            btn_buscar.setBounds(125, 200, 145, 35);
            btn_cancelar.setBounds(280, 200, 170, 35);
            label_alerta.setBounds(10, 240, 400, 32);
            label_alerta.setFont(new java.awt.Font("Arial 18 Plain", Font.PLAIN, 12)); // NOI18N
            label_alerta.setForeground(new java.awt.Color(254, 254, 254));


            comb_eventos_id.addActionListener(actionlistener);
            btn_buscar.addActionListener(actionlistener);
            btn_cancelar.addActionListener(actionlistener);
            radio_estado.addActionListener(actionlistener);
            radio_cupos.addActionListener(actionlistener);
            fecha_inicial.getDateEditor().addPropertyChangeListener(
                    new PropertyChangeListener() {
                @Override
                public void propertyChange(PropertyChangeEvent evt) {
                    label_alerta.setText("");
                    fecha_inicial.setBorder(null);
                    fecha_final.setBorder(null);
                }
            });

            fecha_final.getDateEditor().addPropertyChangeListener(
                    new PropertyChangeListener() {
                @Override
                public void propertyChange(PropertyChangeEvent evt) {
                    label_alerta.setText("");
                    fecha_inicial.setBorder(null);
                    fecha_final.setBorder(null);
                }
            });

            comb_eventos_id.setEnabled(false);
            fecha_inicial.setEnabled(false);
            fecha_final.setEnabled(false);

            add(label_alerta);
            add(radio_estado);
            add(radio_cupos);
            add(label_eventos_id);
            add(comb_eventos_id);
            add(label_metodos_pago);
            add(fecha_inicial);
            add(label_seleccion_operacion);
            add(fecha_final);
            add(btn_buscar);
            add(btn_cancelar);
        }

        public void setComboBox(String opcion, String[] eventos_id_nombre) {
            if (opcion.equals("Listar Asistentes") || opcion.equals("Registrar Asistente Evento")) {
                ControladorEvento controladorEvento = new ControladorEvento();
                eventos_id_nombre = controladorEvento.listarIdNombreEventos();
                comb_eventos_id.addItem("Seleccione");
                for (int i = 0; i < eventos_id_nombre.length; i++) {
                    
                    comb_eventos_id.addItem(eventos_id_nombre[i].split(";;;")[0]+" - "+eventos_id_nombre[i].split(";;;")[2]);
                }
                comb_eventos_id.setSelectedIndex(0);
            } else if (opcion.equals("Pagos")) {
                comb_eventos_id.addItem("Seleccione");
                costos = new String[eventos_id_nombre.length];
                for (int i = 0; i < eventos_id_nombre.length; i++) {
                    String[] temp = eventos_id_nombre[i].split(" - ");
                    comb_eventos_id.addItem(temp[0] + " - " + temp[1]);
                    costos[i] = temp[2];
                }
                comb_eventos_id.setSelectedIndex(0);
            } else if (opcion.equals("Pagos Inicial")) {
                comb_seleccion_operacion.addItem("Seleccione");
                comb_seleccion_operacion.addItem("Registrar pago a evento");
                comb_seleccion_operacion.addItem("Estados de pago de asistente");
            } else if (opcion.equals("Eventos")) {
                comb_eventos_id.addItem("Seleccione");
                comb_eventos_id.addItem("Activo");
                comb_eventos_id.addItem("Finalizado");
                comb_eventos_id.addItem("Cancelado");
            }
        }

        public void buscarAsistente(String id_asistente) {
            if (accion.equals("Modificar Asistente")) {
                ControladorAsistente controladorAsistente = new ControladorAsistente();
                if (controladorAsistente.consultarAsistente(id_asistente)) {
                    removerPaneles();
                    String[] datos_asistente = controladorAsistente.extraerAsistente(id_asistente);
                    mostrarPanelAsistente(accion, datos_asistente, true);
                    this.repaint();
                } else {
                    txt_buscar.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
                    label_alerta.setText("<html><font color='red' size='4'>!!! No Existe el asistente con esa identificación!!!</font></html>");
                    txt_buscar.requestFocus();
                }
            } else if (accion.equals("Imprimir Diplomas")) {
                ControladorAsistente controladorAsistente = new ControladorAsistente();
                ControladorEvento controladorEvento = new ControladorEvento();
                String[] datos_asistente_evento = controladorEvento.listarIdNombreEventosAsistentePago(id_asistente);
               
                if(datos_asistente_evento.length == 0){
                    txt_buscar.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
                    label_alerta.setText("<html><font color='red' size='4'>!!! El usuario no está inscrito a ningún evento!!!</font></html>");
                    txt_buscar.requestFocus();
                
                }
                else if (controladorAsistente.consultarAsistente(id_asistente)) {
                    removerPaneles();
                    String[] datos_asistente = controladorAsistente.extraerAsistente(id_asistente);
                    mostrarPanelAsistente(accion, datos_asistente, true);
                    this.repaint();
                    
                }
                
                
                else {
                    txt_buscar.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
                    label_alerta.setText("<html><font color='red' size='4'>!!! No Existe el asistente con esa identificación!!!</font></html>");
                    txt_buscar.requestFocus();
                }
            } else if (accion.equals("Registrar Asistente Evento")) {
                ControladorAsistente controladorAsistente = new ControladorAsistente();
                if (controladorAsistente.consultarAsistente(id_asistente)) {
                    modificarPanelRegistro();
                } else {
                    txt_buscar.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
                    label_alerta.setText("<html><font color='red' size='4'>!!! No Existe el asistente con esa identificación!!!</font></html>");
                    txt_buscar.requestFocus();
                }
            } else if (accion.equals("Pagos")) {
                ControladorAsistente controladorAsistente = new ControladorAsistente();
                if (controladorAsistente.consultarAsistente(id_asistente)) {
                    modificarPanelPago();
                } else {
                    txt_buscar.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
                    label_alerta.setText("<html><font color='red' size='4'>!!! No Existe el asistente con esa identificación!!!</font></html>");
                    txt_buscar.requestFocus();
                }
            }
        }

        public boolean buscarFiltrarAsistentes() {
            if (accion.equals("Listar Asistentes")) {
                String[] alertas = {"Debe seleccionar una opción en el campo Evento"};
                if (comb_eventos_id.getSelectedIndex() != 0) {
                    return true;
                } else {
                    if (comb_eventos_id.getSelectedIndex() == 0) {
                        comb_eventos_id.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
                        label_alerta.setText("<html><font color='red' size='4'>!!!" + alertas[0] + "!!!</font></html>");
                        txt_buscar.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
                        txt_buscar.requestFocus();
                        return false;
                    }
                }
            } else if (accion.equals("Registrar Asistente Evento")) {
                id_asistente = txt_buscar.getText();
                if (id_asistente.equals("") || !Pattern.matches("[0-9]+", id_asistente)) {
                    txt_buscar.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
                    label_alerta.setText("<html><font color='red' size='4'>!!! Ingrese un número de identificación Correcto !!!</font></html>");
                    txt_buscar.requestFocus();
                    return false;
                } else {
                    return true;
                }
            } else if (accion.equals("Pagos")) {
                id_asistente = txt_buscar.getText();
                if (comb_seleccion_operacion.getSelectedIndex() == 0) {
                    comb_seleccion_operacion.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
                    label_alerta.setText("<html><font color='red' size='4'>!!! Seleccione una operación de la lista !!!</font></html>");
                    comb_seleccion_operacion.requestFocus();
                    return false;
                } else if (id_asistente.equals("") || !Pattern.matches("[0-9]+", id_asistente)) {
                    txt_buscar.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
                    label_alerta.setText("<html><font color='red' size='4'>!!! Ingrese un número de identificación Correcto !!!</font></html>");
                    txt_buscar.requestFocus();
                    return false;
                } else {
                    return true;
                }
            } else if (accion.equals("Modificar Asistente")) {
                id_asistente = txt_buscar.getText();
                if (id_asistente.equals("") || !Pattern.matches("[0-9]+", id_asistente)) {
                    txt_buscar.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
                    label_alerta.setText("<html><font color='red' size='4'>!!! Ingrese un número de identificación Correcto !!!</font></html>");
                    txt_buscar.requestFocus();
                    return false;
                } else {
                    return true;
                }
            } else if (accion.equals("Imprimir Diplomas")) {
                id_asistente = txt_buscar.getText();
                if (id_asistente.equals("") || !Pattern.matches("[0-9]+", id_asistente)) {
                    txt_buscar.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
                    label_alerta.setText("<html><font color='red' size='4'>!!! Ingrese un número de identificación Correcto !!!</font></html>");
                    txt_buscar.requestFocus();
                    return false;
                } 
                
                
                else {
                    return true;
                }
            } else if (accion.equals("Eventos")) {
                if (radio_cupos.isSelected()) {
                    if (fecha_inicial.getDate() == null) {
                        fecha_inicial.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
                        label_alerta.setText("<html><font color='red' size='4'>!!! Seleccione la fecha inicial !!!</font></html>");
                        return false;
                    } else if (fecha_final.getDate() == null) {
                        fecha_final.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
                        label_alerta.setText("<html><font color='red' size='4'>!!! Seleccione la fecha final !!!</font></html>");
                        return false;
                    } else {
                        return true;
                    }
                } else if (radio_estado.isSelected()) {
                    if (comb_eventos_id.getSelectedIndex() == 0) {
                        comb_eventos_id.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
                        label_alerta.setText("<html><font color='red' size='4'>!!! Seleccione una opción !!!</font></html>");
                        return false;
                    } else if (fecha_inicial.getDate() == null) {
                        fecha_inicial.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
                        label_alerta.setText("<html><font color='red' size='4'>!!! Seleccione la fecha inicial !!!</font></html>");
                        return false;
                    } else if (fecha_final.getDate() == null) {
                        fecha_final.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
                        label_alerta.setText("<html><font color='red' size='4'>!!! Seleccione la fecha final !!!</font></html>");
                        return false;
                    } else {
                        return true;
                    }
                } else {
                    label_alerta.setText("<html><font color='red' size='4'>!!! Seleccione los campos !!!</font></html>");
                    return false;
                }
            }
            return false;
        }

        public void modificarPanelRegistro() {
            txt_buscar.setEnabled(false);
            label_eventos_id.setBounds(10, 90, 100, 35);
            comb_eventos_id.setBounds(125, 90, 300, 35);
            remove(btn_buscar);
            btn_confirmar.setBounds(125, 130, 145, 35);
            btn_cancelar.setBounds(280, 130, 145, 35);
            label_alerta.setBounds(10, 170, 400, 25);
            comb_eventos_id.removeAllItems();
            setComboBox("Registrar Asistente Evento", null);
        }

        public void modificarPanelPago() {
            ControladorEvento controladorEvento = new ControladorEvento();
            String[] eventos_id = controladorEvento.listarIdNombreEventosAsistente(txt_buscar.getText());
            if (eventos_id.length > 0) {
                txt_buscar.setEnabled(false);
                label_eventos_id.setBounds(10, 130, 100, 35);
                comb_eventos_id.setBounds(125, 130, 300, 35);
                label_metodos_pago.setBounds(10, 170, 100, 35);
                comb_metodos_pago.setBounds(125, 170, 300, 35);
                label_valor.setBounds(10, 210, 100, 35);
                txt_valor.setBounds(125, 210, 300, 35);
                remove(btn_buscar);
                btn_confirmar.setBounds(125, 250, 145, 35);
                btn_cancelar.setBounds(280, 250, 145, 35);
                label_alerta.setBounds(10, 290, 400, 25);
                comb_eventos_id.removeAllItems();
                setComboBox("Pagos", eventos_id);
            } else {
                txt_buscar.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
                label_alerta.setText("<html><font color='green' size='4'>!!! No Existe el asistente no tiene pagos pendientes!!!</font></html>");
                txt_buscar.requestFocus();
            }

        }

        /*Clase exclusiva para manejar eventos del teclado en este panel*/
        public class Keylistener implements KeyListener {

            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getSource().equals(txt_buscar)) {
                    label_alerta.setText("");
                    txt_buscar.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));
                    if (accion.equals("Pagos")) {
                        comb_seleccion_operacion.setBorder(null);
                    }
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
        public class Actionlistener implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource().equals(btn_buscar)) {

                    if (buscarFiltrarAsistentes()) { //Verificar campos y procesar operacion
                        if (accion.equals("Listar Asistentes")) {
                            removerPaneles();
                            mostrarPanelListar(accion, Integer.parseInt(comb_eventos_id.getSelectedItem().toString().split(" - ")[0]), null);                                                   
                        }else if(accion.equals("Eventos")){
                            removerPaneles();
                            String fecha1 = new SimpleDateFormat("yyyy-MM-dd").format(fecha_inicial.getDate());
                            String fecha2 = new SimpleDateFormat("yyyy-MM-dd").format(fecha_final.getDate());
                            if (radio_estado.isSelected()) {
                                mostrarPanelListarEventosCampos(accion, "estado", comb_eventos_id.getSelectedItem().toString(), fecha1, fecha2);

                            } else if (radio_cupos.isSelected()) {
                                mostrarPanelListarEventosCampos(accion, "cupos", "", fecha1, fecha2);

                            }

                        } else {
                            if (accion.equals("Pagos")) {
                                if (comb_seleccion_operacion.getSelectedIndex() == 1) {
                                    buscarAsistente(id_asistente); //buscar asistente para realizar pago
                                } else if (comb_seleccion_operacion.getSelectedIndex() == 2) {
                                    removerPaneles();
                                    mostrarPanelListar("Estado Pagos Asistente", 0, id_asistente);
                                }
                            } else {
                                buscarAsistente(id_asistente);
                            }
                        }

                    }
                } else if (e.getSource().equals(btn_confirmar)) {
                    if (accion.equals("Registrar Asistente Evento")) {
                        if (comb_eventos_id.getSelectedIndex() > 0) {
                            String asistente_id = txt_buscar.getText();
                            int evento_id = Integer.parseInt(comb_eventos_id.getSelectedItem().toString().split(" - ")[0]);
                            ControladorEvento controladorEvento = new ControladorEvento();
                            if(controladorEvento.getCuposEvento(evento_id)>=1){
                                int filas_gurdadas = controladorEvento.inscribirAsistenteEvento(empleado_id, evento_id, asistente_id, datos_empleado[1]);
                                if (filas_gurdadas != -1) {
                                    controladorEvento.descontarCupoEvento(evento_id);
                                    String msg_confirmacion = "Se inscribió correctamente!";
                                    int res = JOptionPane.showOptionDialog(null, msg_confirmacion, "Test", JOptionPane.DEFAULT_OPTION,
                                            JOptionPane.INFORMATION_MESSAGE, null, null, null);
                                    if (res != -1) {
                                        setVisible(false);
                                        removerPaneles();
                                        mostrarPanelBuscarFiltarPagarInscribir(accion, true);
                                        if (accion.equals("Listar Asistentes")) {
                                            panelBuscarFiltrarPagarRegistrar.setComboBox(accion, null);
                                        }
                                    }
                                } else {
                                    label_alerta.setText("<html><font color='red' size='4'>!!! El usurio ya está registrado a este evento !!!</font></html>");
                                }
                            }else{
                                label_alerta.setText("<html><font color='red' size='4'>!!! Este evento ya no tiene Cupos !!!</font></html>");
                            }
                        } else {
                            label_alerta.setText("<html><font color='red' size='4'>!!! Seleccione un evento de la lista !!!</font></html>");
                        }
                    } else if (accion.equals("Pagos")) {
                        if (comb_eventos_id.getSelectedIndex() > 0 && comb_metodos_pago.getSelectedIndex() > 0) {
                            int evento_id = Integer.parseInt(comb_eventos_id.getSelectedItem().toString().split(" - ")[0]);
                            String asistente_id = txt_buscar.getText();
                            String metodo_pago = comb_metodos_pago.getSelectedItem().toString();
                            ControladorEvento controladorEvento = new ControladorEvento();
                            int filas_actualizadas = controladorEvento.registrarPagoEvento(evento_id, asistente_id, metodo_pago, datos_empleado[0]);
                            if (filas_actualizadas != -1) {
                                String msg_confirmacion = "Comfirmar pago a evento!";
                                int res = JOptionPane.showOptionDialog(null, msg_confirmacion, "Test", JOptionPane.DEFAULT_OPTION,
                                        JOptionPane.INFORMATION_MESSAGE, null, null, null);
                                if (res != -1) {
                                    setVisible(false);
                                    removerPaneles();
                                    mostrarPanelBuscarFiltarPagarInscribir(accion, true);
                                    panelBuscarFiltrarPagarRegistrar.setComboBox("Pagos Inicial", null);
                                }
                            } else {
                                label_alerta.setText("<html><font color='red' size='4'>!!! El usurio ya está registrado a este evento !!!</font></html>");
                            }
                        } else {
                            if (comb_eventos_id.getSelectedIndex() <= 0) {
                                label_alerta.setText("<html><font color='red' size='4'>!!! Seleccione un evento de la lista !!!</font></html>");
                            } else {
                                label_alerta.setText("<html><font color='red' size='4'>!!! Seleccione un metodo de pago de la lista !!!</font></html>");
                            }
                        }
                    }
                } else if (e.getSource().equals(btn_cancelar)) {
                    setVisible(false);
                    removerPaneles();
                    mostrarPanelBuscarFiltarPagarInscribir(accion, true);
                    if (accion.equals("Listar Asistentes")) {
                        panelBuscarFiltrarPagarRegistrar.setComboBox(accion, null);
                    } else if (accion.equals("Pagos")) {
                        panelBuscarFiltrarPagarRegistrar.setComboBox("Pagos Inicial", null);
                    }
                } else if (e.getSource().equals(comb_eventos_id)) {
                    if (accion.equals("Pagos")) {
                        if (comb_eventos_id.getSelectedIndex() > 0) {
                            txt_valor.setText(costos[comb_eventos_id.getSelectedIndex() - 1]);
                        }
                        comb_metodos_pago.setEnabled(true);
                        comb_metodos_pago.setSelectedIndex(0);
                    }
                    label_alerta.setText("");
                    comb_eventos_id.setBorder(null);
                } else if (e.getSource().equals(comb_metodos_pago)) {
                    label_alerta.setText("");
                    comb_eventos_id.setBorder(null);
                } else if (e.getSource().equals(comb_seleccion_operacion)) {
                    label_alerta.setText("");
                    comb_seleccion_operacion.setBorder(null);
                    txt_buscar.setBorder(null);
                } else if (e.getSource().equals(radio_estado)) {
                    radio_cupos.setSelected(false);
                    comb_eventos_id.setEnabled(true);
                    fecha_inicial.setEnabled(true);
                    fecha_final.setEnabled(true);
                } else if (e.getSource().equals(radio_cupos)) {
                    radio_estado.setSelected(false);
                    comb_eventos_id.setEnabled(false);
                    fecha_inicial.setEnabled(true);
                    fecha_final.setEnabled(true);
                }
            }

        }
    }//Fin PanelBuscarFiltrar

    /**
     * InnerClass que muestra el listado de asistentes que asisten a los eventos
     * o alguno en específico
     */
    public class PanelListarAsistentes extends JPanel {

        JTable tabla;
        JScrollPane sp_tabla;
        DefaultTableModel dtm;
        ControladorAsistente controladorAsistente;
        ControladorEvento controladorEvento;

        public PanelListarAsistentes() {

        }

        public PanelListarAsistentes(String operacion, int evento_id) {
            iniciarComponentes(operacion);
            this.setBackground(new java.awt.Color (35,47,65));
            configurarLLenarTablaAsistentesPorEvento(evento_id);
        }

        public PanelListarAsistentes(String operacion, String asistente_id) {
            iniciarComponentes(operacion);
            this.setBackground(new java.awt.Color (35,47,65));
            configurarLLenarTablaEstadoPago(asistente_id);
        }

        public PanelListarAsistentes(String operacion, String campo, String contenido, String fecha1, String fecha2) {
            iniciarComponentes(operacion);
            this.setBackground(new java.awt.Color (35,47,65));
            configurarLLenarTablaEventosCampo(campo, contenido, fecha1, fecha2);
        }

        public void iniciarComponentes(String operacion) {
            setTitle(operacion);                        // colocamos titulo a la ventana
            setSize(420, 550);                                 // colocamos tamanio a la ventana (ancho, alto)
            setLocationRelativeTo(null);                       // centramos la ventana en la pantalla
            setLayout(null);                                   // no usamos ningun layout, solo asi podremos dar posiciones a los componentes
            setResizable(false);                               // hacemos que la ventana no sea redimiensionable
            Border lowerEtched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
            TitledBorder title = BorderFactory.createTitledBorder(lowerEtched, operacion);
            Font titleFont = UIManager.getFont("TitledBorder.font");
            title.setTitleFont(titleFont.deriveFont(Font.ITALIC + Font.BOLD, 20));
            setBorder(title);
        }

        public void configurarLLenarTablaAsistentesPorEvento(int evento_id) {
            String[] titulo = new String[]{"Id", "Nombre", "telefono", "pago", "Evento"};
            controladorEvento = new ControladorEvento();
            String[] ids = controladorEvento.listarIdsEvento(evento_id);
            controladorAsistente = new ControladorAsistente();
            String[] asistentes = controladorAsistente.listarAsistentes(ids);
            dtm = new DefaultTableModel();
            dtm.setColumnIdentifiers(titulo);
            for (int i = 0; i < asistentes.length; i++) {
                String[] fila = asistentes[i].split(";;;");
                String[] temp = {fila[0], fila[1] + " " + fila[2] + " " + fila[3] + " " + fila[4], fila[6], ids[i].split(" - ")[1], String.valueOf(evento_id)};
                dtm.addRow(temp);
            }

            tabla = new JTable(dtm);
            sp_tabla = new JScrollPane(tabla);

            sp_tabla.setBounds(10, 30, 540, 510);

            add(sp_tabla);
        }

        public void configurarLLenarTablaEstadoPago(String asistente_id) {
            String[] titulo = new String[]{"id_asistente", "id_evento", "n_evento", "estado"};
            controladorEvento = new ControladorEvento();
            String[] estados_pago = controladorEvento.estadosPagoEventos(asistente_id);
            dtm = new DefaultTableModel();
            dtm.setColumnIdentifiers(titulo);
            for (int i = 0; i < estados_pago.length; i++) {
                String[] fila = estados_pago[i].split(" - ");
                String[] temp = {fila[0], fila[1], fila[2], fila[3]};
                dtm.addRow(temp);
            }

            tabla = new JTable(dtm);
            sp_tabla = new JScrollPane(tabla);

            sp_tabla.setBounds(10, 30, 540, 510);

            add(sp_tabla);
        }

        public void configurarLLenarTablaEventosCampo(String campo, String contenido, String fecha1, String fecha2) {
            String[] titulo = new String[]{"id_evento", "nombre", campo, "fecha_inicio"};
            controladorEvento = new ControladorEvento();
            String[] estados_pago = controladorEvento.listarEventosPorCampo(campo, contenido, fecha1, fecha2);
            dtm = new DefaultTableModel();
            dtm.setColumnIdentifiers(titulo);
            for (int i = 0; i < estados_pago.length; i++) {
                String[] fila = estados_pago[i].split(" - ");
                String[] temp = {fila[0], fila[1], fila[2], fila[3]};
                dtm.addRow(temp);
            }

            tabla = new JTable(dtm);

            sp_tabla = new JScrollPane(tabla);

            sp_tabla.setBounds(10, 30, 540, 510);

            add(sp_tabla);

        }
    }//Fin panel listar usuarios

    public class GenerarPdf {
        
     
        

        private com.itextpdf.text.Font fuente
                = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.COURIER, 24, Font.BOLD);
        private String[] info_usuario;
        private String header;
        private String info; 
        private String footer;  
        private String salida;
        private String nombre_evento;
           public GenerarPdf(String[] info_asistente, String evento){
    
            info_usuario = info_asistente;
            nombre_evento = evento;
            header = "DIPLOMA";
            info = "ABC certifica que "+info_usuario[1]+" "+info_usuario[2]+" "+info_usuario[3]+" asistió al evento "+nombre_evento 
                    +" y aprobó con éxito todas las exigencias requeridas para su graduación";
            footer  = "Felicitaciones por tu logro!!";
            
    }
        
        
        public void generarPdf() {
            try {
                 JFileChooser fc = new JFileChooser(); 
                    int opcion = fc.showSaveDialog(null);
                    
                    if(opcion == fc.APPROVE_OPTION){
                        
                        Document document = new Document(PageSize.A4, 36, 36, 10, 10);
                        PdfWriter.getInstance(document, new FileOutputStream(fc.getSelectedFile()));
                        document.open();
                        document.add(getHeader(header));
                        document.add(getinfo(info));
                        document.add(getFooter(footer));
                        document.close();
                    
                    }

                
            } catch (Exception e) {
            }

        }
        
        
        public void imprimirPdf() {
            try {
                 
                        
                        Document document = new Document(PageSize.A4, 36, 36, 10, 10);
                        
                        PdfWriter.getInstance(document, new FileOutputStream("/home/alejo/hola.pdf"));
                        document.open();
                        document.add(getHeader(header));
                        document.add(getinfo(info));
                        document.add(getFooter(footer));
                        document.close();
                        File file = new File("/home/alejo/hola.pdf");
                        Desktop.getDesktop().open(file);
                    
                    

                
            } catch (Exception e) {
            }

        }
        
        
   
        public Paragraph getHeader(String texto) {

            Paragraph p = new Paragraph();
            Chunk c = new Chunk();
            p.setAlignment(Element.ALIGN_CENTER);
            p.setSpacingAfter(290);
            c.append(texto);
            c.setFont(fuente);
            p.add(c);
            return p;
        }

        public Paragraph getinfo(String texto) {

            Paragraph p = new Paragraph();
            Chunk c = new Chunk();
            p.setAlignment(Element.ALIGN_CENTER);
            p.setSpacingAfter(900);
            c.append(texto);
            c.setFont(fuente);
            p.add(c);
            return p;
        }

        public Paragraph getFooter(String texto) {

            Paragraph p = new Paragraph();
            Chunk c = new Chunk();
            p.setAlignment(Element.ALIGN_CENTER);
            c.append(texto);
            c.setFont(fuente);
            p.add(c);
            return p;
        }

    }

}
