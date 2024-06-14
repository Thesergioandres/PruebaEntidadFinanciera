package com.example.pruebaentidadfinanciera.entidades;

import com.example.pruebaentidadfinanciera.utils.enums.EstadoCuenta;
import com.example.pruebaentidadfinanciera.utils.enums.TipoCuenta;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "El tipo de cuenta no puede estar vacío")
    private TipoCuenta tipoCuenta;

    @Column(unique = true, nullable = false)
    @NotBlank(message = "El número de cuenta no puede estar vacío")
    private String numeroCuenta;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EstadoCuenta estado;

    @Column(nullable = false)
    @DecimalMin(value = "0.0", message = "El saldo no puede ser negativo")
    private BigDecimal saldo;

    @Column(nullable = false)
    private boolean exentaGMF;

    @Column(nullable = false, updatable = false)
    private LocalDate fechaCreacion;

    @Column
    private LocalDate fechaModificacion;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @Column(nullable = false, updatable = false)
    private EstadoCuenta estadoCuenta;



}
