<?php
$num = trim(fgets(STDIN));
while ( ! feof(STDIN) ) {
  $input[] = trim(fgets(STDIN));
}
foreach(range(0, $num - 1) as $i){
  print_r($input[$i]);  
}
?>
