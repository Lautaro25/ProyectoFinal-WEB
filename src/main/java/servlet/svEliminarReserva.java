/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

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
import logica.reserva;

/**
 *
 * @author lauta
 */
@WebServlet(name = "svEliminarReserva", urlPatterns = {"/svEliminarReserva"})
public class svEliminarReserva extends HttpServlet {
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
        int id = Integer.parseInt(request.getParameter("id"));
        ctrlLogica.eliminarReserva(id);

        // Cargar la lista actualizada de reservas para el usuario logueado
        HttpSession session = request.getSession();
        String idUsuario = (String) session.getAttribute("id");
        List<reserva> listaReserva = ctrlLogica.getReserva();
        session.setAttribute("listaReserva", listaReserva);

        String fromPage = request.getParameter("fromPage");

        // Redireccionar a la página adecuada
        if ("menu".equals(fromPage)) {
            response.sendRedirect("Menu.jsp");
        } else if ("gestion".equals(fromPage)) {
            response.sendRedirect("gestion.jsp");
        }
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
