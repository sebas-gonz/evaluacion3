/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

/**
 *
 * @author Cetecom
 */
public class Cliente {
    private String rut;
    private String nombre;
    private String direccion;
    private String numero_contacto;
    private String tipo_cliente;

    public Cliente() {
    }

    public Cliente(String rut, String nombre, String direccion, String numero_contacto, String tipo_cliente) {
        this.rut = rut;
        this.nombre = nombre;
        this.direccion = direccion;
        this.numero_contacto = numero_contacto;
        this.tipo_cliente = tipo_cliente;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNumero_contacto() {
        return numero_contacto;
    }

    public void setNumero_contacto(String numero_contacto) {
        this.numero_contacto = numero_contacto;
    }

    public String getTipo_cliente() {
        return tipo_cliente;
    }

    public void setTipo_cliente(String tipo_cliente) {
        this.tipo_cliente = tipo_cliente;
    }
    
    
}
