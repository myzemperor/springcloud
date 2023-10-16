package com.atguigu.springcloud.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "CLOUD-PROVIDER-HYSTRIX-PAYMENT",fallback = PaymentHystrixServiceImpl.class)
public interface PaymentHystrixService {
    @GetMapping(value = "/payment/pyamentInfoOk/{id}")
    public String pyamentInfoOk(@PathVariable("id") Integer id);

    @GetMapping(value = "/payment/pyamentInfoTimeOut/{id}")
    public String pyamentInfoTimeOut(@PathVariable("id") Integer id);
}
