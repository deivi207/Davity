package com.crud.work.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crud.work.model.Clientes;
import com.crud.work.repository.ClientesRepository;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "*") // Permite que tu front-end en HTML/JS se comunique con Spring Boot
public class ClientesController {
    
    private final ClientesRepository clientesRepository;

    public ClientesController(ClientesRepository clientesRepository) {
        this.clientesRepository = clientesRepository;
    }

    // 1. CREATE: Endpoint para almacenar un nuevo cliente (POST: http://localhost:8080/api/clientes)
    @PostMapping
    public ResponseEntity<?> crearCliente(@RequestBody Clientes cliente) {
        if (cliente.getId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("La cédula es obligatoria.");
        }

        if (clientesRepository.existsById(cliente.getId())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("Ya existe un cliente registrado con la cédula " + cliente.getId() + ".");
        }

        if (clientesRepository.existsByCorreoElectronico(cliente.getCorreoElectronico())) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("El correo electrónico ya está registrado.");
        
        }
        
        Clientes clienteGuardado = clientesRepository.save(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteGuardado);
    }

    // 2. READ: Endpoint para obtener todos los clientes (GET: http://localhost:8080/api/clientes)
    @GetMapping
    public List<Clientes> getAllClientes() {
        return clientesRepository.findAll();
    }

    // 3. READ: Endpoint para obtener un solo cliente por su ID (GET: http://localhost:8080/api/clientes/{id})
    @GetMapping("/{id}")
    public ResponseEntity<Clientes> getClienteById(@PathVariable Long id) {
        Optional<Clientes> cliente = clientesRepository.findById(id);
        if (cliente.isPresent()) {
            return ResponseEntity.ok(cliente.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 4. UPDATE: Endpoint para actualizar un cliente (PUT: http://localhost:8080/api/clientes/{id})
    @PutMapping("/{id}")
    public ResponseEntity<Clientes> updateCliente(@PathVariable Long id, @RequestBody Clientes clienteDetalles) {
        if (clientesRepository.existsById(id)) {
            // Como el ID lo pones tú (no es autogenerado), aseguramos que se guarde en el registro correcto
            clienteDetalles.setId(id);
            Clientes clienteActualizado = clientesRepository.save(clienteDetalles);
            return ResponseEntity.ok(clienteActualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 5. DELETE: Endpoint para eliminar un cliente (DELETE: http://localhost:8080/api/clientes/{id})
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
        if (clientesRepository.existsById(id)) {
            clientesRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
@PostMapping("/login")
public ResponseEntity<?> login(@RequestBody Map<String, String> credenciales) {
    String correo = credenciales.get("correoElectronico");
    String contrasena = credenciales.get("contrasena");

    Optional<Clientes> clienteOpt = clientesRepository.findByCorreoElectronicoAndContrasena(correo, contrasena);

    if (clienteOpt.isEmpty()) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Correo electrónico o contraseña incorrectos.");
    }

    Clientes cliente = clienteOpt.get();

    
    if (Boolean.FALSE.equals(cliente.getEstadoSuscripcion())) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body("Tu suscripción está inactiva. Contacta al administrador para renovarla.");
    }

    return ResponseEntity.ok(cliente);
}
}