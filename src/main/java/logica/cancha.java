package logica;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class cancha implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private int id;
    @Basic
    private int numCancha;
    private boolean disponibilidad;
    
    //Relación con Reserva
    @OneToMany(mappedBy = "cancha")
    private List<reserva> reservas;


    public cancha() {
    }

    public cancha(int id, int numCancha, boolean disponibilidad, List<reserva> reservas) {
        this.id = id;
        this.numCancha = numCancha;
        this.disponibilidad = disponibilidad;
        this.reservas = reservas;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumCancha() {
        return numCancha;
    }

    public void setNumCancha(int numCancha) {
        this.numCancha = numCancha;
    }

    public boolean isDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public List<reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<reserva> reservas) {
        this.reservas = reservas;
    }

   
    
}
