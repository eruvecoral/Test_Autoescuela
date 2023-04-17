<?php
require( "conexion.php" );

$tabla = !empty( ( $_POST[ 'tabla' ] ) ) ? $_POST[ 'tabla' ] : NULL;
//$tabla = "usuario_elusuario_seccion_x_tema_y";
$usuarioCuestion = !empty( ( $_POST[ 'usuarioCuestion' ] ) ) ? $_POST[ 'usuarioCuestion' ] : NULL;
//$usuarioCuestion = "isabel";
$seccion = !empty( ( $_POST[ 'seccion' ] ) ) ? $_POST[ 'seccion' ] : NULL;
//$seccion = "seccion_1";
$tema = !empty( ( $_POST[ 'tema' ] ) ) ? $_POST[ 'tema' ] : NULL;
//$tema = "tema_1";
$n_correctas = !empty( ( $_POST[ 'n_correctas' ] ) ) ? $_POST[ 'n_correctas' ] : NULL;
//$n_correctas = 10;

$consulta = "INSERT INTO $tabla (`usuarioCuestion`, `seccion`, `tema`, `n_correctas`) VALUES ('" . $usuarioCuestion . "','" . $seccion . "','" . $tema . "', '" . $n_correctas  . "');";

$respuesta = mysqli_Query($conexion,$consulta);

if($respuesta){
	echo '("respuesta":"ok")';
}else{
	echo '("respuesta":"error")';
}
$conexion->close();
?>