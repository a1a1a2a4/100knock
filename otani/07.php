<?php
  $x = "12";
  $y = "気温";
  $z = "22.4";

  print_r(x_y_z($x, $y, $z));

  function x_y_z($x, $y, $z){
    return $x. "時の". $y. "は". $z;
  }

?>
