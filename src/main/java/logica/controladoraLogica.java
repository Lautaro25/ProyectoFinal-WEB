package logica;

import persistencia.controladoraPersistencia;


public class controladoraLogica {
        
    controladoraPersistencia controlPersis = new controladoraPersistencia();
    
    
    
    public void crearUsuario(usuario usuarioo){
        controlPersis.crearUsuario(usuarioo);
    }
    
    public void eliminarUsuario(int id){
        controlPersis.eliminarUsuario(id);
    }
    
    public void modificarUsuario(usuario usuarioo){
        controlPersis.modificarUsuario(usuarioo);    
    }
}
