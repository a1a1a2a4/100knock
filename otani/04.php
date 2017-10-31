<?php
  $str = "Hi He Lied Because Boron Could Not Oxidize Fluorine. New Nations Might Also Sign Peace Security Clause. Arthur King Can.";
  $str = convertToWord($str);
  $res = array();
  $condition = [1, 5, 6, 7, 8, 9, 15, 16, 19];
  $condition = array_map("decrementAll", $condition);
  foreach($str as $i => $word){
    if(in_array($i, $condition)){
      $res[$i] = $word[0];
    }else{
      $res[$i] = $word[0]. $word[1];
    }
  }
  foreach($res as $element){
    echo $element. " ";
  }

  function convertToWord($str){
    $str_rep1 = str_replace(",", "", $str);
    $str_rep2 = str_replace(".", "", $str_rep1);
    $word = explode(" ", $str_rep2);
    return $word;
  }
  function  decrementAll($num){
    return($num - 1);
  }
?>
