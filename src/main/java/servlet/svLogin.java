/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import static java.awt.SystemColor.control;
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

@WebServlet(name = "svLogin", urlPatterns = {"/svLogin"})
public class svLogin extends HttpServlet {
    controladoraLogica control = new controladoraLogica();

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
        String mail = request.getParameter("mail");
        String contrasenia = request.getParameter("contrasenia");

        boolean validacion = control.comprobarIngreso(mail, contrasenia);
        boolean existencia = control.existenciaMail(mail);
        int estadoLogin = 0;
        HttpSession session = request.getSession();
        
        if(existencia){
            if (validacion) {
                usuario usuario = control.obtenerUsuario(mail);
                HttpSession misession = request.getSession(true);

                // Convierte el id a String antes de guardarlo
                misession.setAttribute("id", String.valueOf(usuario.getId())); // Aquí se convierte a String
                misession.setAttribute("nombre", usuario.getNombre());     
                misession.setAttribute("mail", mail);
                response.sendRedirect("Menu.jsp");
            } else {
                estadoLogin = 2;
                session.setAttribute("estadoLogin", estadoLogin);
                request.setAttribute("errorMessage", "Email o contraseña incorrectos.");
                request.getRequestDispatcher("Login.jsp").forward(request, response);
            }
        } else {
                estadoLogin = 2;
                session.setAttribute("estadoLogin", estadoLogin);
                request.setAttribute("errorMessage", "Email no registrado.");
                request.getRequestDispatcher("Login.jsp").forward(request, response);
        }
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
