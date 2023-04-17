<?php
require( "conexion.php" );

$tabla = !empty( ( $_POST[ 'tabla' ] ) ) ? $_POST[ 'tabla' ] : NULL;
//$tabla = "tabla_puntuacion_usuario_isabel";
$usuarioCuestion = !empty( ( $_POST[ 'usuarioCuestion' ] ) ) ? $_POST[ 'usuarioCuestion' ] : NULL;
//$usuarioCuestion = "isabel";
$seccion = !empty( ( $_POST[ 'seccion' ] ) ) ? $_POST[ 'seccion' ] : NULL;
//$seccion = "seccion_1";
$tema = !empty( ( $_POST[ 'tema' ] ) ) ? $_POST[ 'tema' ] : NULL;
//$tema = "tema_1";
$n_correctas = !empty( ( $_POST[ 'n_correctas' ] ) ) ? $_POST[ 'n_correctas' ] : NULL;
//$n_correctas = 10;

$consulta = "INSERT INTO $tabla (`usuarioCuestion`, `seccion`, `tema`, `n_correctas`) VALUES ('" . $usuarioCuestion . "','" . $seccion . "','" . $tema . "', '" . $n_correctas  . "');";

// Se verifica si la tabla ha sido creado
if ($conexion->query($consulta) === TRUE) {
    $msg = "1";
} else {
    $msg = "0";
}
$resultado['msg']=$msg;
        $datos[] = $resultado;

echo json_encode(array("insercion" => $datos));
// Cerramos la conexión
$conexion->close();
?>