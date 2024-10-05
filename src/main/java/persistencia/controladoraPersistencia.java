package persistencia;

import java.util.logging.Level;
import java.util.logging.Logger;
import logica.usuario;
import persistencia.exceptions.NonexistentEntityException;

public class controladoraPersistencia {
    canchaJpaController canchaJpa = new canchaJpaController();
    clienteJpaController clienteJpa = new clienteJpaController();
    horarioJpaController horarioJpa = new horarioJpaController();
    reservaJpaController reservaJpa = new reservaJpaController();
    torneoJpaController torneoJpa = new torneoJpaController();
    usuarioJpaController usuarioJpa = new usuarioJpaController();
    
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
}
