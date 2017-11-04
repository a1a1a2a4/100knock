<?php
  $str1 = "パトカー";
  $str2 = "タクシー";
  $res = "";
  foreach(range(0, 3) as $i){
    $res .= mb_substr($str1, $i, 1). mb_substr($str2, $i, 1);
  }
  echo $res;
 ?>
