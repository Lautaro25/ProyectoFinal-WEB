package logica;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import persistencia.controladoraPersistencia;


public class controladoraLogica {
        
    controladoraPersistencia controlPersis = new controladoraPersistencia();

    
    //Comprobar Ingreso
    public boolean comprobarIngreso(String mail, String contrasenia) {
        List<usuario> listaUsuarios = controlPersis.getUsuario();

        for (usuario usu : listaUsuarios) {
            if (usu.getMail().equals(mail) && usu.getContrasenia().equals(contrasenia)) {
                return true; // Se encontró el usuario
            }
        }
        return false; // No se encontró el usuario
    }

    
        //Comprobar Ingreso
        public usuario obtenerUsuario(String mail) {
            return controlPersis.getUsuario().stream()
                .filter(usu -> usu.getMail().equals(mail))
                .findFirst()
                .orElse(null); // Si no se encuentra, devuelve null
        }

        public boolean existenciaMail(String mail){
        List<usuario> listaUsuarios = controlPersis.getUsuario();
        
        for (usuario usu : listaUsuarios) {
            if (usu.getMail().equals(mail)) {
                return true; // Se encontró el usuario
            }
        }
        
        return false;
        }
        
        public String recuperarPassword(String mail){
        List<usuario> listaUsuarios = controlPersis.getUsuario();
                for (usuario usu : listaUsuarios) {
            if (usu.getMail().equals(mail)) {
                return usu.getContrasenia();
            }
        }
        
        return null;
        }
    

    
    //CRUD Usuario
    public void crearUsuario(usuario usuarioo){
        controlPersis.crearUsuario(usuarioo);
    }
    
    public void eliminarUsuario(int id){
        controlPersis.eliminarUsuario(id);
    }
    
    public void modificarUsuario(usuario usuarioo){
        controlPersis.modificarUsuario(usuarioo);    
    }
    
    public boolean existeUsuario(String mail) {
    return obtenerUsuario(mail) != null;
    }
    
    public List <usuario> getUsuario() {
       
        return controlPersis.getUsuario();  
    } 
    
    public usuario getUsuario(int id) {
    return controlPersis.obtenerUsuarioPorId(id);
}

    
    //CRUD Torneo    
      public void crearTorneo(torneo torn){
        controlPersis.crearTorneo(torn);
    }
      
      public torneo traerTorneo(int idModificar){
        return controlPersis.traerTorneo(idModificar);
    }
      
      public List<torneo> mostrarTorneo(){
        return controlPersis.mostrarTorneo();
    }
    
    public void modificarTorneo(torneo torn){
        controlPersis.modificarTorneo(torn);
    }
    
     public void eliminarTorneo(int id){
        controlPersis.eliminarTorneo(id);
    }
    
    
    //CRUD Cancha
    public void crearCancha(cancha cancha){
        controlPersis.crearCancha(cancha);
    }
    
    public void modificarCancha(cancha cancha){
        controlPersis.modificarCancha(cancha);
    }
    
     public void eliminarCancha(int id){
        controlPersis.eliminarCancha(id);
    }
    
    //CRUD Reserva
      public void crearReserva(reserva reserva){
        controlPersis.crearReserva(reserva);
    }
    
    public void eliminarReserva(int id){
        controlPersis.eliminarReserva(id);
    }
    
    public void modificarReserva(reserva reserva){
        controlPersis.modificarReserva(reserva);    
    }
    
    public List <reserva> getReserva() {
       
        return controlPersis.getReserva();  
    } 
    
    public boolean existeReserva(Date fecha, String horario, int canchaId) {
    List<reserva> listaReservas = controlPersis.getReserva();
    for (reserva res : listaReservas) {
        if (res.getDia().equals(fecha) && 
            res.getHorario().equals(horario) && 
            res.getCancha().getId() == canchaId) {
            return true; // Ya existe una reserva con los mismos datos
        }
    }
    return false; // No existe ninguna reserva en ese horario, fecha y cancha
    }

    public int limiteReserva(Date fecha, String idUsuario) {
        List<reserva> listaReservas = controlPersis.getReserva();
        int cont = 1;
        int idUsuarioInt = Integer.parseInt(idUsuario);

        for (reserva res : listaReservas) {
            if (res.getUsuario().getId() == idUsuarioInt && res.getDia().equals(fecha)) {
                cont++;
            }
        }

        return cont;
    }


    
    //CRUD Tipo de Usuario
      public void crearTipoUsuario(tipoUsuario tipoUsuario){
        controlPersis.crearTipoUsuario(tipoUsuario);
    }
    
    public void eliminarTipoUsuario(int id){
        controlPersis.eliminarTipoUsuario(id);
    }
    
    public void modificarTipoUsuario(tipoUsuario tipoUsuario){
        controlPersis.modificarTipoUsuario(tipoUsuario);    
    }

    
    //Enviar Correo
    public void enviarCorreo(String destinatario, String contrasena) throws MessagingException {
        // Configuración del correo del remitente y la clave (si tienes habilitada la autenticación en dos pasos, usa la contraseña de aplicación)
        String remitente = "lautarovillabona.ar@gmail.com"; // Tu correo electrónico
        String clave = "aneu mxcd xkze fpiz"; // Tu contraseña o contraseña de aplicación si tienes 2FA habilitado

        // Configuración de las propiedades del servidor SMTP
        Properties propiedades = new Properties();
        propiedades.put("mail.smtp.host", "smtp.gmail.com");  // Gmail SMTP
        propiedades.put("mail.smtp.port", "587"); // Puerto para SMTP con STARTTLS
        propiedades.put("mail.smtp.auth", "true");
        propiedades.put("mail.smtp.starttls.enable", "true"); // Habilitar STARTTLS para la seguridad de la conexión

        // Crear sesión con autenticación
        Session sesion = Session.getInstance(propiedades, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(remitente, clave);  // Usar el correo y la contraseña para la autenticación
            }
        });

        try {
            // Crear el mensaje
            Message mensaje = new MimeMessage(sesion);
            mensaje.setFrom(new InternetAddress(remitente)); // Establecer el remitente
            mensaje.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario)); // Establecer destinatario
            mensaje.setSubject("Recuperación de Contraseña"); // Asunto del correo
            mensaje.setText("Hola,\n\nTu contraseña es: " + contrasena + "\n\nSi deseas cambiarla, por favor ingresa a tu perfil y desde ahí podrás modificarla.");  // Cuerpo del correo

            // Enviar el mensaje
            Transport.send(mensaje);
            System.out.println("Correo enviado correctamente.");
        } catch (MessagingException e) {
            // Capturar y mostrar el error si ocurre
            e.printStackTrace();
            throw new MessagingException("Error al enviar el correo", e);
        }
    }
    
}


