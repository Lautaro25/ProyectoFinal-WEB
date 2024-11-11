<%-- 
    Document   : modificarEquipo
    Created on : 24 oct. 2024, 22:09:37
    Author     : User
--%>
<%@page import="java.util.List"%>
<%@page import="logica.torneo"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
         <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="CSS/index.css">

    <script src="https://kit.fontawesome.com/2f7feb84f1.js" crossorigin="anonymous"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat&display=swap" rel="stylesheet">
    <script src="https://kit.fontawesome.com/2f7feb84f1.js" crossorigin="anonymous"></script>
    <link rel="icon" href="Img/iconoWeb2.ico">
        <title>Modificar Equipos</title>
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
    <div class="form-container">
        <% torneo torn = (torneo) request.getSession().getAttribute("tornModif");%>
        
        <h1>Datos del equipo</h1
        <form id="teamPlayerForm" class="form" action='svModificarEquipo' method='POST'></form>     
         <form id="teamPlayerForm" class="form" action='svModificarEquipo' method='POST'>
                <!-- Datos del Equipo -->
                <div class="form-group mb-3">
                    <label for="teamName" class="form-label">Nombre del Equipo:</label>
                    <input type="text" id="teamName" name="teamName" class="form-control" value="<%=torn.getNombreEquipo() %>" >
                </div>
                
                <!-- Jugador 1 -->
                <div class="form-group mb-3">
                    <label for="player1Name" class="form-label">Nombre del Jugador 1:</label>
                    <input type="text" id="player1Name" name="player1Name" class="form-control" value="<%=torn.getNombreJugador1() %>" >
                </div>
                
                <!-- Jugador 2 -->
                <div class="divform-group mb-3">
                    <label for="player2Name" class="form-label">Nombre del Jugador 2:</label>
                    <input type="text" id="player2Name" name="player2Name" class="form-control" value="<%=torn.getNombreJugador2() %>" >
                </div>
                
                 
                <!-- Botón de Guardar -->
                
                <button type="submit" class="btn btn-primary">Guardar</button>
             
         </form>
    </div>
    </div>                
                 <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
    </body>
</html>
