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
public class cliente implements Serializable {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
    @Basic
    private int dni, telefono;
    private String nombre, apellido;

    // Relación 1 a 1 con Usuario
    @OneToOne(mappedBy = "cliente")
    private usuario usuario;
    
    @OneToOne
    @JoinColumn(name = "cancha_id", referencedColumnName = "id")
    private cancha cancha;
    
    public cliente() {
    }

    public cliente(int id, int dni, int telefono, String nombre, String apellido, usuario usuario, cancha cancha) {
        this.id = id;
        this.dni = dni;
        this.telefono = telefono;
        this.nombre = nombre;
        this.apellido = apellido;
        this.usuario = usuario;
        this.cancha = cancha;
    }

    public cancha getCancha() {
        return cancha;
    }

    public void setCancha(cancha cancha) {
        this.cancha = cancha;
    }



    public usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(usuario usuario) {
        this.usuario = usuario;
    }



    public int getId() {
        return id;
    }

    public void setId_Cliente(int id) {
        this.id = id;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getDni() {
        return dni;
    }

    public int getTelefono() {
        return telefono;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }
    
}
