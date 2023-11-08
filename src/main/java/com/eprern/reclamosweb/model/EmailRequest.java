package com.eprern.reclamosweb.model;

import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.util.List;

public class EmailRequest {

    @Getter @Setter
    private String nombre;
    @Getter @Setter
    private String apellido;
    @Getter @Setter
    private String dni;
    @Getter @Setter
    private String telefono;
    @Getter @Setter
    private boolean checkNombrePropio;
    @Getter @Setter
    private String nombreRepresentante;
    @Getter @Setter
    private String apellidoRepresentante;
    @Getter @Setter
    private String dniRepresentante;
    @Getter @Setter
    private String direccion;
    @Getter @Setter
    private String localidad;
    @Getter @Setter
    private String codigoPostal;
    @Getter @Setter
    private String direccionAlternativa;
    @Getter @Setter
    private String localidadAlternativa;
    @Getter @Setter
    private String codigoPostalAlternativo;
    @Getter @Setter
    private String nis;
    @Getter @Setter
    private String numeroDeCliente;
    @Getter @Setter
    private String email;
    @Getter @Setter
    private boolean errorFacturacion;
    @Getter @Setter
    private boolean resarcimiento;
    @Getter @Setter
    private boolean suspencionSuministro;
    @Getter @Setter
    private boolean malaAtencionComercial;
    @Getter @Setter
    private boolean negativaConexion;
    @Getter @Setter
    private boolean inconvenienteTension;
    @Getter @Setter
    private String descripcion;
    @Getter @Setter
    private boolean emailNotifications;
    @Getter @Setter
    private List<File> attachments;
}
