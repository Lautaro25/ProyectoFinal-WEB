document.addEventListener('DOMContentLoaded', () => {
    const bracketContainer = document.getElementById('bracketContainer');
    const updateBracketButton = document.getElementById('updateBracketButton');

    // Función para crear una etapa de la llave
    function createStage(stageName, matchCount) {
        const stageDiv = document.createElement('div');
        stageDiv.className = 'bracket-stage';

        const stageTitle = document.createElement('h3');
        stageTitle.textContent = stageName;
        stageDiv.appendChild(stageTitle);

        for (let i = 0; i < matchCount; i++) {
            const matchDiv = document.createElement('div');
            matchDiv.className = 'bracket-match';

            const team1Div = document.createElement('div');
            team1Div.className = 'bracket-team';
            team1Div.contentEditable = true; // Permitir editar el nombre del equipo
            team1Div.textContent = 'Equipo 1';
            matchDiv.appendChild(team1Div);

            const input1 = document.createElement('input');
            input1.type = 'text';
            input1.className = 'bracket-input';
            matchDiv.appendChild(input1);

            const team2Div = document.createElement('div');
            team2Div.className = 'bracket-team';
            team2Div.contentEditable = true; // Permitir editar el nombre del equipo
            team2Div.textContent = 'Equipo 2';
            matchDiv.appendChild(team2Div);

            const input2 = document.createElement('input');
            input2.type = 'text';
            input2.className = 'bracket-input';
            matchDiv.appendChild(input2);

            stageDiv.appendChild(matchDiv);
        }

        bracketContainer.appendChild(stageDiv);
    }

    // Generar la estructura de la llave de eliminación
    createStage('16avos de Final', 8);  // Fase inicial editable
    createStage('Octavos de Final', 4);
    createStage('Cuartos de Final', 2);
    createStage('Semifinal', 2);
    createStage('Final', 1);

    // Función para actualizar la llave con los equipos ganadores
    function updateBracket() {
        const stages = bracketContainer.getElementsByClassName('bracket-stage');

        // Proceso para determinar ganadores y avanzar a la siguiente fase
        for (let i = 0; i < stages.length - 1; i++) {  // No procesar la última fase
            const currentStage = stages[i];
            const nextStage = stages[i + 1];
            const currentMatches = currentStage.getElementsByClassName('bracket-match');
            const nextStageMatches = nextStage.getElementsByClassName('bracket-match');
            const winners = [];

            // Obtener los ganadores de la fase actual
            for (let match of currentMatches) {
                const team1 = match.children[0].textContent.trim();
                const team2 = match.children[2].textContent.trim();
                const score1 = parseInt(match.children[1].value);
                const score2 = parseInt(match.children[3].value);

                // Determinar el ganador del partido
                if (!isNaN(score1) && !isNaN(score2)) {
                    if (score1 > score2) {
                        winners.push(team1);
                    } else if (score2 > score1) {
                        winners.push(team2);
                    }
                }
            }

            // Colocar los ganadores en la siguiente fase
            for (let j = 0; j < nextStageMatches.length; j++) {
                const match = nextStageMatches[j];
                match.children[0].textContent = winners[j * 2] || 'N/A';
                match.children[2].textContent = winners[j * 2 + 1] || 'N/A';
            }
        }
    }

    // Event listener para actualizar la llave
    updateBracketButton.addEventListener('click', updateBracket);
});
