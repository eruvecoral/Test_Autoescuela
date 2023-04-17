<?php
require( "conexion.php" );

$tabla = !empty( ( $_POST[ 'tabla' ] ) ) ? $_POST[ 'tabla' ] : NULL;
//$tabla = "tabla_puntuacion_usuario_isabel";
$seccion = !empty( ( $_POST[ 'seccion' ] ) ) ? $_POST[ 'seccion' ] : NULL;
//$seccion = "1";

$consulta = "SELECT * FROM `$tabla` WHERE seccion = 'Sección $seccion' ORDER BY `seccion`, `tema`";
$objects = array();
$resultado = $conexion->query( $consulta );

    $i = 0;

    while ( $row = mysqli_fetch_array( $resultado ) ) {

        $objects[ $i ] = array(
            
            "idCuestionario" => $row[ 'idCuestionario' ],
            "usuarioCuestion" => $row[ 'usuarioCuestion' ],
			"seccion" => $row[ 'seccion' ],
            "tema" => $row[ "tema" ],
            "n_correctas" => $row[ "n_correctas" ],
			"porcentaje_correctas" => $row[ "porcentaje_correctas" ]
        );

        $i++;
	
    }

	echo json_encode(array("puntuaciones" => $objects), JSON_UNESCAPED_UNICODE);

    $resultado->free();
?>
