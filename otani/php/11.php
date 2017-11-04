<?php
  $file = (file(__DIR__ . '/hightemp.txt'));
  $res = str_replace("\t", " ", $file);
  var_dump($res);
?>
