<?php
require( "conexion.php" );

$tabla = !empty( ( $_POST[ 'tabla' ] ) ) ? $_POST[ 'tabla' ] : NULL;
//$tabla = "tabla_puntuacion_usuario_isabel";
$seccion = !empty( ( $_POST[ 'seccion' ] ) ) ? $_POST[ 'seccion' ] : NULL;
//$seccion = "5";

$tema = !empty( ( $_POST[ 'tema' ] ) ) ? $_POST[ 'tema' ] : NULL;
//$tema = "2";

$consulta = "SELECT * FROM `$tabla` WHERE `seccion` = 'Sección $seccion' AND `tema` = 'Tema $tema'";
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
