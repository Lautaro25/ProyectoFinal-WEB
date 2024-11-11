<!DOCTYPE html>
<html lang="en">
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
    <title>Contacto</title>
</head>

<!--Desarrollado por Alejo Zapatiel--> 

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
                            <a class="nav-link nav-li" href="index.jsp"><i class="fa-solid fa-arrow-rotate-left"></i> Volver</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>
    </header>
    <main>
        <div class="login_formulario">
            <h2 class="login_formulario-h2">Contactanos</h2>
            <form action="agregar URL" method="post">

                <div class="login_formulario-inputs">
                    <input type="nombre" name="usuario" id="usuario" 
                        placeholder="Ingrese su nombre" required>
                    <input type="email" name="email" id="email"
                        placeholder="Ingrese su mail" required>
                        <input type="tel" name="tel" id="tel" 
                        placeholder="Ingrese su telefono" required>   
                                            
                        <select class="select_contacto" name="deporte" id="deporte" required>
                            <option value="" disabled selected hidden>Seleccione un deporte</option>
                            <option value="FÃºtbol">Fútbol</option>
                            <option value="Tenis">Tenis</option>
                            <option value="Padel">Padel</option>
                        </select>
                        <textarea class="contacto_txt" name="comentario" rows="5" cols="50"  placeholder="Escriba el mensaje" required></textarea>

                </div>

                

                <form action="">
                    <button class="login_formulario-boton" type="submit"><i class="fa-solid fa-handshake"></i>Contactar</button>
                </form>
                <form action="index.jsp">
                    <button type="submit" class="login_formulario-boton">Regresar</button>
                </form>
        </div>
    </main>
    </div>
    <footer class="login_footer">
        <div>
            <p>&copy; 2024 Mi PÃ¡gina Web. Todos los derechos reservados.</p>
        </div>
    </footer>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"></script>
        <script type="text/javascript" src="codigo.js"></script>
</body>
</html>