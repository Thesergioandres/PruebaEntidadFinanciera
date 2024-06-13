package com.example.pruebaentidadfinanciera.controlador;

import com.example.pruebaentidadfinanciera.entidades.Cliente;
import com.example.pruebaentidadfinanciera.servicios.ClienteServicio;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1")
public class ClienteControlador {

    private ClienteServicio clienteServicio;



    @PostMapping("/crearCliente")
    public Cliente crearCliente(@RequestBody Cliente cliente) {
        return clienteServicio.crearCliente(cliente);
    }

    @GetMapping("/obtenerClientes")
    public List<Cliente> obtenerClientes(){
        return clienteServicio.obtenerTodosLosClientes();
    }

    @GetMapping("/obtenerCliente/{id}")
    public Cliente obtenerClientePorId(@PathVariable Long id) {
        return clienteServicio.obtenerClientePorId(id);
    }

    @DeleteMapping("/eliminarCliente/{id}")
    public void eliminarCliente(@PathVariable Long id){
        clienteServicio.eliminarCliente(id);
    }

    @PutMapping("/actualizarCliente/{id}")
    public Cliente modificarCliente(@PathVariable Long id, @RequestBody Cliente clienteActualizado) {
        return clienteServicio.modificarCliente(id, clienteActualizado);
    }




}