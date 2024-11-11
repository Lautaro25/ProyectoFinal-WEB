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
