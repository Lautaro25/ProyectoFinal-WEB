package logica;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import logica.reserva;
import logica.tipoUsuario;
import logica.torneo;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2024-11-19T15:43:21")
@StaticMetamodel(usuario.class)
public class usuario_ { 

    public static volatile SingularAttribute<usuario, torneo> torneo;
    public static volatile SingularAttribute<usuario, String> mail;
    public static volatile ListAttribute<usuario, reserva> reservas;
    public static volatile SingularAttribute<usuario, String> contrasenia;
    public static volatile SingularAttribute<usuario, tipoUsuario> tipoUsuario;
    public static volatile SingularAttribute<usuario, Integer> id;
    public static volatile SingularAttribute<usuario, String> nombre;

}