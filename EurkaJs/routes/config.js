var util = require('util');
var express = require('express');
var router = express.Router();
//自定义 数据库 db
var connPool = require('../DB/MysqlDb');
// 移动文件需要使用fs模块
var fs = require('fs');
var url = require('url');
var querystring = require('querystring');
var multer = require('multer');
var bodyParser = require('body-parser');
var app = express();
app.use(bodyParser.json());

var pool = connPool;
/* GET 请求用于 获得  数据 . */
router.get('/', function (req, res, next) {
    //	res.send(typeof(connPool));
    pool.getConnection(function (err, connection) {
        if (err) {
            console.log(err);
            console.log("建立连接失败");
        } else {
            console.log("建立连接成功");
            console.log(pool._allConnections.length);//1
            connection.query('select * from tb_main', function (err, data) {
                var result = {};
                if (err) {
                    console.log("查询失败");
                } else {
                    result = JSON.stringify(data);
                    result = result.substring(1, result.length - 1);
                    result = JSON.parse(result);
                }
                connection.destroy();
                console.log((result));
                res.render('config', result);
            });
        }
//		pool.end();
    })
});
//* POST 请求 用于 保存数据*/
router.post('/', function (req, res) {
    // 定义了一个post变量，用于暂存请求体的信息
    var post = '';

    // 通过req的data事件监听函数，每当接受到请求体的数据，就累加到post变量中
    req.on('data', function (chunk) {
        post += chunk;
    });

    // 在end事件触发后，通过querystring.parse将post解析为真正的POST请求格式，然后向客户端返回。
    req.on('end', function () {
        post = querystring.parse(post);
        res.end(util.inspect(post));
    });
    upload(req, res, function (err) {
        if (err) {
            throw err;
            return res.end("Something went wrong!");
        }
        return res.end("File uploaded sucessfully!.");
    });
    // res.send('哈哈接受POST 请求');
});

var Storage = multer.diskStorage({

    destination: function (req, file, callback) {
        callback(null, "./public/UserImg");
    },
    filename: function (req, file, callback) {
        var fileName = file.fieldname + "_" + Date.now() + "_" + file.originalname;
        // callback(null, fileName);
        // doDataDisk(req, fileName);
    }
});

var upload = multer({storage: Storage}).array("mbgimg", 3); //Field name and max count
function doDataDisk(req, fileName) {
    console.log(fileName)
    pool.getConnection(function (err, connection) {
        if (err) {
            console.log(err);
            console.log("建立连接失败");
        } else {
            console.log(req.body);
            // connection.
            var sql = 'UPDATE tb_main SET id= ?, m_title= ?, m_content= ? , m_bg_img= ?, m_bt_title= ?, m_bt_url=? , m_bt_desc= ? WHERE (id=?);';
            var param = [];
            param.push(req.body.id);
            param.push(req.body.mtitle);
            param.push(req.body.mbtcontent);
            param.push('UserImg/' + fileName);
            param.push(req.body.mbttitle);
            param.push(req.body.mbturl);
            param.push(req.body.mbtdesc);
            param.push(req.body.id);
            console.log(param);
            connection.query(sql, param, function (data) {
                for (var i = 0; i < arguments.length; i++) {
                    console.log(arguments[i]);
                }
            });


        }
//		pool.end();
    })
}

module.exports = router;
