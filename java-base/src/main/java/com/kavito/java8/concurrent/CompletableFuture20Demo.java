package com.kavito.java8.concurrent;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.time.Duration;
import java.time.LocalTime;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * @Description:
 * @Author: kavito
 * @Date: 2019/12/25 10:20 上午
 */
public class CompletableFuture20Demo {
    private static final Logger LOGGER = LoggerFactory.getLogger(CompletableFuture20Demo.class);

    // 这个简单的示例中创建了一个已经完成的预先设置好结果的CompletableFuture。通常作为计算的起点阶段。
    @Test
    public void completedFutureExample() {
        CompletableFuture cf = CompletableFuture.completedFuture("Hello World!");
        assertTrue(cf.isDone());
        // getNow方法会返回完成后的结果（这里就是message），如果还未完成，则返回传入的默认值null。
        assertEquals("Hello World!", cf.getNow(null));
    }

    @Test
    public void runAsyncExample() {
        LocalTime startTime = LocalTime.now();
        CompletableFuture cf = CompletableFuture.runAsync(() -> {
            assertTrue(Thread.currentThread().isDaemon());
            randomSleep();
        });
        assertFalse(cf.isDone());
        sleepEnough();
        assertTrue(cf.isDone());
        LocalTime endTime = LocalTime.now();
        LOGGER.info("程序运行时间{}毫秒", Duration.between(endTime,startTime).toMillis());

    }


    @Test
    public void test(){
        CompletableFuture<String> futureA = CompletableFuture.supplyAsync(() -> "任务A");
        CompletableFuture<String> futureB = CompletableFuture.supplyAsync(() -> "任务B");
        futureB.thenAccept((b -> {
            System.out.println("执行任务C.");
            System.out.println("参数:" + b);//参数:任务B
        }));
    }


    @Test
    public void Test(){

        ExecutorService executorService = Executors.newFixedThreadPool(4);

        long start = System.currentTimeMillis();
        CompletableFuture<String> futureA = CompletableFuture.supplyAsync(() -> {

            randomSleep();

            return "商品详情";
        },executorService);

        CompletableFuture<String> futureB = CompletableFuture.supplyAsync(() -> {
            randomSleep();
            return "卖家信息";
        },executorService);

        CompletableFuture<String> futureC = CompletableFuture.supplyAsync(() -> {
            randomSleep();
            return "库存信息";
        },executorService);

        CompletableFuture<String> futureD = CompletableFuture.supplyAsync(() -> {
            randomSleep();
            return "订单信息";
        },executorService);

        CompletableFuture<Void> allFuture = CompletableFuture.allOf(futureA, futureB, futureC, futureD);
        String result =(String) CompletableFuture.anyOf(futureA, futureB, futureC, futureD).join();
        System.out.println(result);
        System.out.println(futureA.join() + futureB.join() + futureC.join() + futureD.join());
        System.out.println("总耗时:" + (System.currentTimeMillis() - start));
    }





    private static void sleepEnough() {
        try {
            LOGGER.info("任务二：开始执行！");
            TimeUnit.SECONDS.sleep( 4 );
            LOGGER.info("任务二：执行4秒，完成。");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void randomSleep()  {
        SecureRandom random= null;
        try {
            random = SecureRandom.getInstance("SHA1PRNG");
            Integer randomNum = random.nextInt(3)+1;
            LOGGER.info("{}：开始执行任务{}秒",Thread.currentThread().getName(),randomNum);
            TimeUnit.SECONDS.sleep(randomNum.longValue());
            LOGGER.info("{}：任务执行{}秒，完成。",Thread.currentThread().getName(),randomNum);
        } catch (NoSuchAlgorithmException | InterruptedException e) {
            e.printStackTrace();
        }



    }


}
