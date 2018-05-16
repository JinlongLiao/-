var util = require('util');
var express = require('express');
var router = express.Router();
var connPool = require('../DB/MysqlDb');
var pool = connPool;
/* GET users listing. */
router.get('/', function(req, res, next) {
    res.send('不接受Get 请求');
});
router.post('/', function(req, res, next) {
    //	res.send(typeof(connPool));
    pool.getConnection(function(err, connection) {
        if (err) {
            console.log(err);
            console.log("建立连接失败");
        } else {
            console.log("建立连接成功");
            console.log(pool._allConnections.length);//1
            connection.query('select * from tb_main', function(err, data) {
                if (err) {
                    console.log("查询失败");
                } else {
                    res.writeHead(200, {'Content-Type': 'application/json; charset=utf-8'});
                    res.write(JSON.stringify(data));
                    res.end();
                }
                connection.destroy();
                console.log(pool._allConnections.length);//0
            });
        }
//		pool.end();
    })
   // res.send('哈哈接受POST 请求');
});
module.exports = router;
