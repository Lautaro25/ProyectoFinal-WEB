package logica;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class torneo implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    @Basic
    private String nombreEquipo, nombreJugador;

    // Relación 1 a 1 con Usuario
    @OneToOne(mappedBy = "torneo")
    private usuario usuario;
    
    public torneo() {
    }

    public torneo(int id, String nombreEquipo, String nombreJugador, usuario usuario) {
        this.id = id;
        this.nombreEquipo = nombreEquipo;
        this.nombreJugador = nombreJugador;
        this.usuario = usuario;
    }

    public void setUsuario(usuario usuario) {
        this.usuario = usuario;
    }

    public usuario getUsuario() {
        return usuario;
    }

    public int getId() {
        return id;
    }

    public String getNombreEquipo() {
        return nombreEquipo;
    }

    public String getNombreJugador() {
        return nombreJugador;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

    public void setNombreJugador(String nombreJugador) {
        this.nombreJugador = nombreJugador;
    }
    
    
}
