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
public class RecyclerElemento_String implements Serializable {
    public String Temario, Seccion, Tema;

    public RecyclerElemento_String(String seccion) {
        this.Seccion = seccion;
    }

    public RecyclerElemento_String(String seccion, String tema, String estado ) {
        this.Seccion = seccion;
        this.Tema = tema;
    }


    public String getSeccion() {
        return Seccion;
    }

    public void setSeccion(String seccion) {
        this.Seccion = seccion;
    }

    public String getTema() { return Tema;}

    public void setTema(String tema) {this.Tema = tema;}

    public String getTemario() {return Temario;}

    public void setTemario(String temario) {Temario = temario;}
}
