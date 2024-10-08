package logica;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class cancha implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    @Basic
    private int numCancha;
    private boolean disponibilidad;
    
    @OneToOne(mappedBy = "cancha")
    private cliente cliente;

    @OneToMany(mappedBy = "cancha")
    private List<horario> horarios;

    public cancha() {
    }

    public cancha(int id, int numCancha, boolean disponibilidad, cliente cliente, List<horario> horarios) {
        this.id = id;
        this.numCancha = numCancha;
        this.disponibilidad = disponibilidad;
        this.cliente = cliente;
        this.horarios = horarios;
    }

    public List<horario> getHorarios() {
        return horarios;
    }

    public void setHorarios(List<horario> horarios) {
        this.horarios = horarios;
    }

    public cliente getCliente() {
        return cliente;
    }

    public void setCliente(cliente cliente) {
        this.cliente = cliente;
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
