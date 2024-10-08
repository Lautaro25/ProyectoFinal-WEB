package logica;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class reserva implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    @Basic
    private int idUsuarios;
    @Temporal(TemporalType.DATE)
    private Date fecha;

    @OneToOne
    @JoinColumn(name = "horario_id", referencedColumnName = "id")
    private horario horario;

    public reserva() {
    }

    public reserva(int id, int idUsuarios, Date fecha, horario horario) {
        this.id = id;
        this.idUsuarios = idUsuarios;
        this.fecha = fecha;
        this.horario = horario;
    }

    public horario getHorario() {
        return horario;
    }

    public void setHorario(horario horario) {
        this.horario = horario;
    }

    public int getId() {
        return id;
    }

    public int getIdUsuarios() {
        return idUsuarios;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setIdUsuarios(int idUsuarios) {
        this.idUsuarios = idUsuarios;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    
}
