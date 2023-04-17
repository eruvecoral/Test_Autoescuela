<?php
require( "conexion.php" );
require_once 'RecyclerTest.php';
require_once 'Test.php';
require_once 'OpcionA.php';

$tabla = !empty( ( $_POST[ 'tabla' ] ) ) ? $_POST[ 'tabla' ] : NULL;

$consulta = "SELECT  `foto`, `idPregunta`, `enunciado`, `opcion1`, `opcion2`, `opcion3`, `solucion`, `explicacion`,`idTest` FROM $tabla ";
$objects = array();
$resultado = $conexion->query( $consulta );

    $i = 0;
    while ( $row = mysqli_fetch_array( $resultado ) ) {
        $objects[ $i ] = array(
            "foto" => "data:imgage/jpg; base64," . base64_encode($row[ 'foto' ]),
            "idPregunta" => $row[ 'idPregunta' ],
            "enunciado" => $row[ 'enunciado' ],
            "opcion1" => $row[ "opcion1" ],
            "opcion2" => $row[ "opcion2" ],
            "opcion3" => $row[ "opcion3" ],
            "solucion" => $row[ 'solucion' ],
            "explicacion" => $row[ 'explicacion' ],
			"idTest" => $row['idTest']
        );
        $i++;
    }
	echo json_encode(array("preguntas" => $objects));

    $resultado->free();
    $resultado->close();
	$conexion->close();

?>
