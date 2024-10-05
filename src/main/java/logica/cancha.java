package logica;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class cancha implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    @Basic
    private int numCancha;
    private boolean disponibilidad;

    public cancha() {
    }

    public cancha(int id, int numCancha, boolean disponibilidad) {
        this.id = id;
        this.numCancha = numCancha;
        this.disponibilidad = disponibilidad;
    }

    public int getId() {
        return id;
    }

    public int getNumCancha() {
        return numCancha;
    }

    public boolean isDisponibilidad() {
        return disponibilidad;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNumCancha(int numCancha) {
        this.numCancha = numCancha;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }
    
    
}
