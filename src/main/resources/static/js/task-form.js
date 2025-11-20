document.addEventListener('DOMContentLoaded', function() {

    const btnAi = document.getElementById('btnGenerateAi');
    const inputTopic = document.getElementById('aiTopic');
    const loadingIndicator = document.getElementById('aiLoading');

    // Campos do formulário para preencher
    const fieldTitle = document.getElementById('title');
    const fieldDesc = document.getElementById('description');
    const fieldPoints = document.getElementById('pointsValue');
    const fieldType = document.getElementById('taskType');

    if (btnAi) {
        btnAi.addEventListener('click', function() {
            const topic = inputTopic.value.trim();
            if (!topic) {
                alert('Por favor, digite um tema para a IA.');
                return;
            }

            // UI Loading
            btnAi.disabled = true;
            loadingIndicator.classList.remove('d-none');

            // Chama o Backend Java
            fetch('/admin/tasks/generate-ai', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    // Se o CSRF estivesse ativo, precisaríamos passar o token aqui
                },
                body: JSON.stringify({ topic: topic })
            })
            .then(response => {
                if (!response.ok) throw new Error('Erro na requisição');
                return response.json();
            })
            .then(data => {
                // Preenche o formulário automaticamente!
                fieldTitle.value = data.title;
                fieldDesc.value = data.description;
                fieldPoints.value = data.pointsValue;
                fieldType.value = data.taskType;
            })
            .catch(error => {
                console.error('Erro:', error);
                alert('Falha ao gerar tarefa com IA. Tente novamente.');
            })
            .finally(() => {
                btnAi.disabled = false;
                loadingIndicator.classList.add('d-none');
            });
        });
    }
});