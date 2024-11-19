package logica;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import logica.reserva;

@Generated(value="EclipseLink-2.7.12.v20230209-rNA", date="2024-11-19T15:43:21")
@StaticMetamodel(cancha.class)
public class cancha_ { 

    public static volatile SingularAttribute<cancha, Boolean> disponibilidad;
    public static volatile SingularAttribute<cancha, Integer> numCancha;
    public static volatile ListAttribute<cancha, reserva> reservas;
    public static volatile SingularAttribute<cancha, Integer> id;

}