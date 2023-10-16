package com.atguigu.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {
    public String pyamentInfoOk(Integer id) {
        return "线程池： " + Thread.currentThread().getName() + "paymentInfoOk,id " + id + "O(∩_∩)O哈哈~";
    }

    @HystrixCommand(fallbackMethod = "pyamentInfoTimeOutHandle", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "4000")
    })
    public String pyamentInfoTimeOut(Integer id) {

        int sleepNum = 3;
        try {
            TimeUnit.SECONDS.sleep(sleepNum);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "线程池： " + Thread.currentThread().getName() + "paymentInfoTimeOut,id " + id + "O(∩_∩)O哈哈~" + "耗时(秒)" + sleepNum + "秒钟";
    }

    public String pyamentInfoTimeOutHandle(Integer id) {
        return "线程池： " + Thread.currentThread().getName() + "系统繁忙请稍后再试,id " + id + "o(╥﹏╥)o";
    }

    @HystrixCommand(fallbackMethod = "paymentCircuitBreakerFallBack", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"),
    })
    public String paymentCircuitBreaker(Integer id) {
        if (id < 0) {
            throw new RuntimeException("id不能为负数");
        }
        String uuid = IdUtil.simpleUUID();
        return Thread.currentThread().getName() + "\t" + "调用成功，流水号：" + uuid;
    }

    public String paymentCircuitBreakerFallBack(Integer id) {
        return "id不能为负数o(╥﹏╥)o" + id;
    }

}
