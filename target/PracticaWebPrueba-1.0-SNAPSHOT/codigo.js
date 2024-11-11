
function navigateToPage() {
    let select = document.getElementById('Select');
    let url = select.value;
    if (url) {
        window.location.href = url;
    }
}

function navigateToPage(url) {
    if (url === 'perfil') {
const modal = document.querySelector('.modal-perfil');
const closeModal = document.querySelector('.modal_close-perfil');
modal.classList.add('modal--show');

closeModal.addEventListener('click',(e)=>{
    e.preventDefault();
    modal.classList.remove('modal--show');
    // Restablece el valor del select al valor predeterminado
    const select = document.getElementById('Select');
    select.value = '';
});
    }else if (url === 'turnos'){
const modal = document.querySelector('.modal-turnos');
const closeModal = document.querySelector('.modal_close-turnos');
modal.classList.add('modal--show');

closeModal.addEventListener('click',(e)=>{
    e.preventDefault();
    modal.classList.remove('modal--show');
    // Restablece el valor del select al valor predeterminado
    const select = document.getElementById('Select');
    select.value = '';
});
    } else if (url === 'gestion') {
        window.location.href = 'gestion.jsp';
    } else if (url === 'cerrarSesion'){
        window.location.href = 'Login.jsp';
    }
}

function modificarPerfil(){
    const modalp = document.querySelector('.modal-perfil');
    const modal = document.querySelector('.modal-modificar');
const closeModal = document.querySelector('.modal_close-modificar');
modal.classList.add('modal--show');
modalp.classList.remove('modal--show');

closeModal.addEventListener('click',(e)=>{
    e.preventDefault();
    modal.classList.remove('modal--show');
    // Restablece el valor del select al valor predeterminado
    const select = document.getElementById('Select');
    select.value = '';
});
}



const MAX_COMMENTS = 15;

document.addEventListener('DOMContentLoaded', function () {
    loadComments();

    document.getElementById('commentForm').addEventListener('submit', function (event) {
        event.preventDefault(); // Evitar que el formulario se envíe por defecto
        addComment();
    });

    document.getElementById('clearCommentsButton').addEventListener('click', function () {
        clearComments();
    });
});

function loadComments() {
    const commentsContainer = document.getElementById('commentsContainer');
    const comments = JSON.parse(localStorage.getItem('comments')) || [];
    commentsContainer.innerHTML = '';

    // Iterar a través de los comentarios en orden inverso para que los más recientes aparezcan arriba
    for (let i = comments.length - 1; i >= 0; i--) {
        commentsContainer.insertBefore(createCommentElement(comments[i]), commentsContainer.firstChild);
    }
}

function addComment() {
    const commentText = document.getElementById('commentText').value.trim();
    if (commentText === '') {
        alert('Por favor, escribe un comentario.');
        return;
    }

    let comments = JSON.parse(localStorage.getItem('comments')) || [];

    if (comments.length >= MAX_COMMENTS) {
        comments.shift(); // Eliminar el comentario más antiguo si se alcanza el límite
    }

    const now = new Date();
    let name = document.getElementById('nameComment').value;
    const newComment = {
        author: name,
        text: commentText,
        avatar: 'https://via.placeholder.com/50',
        date: formatDate(now), // Formatear la fecha
        time: formatTime(now) // Formatear la hora
    };

    comments.unshift(newComment); // Agregar el comentario al principio del array
    localStorage.setItem('comments', JSON.stringify(comments));

    // Insertar el nuevo comentario al principio del contenedor
    const commentsContainer = document.getElementById('commentsContainer');
    commentsContainer.insertBefore(createCommentElement(newComment), commentsContainer.firstChild);
    document.getElementById('commentText').value = '';
}

function clearComments() {
    localStorage.removeItem('comments');
    document.getElementById('commentsContainer').innerHTML = '';
}

function createCommentElement(comment) {
    const commentDiv = document.createElement('div');
    commentDiv.classList.add('menu_aside-comentarios-content');
    commentDiv.innerHTML = `
        <div>
            <div class="menu_aside-comentarios-author">${comment.author}</div>
            <div class="menu_aside-comentarios-text">${comment.text}</div>
            <div class="menu_aside-comentarios-date">${comment.date} ${comment.time}</div>
        </div>
    `;
    return commentDiv;
}

function formatDate(date) {
    const options = { year: 'numeric', month: 'numeric', day: 'numeric' };
    return date.toLocaleDateString('es-ES', options);
}

function formatTime(date) {
    const options = { hour: 'numeric', minute: 'numeric' };
    return date.toLocaleTimeString('es-ES', options);
}


/*Ventana de Alquiler*/
// Función para establecer la fecha mínima como la fecha actual
const today = new Date().toISOString().split('T')[0];
document.getElementById("fecha").setAttribute("value", today);
document.getElementById("fecha").setAttribute("min", today);

// Obtener la hora actual
const now = new Date();
let hours = now.getHours();
let minutes = now.getMinutes();

// Redondear los minutos al próximo intervalo de 30 minutos
if (minutes > 0 && minutes <= 30) {
    minutes = "30";
} else if (minutes > 30) {
    minutes = "00";
    hours += 1; // Aumentar la hora al siguiente si ya es más de 30 minutos
}

// Si la hora es menor de 10, agregar un 0 al inicio
hours = hours < 10 ? `0${hours}` : hours;

// Formatear la hora como "HH:MM"
const timeString = `${hours}:${minutes}`;

// Asignar la hora redondeada al campo de hora
document.getElementById("hora").setAttribute("value", timeString);



// Asignar la función a cada botón de horario con el horario correspondiente
const buttons = document.querySelectorAll('.menu_main-horarios-turnos button');
buttons.forEach(button => {
    button.addEventListener('click', () => {
        Alquiler(button.querySelector('span').innerText);
    });
});

//Validacion formulario registro
document.addEventListener('DOMContentLoaded', function () {
    const emailInput = document.getElementById('email');
    const usernameInput = document.getElementById('username');
    const passwordInput = document.getElementById('password');
    const confirmPasswordInput = document.getElementById('confirm-password');
    const form = document.getElementById('register-form');

    function validateEmail(email) {
        const re = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        return re.test(email);
    }

    function showError(element, message) {
        let errorElement = element.nextElementSibling;
        if (!errorElement || !errorElement.classList.contains('error')) {
            errorElement = document.createElement('div');
            errorElement.className = 'error';
            errorElement.style.color = 'red';
            element.insertAdjacentElement('afterend', errorElement);
        }
        errorElement.textContent = message;
    }

    function clearError(element) {
        let errorElement = element.nextElementSibling;
        if (errorElement && errorElement.classList.contains('error')) {
            errorElement.remove();
        }
    }

    form.addEventListener('submit', function (event) {
        event.preventDefault(); // Evitar el envío del formulario

        let isValid = true;

        // Validación de email
        if (!validateEmail(emailInput.value)) {
            showError(emailInput, 'Por favor ingrese un email válido.');
            isValid = false;
        } else {
            clearError(emailInput);
        }

        // Validación de nombre de usuario
        if (usernameInput.value.trim() === '') {
            showError(usernameInput, 'El nombre de usuario es obligatorio.');
            isValid = false;
        } else {
            clearError(usernameInput);
        }

        // Validación de la contraseña
        if (passwordInput.value.trim() === '') {
            showError(passwordInput, 'La contraseña es obligatoria.');
            isValid = false;
        } else if (passwordInput.value.length < 8) { 
            showError(passwordInput, 'La contraseña debe tener mínimo 8 caracteres');
            isValid = false;
        } else {    
            clearError(passwordInput);
        }

        // Validación de confirmación de contraseña
        if (confirmPasswordInput.value !== passwordInput.value) {
            showError(confirmPasswordInput, 'Las contraseñas no coinciden.');
            isValid = false;
        } else {
            clearError(confirmPasswordInput);
        }

        // Enviar el formulario solo si todas las validaciones pasan
        if (isValid) {
            form.submit();
        }
    });
});


function confirmarYEliminar() {
    return confirm("¿Estás seguro de que deseas eliminar este usuario?");
}
function confirmarEliminarReserva() {
    return confirm("¿Estás seguro de que deseas eliminar su reserva?");
}
function confirmarEliminarTorneo() {
    return confirm("¿Estás seguro de que deseas eliminar el equipo?");
}





