package logica;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import logica.cliente;
import logica.torneo;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2024-10-04T20:19:53")
@StaticMetamodel(usuario.class)
public class usuario_ { 

    public static volatile SingularAttribute<usuario, cliente> cliente;
    public static volatile SingularAttribute<usuario, torneo> torneo;
    public static volatile SingularAttribute<usuario, String> mail;
    public static volatile SingularAttribute<usuario, String> contrasenia;
    public static volatile SingularAttribute<usuario, Integer> id;
    public static volatile SingularAttribute<usuario, String> nombre;

}