<?php
require("conexion.php");
require('OpcionA.php');

class RecyclerTest {
    private $imagen, $id;
    private $pregunta,$opcA;
	private $opcB,$opcC;
	private $solucion,$explicacion,$seccion,$tema, $idTest;
    private $test;

    public function __construct($imagen, $id, $pregunta, OpcionA $opcA, OpcionA $opcB, OpcionA $opcC, $solucion, $explicacion, $idTest) {
        $this->imagen = $imagen;
        $this->id = $id;
        $this->pregunta = $pregunta;
        $this->opcA = $opcA;
        $this->opcB = $opcB;
        $this->opcC = $opcC;
        $this->solucion = $solucion;
        $this->explicacion = $explicacion;
		$this->idTest = $idTest;
    }

    public function  getImagen() {
        return $this->imagen;
    }

    public function  setImagen($imagen) {
        $this->imagen = $imagen;
    }

    public function  getId() {
        return $this->id;
    }

    public function  setId($id) {
        $this->id = $id;
    }

    public function  getPregunta() {
        return $this->pregunta;
    }

    public function  setPregunta($pregunta) {
        $this->pregunta = $pregunta;
    }

    public function  getOpcA() {
        return $this->opcA;
    }

    public function  setOpcA($opcA) {
        $this->opcA = $opcA;
    }

    public function  getOpcB() {
        return $this->opcB;
    }

    public function  setOpcB($opcB) {
        $this->opcB = $opcB;
    }

    public function getOpcC() {
        return $this->opcC;
    }

    public function  setOpcC($opcC) {
        $this->opcC = $opcC;
    }

    public function  getSolucion() {
        return $this->solucion;
    }

    public function  setSolucion($solucion) {
        $this->solucion = $solucion;
    }

    public function  getExplicacion() {
        return $this->explicacion;
    }

    public function  setExplicacion($explicacion) {
        $this->explicacion = $explicacion;
    }

    public function  getSeccion() {
        return $this->seccion;
    }

    public function  setSeccion($seccion) {
        $this->seccion = $seccion;
    }

    public function  getTema() {
        return $this->tema;
    }

    public function  setTema($tema) {
        $this->tema = $tema;
    }

    public function  getTest() {
        return $this->test;
    }

    public function  setTest($test) {
        $this->test = $test;
    }
	
	public function  getIdTest() {
        return $this->idTest;
    }

    public function  setIdTest($idTest) {
        $this->idTest = $idTest;
    }
}
?>
