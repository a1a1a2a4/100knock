<?php
  $file = (file(__DIR__ . '/hightemp.txt'));
  touch('col1.txt');
  touch('col2.txt');
  $str_exp = array();
  foreach($file as $key => $value){
    $str_exp[$key] = explode("\t", $value);
    // print_r($str_exp[$key]);
    file_put_contents("col1.txt", $str_exp[$key][0]. PHP_EOL, FILE_APPEND);
    file_put_contents("col2.txt", $str_exp[$key][1]. PHP_EOL, FILE_APPEND);
  }
?>
