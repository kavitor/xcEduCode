package com.kavito.java8.concurrent;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;

/**
 * @Description:
 * @Author: kavito
 * @Date: 2019/12/25 3:19 下午
 */
public class CompletableFutureAsyncTest {

    private final static Logger logger = LoggerFactory.getLogger(CompletableFutureAsyncTest.class);

    @Test
    public  void test1() throws Exception {
        CompletableFuture<Integer> f = new CompletableFuture<Integer>();

        new Thread(() -> {
            // 子线程A启动
            logger.info("子线程A启动");
            try {
                logger.info("子线程A沉睡5s");
                Thread.sleep(5000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            logger.info("子线程A令future完成");
            f.complete(100);  // 当子线程A执行到f.complete的时候，会去看是否有注册好的f的then或者when（非async的），如果有的话，会顺次去执行。
            logger.info("子线程A结束");
        }).start();;


        // 当前线程（主线程）执行到这里的时候，如果子线程还没有执行到f.complete(100)，
        // 那么当前线程会把whenComplete事件注册起来，并且说好哪个线程执行了f.complete(100)，
        // 哪个线程就负责执行whenComplete的内容。
        // 如果当前线程（主线程）执行到这里的时候，f.complete(100)已经被其他线程执行完毕了。
        // 那么只有当前线程自己来执行whenComplete里面的内容了。
        f.whenComplete((i, ex) -> {
            // 这个场景下，whenComplete的回调的执行线程会是子线程A
            logger.info("do something after complete begin");
            try {
                logger.info("沉睡10s");
                Thread.sleep(10000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            logger.info("do something after complete end");
        });


        logger.info("main over");
        System.in.read();

    }

    @Test
    public void test2() throws Exception {


        CompletableFuture<Integer> f = new CompletableFuture<Integer>();

        new Thread(() -> {
            // 子线程A启动
            logger.info("子线程A启动");
            try {
                logger.info("子线程A沉睡5s");
                Thread.sleep(5000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            logger.info("子线程A令future完成");
            f.complete(100);  // 当子线程A执行到f.complete的时候，会去看是否有注册好的f的then或者when（非async的），如果有的话，会顺次去执行。
            logger.info("子线程A结束");
        }).start();;


        // 当前线程（主线程）执行到这里的时候，如果子线程还没有执行到f.complete(100)，
        // 那么当前线程会把whenComplete事件注册起来，并且说好哪个线程执行了f.complete(100)，
        // 哪个线程就负责执行whenComplete的内容。
        // 如果当前线程（主线程）执行到这里的时候，f.complete(100)已经被其他线程执行完毕了。
        // 那么只有当前线程自己来执行whenComplete里面的内容了。

        try {
            logger.info("主线程沉睡10s");
            Thread.sleep(10000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        f.whenComplete((i, ex) -> {
            // 这个场景下，whenComplete的回调的执行线程会是主线程
            logger.info("do something after complete begin");
            try {
                logger.info("沉睡10s");
                Thread.sleep(10000L);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            logger.info("do something after complete end");
        });


        logger.info("main over");
        System.in.read();

    }

}
