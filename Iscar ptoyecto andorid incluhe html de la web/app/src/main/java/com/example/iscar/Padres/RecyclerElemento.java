package com.example.iscar.Padres;

import java.io.Serializable;

/**
 *Clase que representa un objeto de Recicler View
 * referido a un directorio de tests (se usa en ActivityDirectorio)
 * Seccion es la seccion de test, dentro hay temas para cada sección. Estado se refiere a si esta
 * hecho o no.
 * Implementa Serializable para facilitar el intercambio entre
 * activities.
 */
public class RecyclerElemento implements Serializable {
    public String Temario, Seccion, Estado, Tema;

    public RecyclerElemento(String seccion, String estado) {
        this.Seccion = seccion;
        this.Estado = estado;
    }

    public RecyclerElemento(String seccion, String tema, String estado ) {
        this.Seccion = seccion;
        this.Estado = estado;
        this.Tema = tema;
    }


    public RecyclerElemento(String temario, String seccion, String tema, String estado) {
        this.Temario = temario;
        this.Seccion = seccion;
        this.Estado = estado;
        this.Tema = tema;
    }

    public String getSeccion() {
        return Seccion;
    }

    public void setSeccion(String seccion) {
        this.Seccion = seccion;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        this.Estado = estado;
    }

    public String getTema() { return Tema;}

    public void setTema(String tema) {this.Tema = tema;}

    public String getTemario() {return Temario;}

    public void setTemario(String temario) {Temario = temario;}
}
