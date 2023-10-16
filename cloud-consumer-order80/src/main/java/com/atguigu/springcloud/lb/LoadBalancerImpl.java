package com.atguigu.springcloud.lb;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class LoadBalancerImpl implements LoadBalancer {

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    private final int getAndIncrement() {

        int expect;
        int update;
        do {
            expect = this.atomicInteger.get();
            update = expect >= Integer.MAX_VALUE ? 0 : expect + 1;
        } while (!this.atomicInteger.compareAndSet(expect, update));
        System.out.println("第几次访问update" + update);
        return update;


    }

    @Override
    public ServiceInstance instance(List<ServiceInstance> instances) {

        int index = getAndIncrement() % instances.size();

        return instances.get(index);
    }


}
