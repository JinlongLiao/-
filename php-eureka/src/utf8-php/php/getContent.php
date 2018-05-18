<?php
require_once '../../SqlHelper.php';
require_once '../../util.php';
//获取数据
error_reporting(E_ERROR | E_WARNING);
#   $content =  htmlspecialchars($_POST['myEditor']);
$content = str_replace(array("\r\n", "\r", "\n"), "", $_POST['content']);
if ($content != null && trim($content) != '') {
    $client = new \Eureka\SqlHelper();
    if (trim($_POST['id']) == null) {
        $date = date("Y-m-d h:i:s");
        #    file_put_contents('./data.txt',htmlspecialchars($content));
        #添加
        $sql = "INSERT INTO tb_content (content, addTime, updateTime) VALUES ('" . base64_encode($content) . "', '" . $date . "', '" . $date . "');";
        # file_put_contents('./data.txt', ($sql));
        $client->execute_dql2($sql);
        echo '{"RESULT":"SUCCESS"}';
    } else {
#    修改
        $sql = "UPDATE  tb_content SET content='" . base64_encode($content) . "',, updateTime='" . date("Y-m-d h:i:s") . "' WHERE (id='" . mysqli_real_escape_string($_POST['id']) . "')";
        $client->execute_dql2($sql);
        echo '{"RESULT":"SUCCESS"}';
    }
}

//存入数据库或者其他操作
//显示
//echo "<div class='content'>" . htmlspecialchars_decode($content) . "</div>";
