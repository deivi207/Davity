package com.crud.work.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "clientes")
public class Clientes {

    @Id
    private Long id;

    private String nombreCompleto;
    private String correoElectronico;
    private String contrasena;
    private String celular;
    private LocalDate fechaNacimiento;
    private Integer edad;
    private String pais;
    private String region;
    private Integer codigoPostal;
    private String direccion;
    private String nombreTitularTarjeta;
    private String tarjetaCreditoDebito;
    private String suscripcion;
    private LocalDate fechaVencimiento;
    private Boolean estadoSuscripcion;

    public Clientes() {
    }

    public Clientes(String nombreCompleto, String correoElectronico, String contrasena, 
                    String celular, LocalDate fechaNacimiento, Integer edad, String pais, 
                    String region, Integer codigoPostal, String direccion, 
                    String nombreTitularTarjeta, String tarjetaCreditoDebito, 
                    String suscripcion, LocalDate fechaVencimiento, Boolean estadoSuscripcion) {
        this.nombreCompleto = nombreCompleto;
        this.correoElectronico = correoElectronico;
        this.contrasena = contrasena;
        this.celular = celular;
        this.fechaNacimiento = fechaNacimiento;
        this.edad = edad;
        this.pais = pais;
        this.region = region;
        this.codigoPostal = codigoPostal;
        this.direccion = direccion;
        this.nombreTitularTarjeta = nombreTitularTarjeta;
        this.tarjetaCreditoDebito = tarjetaCreditoDebito;
        this.suscripcion = suscripcion;
        this.fechaVencimiento = fechaVencimiento;
        this.estadoSuscripcion = estadoSuscripcion;
    }

    //. Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public Integer getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(Integer codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombreTitularTarjeta() {
        return nombreTitularTarjeta;
    }

    public void setNombreTitularTarjeta(String nombreTitularTarjeta) {
        this.nombreTitularTarjeta = nombreTitularTarjeta;
    }

    public String getTarjetaCreditoDebito() {
        return tarjetaCreditoDebito;
    }

    public void setTarjetaCreditoDebito(String tarjetaCreditoDebito) {
        this.tarjetaCreditoDebito = tarjetaCreditoDebito;
    }

    public String getSuscripcion() {
        return suscripcion;
    }

    public void setSuscripcion(String suscripcion) {
        this.suscripcion = suscripcion;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public Boolean getEstadoSuscripcion() {
        return estadoSuscripcion;
    }

    public void setEstadoSuscripcion(Boolean estadoSuscripcion) {
        this.estadoSuscripcion = estadoSuscripcion;
    }
}