document.addEventListener('DOMContentLoaded', function () {
    loadComments();

    const commentForm = document.getElementById('commentForm');
    const clearCommentsButton = document.getElementById('clearCommentsButton');
    
    if (commentForm) {
        commentForm.addEventListener('submit', function (event) {
            event.preventDefault();
            addComment();
        });
    }
    
    if (clearCommentsButton) {
        clearCommentsButton.addEventListener('click', function () {
            clearComments();
        });
    }

    const select = document.getElementById('Select');
    if (select) {
        select.addEventListener('change', function () {
            navigateToPage(this.value);
        });
    }

    const fechaInput = document.getElementById("fecha");
    const horaInput = document.getElementById("hora");
    if (fechaInput) {
        const today = new Date().toISOString().split('T')[0];
        fechaInput.setAttribute("value", today);
        fechaInput.setAttribute("min", today);
    }

    if (horaInput) {
        const now = new Date();
        let hours = now.getHours();
        let minutes = now.getMinutes();

        if (minutes > 0 && minutes <= 30) {
            minutes = "30";
        } else if (minutes > 30) {
            minutes = "00";
            hours += 1;
        }

        hours = hours < 10 ? `0${hours}` : hours;
        const timeString = `${hours}:${minutes}`;
        horaInput.setAttribute("value", timeString);
    }
});

function navigateToPage(url) {
    if (url === 'perfil') {
        showModal('.modal-perfil', '.modal_close-perfil');
    } else if (url === 'turnos') {
        showModal('.modal-turnos', '.modal_close-turnos');
    } else if (url === 'gestion') {
        window.location.href = 'gestion.jsp';
    } else if (url === 'cerrarSesion') {
        window.location.href = 'Login.jsp';
    }
}

function showModal(modalSelector, closeModalSelector) {
    const modal = document.querySelector(modalSelector);
    const closeModal = document.querySelector(closeModalSelector);
    if (modal && closeModal) {
        modal.classList.add('modal--show');
        closeModal.addEventListener('click', function (e) {
            e.preventDefault();
            modal.classList.remove('modal--show');
            const select = document.getElementById('Select');
            if (select) select.value = '';
        });
    }
}

function modificarPerfil() {
    const modalp = document.querySelector('.modal-perfil');
    const modal = document.querySelector('.modal-modificar');
    const closeModal = document.querySelector('.modal_close-modificar');
    if (modalp && modal && closeModal) {
        modal.classList.add('modal--show');
        modalp.classList.remove('modal--show');
        
        closeModal.addEventListener('click', function (e) {
            e.preventDefault();
            modal.classList.remove('modal--show');
            const select = document.getElementById('Select');
            if (select) select.value = '';
        });
    }
}

const MAX_COMMENTS = 15;

function loadComments() {
    const commentsContainer = document.getElementById('commentsContainer');
    if (commentsContainer) {
        const comments = JSON.parse(localStorage.getItem('comments')) || [];
        commentsContainer.innerHTML = '';

        for (let i = comments.length - 1; i >= 0; i--) {
            commentsContainer.insertBefore(createCommentElement(comments[i]), commentsContainer.firstChild);
        }
    }
}

function addComment() {
    const commentText = document.getElementById('commentText');
    const nameComment = document.getElementById('nameComment');
    const commentsContainer = document.getElementById('commentsContainer');
    if (commentText && nameComment && commentsContainer) {
        const text = commentText.value.trim();
        if (text === '') {
            alert('Por favor, escribe un comentario.');
            return;
        }

        let comments = JSON.parse(localStorage.getItem('comments')) || [];

        if (comments.length >= MAX_COMMENTS) {
            comments.shift();
        }

        const now = new Date();
        const newComment = {
            author: nameComment.value || 'Anónimo',
            text: text,
            avatar: 'https://via.placeholder.com/50',
            date: formatDate(now),
            time: formatTime(now)
        };

        comments.unshift(newComment);
        localStorage.setItem('comments', JSON.stringify(comments));
        commentsContainer.insertBefore(createCommentElement(newComment), commentsContainer.firstChild);
        commentText.value = '';
    }
}

function clearComments() {
    localStorage.removeItem('comments');
    const commentsContainer = document.getElementById('commentsContainer');
    if (commentsContainer) commentsContainer.innerHTML = '';
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

function confirmarYEliminar() {
    return confirm("¿Estás seguro de que deseas eliminar este usuario?");
}

function confirmarEliminarReserva() {
    return confirm("¿Estás seguro de que deseas eliminar su reserva?");
}

function confirmarEliminarTorneo() {
    return confirm("¿Estás seguro de que deseas eliminar el equipo?");
}

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
// Validacion registro
document.addEventListener('DOMContentLoaded', function () {
    const form = document.getElementById('register-form');
    const passwordInput = document.getElementById('password');
    const confirmPasswordInput = document.getElementById('confirm-password');

    form.addEventListener('submit', function (event) {
        let isValid = true;

        // Validación de email
        if (!form.email.checkValidity()) {
            form.email.classList.add('is-invalid');
            isValid = false;
        } else {
            form.email.classList.remove('is-invalid');
        }

        // Validación de nombre y apellido
        if (!form.username.checkValidity()) {
            form.username.classList.add('is-invalid');
            isValid = false;
        } else {
            form.username.classList.remove('is-invalid');
        }

        // Validación de la longitud de la contraseña
        if (passwordInput.value.length < 8) {
            passwordInput.classList.add('is-invalid');
            passwordInput.setCustomValidity("La contraseña debe tener al menos 8 caracteres.");
            isValid = false;
        } else {
            passwordInput.classList.remove('is-invalid');
            passwordInput.setCustomValidity("");
        }

        // Validación de coincidencia de contraseñas
        if (passwordInput.value !== confirmPasswordInput.value) {
            confirmPasswordInput.classList.add('is-invalid');
            confirmPasswordInput.setCustomValidity("Las contraseñas no coinciden.");
            isValid = false;
        } else {
            confirmPasswordInput.classList.remove('is-invalid');
            confirmPasswordInput.setCustomValidity("");
        }

        // Detiene el envío si alguna validación falla
        if (!isValid) {
            event.preventDefault();
            event.stopPropagation();
        } else {
            form.classList.add('was-validated');
        }
    });
});

//Validación Torneo
document.addEventListener('DOMContentLoaded', () => {
    const teamPlayerForm = document.getElementById('teamPlayerForm');

    // Elementos de error
    const teamNameError = document.getElementById('teamNameError');
    const player1NameError = document.getElementById('player1NameError');
    const player2NameError = document.getElementById('player2NameError');

    // Función para mostrar un mensaje de error debajo de un campo
    function showError(element, message) {
        element.textContent = message;
        element.style.display = 'block';
    }

    // Función para ocultar un mensaje de error
    function hideError(element) {
        element.style.display = 'none';
    }

    teamPlayerForm.addEventListener('submit', (event) => {
        // Evita que el formulario se envíe antes de la validación
        event.preventDefault();

        // Obtener los valores de los campos de entrada
        const teamName = document.getElementById('teamName').value.trim();
        const player1Name = document.getElementById('player1Name').value.trim();
        const player2Name = document.getElementById('player2Name').value.trim();

        let isValid = true;

        // Validación para el nombre del equipo
        if (teamName === '') {
            showError(teamNameError, 'Por favor, ingrese un nombre para el equipo.');
            isValid = false;
        } else {
            hideError(teamNameError);
        }

        // Validación para el nombre del Jugador 1
        if (player1Name === '') {
            showError(player1NameError, 'Por favor, ingrese el nombre del Jugador 1.');
            isValid = false;
        } else {
            hideError(player1NameError);
        }

        // Validación para el nombre del Jugador 2
        if (player2Name === '') {
            showError(player2NameError, 'Por favor, ingrese el nombre del Jugador 2.');
            isValid = false;
        } else {
            hideError(player2NameError);
        }

        // Validación para asegurar que los nombres de los jugadores sean distintos
        if (player1Name === player2Name && player1Name !== '' && player2Name !== '') {
            showError(player2NameError, 'El nombre del Jugador 1 y el Jugador 2 no pueden ser iguales.');
            isValid = false;
        } else if (player1Name !== player2Name) {
            hideError(player2NameError);
        }

        // Si todas las validaciones pasan, enviamos el formulario
        if (isValid) {
            teamPlayerForm.submit();
        }
    });
    
    // Obtener el formulario y el elemento de error para el ID de modificación
    const modifyTeamForm = document.getElementById('modifyTeamForm');
    const idError = document.getElementById('idError');

    // Función para mostrar un mensaje de error debajo del campo de ID
    function showIdError(message) {
        idError.textContent = message;
        idError.style.display = 'block';
    }

    // Función para ocultar el mensaje de error del campo de ID
    function hideIdError() {
        idError.style.display = 'none';
    }

    // Validación para el formulario de modificación de equipo
    modifyTeamForm.addEventListener('submit', (event) => {
        const idEquipoModif = document.getElementById('idEquipoModif').value.trim();

        if (idEquipoModif === '') {
            // Si el campo está vacío, mostramos un mensaje de error y evitamos el envío
            event.preventDefault();
            showIdError('Por favor, ingrese un ID para modificar el equipo.');
        } else {
            // Si el campo está lleno, ocultamos el mensaje de error
            hideIdError();
        }
    });
    
    // Confirmación antes de eliminar un equipo
    const deleteButtons = document.querySelectorAll('form[action="svEliminarEquipo"] button[type="submit"]');
    deleteButtons.forEach(button => {
        button.addEventListener('click', (event) => {
            event.preventDefault(); // Evitar el envío automático del formulario
            const isConfirmed = confirm("¿Está seguro de que desea eliminar el equipo?");
            if (isConfirmed) {
                button.closest('form').submit(); // Enviar el formulario si se confirma
            }
        });
    });
});

            //validacion contacto
            document.addEventListener('DOMContentLoaded', () => {
                const form = document.getElementById('contactForm');

                form.addEventListener('submit', function (event) {
                    // Si el formulario no es válido, prevenimos el envío y mostramos la validación
                    if (!form.checkValidity()) {
                        event.preventDefault(); // Detenemos el envío
                        event.stopPropagation(); // Detenemos la propagación del evento
                    } else {
                        // Si el formulario es válido, mostramos un mensaje de alerta
                        alert('Formulario enviado correctamente');
                    }

                    // Añadimos la clase para mostrar los estilos de validación
                    form.classList.add('was-validated');
                });
            });



        //Validacion de formulario reserva y comentarios
        (function () {
            'use strict';
            window.addEventListener('load', function () {
              var forms = document.getElementsByClassName('needs-validation');
              Array.prototype.filter.call(forms, function (form) {
                form.addEventListener('submit', function (event) {
                  if (form.checkValidity() === false) {
                    event.preventDefault();
                    event.stopPropagation();
                  }
                  form.classList.add('was-validated');
                }, false);
              });
            }, false);
          })();