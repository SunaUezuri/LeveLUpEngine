document.addEventListener('DOMContentLoaded', function () {

    // --- FUNCIONALIDADE 1: GERAR TAREFA (Brainstorm) ---
    const btnGenerate = document.getElementById('btnGenerateAi');

    if (btnGenerate) {
        btnGenerate.addEventListener('click', function () {
            const topicInput = document.getElementById('aiTopic');
            const loadingDiv = document.getElementById('aiLoading');
            const topic = topicInput.value;

            if (!topic.trim()) {
                alert('Por favor, digite um tema para a IA.');
                return;
            }

            // UI Loading
            btnGenerate.disabled = true;
            btnGenerate.innerHTML = '<i class="fa-solid fa-spinner fa-spin"></i>';
            loadingDiv.classList.remove('d-none');

            fetch('/admin/tasks/generate-ai', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ topic: topic })
            })
            .then(response => response.json())
            .then(data => {
                // Preencher formulário
                document.getElementById('title').value = data.title;
                document.getElementById('description').value = data.description;
                document.getElementById('pointsValue').value = data.pointsValue;
                document.getElementById('taskType').value = data.taskType;
            })
            .catch(error => {
                console.error('Erro:', error);
                alert('Erro ao consultar a IA. Tente novamente.');
            })
            .finally(() => {
                // UI Reset
                btnGenerate.disabled = false;
                btnGenerate.innerHTML = '<i class="fa-solid fa-bolt me-2"></i> Gerar';
                loadingDiv.classList.add('d-none');
            });
        });
    }

    // --- FUNCIONALIDADE 2: ANALISAR E ESTIMAR PONTOS ---
    const btnAnalyze = document.getElementById('btnAnalyzeAi');

    if (btnAnalyze) {
        btnAnalyze.addEventListener('click', function () {
            const title = document.getElementById('title').value;
            const description = document.getElementById('description').value;

            if (!title.trim()) {
                alert('Preencha pelo menos o Título para a IA analisar a dificuldade.');
                return;
            }

            // UI Loading Específico do Botão
            const originalIcon = btnAnalyze.innerHTML;
            btnAnalyze.innerHTML = '<i class="fa-solid fa-brain fa-bounce"></i> Analisando...';
            btnAnalyze.classList.add('analyzing');

            fetch('/admin/tasks/analyze-ai', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({
                    title: title,
                    description: description
                })
            })
            .then(response => response.json())
            .then(data => {
                // Atualiza apenas Pontos e Tipo (preserva título e melhora descrição se vier)

                // Animação visual no campo de pontos para mostrar que mudou
                const pointsInput = document.getElementById('pointsValue');
                pointsInput.style.backgroundColor = 'rgba(142, 45, 226, 0.3)';
                pointsInput.value = data.pointsValue;

                const typeInput = document.getElementById('taskType');
                typeInput.value = data.taskType;
                typeInput.style.backgroundColor = 'rgba(142, 45, 226, 0.3)';

                // Melhora a descrição se a IA mandou uma versão melhorada
                if(data.description && data.description.length > description.length) {
                     document.getElementById('description').value = data.description;
                }

                // Remove o destaque após 1 segundo
                setTimeout(() => {
                    pointsInput.style.backgroundColor = '';
                    typeInput.style.backgroundColor = '';
                }, 1000);
            })
            .catch(error => {
                console.error('Erro:', error);
                alert('Não foi possível estimar os pontos agora.');
            })
            .finally(() => {
                btnAnalyze.innerHTML = originalIcon;
                btnAnalyze.classList.remove('analyzing');
            });
        });
    }
});