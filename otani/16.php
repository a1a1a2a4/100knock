<?php
  $num = trim(fgets(STDIN));
  $file = (file(__DIR__ . '/hightemp.txt'));
  $line = count($file) / $num;
  $rest = count($file) % $num;
  print($line);

  $n = 1;
  $count = 0;
  foreach(range(0, count($file) - 1) as $i){
    file_put_contents("divide-hightemp-". $n. ".txt", $file[$i]. PHP_EOL, FILE_APPEND);
    $count++;
    if($rest != 0 && $n >= $num - 1){

    }elseif($count >= $line){
      $n++;
      $count = 0;
    }
  }

?>
