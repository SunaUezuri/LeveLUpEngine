document.addEventListener('DOMContentLoaded', function() {

    // Efeito simples de entrada nos inputs
    const inputs = document.querySelectorAll('.form-control');

    inputs.forEach(input => {
        input.addEventListener('focus', () => {
            input.parentElement.classList.add('input-focused');
        });

        input.addEventListener('blur', () => {
            input.parentElement.classList.remove('input-focused');
        });
    });

    // Efeito de Carregamento no Botão
    const loginForm = document.querySelector('form');
    const submitBtn = document.querySelector('.btn-tech');
    const btnText = submitBtn.querySelector('span');
    const btnIcon = submitBtn.querySelector('i');

    loginForm.addEventListener('submit', function() {
        // Adiciona estado de loading visual
        submitBtn.classList.add('disabled');
        submitBtn.setAttribute('disabled', 'disabled');

        // Troca ícone por spinner
        btnIcon.className = 'fa-solid fa-circle-notch fa-spin';
        btnText.textContent = ' Acessando...';
    });
});