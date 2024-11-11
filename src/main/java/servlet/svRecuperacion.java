/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import logica.controladoraLogica;

/**
 *
 * @author lauta
 */
@WebServlet(name = "svRecuperacion", urlPatterns = {"/svRecuperacion"})
public class svRecuperacion extends HttpServlet {
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
        String mail = request.getParameter("mail");
        String pass = ctrlLogica.recuperarPassword(mail);
        int estadoRecuperar = 0;
        HttpSession session = request.getSession();
        
        if (pass == null){
            estadoRecuperar = 2;
            session.setAttribute("estadoRecuperar", estadoRecuperar); 
            request.setAttribute("errorMessage", "Email no registrado");
            request.getRequestDispatcher("password.jsp").forward(request, response);
        } else {
            // Enviar correo con la contraseña
            try {
                estadoRecuperar = 1;
                session.setAttribute("estadoRecuperar", estadoRecuperar);                 
                ctrlLogica.enviarCorreo(mail, pass);
                request.setAttribute("successMessage", "Se ha enviado un correo con tu contraseña.");
                request.getRequestDispatcher("password.jsp").forward(request, response); // Redirigir a la misma página o a otra de éxito
            } catch (MessagingException e) {
                e.printStackTrace();
                request.setAttribute("errorMessage", "Hubo un error al enviar el correo.");
                request.getRequestDispatcher("password.jsp").forward(request, response);
            }
        }
        
        

        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
