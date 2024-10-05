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
public class usuario implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    @Basic
   private String nombre, mail, contrasenia;
    
    // Relación 1 a 1 con Cliente
    @OneToOne
    @JoinColumn(name = "cliente_id", referencedColumnName = "id")
    private cliente cliente;

    // Relación 1 a 1 con Torneo
    @OneToOne
    @JoinColumn(name = "torneo_id", referencedColumnName = "id")
    private torneo torneo;

    public usuario() {
    }

    public usuario(int id, String nombre, String mail, String contrasenia, cliente cliente, torneo torneo) {
        this.id = id;
        this.nombre = nombre;
        this.mail = mail;
        this.contrasenia = contrasenia;
        this.cliente = cliente;
        this.torneo = torneo;
    }

    public cliente getCliente() {
        return cliente;
    }

    public torneo getTorneo() {
        return torneo;
    }

    public void setCliente(cliente cliente) {
        this.cliente = cliente;
    }

    public void setTorneo(torneo torneo) {
        this.torneo = torneo;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getMail() {
        return mail;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
    
}
