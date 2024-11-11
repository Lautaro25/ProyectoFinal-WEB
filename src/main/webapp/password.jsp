<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Recuperación de Contraseña</title>
    <link rel="stylesheet" href="CSS/index.css">
    <script src="https://kit.fontawesome.com/2f7feb84f1.js" crossorigin="anonymous"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat&display=swap" rel="stylesheet">
    <link rel="icon" href="Img/iconoWeb2.ico">
</head>

<!--Desarrollado por Villabona Lautaro-->
<body class="login_body">
    <header>
        <nav class="navbar navbar-expand-lg">
            <div class="container-fluid">
                <div class="menu_header-img"><img src="Img/iconoWeb2.ico" alt=""></div>
                <a class="navbar-brand" href="#">
                    <h2>Página Principal</h2>
                </a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#menu"
                    aria-controls="menu" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse justify-content-end" id="menu">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link" href="contaco.html"><i class="fas fa-users"></i>Contacta con nosotros</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    </header>

    <main>
                    <%
                        Integer estadoRecuperar = (Integer) session.getAttribute("estadoRecuperar");

                        if (estadoRecuperar != null) {
                            if (estadoRecuperar == 1) {
                    %>
                                <div class="notification success">Mensaje enviado. Revisar su correo!</div>
                    <%
                            } else if (estadoRecuperar == 2) {
                    %>
                                <div class="notification error">Hubo un error</div>
                    <%
                            }
                        } else {
                           session.removeAttribute("mensajeError");
                        }

                        // Limpiar los atributos para que no persistan en la siguiente solicitud
                        session.removeAttribute("estadoRecuperar");
                    %>
        <div class="login_formulario">
            <label for=""><i class="fa-solid fa-unlock"></i></label>
            <h2 class="login_formulario-h2">Recuperar Contraseña</h2>
            <form action="svRecuperacion" method="POST">
                <c:if test="${not empty errorMessage}">
                    <p style="color: red; text-align: center; font-size: 18px">${errorMessage}</p>
                </c:if>
                <div class="login_formulario-inputs login_formulario-password">
                    <input type="email" name="mail" id="usuario" required
                        placeholder="Ingrese su email">
                </div>
                    <button class="login_formulario-boton" type="submit">Verificar</button>
            </form>
                <form action="registro.jsp">
                <button class="login_formulario-boton" type="submit">Regresar</button>
        </div>
    </main>
    <footer class="login_footer">
        <div>
            <p>&copy; 2024 Mi Página Web. Todos los derechos reservados.</p>
        </div>
    </footer>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
</body>
</html>