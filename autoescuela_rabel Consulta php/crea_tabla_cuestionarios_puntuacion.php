<?php
require( "conexion.php" );

$tabla = !empty( ( $_POST[ 'tabla' ] ) ) ? $_POST[ 'tabla' ] : NULL;
//$tabla = "usuario_elusuario_seccion_x_tema_y";

$consulta = "CREATE TABLE `$tabla` (
  `idCuestionario` int(11) NOT NULL AUTO_INCREMENT,
  `usuarioCuestion` varchar(50) NOT NULL,
  `seccion` varchar(50) NOT NULL,
  `tema` varchar(20) NOT NULL,
  `n_correctas` varchar(20) NOT NULL,
  `porcentaje_correctas` int(11) AS ((n_correctas*100)/30),
  PRIMARY KEY (`idCuestionario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;";

$respuesta = mysqli_Query($conexion,$consulta);

if($respuesta){
	echo '("respuesta":"ok")';
}else{
	echo '("respuesta":"error")';
}
$conexion->close();
?>
