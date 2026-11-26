package com.crud.work.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.crud.work.model.Clientes;

@Repository
public interface ClientesRepository extends JpaRepository<Clientes, Long> { 
    Optional<Clientes> findByCorreoElectronicoAndContrasena(String correoElectronico, String contrasena);
    boolean existsByCorreoElectronico(String correoElectronico);
}
 