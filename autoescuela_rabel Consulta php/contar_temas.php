<?php

require 'conexion.php';
//$tabla = !empty( ( $_POST[ 'tabla' ] ) ) ? $_POST[ 'tabla' ] : NULL;
$tabla = "seccion_1_tema1_test_1";

$sentencia=$conexion->query("SELECT `idPregunta`, `foto`, `enunciado`, `opcion1`, `opcion2`, `opcion3`, `solucion`, `explicacion` FROM $tabla");

	 while ($fila = $sentencia->fetch_assoc()) {
            echo "ID " . $fila["idPregunta"].PHP_EOL . "<br>";
		 echo $fila["enunciado"].PHP_EOL. "<br>";
		 echo $fila["opcion1"].PHP_EOL. "<br>";
		 echo $fila["opcion2"].PHP_EOL. "<br>";
		 echo $fila["opcion3"].PHP_EOL. "<br>";
		 echo $fila["solucion"].PHP_EOL. "<br>";
		 echo $fila["explicacion"].PHP_EOL. "<br>". "<br>";
        } 

$sentencia->close();
$conexion->close();

?>
