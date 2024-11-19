<%@page import="logica.usuario"%>
<%@page import="java.util.List"%>
<%@page import="logica.reserva"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>


<!DOCTYPE html>
<html lang="es">

<head>
    <script src="https://polyfill.io/v3/polyfill.min.js?features=default"></script>
    <script type="module" src="./codigo.js"></script>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://kit.fontawesome.com/2f7feb84f1.js" crossorigin="anonymous"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/5.1.3/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/animate.css/4.1.1/animate.min.css" />
    <link rel="stylesheet" href="CSS/index.css">
    <link rel="icon" href="Img/iconoWeb2.ico">
    <title>Alquilá tu Cancha</title>
</head>

<!--Desarrollado por Lautaro Villabona-->

<body class="login_body  menu_body">
        <!-- Validación Sesión -->
        <% 
            HttpSession misession = request.getSession();
            String id = (String) misession.getAttribute("id");
            String mail = (String) misession.getAttribute("mail");
            String nombre = (String) misession.getAttribute("nombre");
            if (id == null || mail == null || nombre == null) {
                response.sendRedirect("registro.jsp");
                return;
            }
        %>

    <header class="menu_header">
        <nav class="navbar navbar-expand-lg">
            <div class="container-fluid">
                <div class="menu_header-img"><img src="Img/iconoWeb2.ico" alt=""></div>
                <a class="navbar-brand" href="Menu.jsp">
                    <h2 class="animate__animated animate__pulse">Página Principal</h2>
                </a>
                <button class="navbar-toggler " type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                    aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon "></span>
                </button>
                <% 
                    String adminEmail = "lautarovillabona.ar@gmail.com"; // Reemplaza con el email del administrador
                %>

               <div class="collapse navbar-collapse" id="navbarNav">
                    <ul class="navbar-nav">

                        <%-- Sección Condicional: Mostrar solo para el administrador --%>
                        <% if (adminEmail.equals(mail)) { %>
                            <li class="nav-item">
                                <a class="nav-link with-acount" href="torneo.jsp"><i class="fas fa-solid fa-trophy"></i>Torneos</a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="contaco.jsp"><i class="fas fa-users"></i>Contacta con
                                    nosotros</a>
                            </li>
                            <li class="nav-item menu_nav-select with-acount">
                                <i class="fa-solid fa-user"></i>
                                <select class="nav-link menu_nav-select select-resp" name="" id="Select" onchange="navigateToPage(this.value)">
                                    <option value="" disabled selected hidden>Cuenta</option>
                                    <option value="perfil" class="hero_cta">Perfil</option>
                                    <option value="turnos" class="hero_cta">Turnos</option>
                                    <option value="gestion" class="">Gestionar</option> <!-- Visible solo para el administrador -->
                                    <option value="cerrarSesion">Cerrar Sesión</option>
                                </select>
                            </li>
                        <% } else { %>
                             <li class="nav-item">
                                <a class="nav-link" href="contaco.jsp"><i class="fas fa-users"></i>Contacta con
                                    nosotros</a>
                            </li>
                            <li class="nav-item menu_nav-select with-acount">
                                <i class="fa-solid fa-user"></i>
                                <select class="nav-link menu_nav-select select-resp" name="" id="Select" onchange="navigateToPage(this.value)">
                                    <option value="" disabled selected hidden>Cuenta</option>
                                    <option value="perfil" class="hero_cta">Perfil</option>
                                    <option value="turnos" class="hero_cta">Turnos</option>
                                    <option value="cerrarSesion">Cerrar Sesión</option>
                                </select>
                            </li>
                        <% } %>
                    </ul>
                </div>
            </div>  
        </nav>
    </header>
                    <%
                        Integer estadoReserva = (Integer) session.getAttribute("estadoReserva");

                        if (estadoReserva != null) {
                            if (estadoReserva == 1) {
                    %>
                                <div class="notification success">Reserva exitosa</div>
                    <%
                            } else if (estadoReserva == 2) {
                    %>
                                <div class="notification error">Hubo un error</div>
                    <%
                            }
                        } else {
                           session.removeAttribute("mensajeError");
                        }

                        // Limpiar los atributos para que no persistan en la siguiente solicitud
                        session.removeAttribute("estadoReserva");
                    %>

      
    <section class="modal modal-perfil">
        <div class="modal_container">
            <a href="#" class="modal_close modal_close-perfil" onclick="closeModal()">&times;</a>
            <i class="fa-solid fa-user user-perfil"></i>
            <h2 class="modal_title">Información del Perfil</h2>
            <p class="modal_p">ID: <%=request.getSession().getAttribute("id")%></p>
            <p class="modal_p">Nombre de Usuario: <%=request.getSession().getAttribute("nombre")%></p>
            <p class="modal_p">Email: <%=request.getSession().getAttribute("mail")%></p>
            <form action="svModificarUsuario" method="GET"></form>  
            <form action="svModificarUsuario" method="GET" class="form-modif">
            <button type="submit" class="modal_buttonn hero_cta"><i class="fa-solid fa-pen-to-square icon-button"></i>Modificar Información</button>
            <input type="hidden" value="<%=request.getSession().getAttribute("id")%>" name="idUsuario"> 
            </form>
        </div>
    </section>


<section class="modal modal-turnos">
    <div class="modal_container">
        <a href="#" class="modal_close modal_close-turnos" onclick="closeModal()">&times;</a>
        <h2 class="modal_title"><i class="fa-solid fa-pen-to-square icon-button icon-buttonn"></i>Turnos Agendados</h2>
        <table class="table table-turnos">
            <thead>
                <tr>
                    <th scope="col">Turno</th>
                    <th scope="col">Día</th>
                    <th scope="col">Horario</th>
                    <th scope="col">Cancha</th>
                    <th scope="col"></th>
                </tr>
            </thead>
            <tbody>
                <%
                    List<reserva> listaReserva = (List<reserva>) request.getSession().getAttribute("listaReserva");
                    String idUsuarioLogueado = (String) request.getSession().getAttribute("id"); // Obtenemos el ID del usuario logueado
                    int idUsuarioLogueadoInt = Integer.parseInt(idUsuarioLogueado); // Convertimos el id a int
                    int contador = 1; // Inicializamos el contador en 1
                    boolean tieneReservas = false; // Bandera para saber si el usuario tiene reservas

                    if (listaReserva == null || listaReserva.isEmpty()) {
                %>
                        <tr><td colspan="5">No hay turnos agendados.</td></tr>
                <%
                    } else {
                        for (reserva res : listaReserva) {
                            // Filtrar las reservas por el id del usuario logueado (comparación de int)
                            if (res.getUsuario().getId() == idUsuarioLogueadoInt) {  
                                tieneReservas = true;  // El usuario tiene al menos una reserva
                %>
                                <tr>
                                    <td><%= contador++ %></td> <!-- Mostrar el número de la lista -->
                                    <td><fmt:formatDate value="<%= res.getDia() %>" pattern="dd/MM/yyyy" /></td>
                                    <td><%= res.getHorario() %></td>
                                    <td><%= res.getCancha().getId() %></td> <!-- Asegúrate de tener el método 'getNumeroCancha()' en la clase Cancha -->
                                   <form action="svEliminarReserva" method="POST">
                                    <td><button type="submit" onclick="return confirmarEliminarReserva()"><i class="fa-solid fa-trash"></i></button></td>
                                    <input type="hidden" name="id" value="<%=res.getId()%>">
                                    <input type="hidden" name="fromPage" value="menu">
                                   </form>
                                </tr>
                <%
                            }
                        }

                        // Si después de recorrer todas las reservas no hay ninguna del usuario, mostramos el mensaje
                        if (!tieneReservas) {
                %>
                            <tr><td colspan="5">No hay turnos agendados.</td></tr>
                <%
                        }
                    }
                %>
            </tbody>
        </table>
    </div>
</section>

    <main class="menu_main">
        <section class="menu_section-main">
            <div id="photoCarousel" class="carousel slide" data-bs-ride="carousel">
                <!-- Indicadores del carrusel -->
                <div class="carousel-indicators">
                    <button type="button" data-bs-target="#photoCarousel" data-bs-slide-to="0" class="active"
                        aria-current="true" aria-label="Slide 1"></button>
                    <button type="button" data-bs-target="#photoCarousel" data-bs-slide-to="1"
                        aria-label="Slide 2"></button>
                    <button type="button" data-bs-target="#photoCarousel" data-bs-slide-to="2"
                        aria-label="Slide 3"></button>
                </div>
                <!-- Contenido del carrusel -->
                <div class="carousel-inner">
                    <div class="carousel-item active">
                        <img src="Img/cancha3.jpg" class="d-block w-100" alt="Cancha 1">
                    </div>
                    <div class="carousel-item">
                        <img src="Img/cancha2.jpg" class="d-block w-100" alt="Cancha 2">
                    </div>
                    <div class="carousel-item">
                        <img src="Img/cancha1.jpg" class="d-block w-100" alt="Cancha 3">
                    </div>
                </div>
                <!-- Controles de navegaciÃ³n -->
                <button class="carousel-control-prev" type="button" data-bs-target="#photoCarousel"
                    data-bs-slide="prev">
                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Anterior</span>
                </button>
                <button class="carousel-control-next" type="button" data-bs-target="#photoCarousel"
                    data-bs-slide="next">
                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                    <span class="visually-hidden">Siguiente</span>
                </button>
            </div>
            
            
            <div class="reserva-section">
                <h2>Reserva de Cancha</h2>
                <c:if test="${not empty mensajeError}">
                    <p style="color: red; text-align: center;  font-size: 18px">${mensajeError}</p>
                </c:if>
                <form action="svReserva" method="POST">
                    <!-- Primera fila: Número de cancha -->
                    <div class="form-row">
                        <div class="form-group">
                            <label for="cancha">Número de Cancha</label>
                            <select name="cancha" id="cancha" required>
                                <option value="1">Cancha 1</option>
                                <option value="2">Cancha 2</option>
                                <option value="3">Cancha 3</option>
                            </select>
                        </div>

                        <div class="form-group">
                            <label for="fecha">Día</label>
                            <input type="date" id="fecha" name="fecha" required>
                        </div>

                        <div class="form-group">
                            <label for="hora">Hora</label>
                            <input type="time" id="hora" name="horario" min="08:00" max="00:00" step="1800" required> <!-- 1800 segundos = 30 minutos -->
                        </div>
                    </div>

                    <!-- Segunda fila: Datos personales -->
                    <div class="form-row">
                        <div class="form-group">
                            <label for="nombre">Nombre y Apellido</label>
                            <input type="text" id="nombre" name="nombre" placeholder="Nombre y Apellido" required>
                        </div>

                        <div class="form-group">
                            <label for="telefono">Número de Teléfono</label>
                            <input type="tel" id="telefono" name="telef" placeholder="Teléfono" required>
                        </div>

                        <div class="form-group">
                            <label for="dni">DNI</label>
                            <input type="text" id="dni" name="dni" placeholder="DNI" required>
                        </div>
                    </div>
                    

                    <!-- Botón de enviar -->
                    <button type="submit">Reservar Cancha</button>
                </form>

            </div>
        <div class="menu_main-grid">
            <section class="menu_section">
                <div class="menu_section-ubi">
                    <label for="" class="menu-label">Encuentranos Aquí</label>
                    <iframe
                        src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d351.1366134105441!2d-59.30895426033219!3d-33.1460484005007!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x95b09ca04391d571%3A0x49cc8f71a8d57e24!2sEscuela%20Superior%20N%C2%B0%208%20%22Celestino%20Irineo%20Marc%C3%B3%22!5e0!3m2!1ses-419!2sar!4v1715637808003!5m2!1ses-419!2sar"
                        width="100%" height="450" style="border:0;" allowfullscreen="" loading="lazy"
                        referrerpolicy="no-referrer-when-downgrade"></iframe>
                </div>
            </section>
            <aside class="menu_aside">
                <div class="menu_aside-fondoComentarios">
                    <h3>Sección de Comentario</h3>
                    <div class="menu_aside-comentarios" id="commentsContainer"></div>
                    <form class="menu_aside-comentarios-form" id="commentForm">
                        <input type="hidden" value="<%=request.getSession().getAttribute("nombre")%>" id="nameComment">
                        <textarea id="commentText" placeholder="Escribe tu comentario aquí­..." rows="4"></textarea>
                        <button type="submit" class="menu_aside-comentarios-form-button">Agregar comentario</button>
                    </form>
                    <% if (adminEmail.equals(mail)) { %>
                    <button id="clearCommentsButton" class="menu_aside-comentarios-form-button">Borrar</button>
                    <%}%>
                </div>
            </aside>
        </div>
    </main>
    <footer class="login_footer menu_footer">
        <div>
            <p>&copy; 2024 Mi Pgina Web. Todos los derechos reservados.</p>
        </div>
    </footer>
                
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/5.1.3/js/bootstrap.min.js"></script>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
    <script src="codigo.js"></script>

</body>

</html>