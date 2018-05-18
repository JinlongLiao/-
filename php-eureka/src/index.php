<h1>It Work</h1>
<a href="./doRegister.php" target="nnn"> 去注册一下</a>
<?php
require_once './vendor/autoload.php';
require_once './SqlHelper.php';
/**
 * Created by IntelliJ IDEA.
 * User: liaojl
 * Date: 2018/5/17
 * Time: 20:29
 */
header('Content-Type:text/html;charset=UTF-8');
$mysql = new \Eureka\SqlHelper();
var_dump($mysql->execute_dql2("select * from user"));