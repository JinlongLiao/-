var createError = require('http-errors');
var express = require('express');
var path = require('path');
var cookieParser = require('cookie-parser');
var logger = require('morgan');

var indexRouter = require('./routes/index');
var usersRouter = require('./routes/users');
var mainsRouter = require('./routes/main');
/*
启动Eureka js 客户端
 */
var Eureka = require('eureka-js-client').Eureka
var client = new Eureka({
    instance: {
        instanceId: 'NODEJS-PROVIDER',
        app: 'NODEJS-PROVIDER',
        hostName: '127.0.0.1',
        ipAddr: '127.0.0.1',
        preferIpAddress: true, // default is false and host will be used.
        homePageUrl: 'http://localhost:3000/info',
        statusPageUrl: 'http://localhost:3000/info',
        // healthCheckUrl: 'http://localhost:3000info',
        port: {
            '$': 3000,
            '@enabled': 'true',
        },
        vipAddress: 'NODEJS-PROVIDER', // Important, otherwise spring-apigateway cannot find instance of book-service
        // secureVipAddress: 'book-service',
        dataCenterInfo: {
            '@class': 'com.netflix.appinfo.InstanceInfo$DefaultDataCenterInfo',
            name: 'MyOwn',
        },
    },
    eureka: {
        fetchRegistry: false,
        host: 'localhost',
        port: 890,
        servicePath: '/eureka/apps/'
    },
});
client.logger.level('debug');
client.start(function (error) {
    console.log(error || 'complete');
});
client.start()

var app = express();

// view engine setup
app.set('views', path.join(__dirname, 'views'));
app.set('view engine', 'ejs');

app.use(logger('dev'));
app.use(express.json());
app.use(express.urlencoded({extended: false}));
app.use(cookieParser());
app.use(express.static(path.join(__dirname, 'public')));
app.use('/info', function (req, res, next) {
    res.setHeader('Content-Type', 'application/json');
    res.send('{}');
})
app.use('/health', function (req, res) {
    res.setHeader('Content-Type', 'application/json');
    res.send("{status: 'UP' }");
});
app.use('/', indexRouter);
app.use('/users', usersRouter);
app.use('/main', mainsRouter);

// catch 404 and forward to error handler
app.use(function (req, res, next) {
    next(createError(404));
});

// error handler
app.use(function (err, req, res, next) {
    // set locals, only providing error in development
    res.locals.message = err.message;
    res.locals.error = req.app.get('env') === 'development' ? err : {};

    // render the error page
    res.status(err.status || 500);
    res.render('error');
});

module.exports = app;
console.log("Servver is Running!!!!!");
