package com.kavito.controller;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

/**
 * @author kavito
 * @date 2019/7/10 23:12
 * 在启动类中配置了@bean redisson
 */
@RestController
@RequestMapping("/stock")
public class StockController {

   @Autowired
   private Redisson redisson;
   @Autowired
   private StringRedisTemplate stringRedisTemplate;



    @GetMapping("/deduct_stock")
    public String deductStock() throws  InterruptedException{
        String lockkey="lockkey";
        RLock lock=redisson.getLock(lockkey);
        try {
            lock.tryLock(30, TimeUnit.SECONDS);
            int stock=Integer.parseInt(stringRedisTemplate.opsForValue().get("stock"));
            if(stock>0){
                int realStock=stock-1;
                stringRedisTemplate.opsForValue().set("stock",realStock+"");
                System.out.println("扣减成功，剩余库存："+ realStock +"");
            }else{
                System.out.println("扣减失败，库存不足");
            }

        }finally {
            lock.unlock();
        }
        return "end";
    }


}
