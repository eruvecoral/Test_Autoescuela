<?php
require( "conexion.php" );

$tabla = !empty( ( $_POST[ 'tabla' ] ) ) ? $_POST[ 'tabla' ] : NULL;
//$tabla = "usuario_isabel_seccion_1_tema_1";

$consulta = "SELECT COUNT(*)
 FROM information_schema.tables
 WHERE table_schema = 'test_autoescuela' AND
       table_name like '" . $tabla . "';";
$resultado=mysqli_query($conexion,$consulta);

while ($row = mysqli_fetch_row($resultado))
{
echo $row[0];
}

mysqli_free_result($resultado);
?>
