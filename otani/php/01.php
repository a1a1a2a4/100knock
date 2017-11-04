<?php
  $str = "パタトクカシーー";
  $res = mb_substr($str, 0, 1). mb_substr($str, 2, 1). mb_substr($str, 4, 1). mb_substr($str, 6, 1);
  echo $res;
?>
