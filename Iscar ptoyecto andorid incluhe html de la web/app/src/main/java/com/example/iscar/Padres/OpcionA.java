package com.example.iscar.Padres;

import java.io.Serializable;

public class OpcionA implements Serializable {

    int idPregunta;
    private String name;
    private boolean isSelected;

    public OpcionA() {
    }

    public OpcionA(String name, boolean isSelected) {
        this.name = name;
        this.isSelected = isSelected;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public int getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(int idPregunta) {
        this.idPregunta = idPregunta;
    }

    @Override
    public String toString() {
        return "OpcionA{" +
                "name='" + name + '\'' +
                ", isSelected=" + isSelected +
                '}';
    }
}
