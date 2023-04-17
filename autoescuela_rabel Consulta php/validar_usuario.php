<?php
require( "conexion.php" );

$usu_usuario = !empty( ( $_POST[ 'usuario' ] ) ) ? $_POST[ 'usuario' ] : NULL;
$usu_password = !empty( ( $_POST[ 'password' ] ) ) ? $_POST[ 'password' ] : NULL;

//$usu_usuario = 'isabel';
//$usu_password = 'isa';

$consulta = "SELECT COUNT(*)
 FROM usuarios
 WHERE `usuario` = '$usu_usuario' AND
       password = '$usu_password'";
$resultado=mysqli_query($conexion,$consulta);

while ($row = mysqli_fetch_row($resultado))
{
echo $row[0];
}

mysqli_free_result($resultado);
?>