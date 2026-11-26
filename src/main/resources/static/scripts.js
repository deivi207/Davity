const API_URL = 'http://localhost:8080/api/clientes';

document.addEventListener('DOMContentLoaded', () => {
    const fechaNacimientoInput = document.getElementById('fechaNacimiento');
    const edadInput = document.getElementById('edad');
    const registroForm = document.getElementById('registroForm');

    // 1. Calcular edad automáticamente al seleccionar fecha de nacimiento
    fechaNacimientoInput.addEventListener('change', () => {
        const fechaNac = new Date(fechaNacimientoInput.value);
        if (isNaN(fechaNac.getTime())) {
            edadInput.value = '';
            return;
        }

        const hoy = new Date();
        let edad = hoy.getFullYear() - fechaNac.getFullYear();
        const mes = hoy.getMonth() - fechaNac.getMonth();

        if (mes < 0 || (mes === 0 && hoy.getDate() < fechaNac.getDate())) {
            edad--;
        }

        edadInput.value = edad >= 0 ? edad : 0;
    });

    // 2. Enviar datos del formulario al Back-end (POST)
    registroForm.addEventListener('submit', async (event) => {
        event.preventDefault(); // Evita que la página se recargue

        const planSeleccionado = document.querySelector('input[name="planType"]:checked').value;

        const nuevoCliente = {
            id: parseInt(document.getElementById('id').value, 10),
            nombreCompleto: document.getElementById('nombreCompleto').value,
            correoElectronico: document.getElementById('correoElectronico').value,
            contrasena: document.getElementById('contrasena').value,
            celular: document.getElementById('celular').value,
            fechaNacimiento: document.getElementById('fechaNacimiento').value,
            edad: parseInt(document.getElementById('edad').value, 10),
            pais: document.getElementById('pais').value,
            region: document.getElementById('region').value,
            codigoPostal: parseInt(document.getElementById('codigoPostal').value, 10),
            direccion: document.getElementById('direccion').value,
            suscripcion: planSeleccionado,
            estadoSuscripcion: true,
            nombreTitularTarjeta: document.getElementById('nombreTitularTarjeta').value,
            tarjetaCreditoDebito: document.getElementById('tarjetaCreditoDebito').value,
            fechaVencimiento: document.getElementById('fechaVencimiento').value
        };

        try {
            const response = await fetch(API_URL, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(nuevoCliente)
            });

            if (response.ok) {
                const clienteGuardado = await response.json();
                alert(`¡Registro exitoso! Bienvenido a Davity, ${clienteGuardado.nombreCompleto}.`);
                registroForm.reset();
            } else {
                const errorTexto = await response.text();
                alert(`Error al registrar el cliente (Código ${response.status}): ${errorTexto}`);
            }
        } catch (error) {
            console.error('Error de red:', error);
            alert('No se pudo conectar con el servidor. Verifica que Spring Boot esté ejecutándose.');
        }
    });
});