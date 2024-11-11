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
import logica.cancha;
import logica.controladoraLogica;

/**
 *
 * @author lauta
 */
@WebServlet(name = "svCancha", urlPatterns = {"/svCancha"})
public class svCancha extends HttpServlet {
    
          controladoraLogica ctrl = new controladoraLogica();

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
                // Solo agregar canchas si no existen
            cancha cancha1 = new cancha(1, 1, false, null); // Ajusta según tu constructor
            cancha cancha2 = new cancha(2, 1, false, null);
            cancha cancha3 = new cancha(3, 1, false, null);

            ctrl.crearCancha(cancha1);
            ctrl.crearCancha(cancha2);
            ctrl.crearCancha(cancha3);
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
