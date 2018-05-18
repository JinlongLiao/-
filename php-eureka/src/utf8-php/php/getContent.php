<?php
require_once '../../SqlHelper.php';
require_once '../../util.php';
//获取数据
error_reporting(E_ERROR | E_WARNING);
$client = new \Eureka\SqlHelper();
# 有 id 单个
if (is_numeric($_GET['id']) && trim($_GET['id']) != '') {
    $sql = "select * from tb_content where id=" . mysqli_real_escape_string($client->conn, $_GET['id']);
//    echo $sql;
    $result = $client->execute_dql2($sql);
    foreach ($result as $value) {
        echo base64_decode($value['content']);
    }
} else {
    # 无id 全部
    $sql = "SELECT * FROM tb_content  ";
    $DEFAULT_SIZE = 50;
#开始是不是 数字
    if (is_numeric($_REQUEST['start']) && $_REQUEST['start'] > 0) {
        #偏移量
        if (is_numeric($_REQUEST['size']) && $_REQUEST['size'] > 0) {
            $sql = $sql . "LIMIT " . $_REQUEST['start'] . ", " . $_REQUEST['size'];
        } else {
            $sql = $sql . "LIMIT " . $_REQUEST['start'] . ", " . $DEFAULT_SIZE;
        }
    } else {
        $sql = $sql . " LIMIT " . $DEFAULT_SIZE;
    }

    $result = $client->execute_dql2($sql);
#echo base64_decode($result[0]['title']);
    foreach ($result as $key => $value) {
        $result[$key]['title'] = base64_decode($value['title']);
    }
#var_dump($result);
    echo json_encode($result);
}