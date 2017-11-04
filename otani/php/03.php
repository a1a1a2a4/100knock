<?php
  $str = "Now I need a drink, alcoholic of course, after the heavy lectures involving quantum mechanics.";
  $str_rep1 = str_replace(",", "", $str);
  $str_rep2 = str_replace(".", "", $str_rep1);
  $str_exp = explode(" ", $str_rep2);
  $i = 0;
  foreach($str_exp as $word){
    $res[$i++] = strlen($word);
  }
  foreach($res as $num){
    echo $num. " ";
  }
?>
