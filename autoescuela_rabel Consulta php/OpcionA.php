<?php

require("conexion.php");

 class OpcionA {

    private $name;
    private $isSelected;

    public function __construct($name) {
        $this->name = $name;
        $this->isSelected = false;
    }

    public function getName() {
        return $this->name;
    }

    public function setName($name) {
        $this->name = $name;
    }

    public function isSelected() {
        return $this->isSelected;
    }

    public function setSelected($selected) {
        $this->isSelected = $selected;
    }
	 
	 public function __toString()
{
    return self::class . ": " . $this->name . " " . $this->isSelected;
}

}

?>