package logica;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import logica.usuario;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2024-10-04T20:19:53")
@StaticMetamodel(cliente.class)
public class cliente_ { 

    public static volatile SingularAttribute<cliente, String> apellido;
    public static volatile SingularAttribute<cliente, usuario> usuario;
    public static volatile SingularAttribute<cliente, Integer> id;
    public static volatile SingularAttribute<cliente, Integer> telefono;
    public static volatile SingularAttribute<cliente, String> nombre;
    public static volatile SingularAttribute<cliente, Integer> dni;

}