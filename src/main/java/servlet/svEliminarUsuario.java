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
import logica.usuario;

/**
 *
 * @author lauta
 */
@WebServlet(name = "svEliminarUsuario", urlPatterns = {"/svEliminarUsuario"})
public class svEliminarUsuario extends HttpServlet {
    controladoraLogica ctrlLogica = new controladoraLogica();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
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

        ctrlLogica.eliminarUsuario(id); // Verifica que este método funcione correctamente

        // Cargar la lista actualizada de usuarios
        HttpSession session = request.getSession();
        String idUsuario = (String) session.getAttribute("id");
        List<usuario> listaUsuario = ctrlLogica.getUsuario();
        session.setAttribute("listaUsuario", listaUsuario);

        response.sendRedirect("gestion.jsp");
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
