/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.controladoraLogica;
import logica.usuario;

/**
 *
 * @author lauta
 */
@WebServlet(name = "svModificarUsuario", urlPatterns = {"/svModificarUsuario"})
public class svModificarUsuario extends HttpServlet {
    controladoraLogica ctrlLogica = new controladoraLogica();    

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idModificar = Integer.parseInt(request.getParameter("idUsuario"));
        usuario usu = ctrlLogica.getUsuario(idModificar);
        HttpSession misesion = request.getSession();
        misesion.setAttribute("usuModif", usu);
        response.sendRedirect("modificarUsuario.jsp");
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombre = request.getParameter("name");
        String contrasenia = request.getParameter("contrasenia");
        
        usuario usu = (usuario) request.getSession().getAttribute("usuModif");
        usu.setNombre(nombre);
        usu.setContrasenia(contrasenia);
        
        ctrlLogica.modificarUsuario(usu);
        response.sendRedirect("Login.jsp");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
