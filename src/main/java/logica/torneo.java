package logica;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class torneo implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    @Basic
    private String nombreEquipo, nombreJugador1, nombreJugador2;

    // Relación 1 a 1 con Usuario
    @OneToOne
    @JoinColumn(name = "usuario_id")
    private usuario usuario;
    
    public torneo() {
    }

    public torneo(int id, String nombreEquipo, String nombreJugador1, String nombreJugador2, usuario usuario) {
        this.id = id;
        this.nombreEquipo = nombreEquipo;
        this.nombreJugador1 = nombreJugador1;
        this.nombreJugador2 = nombreJugador2;
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

    public String getNombreJugador1() {
        return nombreJugador1;
    }
    
    public String getNombreJugador2() {
        return nombreJugador2;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombreEquipo(String nombreEquipo) {
        this.nombreEquipo = nombreEquipo;
    }

    public void setNombreJugador1(String nombreJugador1) {
        this.nombreJugador1 = nombreJugador1;
    }
    
    public void setNombreJugador2(String nombreJugador2) {
        this.nombreJugador2 = nombreJugador2;
    }
    
    
}
