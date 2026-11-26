const LOGIN_URL = 'http://localhost:8080/api/clientes/login';

document.getElementById('loginForm').addEventListener('submit', async (event) => {
    event.preventDefault();

    const alerta = document.getElementById('alertaLogin');
    alerta.classList.add('d-none');

    const credenciales = {
        correoElectronico: document.getElementById('correo').value,
        contrasena: document.getElementById('contrasena').value
    };

    try {
        const response = await fetch(LOGIN_URL, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(credenciales)
        });

        if (response.ok) {
            const cliente = await response.json();
            // Guardamos la sesión temporalmente en el navegador incluyendo el id
            sessionStorage.setItem('clienteActivo', JSON.stringify({
                id: cliente.id,
                nombre: cliente.nombreCompleto,
                plan: cliente.suscripcion
            }));
            window.location.href = 'transmision.html';
        } else {
            const mensajeError = await response.text();
            alerta.textContent = mensajeError;
            alerta.classList.remove('d-none');
        }
    } catch (error) {
        console.error('Error de conexión:', error);
        alerta.textContent = 'No se pudo conectar con el servidor.';
        alerta.classList.remove('d-none');
    }
});