/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import Bd.Conexion;
import Controller.HelperController;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import models.Cliente;
/**
 *
 * @author Cetecom
 */
public class ClienteController {
    private Conexion cx;
    private HelperController helper = new HelperController();
    public ClienteController() {
        cx = new Conexion();
        cx.conectar();
    }
    
    public ArrayList<Cliente> obtenerClientes(){
        ArrayList<Cliente> clientes = new ArrayList<>();
        String query = "SELECT * FROM cliente;";
        try {
            ResultSet rs = cx.EjecutarQuery(query);
            while(rs.next()){
                clientes.add(new Cliente(
                rs.getString("rut"),
                rs.getString("nombre"),
                rs.getString("direccion"),
                rs.getString("numero_contacto"),
                rs.getString("tipo_cliente")
                ));
            }
        } catch (Exception e) {
            helper.showError("Error al obtener los clientes." + e.getMessage());
        }
        return clientes;
    }
    
    public ArrayList<Cliente> obtenerClientesPorNombre(String nombre){
        ArrayList<Cliente> clientes = new ArrayList<>();
        String query = "SELECT * FROM cliente WHERE nombre = '" + nombre + "';";
        try {
            ResultSet rs = cx.EjecutarQuery(query);
            while(rs.next()){
                clientes.add(new Cliente(
                rs.getString("rut"),
                rs.getString("nombre"),
                rs.getString("direccion"),
                rs.getString("numero_contacto"),
                rs.getString("tipo_cliente")
                ));
            }
        } catch (Exception e) {
            helper.showError("Error al obtener los clientes." + e.getMessage());
        }
        return clientes;
    }
    
    public void AgregarCliente(String rut,String nombre, String direccion, String numero_contacto, String tipo_cliente){
        if(estaVacio(rut, nombre, direccion, numero_contacto, tipo_cliente)){
            if(rut.isBlank()){
                helper.showWarning("El rut no puede estar vacio");
            } else if(nombre.isBlank()){
                helper.showWarning("El nombre no puede estar vacio");
            } else if(direccion.isBlank()){
                helper.showWarning("La direccion no puede etar vacia");
            } else if(numero_contacto.isBlank()){
                helper.showWarning("El numero de contacto no puede estar vacio");
            } else if(tipo_cliente.isBlank()){
                helper.showWarning("El tipo de cliente no puede estar vacio");
            }
        } else {
            String query = "INSERT INTO `cliente` (rut,nombre,direccion,numero_contacto,tipo_cliente) VALUES (?,?,?,?,?);";
            try {
                PreparedStatement st = cx.getConnection().prepareStatement(query);
                st.setString(1, rut);
                st.setString(2, nombre);
                st.setString(3, direccion);
                st.setString(4, numero_contacto);
                st.setString(5, tipo_cliente);
                st.executeUpdate();
                helper.showInformation("Cliente agregado.");
            } catch (Exception e) {
                helper.showError("Error al agregar cliente." + e.getMessage());
            }
        }
   
        
    }
        public Cliente buscarClientePorRut(String rut){
        Cliente clienteEncontrado = null;
        String query = "SELECT * FROM CLIENTE WHERE rut = '" + rut + "';";
        try {
            ResultSet rs = cx.EjecutarQuery(query);
            if(rs.next()){
                clienteEncontrado = new Cliente(
                rs.getString("rut"),
                rs.getString("nombre"),
                rs.getString("direccion"),
                rs.getString("numero_contacto"),
                rs.getString("tipo_cliente")
                );
            } else {
                helper.showWarning("Cliente no encontrado.");
            }
        } catch (Exception e) {
            helper.showError("Error al buscar Cliente");
        } return clienteEncontrado;
    }    
    
    public void EditarCliente(String rut,String nombre, String direccion, String numero_contacto, String tipo_cliente){
        Cliente clienteEncontrado = buscarClientePorRut(rut);
        if(clienteEncontrado != null){
            if(estaVacio(rut, nombre, direccion, numero_contacto, tipo_cliente)){
                if(rut.isBlank()){
                    helper.showWarning("El rut no puede estar vacio");
                } else if(nombre.isBlank()){
                    helper.showWarning("El nombre no puede estar vacio");
                } else if(direccion.isBlank()){
                    helper.showWarning("La direccion no puede etar vacia");
                } else if(numero_contacto.isBlank()){
                    helper.showWarning("El numero de contacto no puede estar vacio");
                } else if(tipo_cliente.isBlank()){
                    helper.showWarning("El tipo de cliente no puede estar vacio");
                }
            } else {
                String query = "UPDATE cliente SET rut = '" + rut + "', nombre = '" + nombre + "', direccion = '" + direccion + 
                        "', numero_contacto = '" + numero_contacto + "', tipo_cliente = '" + tipo_cliente + "' WHERE rut = '" + rut + "';";
                try {
                    Statement st = cx.getConnection().createStatement();
                    st.executeUpdate(query);
                    helper.showInformation("Cliente editado.");
                } catch (Exception e) {
                    helper.showError("Error al editar cliente." + e.getMessage());
                }
            }
        }
    }
    
    public void eliminarCliente(String rut){
        Cliente clienteEncontrado = buscarClientePorRut(rut);
        if(clienteEncontrado != null){
            String query = "DELETE FROM cliente WHERE rut = '" + rut + "';";
            try {
                Statement st = cx.getConnection().createStatement();
                st.executeUpdate(query);
                helper.showInformation("Cliente eliminado");
            } catch (Exception e) {
                helper.showError("Error al eliminar cliente" + e.getMessage());
            }
        }
        
    }
    
    public ArrayList<String> obtenerNombreCliente(){
        ArrayList<String> nombres = new ArrayList<>();
        String query = "SELECT DISTINCT nombre FROM cliente;";
        try {
            ResultSet rs = cx.EjecutarQuery(query);
            while(rs.next()){
                nombres.add(rs.getString("nombre"));
            }
        } catch (Exception e) {
            helper.showError("Error al obtener los nombres de los clientes." + e.getMessage());
        } return nombres;
    }
    public boolean estaVacio(String rut,String nombre,String direccion,String numero_contacto,String tipo_contacto){
        return rut.isBlank() || nombre.isBlank() || direccion.isBlank() || numero_contacto.isBlank() || tipo_contacto.isBlank();
    }
}
