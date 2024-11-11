package logica;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class usuario implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    @Basic
   private String nombre, mail, contrasenia;
    
    //Relación con Torneo
    @OneToOne(mappedBy = "usuario")
    private torneo torneo;
    
    //Relación con Reserva
    @OneToMany(mappedBy = "usuario")
    private List<reserva> reservas;
    
    //Relación con tipo de Usuario
    @OneToOne
    @JoinColumn(name = "tipo_usuario", referencedColumnName = "tipo_Usuario")
    private tipoUsuario tipoUsuario;



    public usuario() {
    }

    public usuario(int id, String nombre, String mail, String contrasenia, torneo torneo, List<reserva> reservas, tipoUsuario tipoUsuario) {
        this.id = id;
        this.nombre = nombre;
        this.mail = mail;
        this.contrasenia = contrasenia;
        this.torneo = torneo;
        this.reservas = reservas;
        this.tipoUsuario = tipoUsuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public torneo getTorneo() {
        return torneo;
    }

    public void setTorneo(torneo torneo) {
        this.torneo = torneo;
    }

    public List<reserva> getReservas() {
        return reservas;
    }

    public void setReservas(List<reserva> reservas) {
        this.reservas = reservas;
    }

    public tipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(tipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    
}
