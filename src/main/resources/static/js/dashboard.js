document.addEventListener('DOMContentLoaded', function() {

    // Toggle Sidebar no Mobile
    const sidebarCollapse = document.getElementById('sidebarCollapse');
    const sidebar = document.getElementById('sidebar');
    const content = document.getElementById('content');

    if(sidebarCollapse) {
        sidebarCollapse.addEventListener('click', function() {
            sidebar.classList.toggle('active');
            // Ajusta margem se necessário para mobile
            if (sidebar.style.marginLeft === '-250px') {
                sidebar.style.marginLeft = '0';
            } else {
                sidebar.style.marginLeft = '-250px';
            }
        });
    }
});