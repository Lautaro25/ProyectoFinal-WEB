<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Formulario de Registro</title>
    <script src="https://kit.fontawesome.com/2f7feb84f1.js" crossorigin="anonymous"></script>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="CSS/index.css">
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
                            <a class="nav-link" href="contaco.jsp"><i class="fas fa-users"></i>Contacta con nosotros</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    </header>
    

    <main>
                            <%
                        Integer estadoRegistro = (Integer) session.getAttribute("estadoRegistro");

                        if (estadoRegistro != null) {
                            if (estadoRegistro == 1) {
                    %>
                                <div class="notification success">Registro Exitoso</div>
                    <%
                            } else if (estadoRegistro == 2) {
                    %>
                                <div class="notification error">Hubo un error</div>
                    <%
                            }
                        } else {
                           session.removeAttribute("mensajeError");
                        }

                        // Limpiar los atributos para que no persistan en la siguiente solicitud
                        session.removeAttribute("estadoRegistro");
                    %>
         <div class="login_formulario">
            <h2 class="login_formulario-h2">Registro</h2>
            <form action="svRegistro" id="register-form" class="needs-validation" method="post" novalidate>
                <c:if test="${not empty errorMessage}">
                    <p style="color: red; text-align: center; font-size: 18px">${errorMessage}</p>
                </c:if>
                           <div class="login_formulario-inputs">
                    <input type="email" id="email" name="email" class="form-control" placeholder="Ingrese su email" required>
                    <div class="invalid-feedback">Por favor ingrese un correo válido.</div>
                    <input type="text" id="username" name="nombre" class="form-control" placeholder="Ingrese su nombre y apellido" required>
                    <div class="invalid-feedback">Por favor ingrese su nombre y apellido.</div>
            
                    <div class="password-container">
                        <input type="password" id="password" name="contrasenia" class="form-control" placeholder="Ingrese su contraseña" required minlength="8">
                        <div class="invalid-feedback">La contraseña debe tener al menos 8 caracteres.</div>
                    </div>
            
                    <div class="password-container">
                        <input type="password" id="confirm-password" name="confirm-password" class="form-control" placeholder="Confirme su contraseña" required>
                        <div class="invalid-feedback">Las contraseñas no coinciden.</div>
                    </div>
                </div>

                <div class="login_desvio">
                    <p>¿Ya tienes cuenta? Inicia sesión <a href="Login.jsp">aquí.</a></p>
                </div>

                <button class="login_formulario-boton btn btn-primary" type="submit">Registrarse</button>
            </form>

            <button class="login_formulario-boton btn btn-secondary" type="button" onclick="window.location.href='index.jsp'">Regresar</button>
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
        <script type="text/javascript" src="codigo.js"></script>
</body>
</html>