package logica;

import persistencia.controladoraPersistencia;


public class controladoraLogica {
        
    controladoraPersistencia controlPersis = new controladoraPersistencia();
    
    
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
    
    
    //CRUD Torneo    
    public void crearToreno(torneo torneo){
        controlPersis.crearTorneo(torneo);
    }
    
    public void eliminarTorneo(int id){
        controlPersis.eliminarTorneo(id);
    }
    
    public void modificarTorneo(torneo torneo){
        controlPersis.modificarTorneo(torneo);    
    }
}
