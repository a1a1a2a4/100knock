<?php
  touch("col3.txt");
  $col1 = (file(__DIR__ . '/col1.txt'));
  $col2 = (file(__DIR__ . '/col2.txt'));

  $col3 = array();
  for ($i = 0; $i < count($col1)  ; $i++) {
    $col1[$i] = str_replace(PHP_EOL, '', $col1[$i]);
    $col2[$i] = str_replace(PHP_EOL, '', $col2[$i]);
    $col3[$i] = $col1[$i]. "\t". $col2[$i];
    file_put_contents("col3.txt", $col3[$i]. PHP_EOL, FILE_APPEND);
  }
?>
