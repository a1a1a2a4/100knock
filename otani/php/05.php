<?php
  $stdin = trim(fgets(STDIN));
  $stdin = explode(" ", $stdin);
  $str = $stdin[0];
  $num = $stdin[1];

  var_dump(ngram($str, $num));

  function ngram($str, $num){
    $ngram = array();
    $j = 0;
    for($i = 0; $i < (strlen($str) - $num + 1); $i++) {
        $ngram[$j++] = mb_substr($str, $i, $num);
    }
    return $ngram;
  }
?>
