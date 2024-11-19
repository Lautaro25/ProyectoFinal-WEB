package logica;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import logica.cancha;
import logica.usuario;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2024-11-19T15:43:21")
@StaticMetamodel(reserva.class)
public class reserva_ { 

    public static volatile SingularAttribute<reserva, String> nombreApellido;
    public static volatile SingularAttribute<reserva, String> horario;
    public static volatile SingularAttribute<reserva, cancha> cancha;
    public static volatile SingularAttribute<reserva, usuario> usuario;
    public static volatile SingularAttribute<reserva, Integer> id;
    public static volatile SingularAttribute<reserva, String> telefono;
    public static volatile SingularAttribute<reserva, Date> dia;
    public static volatile SingularAttribute<reserva, String> dni;

}