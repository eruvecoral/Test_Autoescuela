package com.example.iscar.Padres;

import java.io.Serializable;

public class Usuario implements Serializable {

    int id;
    String usuario;
    String password;
    String Nombre;
    String Apellidos;

    public Usuario() {
    }

    public Usuario(String usuario, String password, String nombre, String apellidos) {
        this.usuario = usuario;
        this.password = password;
        Nombre = nombre;
        Apellidos = apellidos;
    }

    public Usuario(int id, String usuario, String password, String nombre, String apellidos) {
        this.id = id;
        this.usuario = usuario;
        this.password = password;
        Nombre = nombre;
        Apellidos = apellidos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String apellidos) {
        Apellidos = apellidos;
    }
}
