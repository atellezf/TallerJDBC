package dev.atellezf.nomina.beans;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Empleado {

    private final int id;
    private String nombre, apellidos, email;
    private float salario;
    private int departamento;

}
