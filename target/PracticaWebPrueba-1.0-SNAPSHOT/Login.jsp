<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inicio Sesión</title>
    <link rel="stylesheet" href="CSS/index.css">
    <script src="https://kit.fontawesome.com/2f7feb84f1.js" crossorigin="anonymous"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css2?family=Montserrat&display=swap" rel="stylesheet">
    <link rel="icon" href="Img/iconoWeb2.ico">
</head>

<!--Desarrollado por Alejo Zapatiel-->

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
                            <a class="nav-link" href="contaco.jsp"><i class="fas fa-users"></i>Contacta con
                                nosotros</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    </header>
 <div class="login_formulario">
                    <%
                        Integer estadoLogin = (Integer) session.getAttribute("estadoLogin");

                        if (estadoLogin != null) {
                            if (estadoLogin == 1) {
                    %>
                                <div class="notification success">Reserva exitosa</div>
                    <%
                            } else if (estadoLogin == 2) {
                    %>
                                <div class="notification error">Hubo un error</div>
                    <%
                            }
                        } else {
                           session.removeAttribute("mensajeError");
                        }

                        session.removeAttribute("estadoLogin");
                    %>
            <h2 class="login_formulario-h2">Iniciar Sesión</h2>
            <form action="/svLogin" method="POST" id="loginForm" onsubmit="return validateForm()" class="needs-validation" novalidate>
                <c:if test="${not empty errorMessage}">
                    <p style="color: red; text-align: center; font-size: 18px">${errorMessage}</p>
                </c:if>

                <div class="login_formulario-inputs">
                    <input type="email" class="form-control" name="mail" id="usuario" placeholder="Ingrese su email" required>
                    <div class="invalid-feedback">Por favor, ingrese un email válido.</div>
                    
                    <input type="password" class="form-control" name="contrasenia" id="pass" placeholder="Ingrese su contraseña" required minlength="8">
                    <div class="invalid-feedback">La contraseña debe tener minimo 8 digitos.</div>
                </div>

                <div class="login_desvio">
                    <a href="password.jsp" class="login_desvio-a">Olvidaste tu contraseña?</a>
                    <p>¿No tienes cuenta? Registrate </p><a href="registro.jsp">aquí­.</a>
                </div>

                <button class="login_formulario-boton" type="submit">Iniciar Sesión</button>
            </form>
            <form action="index.jsp">
                <button class="login_formulario-boton" type="submit">Regresar</button>
            </form>
        </div>

    <footer class="login_footer">
        <div>
            <p>&copy; 2024 Mi Página Web. Todos los derechos reservados.</p>
        </div>
    </footer>
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
    <script src="codigo.js"></script>
    <script >
        //Validacion Login
        (function () {
            const forms = document.querySelectorAll('.needs-validation');
        
            Array.from(forms).forEach(form => {
                form.addEventListener('submit', event => {
                    if (!form.checkValidity()) {
                        event.preventDefault();
                        event.stopPropagation();
                    }
                    form.classList.add('was-validated');
                }, false);
            });
        })();
    </script>
</body>

</html>