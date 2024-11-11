/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Servlets;

import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "svModificarEquipo", urlPatterns = {"/svModificarEquipo"})
public class svModificarEquipo extends HttpServlet {
    
     controladoraLogica ctrl = new controladoraLogica();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
              
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
         /*pasamos el parametro del Jsp idEstuModif a un atributo y lo parseamos a int */
        int idModificar = Integer.parseInt(request.getParameter("idEquipoModif"));
        
        /*Luego creamos un objeto Equipo instanciando la controladora logica y creamos
        el metodo traerEquipo, ojo es singular*/
         torneo torn = ctrl.traerTorneo(idModificar);
         
           /*Para pasar los datos conservamos la sesion */
        HttpSession misesion = request.getSession();
        misesion.setAttribute("tornModif", torn);
        
        /*Luego redirigimos a un nuevo Jsp2*/
        response.sendRedirect("modificarEquipo.jsp");
        
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        String nombreEquipo = request.getParameter("teamName");
        String nombreJugador1 = request.getParameter("player1Name");
        String nombreJugador2 = request.getParameter("player2Name");
        
        torneo torn = (torneo) request.getSession().getAttribute("tornModif");       
               torn.setNombreEquipo(nombreEquipo);
               torn.setNombreJugador1(nombreJugador1);
               torn.setNombreJugador2(nombreJugador2);
         
         ctrl.modificarTorneo(torn);
         
                 //Cargar la lista actualizada
        HttpSession session = request.getSession();
        String idEquipo = (String) session.getAttribute("idEquipoo");
        List<torneo> listaTorneo = ctrl.mostrarTorneo();
        session.setAttribute("listaTorneo", listaTorneo);
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
