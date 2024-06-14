package com.example.pruebaentidadfinanciera.servicios;

import com.example.pruebaentidadfinanciera.entidades.Cliente;
import com.example.pruebaentidadfinanciera.utils.enums.EstadoCuenta;
import com.example.pruebaentidadfinanciera.entidades.Producto;
import com.example.pruebaentidadfinanciera.utils.enums.TipoCuenta;
import com.example.pruebaentidadfinanciera.repositorio.ClienteRepositorio;
import com.example.pruebaentidadfinanciera.repositorio.ProductoRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;


@RequiredArgsConstructor
@Service
public class ProductoServicio {

    private final ProductoRepositorio productoRepositorio;
    private final ClienteRepositorio clienteRepositorio;

   public Producto crearProducto (Long id, Producto producto) {
        obtenerCliente(id);
        asignacionProducto(id);
        horaCreacion(producto);
        validarProducto(producto);
        generarNumeroCuenta(producto);
        validarSaldo(producto);
        return productoRepositorio.save(producto);
   }

   private Cliente obtenerCliente(Long clienteId){
       return clienteRepositorio.findById(clienteId)
               .orElseThrow(() -> new IllegalStateException("Cliente no encontrado"));
   }

   private void horaCreacion(Producto producto){
       producto.setFechaCreacion(LocalDate.now());
   }

   private void asignacionProducto(Long id){
    Producto producto = new Producto();
    producto.setCliente(obtenerCliente(id));
   }

    private void validarProducto(Producto producto) {
        if (producto.getTipoCuenta() == TipoCuenta.AHORROS){
            producto.setTipoCuenta(TipoCuenta.AHORROS);
            producto.setEstadoCuenta(EstadoCuenta.ACTIVA);
        } else {
            producto.setTipoCuenta(TipoCuenta.CORRIENTE);
        }
    }

    private void generarNumeroCuenta(Producto producto) {
       long count = productoRepositorio.count() + 1;
       if (producto.getTipoCuenta() == TipoCuenta.AHORROS){
           producto.setNumeroCuenta("53" + String.format("%08d",(int) count));
       } else {
           producto.setNumeroCuenta("33" + String.format("%08d",(int) count));
       }
    }

    private void validarSaldo (Producto producto){
       if(producto.getTipoCuenta() == TipoCuenta.AHORROS){
           if(producto.getSaldo().compareTo(BigDecimal.ZERO) <= 0 ){
               System.out.println("El saldo no puede ser menor a 0");
           }
       }
    }
















}
