package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {

    @Resource
    PaymentService paymentService;


    @GetMapping(value = "/payment/pyamentInfoOk/{id}")
    public String pyamentInfoOk(@PathVariable("id") Integer id) {
        String result = paymentService.pyamentInfoOk(id);
        log.info("result:" + result);
        return result;
    }


    @GetMapping(value = "/payment/pyamentInfoTimeOut/{id}")
    public String pyamentInfoTimeOut(@PathVariable("id") Integer id) {
        String result = paymentService.pyamentInfoTimeOut(id);
        log.info("result:" + result);
        return result;
    }

    @GetMapping(value = "/payment/paymentCircuitBreaker/{id}")
    public String paymentCircuitBreaker(@PathVariable("id") Integer id) {
        String result = paymentService.paymentCircuitBreaker(id);
        log.info("result:" + result);
        return result;
    }

}
