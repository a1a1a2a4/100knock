<?php
  $str1 = "paraparaparadise";
  $str2 = "paragraph";
  $x = bigram($str1);
  $y = bigram($str2);

  $union = array_unique(array_merge($x , $y));
  $diffset = array_unique(array_diff($x, $y));
  $intersection = array_unique(array_intersect($x, $y));

  print(in_array("se", $union));

  // var_dump($union);
  // var_dump($diffset);
  // var_dump($intersection);

  function bigram($str){
    $bigram = array();
    $j = 0;
    for($i = 0; $i < (strlen($str) - 1); $i++) {
        $bigram[$j++] = mb_substr($str, $i, 2);
    }
    return $bigram;
  }
?>
