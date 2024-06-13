package com.example.pruebaentidadfinanciera.servicios;

import com.example.pruebaentidadfinanciera.entidades.Cliente;
import com.example.pruebaentidadfinanciera.repositorio.ClienteRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ClienteServicio {

    private final ClienteRepositorio repositorio;

    public Cliente crearCliente(Cliente cliente) {

        cliente.setFechaCreacion(LocalDate.now());

        boolean fechaValidada = esMayorDeEdad(cliente.getFechaNacimiento());

        if (!fechaValidada) {
            throw new IllegalStateException("Cliente es menor de edad");
        }

        repositorio.save(cliente);
        return cliente;
    }

    public Cliente modificarCliente(Long id, Cliente clienteActualizado) {
        Cliente clienteExistente = repositorio.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));

        boolean fechaValidada = esMayorDeEdad(clienteActualizado.getFechaNacimiento());

        if (!fechaValidada) {
            throw new IllegalArgumentException("El cliente debe ser mayor de edad");
        }

        clienteExistente.setPrimerNombre(clienteActualizado.getPrimerNombre());
        clienteExistente.setSegundoNombre(clienteActualizado.getSegundoNombre());
        clienteExistente.setPrimerApellido(clienteActualizado.getPrimerApellido());
        clienteExistente.setSegundoApellido(clienteActualizado.getSegundoApellido());
        clienteExistente.setFechaModificacion(LocalDate.now());
        clienteExistente.setCorreoElectronico(clienteActualizado.getCorreoElectronico());
        clienteExistente.setNumeroIdentificacion(clienteActualizado.getNumeroIdentificacion());
        clienteExistente.setTipoIdentificacion(clienteActualizado.getTipoIdentificacion());

        repositorio.save(clienteExistente);
        return clienteExistente;
    }

    public void eliminarCliente(Long id) {

        Cliente clienteExistente = repositorio.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));

        repositorio.deleteById(id);
    }

    public Cliente obtenerClientePorId(Long id) {
    return repositorio.findById(id).orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));
    }



    public boolean esMayorDeEdad(LocalDate fechaNacimiento) {
        return Period.between(fechaNacimiento, LocalDate.now()).getYears() >= 18;
    }

    public List<Cliente> obtenerTodosLosClientes() {
       return (List<Cliente>) repositorio.findAll();
    }
}
