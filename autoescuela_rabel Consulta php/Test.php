<?php
require("conexion.php");
require_once 'RecyclerTest.php';
class Test{
    private  $recyclerTest=array();

    function __construct() {
        
    }
    
    function addTest($unTest){
        if ($unTest instanceof RecyclerTest) {
            $this->recyclerTest[]=$unTest;
        }
    }
    
    function getTotal(){
        $total=0;
        foreach($this->recyclerTest as $arr) {
            $total+=$arr->getPtos();
        }
        return $total;
    }
    
}
?>