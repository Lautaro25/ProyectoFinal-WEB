<%@page import="java.util.List"%>
<%@page import="logica.torneo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
</head>

<body class="login_body">
    <header>
        <nav class="navbar navbar-expand-lg">
            <div class="container-fluid">
                <div class="menu_header-img"><img src="Img/iconoWeb2.ico" alt=""></div>
                <a class="navbar-brand" href="index.html">
                    <h2>Página Principal</h2>
                </a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#menu"
                    aria-controls="menu" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse justify-content-end" id="menu">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link nav-li" href="Menú.html"><i class="fas fa-home"></i>Inicio</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link nav-li" href="index.html">Volver</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    </header>
    <div class="container">
        <h1 class="title">Registro para Torneos de Pádel</h1>

        <!-- Formulario de Registro de Equipos y Jugadores -->
            <main>
                    <%
                        Integer estadoTorneo = (Integer) session.getAttribute("estadoTorneo");

                        if (estadoTorneo != null) {
                            if (estadoTorneo == 1) {
                    %>
                                <div class="notification success">Equipo registrado!</div>
                    <%
                            } else if (estadoTorneo == 2) {
                    %>
                                <div class="notification error">Hubo un error</div>
                    <%
                            }
                        } else {
                           session.removeAttribute("mensajeError");
                        }

                        // Limpiar los atributos para que no persistan en la siguiente solicitud
                        session.removeAttribute("estadoTorneo");
                    %>
        <div class="form-container">
            <h2 class="form-title">Registrar Equipo y Jugadores</h2>
            
            <form id="teamPlayerForm" class="form" action='svTorneo' method='POST'>
                <!-- Datos del Equipo -->
                <div class="form-group mb-3">
                    <label for="teamName" class="form-label">Nombre del Equipo:</label>
                    <input type="text" id="teamName" name="teamName" class="form-control" required>
                    <span id="teamNameError" class="error-message" style="display: none; color: red;"></span>
                </div>
                
                <!-- Jugador 1 -->
                <div class="form-group mb-3">
                    <label for="player1Name" class="form-label">Nombre del Jugador 1:</label>
                    <input type="text" id="player1Name" name="player1Name" class="form-control" required>
                    <span id="player1NameError" class="error-message" style="display: none; color: red;"></span>
                </div>
                
                <!-- Jugador 2 -->
                <div class="form-group mb-3">
                    <label for="player2Name" class="form-label">Nombre del Jugador 2:</label>
                    <input type="text" id="player2Name" name="player2Name" class="form-control" required>
                    <span id="player2NameError" class="error-message" style="display: none; color: red;"></span>
                </div>

                <!-- Botón de Enviar -->
                <button type="submit" class="btn btn-primary">Registrar Equipo y Jugadores</button>
            </form>
        </div>
        
        <!--Seccion para Modificar Equipos y Jugadores-->
        <h2>Modificar Equipos</h2>
        <p>Ingrese el ID del Equipo que desea modificar</p>
        <form action="svModificarEquipo" method="GET">
        <p><label> ID:         </label><input type="text" name="idEquipoModif" required> </p>
         <button type='submit' class="btn btn-primary">Modificar Equipo </button>
        </form>

        <!-- Sección para listar Equipos y Jugadores -->
        <div class="list-container mt-5">
            <h2 class="list-title">Equipos Registrados</h2>
<table id="usuarios-table" class="display table table-striped">
    <thead>
        <tr>
                        <th scope="col">Id</th>
                        <th scope="col">Equipo</th>
                        <th scope="col">Jugador N°1</th> 
                        <th scope="col">Jugador N°2</th>  
                        <th></th>
        </tr>
    </thead>
                <tbody>
                    <%
                       List<torneo> listaTorneo = (List<torneo>) request.getSession().getAttribute("listaTorneo");
                       int contador = 1; //inicializamos el contador en 1
                       if (listaTorneo == null || listaTorneo.isEmpty()) {
                    %>
                    <tr><td colspan="5"> No hay equipos registrados.</tr></td>
                    <%  }else{
                           for(torneo tor : listaTorneo){%> 
                           <tr>
                              
                               <td><%= tor.getId()%></td>
                               <td><%= tor.getNombreEquipo() %></td>
                               <td><%= tor.getNombreJugador1() %></td>
                               <td><%= tor.getNombreJugador2() %></td
                               
                                <!--Boton de Eliminar-->
                               <td>
                                  <form style="display:inline;" action="svEliminarEquipo" method="POST">
                                       <button type="submit" onclick="return confirmarEliminarTorneo()">
                                           <i class="fa-solid fa-trash"></i></button>
                                           <input type="hidden" name="idEquipoo" value="<%= tor.getId() %>">
                                  </form>
                               </td>
                           </tr>
                         
                           
                           <%}}

                    %>                  
                </tbody>
</table>
                
        </div>
    </div>
                <footer class="login_footer" style="margin-top: 50px">
        <div>
            <p>&copy; 2024 Mi Página Web. Todos los derechos reservados.</p>
        </div>
    </footer>
    <script src="torneo.js"></script>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
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

