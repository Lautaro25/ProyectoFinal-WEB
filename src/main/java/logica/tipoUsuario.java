
package logica;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class tipoUsuario implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    @Basic
    private boolean tipo_Usuario;
    
    //Relación con Usuario
    @OneToOne(mappedBy = "tipoUsuario")
    private usuario usuario;


    public tipoUsuario() {
    }

    public tipoUsuario(int id, usuario usuario) {
        this.id = id;
        this.usuario = usuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isTipo_Usuario() {
        return tipo_Usuario;
    }

    public void setTipo_Usuario(boolean tipo_Usuario) {
        this.tipo_Usuario = tipo_Usuario;
    }

    public usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(usuario usuario) {
        this.usuario = usuario;
    }

   
    
    
}
