package com.example.iscar.Padres;

import java.io.Serializable;
import java.util.List;

public class RecyclerTest implements Serializable {
    private int id, selecionado;
    private String pregunta, solucion,explicacion,seccion,tema,imagen,respuesta,idTest;
    private OpcionA opcA = new OpcionA(),opcB= new OpcionA(),opcC= new OpcionA();
    private int test;
    private boolean esCorrecta;
    private List<RecyclerTest> listRecyclerTest;



    public RecyclerTest(String imagen, int id, String pregunta, OpcionA opcA, OpcionA opcB, OpcionA opcC, String solucion, String explicacion, String respuesta, String idTest, Boolean esCorrecta, int selecionado, String seccion, String tema, int test) {
        this.imagen = imagen;
        this.id = id;
        this.pregunta = pregunta;
        this.opcA = opcA;
        this.opcB = opcB;
        this.opcC = opcC;
        this.solucion = solucion;
        this.explicacion = explicacion;
        this.respuesta = respuesta;
        this.idTest = idTest;
        this.selecionado = selecionado;
        this.esCorrecta = esCorrecta;
        this.seccion = seccion;
        this.tema = tema;
        this.test = test;
    }

    public RecyclerTest(String imagen, int id, String pregunta, OpcionA opcA, OpcionA opcB, OpcionA opcC, String explicacion) {
        this.imagen = imagen;
        this.id = id;
        this.pregunta = pregunta;
        this.opcA = opcA;
        this.opcB = opcB;
        this.opcC = opcC;
        this.explicacion = explicacion;
    }

    public RecyclerTest(String imagen, int id, String pregunta, OpcionA opcA, OpcionA opcB, OpcionA opcC, String solucion, String explicacion) {
        this.imagen = imagen;
        this.id = id;
        this.pregunta = pregunta;
        this.opcA = opcA;
        this.opcB = opcB;
        this.opcC = opcC;
        this.solucion = solucion;
        this.explicacion = explicacion;
    }

    public RecyclerTest(int id, String pregunta, String solucion, String explicacion, String imagen, String respuesta, OpcionA opcA, OpcionA opcB, OpcionA opcC, boolean esCorrecta) {
        this.id = id;
        this.pregunta = pregunta;
        this.solucion = solucion;
        this.explicacion = explicacion;
        this.imagen = imagen;
        this.respuesta = respuesta;
        this.opcA = opcA;
        this.opcB = opcB;
        this.opcC = opcC;
        this.esCorrecta = esCorrecta;
    }

    public RecyclerTest() {

    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public OpcionA getOpcA() {
        return opcA;
    }

    public void setOpcA(String opcA) {

        this.opcA.setName(opcA);
        this.opcA.setSelected(false);
    }

    public OpcionA getOpcB() {
        return opcB;
    }

    public void setOpcB(String opcB) {
        this.opcB.setName(opcB);
        this.opcB.setSelected(false);
    }

    public OpcionA getOpcC() {
        return opcC;
    }

    public void setOpcC(String opcC) {
        this.opcC.setName(opcC);
        this.opcC.setSelected(false);
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

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public int getTest() {
        return test;
    }

    public void setTest(int test) {
        this.test = test;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public boolean isEsCorrecta() {
        return esCorrecta;
    }

    public void setEsCorrecta(String respuesta, String correcta) {
        if(respuesta.equals(correcta)){
            this.esCorrecta = true;
        }else{
            this.esCorrecta = false;
        }
    }

    public String getIdTest() {
        return idTest;
    }

    public void setIdTest(String idTest) {
        this.idTest = idTest;
    }

    public void setOpcA(OpcionA opcA) {
        this.opcA = opcA;
    }

    public void setOpcB(OpcionA opcB) {
        this.opcB = opcB;
    }

    public void setOpcC(OpcionA opcC) {
        this.opcC = opcC;
    }

    public void setEsCorrecta(boolean esCorrecta) {
        this.esCorrecta = esCorrecta;
    }

    public int getSelecionado() {
        return selecionado;
    }

    public void setSelecionado(int selecionado) {
        this.selecionado = selecionado;
    }

    public List<RecyclerTest> getListRecyclerTest() {
        return listRecyclerTest;
    }

    public void setListRecyclerTest(List<RecyclerTest> listRecyclerTest) {
        this.listRecyclerTest = listRecyclerTest;
    }

    @Override
    public String toString() {
        return "RecyclerTest{" +
                "imagen=" + imagen +
                ", id=" + id +
                ", pregunta='" + pregunta + '\'' +
                ", opcA='" + opcA + '\'' +
                ", opcB='" + opcB + '\'' +
                ", opcC='" + opcC + '\'' +
                ", solucion='" + solucion + '\'' +
                ", explicacion='" + explicacion + '\'' +
                ", seccion='" + seccion + '\'' +
                ", tema='" + tema + '\'' +
                ", test=" + test +
                '}';
    }
}

