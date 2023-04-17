<?php
require( "conexion.php" );

$tabla = !empty( ( $_POST[ 'tabla' ] ) ) ? $_POST[ 'tabla' ] : NULL;
//$tabla = "tabla_puntuacion_usuario_isabel";

$consulta = "SELECT DISTINCT `seccion` FROM `$tabla` ORDER BY `$tabla`.`seccion` ASC";
$objects = array();
$resultado = $conexion->query( $consulta );

    $i = 0;

    while ( $row = mysqli_fetch_array( $resultado ) ) {


        // ARRAY DONDE SE GENERA A PARTIR DE UN $i 0

        $objects[ $i ] = array(
            "seccion" => $row[ 'seccion' ]
        );

        $i++;	
		
    }

	echo json_encode(array("secciones" => $objects), JSON_UNESCAPED_UNICODE);

    $resultado->free();

?>
