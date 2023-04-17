<?php
$hostname='localhost';
$database='test_autoescuela';
$username='root';
$password='12345';

$conexion=new mysqli($hostname,$username,$password,$database);
if($conexion->connect_errno){
    die("El sitio web está experimentado problemas" . mysqli_connect_error()) ;
}else{
//    echo "Conexion realizada con exito <br>";
}
?>