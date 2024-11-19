<%@page import="logica.usuario"%>
<%@page import="java.util.List"%>
<%@page import="logica.reserva"%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="CSS/index.css">
    <script src="https://kit.fontawesome.com/2f7feb84f1.js" crossorigin="anonymous"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat&display=swap" rel="stylesheet">
    <link rel="icon" href="Img/iconoWeb2.ico">
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.11.5/css/jquery.dataTables.min.css">
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.min.js"></script>
    <title>Gestión</title>  
</head>
<body class="login_body">
    <header>
        <nav class="navbar navbar-expand-lg">
            <div class="container-fluid">
                <div class="menu_header-img"><img src="Img/iconoWeb2.ico" alt=""></div>
                <a class="navbar-brand" href="Menu.jsp">
                    <h2>Página Principal</h2>
                </a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#menu"
                    aria-controls="menu" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse justify-content-end" id="menu">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link nav-li" href="Menu.jsp"><i class="fa-solid fa-arrow-rotate-left"></i> Volver</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    </header>
    <div class="container my-5" >
        <h2>Gestión de Turnos de Canchas</h2>
        
        <!-- Filtros de búsqueda -->
        <div class="row mb-3">
            <div class="col-md-4">
                <label for="cancha-filter">Cancha:</label>
                <select id="cancha-filter" class="form-select">
                    <option value="">Todas</option>
                    <option value="1">Cancha N°1</option>
                    <option value="2">Cancha N°2</option>
                    <option value="3">Cancha N°3</option>
                </select>
            </div>
            <div class="col-md-4">
                <label for="date-filter">Día:</label>
                <input type="date" id="date-filter" class="form-control">
            </div>
            <div class="col-md-4">
                <label for="time-filter">Hora:</label>
                <input type="time" id="time-filter" class="form-control">
            </div>
        </div>
        
        <!-- Tabla -->
        <table id="turnos-table" class="display table table-striped">
            <thead>
                <tr>
                    <th>#ID</th>
                    <th>ID del Usuario</th>
                    <th>Nombre y Apellido</th>
                    <th>Teléfono</th>
                    <th>DNI</th>
                    <th>Cancha</th>
                    <th>Día</th>
                    <th>Hora</th>
                </tr>
            </thead>
            <tbody>
                                                <%
                    List<reserva> listaReserva = (List<reserva>) request.getSession().getAttribute("listaReserva");
                    int contador = 1; // Inicializamos el contador en 1

                    if (listaReserva == null || listaReserva.isEmpty()) {
                %>
                        <tr><td colspan="8">No hay turnos agendados.</td></tr>
                <%
                    } else {
                        for (reserva res : listaReserva) {
                            %>
                                <tr>
                                    <td><%= contador++ %></td> <!-- Mostrar el número de la lista -->
                                    <td><%= res.getUsuario().getId() %></td>
                                    <td><%= res.getNombreApellido() %></td>
                                    <td><%= res.getTelefono() %></td>
                                    <td><%= res.getDni() %></td>
                                    <td><%= res.getCancha().getId() %></td> <!-- Asegúrate de tener el método 'getNumeroCancha()' en la clase Cancha -->
                                    <td><fmt:formatDate value="<%= res.getDia() %>" pattern="yyyy-MM-dd" /></td>
                                   <form action="svEliminarReserva" method="POST">
                                    <td class="contact">
                                        <div class="contact-container">
                                        <%= res.getHorario() %>
                                        <button type="submit" onclick="return confirmarEliminarReserva()"><i class="fa-solid fa-trash"></i></button>
                                    <input type="hidden" name="id" value="<%=res.getId()%>">
                                    <input type="hidden" name="fromPage" value="gestion">
                                        </div>
                                    </td>
                                   </form>
                                </tr>
                <%
                            
                        }
                    }
                %>
            </tbody>
        </table>


    <!-- Nueva Tabla de Usuarios -->
<h2 class="mt-5">Gestión de Usuarios</h2>

<!-- Tabla -->
<table id="usuarios-table" class="display table table-striped">
    <thead>
        <tr>
            <th>ID Usuario</th>
            <th>Nombre</th>
            <th>Email</th>
        </tr>
    </thead>
    <tbody>
                    <%
                List<usuario> listaUsuario = (List<usuario>) request.getSession().getAttribute("listaUsuario");
                int cont = 1;

                if (listaUsuario == null || listaUsuario.isEmpty()) {
            %>
                    <tr><td colspan="7">No hay usuarios registrados.</td></tr>
            <%
                } else {
                    for (usuario usu : listaUsuario) {
            %>
                        <tr>
                            <td><%= usu.getId() %></td>
                            <td><%= usu.getNombre() %></td>
                            <form action="svEliminarUsuario" method="POST">
                                <td class="contact">
                                    <div class="contact-container">
                                        <%= usu.getMail() %>
                                        <button type="submit" onclick="return confirmarYEliminar()">
                                            <i class="fa-solid fa-trash"></i>
                                        </button>
                                    </div>
                                </td>
                                <input type="hidden" name="id" value="<%= usu.getId() %>">
                            </form>
                        </tr>
            <%
                    }
                }
            %>
    </tbody>
</table>
</div>
    <footer class="login_footer">
        <div>
            <p>&copy; 2024 Mi Página Web. Todos los derechos reservados.</p>
        </div>
    </footer>
  <script src="codigo.js"></script>    
<script>
    $(document).ready(function() {
        var usuariosTable = $('#usuarios-table').DataTable();

        // Filtro de búsqueda
        $('#user-search').on('keyup', function() {
            usuariosTable.search(this.value).draw();
        });
    });

        $(document).ready(function() {
            var table = $('#turnos-table').DataTable();

            // Filtro por cancha
            $('#cancha-filter').on('change', function() {
                var cancha = $(this).val();
                table.column(5).search(cancha).draw();
            });

            // Filtro por día
            $('#date-filter').on('change', function() {
                var date = $(this).val();
                table.column(6).search(date).draw();
            });

            // Filtro por hora
            $('#time-filter').on('change', function() {
                var time = $(this).val();
                table.column(7).search(time).draw();
            });
        });
    </script>
</body>
</html>
