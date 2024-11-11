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
    String nombreApellido, telefono, dni, horario;
    @Temporal(TemporalType.DATE)
    private Date dia;

    //Relación con Usuario
    @ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private usuario usuario;
    
    //Relación con Cancha
    @ManyToOne
    @JoinColumn(name = "cancha_id", referencedColumnName = "id")
    private cancha cancha;

    public reserva() {
    }

    public reserva(int id, String nombreApellido, String telefono, String dni, String horario, Date dia, usuario usuario, cancha cancha) {
        this.id = id;
        this.nombreApellido = nombreApellido;
        this.telefono = telefono;
        this.dni = dni;
        this.horario = horario;
        this.dia = dia;
        this.usuario = usuario;
        this.cancha = cancha;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreApellido() {
        return nombreApellido;
    }

    public void setNombreApellido(String nombreApellido) {
        this.nombreApellido = nombreApellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public Date getDia() {
        return dia;
    }

    public void setDia(Date dia) {
        this.dia = dia;
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


   
    

    

    
    
}
