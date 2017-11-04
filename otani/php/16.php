<?php
  $num = trim(fgets(STDIN));
  $file = (file(__DIR__ . '/hightemp.txt'));
  $line = floor(count($file) / $num);
  $rest = count($file) % $num;
  $cnt = $num - (($num * ($line + 1)) - count($file));

  $n = 1;
  for($i = 0; $i <= count($file) - 1; $i++){
    print($i);
    file_put_contents("divide-hightemp-". $n. ".txt", $file[$i]. PHP_EOL, FILE_APPEND);
    if($rest != 0){
      if((($i + 1) % $line) == 0 && $i != 0){
        $n++;
        if($cnt > 0){
          $n--;
          for($j = 1; $j <= ((($line + 1) * $n) - ($i + 1)); $j++){
            file_put_contents("divide-hightemp-". $n. ".txt", $file[$i + $j]. PHP_EOL, FILE_APPEND);
          }
          $n++;
          $i += ($j - 1);
          print($i. " ]". PHP_EOL);
        }
        $cnt--;
      }
    }elseif((($i + 1) % $line) == 0 && $i != 0){
      $n++;
    }
  }

?>
