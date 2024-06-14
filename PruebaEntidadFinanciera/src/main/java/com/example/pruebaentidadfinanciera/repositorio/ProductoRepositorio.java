package com.example.pruebaentidadfinanciera.repositorio;

import com.example.pruebaentidadfinanciera.entidades.Producto;
import org.springframework.data.repository.CrudRepository;

public interface ProductoRepositorio extends CrudRepository<Producto, Long> {
}
