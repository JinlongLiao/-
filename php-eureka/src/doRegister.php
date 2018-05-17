<?php

require_once '../vendor/autoload.php';

$client = new \Eureka\EurekaClient(['eurekaDefaultUrl' => 'http://127.0.0.1:890/eureka',
    'hostName' => '127.0.0.1',
    'appName' => 'PHP_DATA_PROVIDER',
    'ip' => '127.0.0.1',
    'port' => ['81', true],
    'homePageUrl' => 'http://127.0.0.1:81',
    'statusPageUrl' => 'http://127.0.0.1:81/info.php',
    'healthCheckUrl' => 'http://127.0.0.1:81/health.php',
    'vipAddress' => 'PHP_DATA_PROVIDER',
    'secureVipAddress' => 'PHP_DATA_PROVIDER']);

class DummyProvider implements \Eureka\Interfaces\InstanceProvider
{

    public function getInstances($appName)
    {
        return null;
    }
}

$client->getConfig()->setInstanceProvider(new DummyProvider());
$GLOBALS['client']=$client;

try {
    $client->start();
} catch (\Eureka\Exceptions\EurekaClientException $e) {
    echo $e->getMessage();
}