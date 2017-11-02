<?php
$str = "I couldn't believe that I could actually understand what I was reading : the phenomenal power of the human mind .";
$str = divide_word($str);

$str_partial = array();
foreach($str as $key => $word){
  if(strlen($word) >= 5){
    $str_partial[$key] = substr($word, 1, strlen($word) - 2);
    $str_partial[$key] = str_shuffle($str_partial[$key]);
    $str[$key] = $str[$key][0]. $str_partial[$key]. $str[$key][strlen($word) - 1];
  }else{
    $str_partial[$key] = $word;
  }
}
var_dump($str);

function divide_word($str){
  $str_rep1 = str_replace(",", "", $str);
  $str_rep2 = str_replace(".", "", $str_rep1);
  $str_rep3 = str_replace(":", "", $str_rep2);
  $str_rep4 = str_replace("  ", " ", $str_rep3);
  $str_exp = explode(" ", $str_rep4);
  return $str_exp;
}

?>
