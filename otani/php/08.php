<?php
  $stdin = trim(fgets(STDIN));
  cipher($stdin);

  function cipher($str){
    for($i = 0; $i < mb_strlen($str); $i++){
      $char = mb_substr($str, $i, 1);
      if(ctype_lower($char)){
        $str = mb_ereg_replace($char, chr(219), $str);
      }
    }
    echo $str;
  }
?>
