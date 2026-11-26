const API_URL = 'http://localhost:8080/api/clientes';
let clientesCache = [];
let modalEditarBootstrap;

document.addEventListener('DOMContentLoaded', () => {
    modalEditarBootstrap = new bootstrap.Modal(document.getElementById('modalEditar'));
    cargarClientes();

    document.getElementById('btnRecargar').addEventListener('click', cargarClientes);
    document.getElementById('formEditar').addEventListener('submit', guardarCambios);
});

// 1. LEER (GET)
async function cargarClientes() {
    const tbody = document.getElementById('tablaClientesBody');
    tbody.innerHTML = '<tr><td colspan="7" class="text-center">Cargando...</td></tr>';

    try {
        const response = await fetch(API_URL);
        if (!response.ok) throw new Error(`Error HTTP ${response.status}`);

        clientesCache = await response.json();
        tbody.innerHTML = '';

        if (clientesCache.length === 0) {
            tbody.innerHTML = '<tr><td colspan="7" class="text-center text-muted">No hay clientes registrados.</td></tr>';
            return;
        }

        clientesCache.forEach(cliente => {
            const estadoBadge = cliente.estadoSuscripcion
                ? '<span class="badge bg-success">Activa</span>'
                : '<span class="badge bg-secondary">Inactiva</span>';

            tbody.innerHTML += `
                <tr>
                    <td>${cliente.id}</td>
                    <td>${cliente.nombreCompleto}</td>
                    <td>${cliente.correoElectronico}</td>
                    <td>${cliente.celular}</td>
                    <td>${cliente.suscripcion}</td>
                    <td>${estadoBadge}</td>
                    <td class="text-center">
                        <button class="btn btn-sm btn-warning me-1" onclick="abrirModalEditar(${cliente.id})">Editar</button>
                        <button class="btn btn-sm btn-danger" onclick="eliminarCliente(${cliente.id})">Eliminar</button>
                    </td>
                </tr>
            `;
        });
    } catch (error) {
        console.error('Error al cargar clientes:', error);
        tbody.innerHTML = '<tr><td colspan="7" class="text-center text-danger">Error al conectar con el servidor.</td></tr>';
    }
}

// 2. PREPARAR EDICIÓN
function abrirModalEditar(id) {
    const cliente = clientesCache.find(c => c.id === id);
    if (!cliente) return;

    document.getElementById('editId').value = cliente.id;
    document.getElementById('editNombre').value = cliente.nombreCompleto;
    document.getElementById('editCorreo').value = cliente.correoElectronico;
    document.getElementById('editCelular').value = cliente.celular;
    document.getElementById('editSuscripcion').value = cliente.suscripcion;
    document.getElementById('editEstado').checked = cliente.estadoSuscripcion;

    modalEditarBootstrap.show();
}

// 3. ACTUALIZAR (PUT)
async function guardarCambios(event) {
    event.preventDefault();
    const id = parseInt(document.getElementById('editId').value, 10);
    const clienteOriginal = clientesCache.find(c => c.id === id);

    // Mantiene intactos los campos que no están en el modal (contraseña, tarjeta, dirección, etc.)
    const clienteActualizado = {
        ...clienteOriginal,
        nombreCompleto: document.getElementById('editNombre').value,
        correoElectronico: document.getElementById('editCorreo').value,
        celular: document.getElementById('editCelular').value,
        suscripcion: document.getElementById('editSuscripcion').value,
        estadoSuscripcion: document.getElementById('editEstado').checked
    };

    try {
        const response = await fetch(`${API_URL}/${id}`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(clienteActualizado)
        });

        if (response.ok) {
            modalEditarBootstrap.hide();
            cargarClientes();
        } else {
            alert(`No se pudo actualizar (Código ${response.status}). Verifica el método @PutMapping en tu controlador.`);
        }
    } catch (error) {
        console.error('Error al actualizar:', error);
    }
}

// 4. ELIMINAR (DELETE)
async function eliminarCliente(id) {
    if (!confirm(`¿Seguro que deseas eliminar al cliente con cédula ${id}?`)) return;

    try {
        const response = await fetch(`${API_URL}/${id}`, {
            method: 'DELETE'
        });

        if (response.ok) {
            cargarClientes();
        } else {
            alert(`No se pudo eliminar (Código ${response.status}). Verifica el método @DeleteMapping en tu controlador.`);
        }
    } catch (error) {
        console.error('Error al eliminar:', error);
    }
}