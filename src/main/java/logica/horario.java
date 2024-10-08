package logica;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class horario implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    @Temporal(TemporalType.DATE)
    private Date dia,horaInicio, horaFin;
    
    @ManyToOne
    @JoinColumn(name = "cancha_id", referencedColumnName = "id")
    private cancha cancha;
    
    @OneToMany(mappedBy = "horario")
    private List<reserva> reservas;

    public horario() {
    }

    public horario(int id, Date dia, Date horaInicio, Date horaFin, cancha cancha, List<reserva> reservas) {
        this.id = id;
        this.dia = dia;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.cancha = cancha;
        this.reservas = reservas;
    }

    public cancha getCancha() {
        return cancha;
    }

    public void setCancha(cancha cancha) {
        this.cancha = cancha;
    }



    public List<reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<reserva> reservas) {
        this.reservas = reservas;
    }


    public int getId() {
        return id;
    }

    public Date getDia() {
        return dia;
    }

    public Date getHoraInicio() {
        return horaInicio;
    }

    public Date getHoraFin() {
        return horaFin;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDia(Date dia) {
        this.dia = dia;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public void setHoraFin(Date horaFin) {
        this.horaFin = horaFin;
    }
    
    
}
