package com.crud.work;

import java.time.LocalDate;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.crud.work.model.Clientes;
import com.crud.work.repository.ClientesRepository;

@SpringBootApplication
public class WorkApplication {

    public static void main(String[] args) {
        SpringApplication.run(WorkApplication.class, args);
    }

    @Bean
    CommandLineRunner initDatabase(ClientesRepository repository) {
        return args -> {
            // Usamos el constructor vacío y llenamos los datos uno por uno
            Clientes clientePrueba = new Clientes();
            
            clientePrueba.setId(1001234567L); // Cédula manual
            clientePrueba.setNombreCompleto("David Gonzalez");
            clientePrueba.setCorreoElectronico("david@ejemplo.com");
            clientePrueba.setContrasena("password123");
            clientePrueba.setCelular("3001234567");
            clientePrueba.setFechaNacimiento(LocalDate.of(1990, 5, 15));
            clientePrueba.setEdad(36);
            clientePrueba.setPais("Colombia");
            clientePrueba.setRegion("Antioquia");
            clientePrueba.setCodigoPostal(50001);
            clientePrueba.setDireccion("Calle Falsa 123");
            clientePrueba.setNombreTitularTarjeta("David Gonzalez");
            clientePrueba.setTarjetaCreditoDebito("1234567890123456");
            clientePrueba.setSuscripcion("Mensual");
            clientePrueba.setFechaVencimiento(LocalDate.now().plusMonths(1));
            clientePrueba.setEstadoSuscripcion(true);

            repository.save(clientePrueba);
            System.out.println("Cliente de prueba guardado exitosamente en la base de datos.");
        };
    }
}