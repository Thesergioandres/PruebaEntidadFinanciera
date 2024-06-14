package com.example.pruebaentidadfinanciera.entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;
import java.time.Period;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    @NotBlank(message = "El tipo de identificación no puede estar vacío")
    private String tipoIdentificacion;

    @Column(unique = true, nullable = false)
    @NotNull(message = "El número de identificación no puede estar vacío")
    private Long numeroIdentificacion;

    @Column(nullable = false)
    @NotBlank(message = "El primer nombre no puede estar vacío")
    @Size(min = 2, message = "El primer nombre debe tener al menos 2 caracteres")//esto es para que la cantidad minima de caracteres en el campo sea 2
    private String primerNombre;

    @Column
    private String segundoNombre;

    @Column(nullable = false)
    @NotBlank(message = "El primer apellido no puede estar vacío")
    @Size(min = 2, message = "El primer apellido debe tener al menos 2 caracteres")
    private String primerApellido;

    @Column(nullable = false)
    @NotBlank(message = "El segundo apellido no puede estar vacío")
    @Size(min = 2, message = "El segundo apellido debe tener al menos 2 caracteres")
    private String segundoApellido;

    @Column(unique = true, nullable = false)
    @Email(message = "El correo debe ser válido")//esto es para que la informacion corresponda a un correo electronico
    @NotBlank(message = "El correo no puede estar vacío")
    private String correoElectronico;

    @Column(nullable = false)
    @NotNull(message = "La fecha de nacimiento no puede estar vacía")
    private LocalDate fechaNacimiento;

    @Column(nullable = false, updatable = false)
    private LocalDate fechaCreacion;

    @Column
    private LocalDate fechaModificacion;


}
