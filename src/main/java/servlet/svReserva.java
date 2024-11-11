package servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.cancha;
import logica.controladoraLogica;
import logica.reserva;
import logica.usuario;


@WebServlet(name = "svReserva", urlPatterns = {"/svReserva"})
public class svReserva extends HttpServlet {
    
    controladoraLogica ctrlLogica = new controladoraLogica();
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nombre = request.getParameter("nombre");
        String telefono = request.getParameter("telef");
        String dni = request.getParameter("dni");
        String fechaStr = request.getParameter("fecha");
        String horario = request.getParameter("horario");
        String numCanchaStr = request.getParameter("cancha");

        HttpSession session = request.getSession();
        String usuarioIdStr = (String) session.getAttribute("id");
        Integer usuarioId = (usuarioIdStr != null) ? Integer.parseInt(usuarioIdStr) : null;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = null;
        try {
            fecha = sdf.parse(fechaStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        int canchaId = Integer.parseInt(numCanchaStr);
        int estadoReserva = 0;

        // Verificar disponibilidad antes de crear la reserva
        if (ctrlLogica.existeReserva(fecha, horario, canchaId)) {
            estadoReserva = 2; // Estado de error si la reserva ya existe
            session.setAttribute("estadoReserva", estadoReserva);
            session.setAttribute("mensajeError", "La cancha ya está reservada en ese horario");
            response.sendRedirect("Menu.jsp");
        } else if (ctrlLogica.limiteReserva(fecha, usuarioIdStr)>3){
            estadoReserva = 2;
            session.setAttribute("estadoReserva", estadoReserva);
            session.setAttribute("mensajeError", "Limite de reservas diarias alcanzado");
            response.sendRedirect("Menu.jsp"); 
        } else {
        
        // Crear nueva reserva si está disponible
        reserva res = new reserva();
        res.setNombreApellido(nombre);
        res.setTelefono(telefono);
        res.setDni(dni);
        res.setHorario(horario);
        res.setDia(fecha);
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PracticaWebPrueba_PU");
        EntityManager em = emf.createEntityManager();
        
        try {
            cancha canchaSeleccionada = em.find(cancha.class, canchaId);

            if (canchaSeleccionada != null) {
                res.setCancha(canchaSeleccionada);
                usuario user = em.find(usuario.class, usuarioId);
                if (user != null) {
                    res.setUsuario(user);
                }
                ctrlLogica.crearReserva(res);

                HttpSession sessionReserva = request.getSession();
                List<reserva> listaReserva = (List<reserva>) sessionReserva.getAttribute("listaReserva");
                if (listaReserva == null) {
                    listaReserva = new ArrayList<>();
                }
                listaReserva.add(res);
                        // Si la reserva es exitosa
                estadoReserva = 1;
                session.setAttribute("estadoReserva", estadoReserva); // Asegúrate de establecer el estado
                sessionReserva.setAttribute("listaReserva", listaReserva);
                response.sendRedirect("Menu.jsp");
            } else {
                System.out.println("Cancha no encontrada.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            em.close();
        }}
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
