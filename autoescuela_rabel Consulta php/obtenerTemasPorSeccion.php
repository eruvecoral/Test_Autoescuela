<?php
require( "conexion.php" );


$tabla = !empty( ( $_POST[ 'tabla' ] ) ) ? $_POST[ 'tabla' ] : NULL;
//$tabla = "tabla_puntuacion_usuario_isabel";
$seccion = !empty( ( $_POST[ 'seccion' ] ) ) ? $_POST[ 'seccion' ] : NULL;
//$seccion = "1";

$consulta = "SELECT DISTINCT `tema` FROM `$tabla` WHERE seccion = 'Sección $seccion' ORDER BY `tema`";
$objects = array();
$resultado = $conexion->query( $consulta );

    $i = 0;

    while ( $row = mysqli_fetch_array( $resultado ) ) {

        $objects[ $i ] = array(
            
            "tema" => $row[ 'tema' ]
        );

        $i++;
	
    }

	echo json_encode(array("temas" => $objects));

    $resultado->free();
?>
