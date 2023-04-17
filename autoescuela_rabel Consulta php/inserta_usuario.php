<?php
require( "conexion.php" );

$usuario = !empty( ( $_POST[ 'usuario' ] ) ) ? $_POST[ 'usuario' ] : NULL;
//$usuario = "Mario";
$password = !empty( ( $_POST[ 'password' ] ) ) ? $_POST[ 'password' ] : NULL;
//$password = "1414";
$nombre = !empty( ( $_POST[ 'nombre' ] ) ) ? $_POST[ 'nombre' ] : NULL;
//$nombre = "Mario";
$apellidos = !empty( ( $_POST[ 'apellidos' ] ) ) ? $_POST[ 'apellidos' ] : NULL;
//$apellidos = "De Sampedro";

$consulta = "INSERT INTO `usuarios` (`usuario`, `password`, `nombre`, `apellidos`) VALUES ('" . $usuario . "','" . $password . "','" . $nombre . "', '" . $apellidos  . "');";

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