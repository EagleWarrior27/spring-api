package com.mst.app.models;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@Builder
public class Usuario {
    private Integer idUsuario;

    @NotEmpty(message = "El nombre del usuario no puede estar vacío")
    @Size(min = 3, max = 30, message = "El nombre del usuario debe tener al menos 3 letras y ser menor a 30")
    private String nombre;

    @Email(message="El correo del usuario no puede estar vacío")
    @Pattern(regexp=".+@.+\\..+", message="El correo debe tener un formato válido")
    private String correo;

    public Usuario() { }

    public Integer getIdUsuario() { return idUsuario; }

    public void setIdUsuario(Integer idUsuario) { this.idUsuario = idUsuario; }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCorreo() { return correo; }

    public void setCorreo(String correo) { this.correo = correo; }
}
