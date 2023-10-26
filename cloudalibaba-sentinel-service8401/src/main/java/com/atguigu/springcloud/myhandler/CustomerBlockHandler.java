package com.atguigu.springcloud.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.entities.CommenResult;

public class CustomerBlockHandler {

    public static CommenResult handlerException1(BlockException exception) {
        return new CommenResult(444, exception.getClass().getCanonicalName() + "\t globle handlerException1");
    }

    public static CommenResult handlerException2(BlockException exception) {
        return new CommenResult(444, exception.getClass().getCanonicalName() + "\t globle handlerException2");
    }

}
