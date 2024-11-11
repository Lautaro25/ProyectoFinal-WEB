package persistencia;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import logica.cancha;
import logica.reserva;
import logica.tipoUsuario;
import logica.torneo;
import logica.usuario;
import persistencia.exceptions.NonexistentEntityException;

public class controladoraPersistencia {
    canchaJpaController canchaJpa = new canchaJpaController();
    reservaJpaController reservaJpa = new reservaJpaController();
    torneoJpaController torneoJpa = new torneoJpaController();
    usuarioJpaController usuarioJpa = new usuarioJpaController();
    tipoUsuarioJpaController tipoUsuarioJpa = new tipoUsuarioJpaController();
    
    //CRUD Usuario
    
    public void crearUsuario (usuario usuarioo) {
        usuarioJpa.create(usuarioo);
    }
    
    public void eliminarUsuario(int id) {
        try {
            usuarioJpa.destroy(id);   
        } catch (NonexistentEntityException e) {
            Logger.getLogger(controladoraPersistencia.class.getName()).log(Level.SEVERE, null, e);
        }
   
    }

    public void modificarUsuario(usuario usuarioo) {
        try {
            usuarioJpa.edit(usuarioo);
        } catch (Exception e) {
            Logger.getLogger(controladoraPersistencia.class.getName()).log(Level.SEVERE, null, e);
        }
    
    }
    
    public List<usuario> getUsuario(){
        return usuarioJpa.findusuarioEntities();
    }    
    
    public usuario obtenerUsuarioPorId(int id) {
    return usuarioJpa.findusuario(id); // Esto asume que el método existe en usuarioJpaController
}

    
    //CRUD Torneo
     public void crearTorneo(torneo torn) {
      torneoJpa.create(torn);
    }
     
     public torneo traerTorneo(int idModificar) {
        return torneoJpa.findtorneo(idModificar);
    }
     
      public List<torneo> mostrarTorneo() {
        return torneoJpa.findtorneoEntities();
    }
    
    public void modificarTorneo(torneo torn){
        try { 
            torneoJpa.edit(torn);
    } catch (Exception ex){
        Logger.getLogger(controladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    
    public void eliminarTorneo(int id) {
       try {
           torneoJpa.destroy(id);
       } catch (NonexistentEntityException ex) {
           Logger.getLogger(controladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
       }
    }
    
    //CRUD Cancha
    public void crearCancha(cancha cancha) {
      canchaJpa.create(cancha);
    }
    
    public void modificarCancha(cancha cancha){
        try { 
            canchaJpa.edit(cancha);
    } catch (Exception ex){
        Logger.getLogger(controladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
    
    public void eliminarCancha(int id) {
       try {
           canchaJpa.destroy(id);
       } catch (NonexistentEntityException ex) {
           Logger.getLogger(controladoraPersistencia.class.getName()).log(Level.SEVERE, null, ex);
       }
    } 
    
    public List<cancha> encontrarCancha() {
        return canchaJpa.findcanchaEntities();
    }

    
    
    //CRUD Reserva
    public void crearReserva(reserva reserva) {
        reservaJpa.create(reserva);
    }

    public void eliminarReserva(int id) {
           try {
            reservaJpa.destroy(id);   
        } catch (NonexistentEntityException e) {
            Logger.getLogger(controladoraPersistencia.class.getName()).log(Level.SEVERE, null, e);
        }
    }

 
    public void modificarReserva(reserva reserva) {
    try {
            reservaJpa.edit(reserva);
        } catch (Exception e) {
            Logger.getLogger(controladoraPersistencia.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    public List<reserva> getReserva() {
        return reservaJpa.findreservaEntities();
    }

    
    
    
    //CRUD TipoUsuario
    public void crearTipoUsuario(tipoUsuario tipoUsuario) {
        tipoUsuarioJpa.create(tipoUsuario);
    }

    public void eliminarTipoUsuario(int id) {
           try {
            tipoUsuarioJpa.destroy(id);   
        } catch (NonexistentEntityException e) {
            Logger.getLogger(controladoraPersistencia.class.getName()).log(Level.SEVERE, null, e);
        }
    }

 
    public void modificarTipoUsuario(tipoUsuario tipoUsuario) {
    try {
            tipoUsuarioJpa.edit(tipoUsuario);
        } catch (Exception e) {
            Logger.getLogger(controladoraPersistencia.class.getName()).log(Level.SEVERE, null, e);
        }
    }
    
    
}
