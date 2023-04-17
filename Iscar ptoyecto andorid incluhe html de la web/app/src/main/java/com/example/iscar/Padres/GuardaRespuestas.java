package com.example.iscar.Padres;

public class GuardaRespuestas {
    int idPregunta;
    char respuesta;

    public GuardaRespuestas(int adapterPosition, String radioID) {
    }

    public GuardaRespuestas(int idPregunta, char respuesta) {
        this.idPregunta = idPregunta;
        this.respuesta = respuesta;
    }

    public int getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(int idPregunta) {
        this.idPregunta = idPregunta;
    }

    public char getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(char respuesta) {
        this.respuesta = respuesta;
    }

    @Override
    public String toString() {
        return "GuardaRespuestas{" +
                "idPregunta=" + idPregunta +
                ", respuesta=" + respuesta +
                '}';
    }
}
