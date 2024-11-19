<%-- 
    Document   : modificarUsuario
    Created on : 28 oct 2024, 8:12:21 p. m.
    Author     : lauta
--%>

<%@page import="logica.usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="es">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Modificación de Usuario</title>
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
    <main>
        <div class="login_formulario">
            <h2 class="login_formulario-h2">Modificar Usuario</h2>
            <form action="svModificarUsuario" method="POST">

                <div class="login_formulario-inputs">
                    <%usuario usu = (usuario) request.getSession().getAttribute("usuModif");%>
                    <input type="text" name="name" id="usuario" value="<%=usu.getNombre()%>"  placeholder="Ingrese su nuevo Nombre" required>
                        <input type="password" name="contrasenia" class="pass" id="pass" 
                               placeholder="Ingrese su nueva contraseña" required="">
                </div>
                    <button class="login_formulario-boton" type="submit">Aplicar</button>
                    
            </form>
            <form action="Menu.jsp">
                <button class="login_formulario-boton" type="submit">Regresar</button>
            </form>
        </div>
    </main>
    </div>
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
