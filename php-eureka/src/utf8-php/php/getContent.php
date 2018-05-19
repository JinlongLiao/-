<?php
header('Content-Type:text/html;charset=UTF-8');
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
    if ($_REQUEST['client'] != null) {
//    其他客户端 自己 解码
        foreach ($result as $value) {
            echo($value['html']);
        }
    } else {
//    php 自己访问 解码
        foreach ($result as $value) {
            echo base64_decode($value['html']);
        }
    }
} else if (trim($_GET['id']) == '' || trim($_GET['id']) == null) {
    # 无id 全部
    $sql = "SELECT id,title,addTime,updateTime FROM tb_content";
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
} else {
    echo "<script>alert('小朋友不乖哦(≧∇≦)ﾉ(≧∇≦)ﾉ(≧∇≦)ﾉ(≧∇≦)ﾉ(≧∇≦)ﾉ(≧∇≦)ﾉ(≧∇≦)ﾉ 根本没有这条记录●﹏● ●﹏● ●﹏● ●﹏●');</script>";
}