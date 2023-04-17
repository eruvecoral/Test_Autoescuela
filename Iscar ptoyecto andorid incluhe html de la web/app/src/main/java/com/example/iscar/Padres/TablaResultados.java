package com.example.iscar.Padres;

import java.io.Serializable;
import java.util.List;

public class TablaResultados implements Serializable{

  int idCuestionario;
  String usuarioCuestion;
  String seccion;
  String tema;
  int n_correctas;
  double porcentaje_Correctas;
  List<String> leyenda;

  public TablaResultados(int idCuestionario, String usuarioCuestion, String seccion, String tema, int n_correctas) {
    this.idCuestionario = idCuestionario;
    this.usuarioCuestion = usuarioCuestion;
    this.seccion = seccion;
    this.tema = tema;
    this.n_correctas = n_correctas;
  }

  public TablaResultados(String usuarioCuestion, String seccion, String tema, int n_correctas) {
    this.usuarioCuestion = usuarioCuestion;
    this.seccion = seccion;
    this.tema = tema;
    this.n_correctas = n_correctas;
  }

  public TablaResultados() {
  }

  public int getIdCuestionario() {
    return idCuestionario;
  }

  public void setIdCuestionario(int idCuestionario) {
    this.idCuestionario = idCuestionario;
  }

  public String getUsuarioCuestion() {
    return usuarioCuestion;
  }

  public void setUsuarioCuestion(String usuarioCuestion) {
    this.usuarioCuestion = usuarioCuestion;
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

  public int getN_correctas() {
    return n_correctas;
  }

  public void setN_correctas(int n_correctas) {
    this.n_correctas = n_correctas;
  }

  public List<String> getLeyenda() {
    return leyenda;
  }

  public void setLeyenda(List<String> leyenda) {
    this.leyenda = leyenda;
  }

  public double getPorcentaje_Correctas() {
    return porcentaje_Correctas;
  }

  public void setPorcentaje_Correctas(double porcentaje_Correctas) {
    this.porcentaje_Correctas = porcentaje_Correctas;
  }

  @Override
  public String toString() {
    return "TablaResultados{" +
            "\n idCuestionario=" + idCuestionario +
            ",\n usuarioCuestion='" + usuarioCuestion + '\'' +
            ",\n seccion='" + seccion + '\'' +
            ",\n tema='" + tema + '\'' +
            ",\n n_correctas=" + n_correctas +
            '}';
  }
}
