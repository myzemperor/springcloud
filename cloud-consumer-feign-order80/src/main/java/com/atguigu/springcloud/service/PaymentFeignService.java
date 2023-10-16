package com.atguigu.springcloud.service;

import com.atguigu.springcloud.entities.CommenResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "cloud-payment-service")
public interface PaymentFeignService {

    @GetMapping(value = "/payment/get/{id}")
    CommenResult getPaymentById(@PathVariable("id") Long id);

    @GetMapping(value = "/payment/getPaymentTimeOut")
    public String getPaymentTimeOut();
}
