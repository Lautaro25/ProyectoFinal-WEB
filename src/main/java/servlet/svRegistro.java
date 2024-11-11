/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
import logica.controladoraLogica;
import logica.reserva;
import logica.tipoUsuario;
import logica.usuario;


@WebServlet(name = "svRegistro", urlPatterns = {"/svRegistro"})
public class svRegistro extends HttpServlet {
    
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
    String email = request.getParameter("email");
    String nombre = request.getParameter("nombre");
    String contrasenia = request.getParameter("contrasenia");
    int estadoRegistro = 0;
    HttpSession session = request.getSession();
    
    usuario usu = new usuario();
    
    // Validación de entradas
    if (email == null || nombre == null || contrasenia == null || email.isEmpty() || nombre.isEmpty() || contrasenia.isEmpty()) {
        request.setAttribute("errorMessage", "Todos los campos son obligatorios.");
        request.getRequestDispatcher("registro.jsp").forward(request, response);
        return;
    }

    usu.setMail(email);
    usu.setNombre(nombre);
    usu.setContrasenia(contrasenia);
    
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("PracticaWebPrueba_PU");
    EntityManager em = emf.createEntityManager();
// Asume que la clave primaria para 'ADMIN' es 1 y para 'COMUN' es 2
tipoUsuario tipoAdmin = em.find(tipoUsuario.class, 1); // ID del tipo de usuario ADMIN
tipoUsuario tipoComun = em.find(tipoUsuario.class, 2); // ID del tipo de usuario COMUN

// Asigna el tipo de usuario en función del email
if (email.equals("lautarovillabona.ar@gmail.com")) {
    usu.setTipoUsuario(tipoAdmin);
} else {
    usu.setTipoUsuario(tipoComun);
}


    try {
        // Comprobar si el usuario ya existe
        if (ctrlLogica.existeUsuario(email)) {
            estadoRegistro = 2;
            session.setAttribute("estadoRegistro", estadoRegistro);
            request.setAttribute("errorMessage", "El correo electrónico ya está registrado.");
            request.getRequestDispatcher("registro.jsp").forward(request, response);
            return;
        }

        // Crear usuario
        ctrlLogica.crearUsuario(usu);
        estadoRegistro = 1;
        session.setAttribute("estadoRegistro", estadoRegistro);
        response.sendRedirect("registro.jsp");
    } catch (Exception e) {
        // Manejo de error
        request.setAttribute("errorMessage", "Error al crear el usuario: " + e.getMessage());
        request.getRequestDispatcher("error.jsp").forward(request, response);
    }
    
    
    
        // **Manejo de la sesión para la lista de reservas**
        HttpSession sessionUsuario = request.getSession();
        List<usuario> listaUsuario = (List<usuario>) sessionUsuario.getAttribute("listaUsuario");
            if (listaUsuario == null) {
                listaUsuario = new ArrayList<>();
            }
        listaUsuario.add(usu); // Agrega la nueva reserva
        sessionUsuario.setAttribute("listaUsuario", listaUsuario); // Guarda la lista de reservas en la sesión

    
}


    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
