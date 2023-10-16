package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "globalPyamentInfoTimeOutHandle")
public class OrderHystirxController {
    @Resource
    PaymentHystrixService paymentHystrixService;

    @GetMapping(value = "/consumer/payment/pyamentInfoOk/{id}")
    public String pyamentInfoOk(@PathVariable("id") Integer id) {
        String result = paymentHystrixService.pyamentInfoOk(id);
        log.info("result:" + result);
        return result;
    }

    @GetMapping(value = "/consumer/payment/pyamentInfoTimeOut/{id}")
//    @HystrixCommand(fallbackMethod = "pyamentInfoTimeOutHandle", commandProperties = {
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
//    })
    @HystrixCommand
    public String pyamentInfoTimeOut(@PathVariable("id") Integer id) {
//        int age = 10 / 0;
        String result = paymentHystrixService.pyamentInfoTimeOut(id);
        log.info("result:" + result);
        return result;
    }

    public String pyamentInfoTimeOutHandle(@PathVariable("id") Integer id) {
        return "我是消费者80，对方支付系统繁忙或者自己运行出错，o(╥﹏╥)o";
    }

    public String globalPyamentInfoTimeOutHandle() {
        return "我是消费者global，对方支付系统繁忙或者自己运行出错，o(╥﹏╥)o";
    }
}
