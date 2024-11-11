<%@page import="logica.cancha"%>
<%@page import="persistencia.controladoraPersistencia"%>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="CSS/index.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <script src="https://kit.fontawesome.com/2f7feb84f1.js" crossorigin="anonymous"></script>
    <link rel="icon" href="Img/iconoWeb2.ico">
    <title>Complejo Deportivo</title>
</head>

<!--Desarrollado por Lautaro Villabona-->

<body class="index_body">

        <% 
        controladoraPersistencia controlPersis = new controladoraPersistencia();
            cancha cancha1 = new cancha(1, 1, true, null);
            cancha cancha2 = new cancha(2, 2, true, null);
            cancha cancha3 = new cancha(3, 3, true, null);

            controlPersis.crearCancha(cancha1);
            controlPersis.crearCancha(cancha2);
            controlPersis.crearCancha(cancha3);
        %>

    
    <main class="index_main">
        <div class="index_main_div">
            <div class="index_main_div-img"><img src="Img/iconoWeb2.ico" alt=""></div>
            <div class="index_main_div-h1">
                <h2>Complejo Deportivo</h2>
            </div>
            <div class="index_main_div-direc">
                <p>Whatsapp: 3444-620583. Dirección: 25 de Mayo 1930</p>
            </div>
            <div class="index_main_div-redes">
                <a href="https://es-la.facebook.com/" target="_blank"><i class="fa-brands fa-facebook"></i></a>
                <a href="https://www.instagram.com/" target="_blank"><i class="fa-brands fa-instagram"></i></i></a>
                <a href="https://web.whatsapp.com/" target="_blank"><i class="fa-brands fa-whatsapp"></i></a>
            </div>
            <div class="index_main_div-alquiler">
                <form action="Login.jsp" id="Input_Menu"><i class="fa-solid fa-user"></i><input type="submit"
                        value="Iniciar Sesión"></form>

            </div>
            <div class="index_main_div-alquiler">
                <form action="registro.jsp" id="Input_Menu"><i class="fa-solid fa-user-plus"></i><input type="submit"
                        value="Registrarse"></form>
            </div>
        </div>
    </main>



    <script src="codigo.js"></script>


    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>

</html>