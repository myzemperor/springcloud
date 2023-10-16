package com.atguigu.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentHystrixServiceImpl implements PaymentHystrixService {
    @Override
    public String pyamentInfoOk(Integer id) {
        return "PaymentHystrixServiceImpl.pyamentInfoOk服务宕机o(╥﹏╥)o";
    }

    @Override
    public String pyamentInfoTimeOut(Integer id) {
        return "PaymentHystrixServiceImpl.pyamentInfoTimeOut服务宕机o(╥﹏╥)o";
    }
}
