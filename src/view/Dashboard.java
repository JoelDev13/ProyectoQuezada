package view;

import controller.AgendaDoctorController;
import controller.AgendarUnaCitaController;
import controller.CitasController;
import controller.PacienteController;
import controller.ServiciosController;
import java.awt.Color;
import java.awt.Component;
import javax.swing.ImageIcon;
import model.HistorialPago;
import model.dao.PacienteDao;
import model.dao.citas.CitasDao;
import model.dao.doctor.DoctorLigeroDAO;
import model.dao.especialidad.EspecialidadDao;
import model.dao.servicios.ServiciosDao;
import model.usuario.Usuario;
import view.component.menu.panelesEnum.Paneles;
import view.component.menu.event.EventMenu;
import static view.component.menu.panelesEnum.Paneles.METODOS_DE_PAGOS;


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

/**
 * * Este dashboard tiene la propiedad undecorated activada, por lo que no se muestran sus bordes.
 * Este es el siguiente view que viene despues del Login. Este recibe un parametro llamado ROl
 * que utilizara para llamar a un inicializador espececifico del menu.
 *
 * @author luis-
 *
 */
public class Dashboard extends javax.swing.JFrame {

    /**
     * Crea una nueva vista Dashboard usando la informacion de un usuario
     * @usuario usuario de donde se extraen los datos
     */
    
    // TODO cambiar el detalle del rol a un ENUM

    public Dashboard(Usuario usuario) { 
        initComponents();
        setBackground(new Color(0, 0, 0, 0)); // hacemos transparente la ventana
 
        /*
            La interfaz EventMenu fue creada unicamente para ser sobreescrita
            en este punto. 
        
            Si quieres agregar tu panel a los botones del Dashboard, haz que
            el case especifico de tu panel cree una vista y un controlador y los una.
            Luego que pase la vista al metodo mostrarPanel
        */
        
        EventMenu event = new EventMenu() {
            @Override
            public void seleccionado(Paneles panel) {
               // System.out.println(panel);
                        
               
                switch (panel) {
                    case PACIENTES -> {
                        Pacientes p = new Pacientes();
                        PacienteDao pDAO = new PacienteDao();
                        PacienteController pController = new PacienteController(pDAO, p);
                        mostrarPanel(p);
                    }
                    case AGENDAR_CITAS -> { 
                        AgendarUnaCita agendarView = new AgendarUnaCita();
                        CitasDao agendarCitaDao = new CitasDao();
                        ServiciosDao servicioDao = new ServiciosDao();
                        EspecialidadDao especialidadDao = new EspecialidadDao();
                        DoctorLigeroDAO doctorDao = new DoctorLigeroDAO();
                        AgendarUnaCitaController agendarController = new AgendarUnaCitaController(agendarView, agendarCitaDao, especialidadDao, servicioDao, doctorDao);
                        mostrarPanel(agendarView);
                    }
                    case GESTOR_DE_CITAS -> {
                        System.out.println("ENTRASTE AL PANEL");

                        Citas citasView = new Citas();
                        CitasDao citasDao = new CitasDao();
                        ServiciosDao servicioDao = new ServiciosDao();
                        EspecialidadDao especialidadDao = new EspecialidadDao();
                        
                        CitasController citasController = new CitasController(citasView, citasDao, servicioDao, especialidadDao);
                        mostrarPanel(citasView);
                    }
                    case AGENDA_DOC -> { 
                        Citas citasView2 = new Citas();
                        CitasDao citasDao2 = new CitasDao();
                        ServiciosDao servicioDao2 = new ServiciosDao();
                        EspecialidadDao especialidadDao2 = new EspecialidadDao();
                        
                        AgendaDoctorController agendaController = new AgendaDoctorController(citasView2, citasDao2, servicioDao2, especialidadDao2, usuario);
                        mostrarPanel(citasView2);
                    }
                    case DOCTORES -> mostrarPanel(new FormModelo());
                    case ESPECIALIDADES_DOC -> mostrarPanel(new FormModelo());
                    case USUARIOS -> mostrarPanel(new FormModelo());
                    case SERVICIOS -> {
                        panelServicios panelServicios = new panelServicios();
                        ServiciosDao servicioDao = new ServiciosDao();
                        ServiciosController servicioController = new ServiciosController(panelServicios, servicioDao);
                        mostrarPanel(panelServicios);

                    }
                    
                    case HISTORICO_DE_PAGOS -> mostrarPanel(new PagosPanel());
                        
                    case METODOS_DE_PAGOS -> mostrarPanel(new FormModelo());
                    case LOG_OFF -> dispose() ;
                    default -> mostrarPanel(new FormModelo());
                }
            }
        };
        
        // Dependiendo del rol entregado, se inicializa el menu adecuado.
        switch (usuario.getRol()) {
            case ADMIN: menu1.initAdmin(event); break;
            case DOCTOR: menu1.initDoctor(event); break;
            case SECRETARIA: menu1.initSecretaria(event); break;
            default: throw new AssertionError();
            
        }
        
        // setteamos la informacion del usuario en el perfil del menu.
        menu1.cambiarNombrePerfil(usuario.getNombre() + usuario.getApellido());
        menu1.cambiarCorreoPerfil(usuario.getEmail());
        menu1.cambiarRolPerfil(usuario.getRol().name());
        menu1.cambiarFotoPerfil(usuario.getImagen());

//         Debuggin Logs
        System.out.printf("%d , %d ", panelCambiante.getWidth(), panelCambiante.getHeight());
//        Agregar en este comentario un menu predeterminado.
//        mostrarPanel(new FormModelo());
    }
    
    /**
     * Metodo usado para cambiar el panel mostrado por el Dashboard. 
     * @param comp El panel que va a mostrar ahora el dashboard.
     */
    private void mostrarPanel(Component comp) {
        panelCambiante.removeAll();
        panelCambiante.add(comp);
        panelCambiante.revalidate();
        panelCambiante.repaint();
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form. WARNING: Do NOT modify this code. The content of this method is always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBackground = new view.component.RoundPanel();
        panelCambiante = new javax.swing.JPanel();
        menu1 = new view.component.menu.Menu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        panelBackground.setBackground(new java.awt.Color(204, 204, 255));

        panelCambiante.setOpaque(false);
        panelCambiante.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout panelBackgroundLayout = new javax.swing.GroupLayout(panelBackground);
        panelBackground.setLayout(panelBackgroundLayout);
        panelBackgroundLayout.setHorizontalGroup(
            panelBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBackgroundLayout.createSequentialGroup()
                .addComponent(menu1, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(panelCambiante, javax.swing.GroupLayout.DEFAULT_SIZE, 1024, Short.MAX_VALUE)
                .addGap(3, 3, 3))
        );
        panelBackgroundLayout.setVerticalGroup(
            panelBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(menu1, javax.swing.GroupLayout.DEFAULT_SIZE, 711, Short.MAX_VALUE)
            .addGroup(panelBackgroundLayout.createSequentialGroup()
                .addGap(3, 3, 3)
                .addComponent(panelCambiante, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(3, 3, 3))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private view.component.menu.Menu menu1;
    private view.component.RoundPanel panelBackground;
    private javax.swing.JPanel panelCambiante;
    // End of variables declaration//GEN-END:variables
}
