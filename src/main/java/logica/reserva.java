package logica;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
    
    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private usuario usuario;

    @ManyToOne
    @JoinColumn(name = "cancha_id")
    private cancha cancha;

    @ManyToOne
    @JoinColumn(name = "horario_id")
    private horario horario;

    public reserva() {
    }

    public reserva(int id, int idUsuarios, Date fecha, usuario usuario, cancha cancha, horario horario) {
        this.id = id;
        this.idUsuarios = idUsuarios;
        this.fecha = fecha;
        this.usuario = usuario;
        this.cancha = cancha;
        this.horario = horario;
    }

    public usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(usuario usuario) {
        this.usuario = usuario;
    }

    public cancha getCancha() {
        return cancha;
    }

    public void setCancha(cancha cancha) {
        this.cancha = cancha;
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
