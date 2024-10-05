package logica;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import logica.usuario;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2024-10-04T20:19:53")
@StaticMetamodel(torneo.class)
public class torneo_ { 

    public static volatile SingularAttribute<torneo, String> nombreEquipo;
    public static volatile SingularAttribute<torneo, String> nombreJugador;
    public static volatile SingularAttribute<torneo, usuario> usuario;
    public static volatile SingularAttribute<torneo, Integer> id;

}