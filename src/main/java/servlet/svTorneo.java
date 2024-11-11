
package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.controladoraLogica;
import logica.torneo;

/**
 *
 * @author User
 */
@WebServlet(name = "svTorneo", urlPatterns = {"/svTorneo"})
public class svTorneo extends HttpServlet {
    
    controladoraLogica ctrl = new controladoraLogica();

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
        processRequest(request, response);
        
        String nombreEquipo = request.getParameter("teamName");
        String nombreJugador1 = request.getParameter("player1Name");
        String nombreJugador2 = request.getParameter("player2Name");
        int estadoTorneo = 0;
        
         torneo torn = new torneo();
                torn.setNombreEquipo(nombreEquipo);
                torn.setNombreJugador1(nombreJugador1);
                torn.setNombreJugador2(nombreJugador2);
                
                ctrl.crearTorneo(torn);
                
                HttpSession sessionTorneo = request.getSession();
                List<torneo> listaTorneo = (List<torneo>) sessionTorneo.getAttribute("listaTorneo");
                if (listaTorneo == null){
                    listaTorneo = new ArrayList<>();
                }
                listaTorneo.add(torn);
                sessionTorneo.setAttribute("listaTorneo", listaTorneo);
                
                        //Cargar la lista actualizada
        HttpSession session = request.getSession();
        String idEquipo = (String) session.getAttribute("idEquipoo");
        listaTorneo = ctrl.mostrarTorneo();
        session.setAttribute("listaTorneo", listaTorneo);
            estadoTorneo = 1;
            session.setAttribute("estadoTorneo", estadoTorneo); 
                response.sendRedirect("torneo.jsp");
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
