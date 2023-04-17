package com.example.iscar.Padres;

import java.io.Serializable;

public class RecyclerTemario implements Serializable {

    public String Temario, Seccion, Estado, Tema, css, img;

    public RecyclerTemario(String temario, String seccion, String tema, String estado, String css, String img) {
        this.Temario = temario;
        this.Seccion = seccion;
        this.Tema = tema;
        this.Estado = estado;
        this.css = css;
        this.img = img;
    }

    public RecyclerTemario(String seccion, String tema , String estado ) {
        this.Seccion = seccion;
        this.Tema = tema;
        this.Estado = estado;
    }

    public RecyclerTemario(String seccion, String estado) {
        this.Seccion = seccion;
        this.Estado = estado;
    }

    public String getTemario() {
        return Temario;
    }

    public void setTemario(String temario) {
        Temario = temario;
    }

    public String getSeccion() {
        return Seccion;
    }

    public void setSeccion(String seccion) {
        Seccion = seccion;
    }

    public String getEstado() {
        return Estado;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }

    public String getTema() {
        return Tema;
    }

    public void setTema(String tema) {
        Tema = tema;
    }

    public String getCss() {
        return css;
    }

    public void setCss(String css) {
        this.css = css;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    @Override
    public String toString() {
        return "RecyclerTemario{" +
                "Temario='" + Temario + '\'' +
                ", Seccion='" + Seccion + '\'' +
                ", Estado='" + Estado + '\'' +
                ", Tema='" + Tema + '\'' +
                ", css='" + css + '\'' +
                ", img='" + img + '\'' +
                '}';
    }
}
