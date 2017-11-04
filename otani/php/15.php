<?php
$num = trim(fgets(STDIN));
while ( ! feof(STDIN) ) {
  $input[] = trim(fgets(STDIN));
}
$input = array_reverse($input);
foreach(range(0, $num - 1 + 1) as $i){ #改行コード分+1
  print_r($input[$i]);
}
?>
