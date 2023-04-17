package com.example.iscar.Padres;

import android.media.Image;

import java.util.Objects;

public class Test {

    int idPregunta;
    String enunciado;
    OpcionA opcion1;
    OpcionA opcion2;
    OpcionA opcion3;
    String solucion;
    String explicacion;
    Image foto;
    String idTest;

    public Test(int idPregunta, String enunciado, OpcionA opcion1, OpcionA opcion2, OpcionA opcion3, String solucion, String explicacion, Image foto, String idTest) {
        this.idPregunta = idPregunta;
        this.enunciado = enunciado;
        this.opcion1 = opcion1;
        this.opcion2 = opcion2;
        this.opcion3 = opcion3;
        this.solucion = solucion;
        this.explicacion = explicacion;
        this.foto = foto;
        this.idTest = idTest;
    }

    public int getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(int idPregunta) {
        this.idPregunta = idPregunta;
    }

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    public OpcionA getOpcion1() {
        return opcion1;
    }

    public void setOpcion1(OpcionA opcion1) {
        this.opcion1 = opcion1;
    }

    public OpcionA getOpcion2() {
        return opcion2;
    }

    public void setOpcion2(OpcionA opcion2) {
        this.opcion2 = opcion2;
    }

    public OpcionA getOpcion3() {
        return opcion3;
    }

    public void setOpcion3(OpcionA opcion3) {
        this.opcion3 = opcion3;
    }

    public String getSolucion() {
        return solucion;
    }

    public void setSolucion(String solucion) {
        this.solucion = solucion;
    }

    public String getExplicacion() {
        return explicacion;
    }

    public void setExplicacion(String explicacion) {
        this.explicacion = explicacion;
    }

    public Image getFoto() {
        return foto;
    }

    public void setFoto(Image foto) {
        this.foto = foto;
    }

    public String getIdTest() {
        return idTest;
    }

    public void setIdTest(String idTest) {
        this.idTest = idTest;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Test)) return false;
        Test test = (Test) o;
        return getIdPregunta() == test.getIdPregunta() && getEnunciado().equals(test.getEnunciado()) && getOpcion1().equals(test.getOpcion1()) && getOpcion2().equals(test.getOpcion2()) && getOpcion3().equals(test.getOpcion3()) && getSolucion().equals(test.getSolucion()) && getExplicacion().equals(test.getExplicacion()) && getFoto().equals(test.getFoto()) && getIdTest().equals(test.getIdTest());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdPregunta(), getEnunciado(), getOpcion1(), getOpcion2(), getOpcion3(), getSolucion(), getExplicacion(), getFoto(), getIdTest());
    }

    @Override
    public String toString() {
        return "Test{" +
                "idPregunta=" + idPregunta +
                ", enunciado='" + enunciado + '\'' +
                ", opcion1='" + opcion1 + '\'' +
                ", opcion2='" + opcion2 + '\'' +
                ", opcion3='" + opcion3 + '\'' +
                ", solucion='" + solucion + '\'' +
                ", explicacion='" + explicacion + '\'' +
                ", foto=" + foto +
                ", idTest='" + idTest + '\'' +
                '}';
    }
}
