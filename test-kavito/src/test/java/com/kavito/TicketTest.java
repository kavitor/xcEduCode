package com.kavito;

import com.kavito.mapper.MysqlLockMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.locks.Lock;

/**
 * @author kavito
 * @date 2019/7/13 14:01
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TicketTest {
    private Integer ticket=100;//100张票
    @Autowired
    private Lock lock;

   @Autowired
    private  MysqlLockMapper mysqlLockMapper;


    @Test
    public void sellTicket() throws InterruptedException{
        TicketRunnable tr=new TicketRunnable();
        //四个线程对应四个窗口
        Thread t1=new Thread(tr,"窗口A");
        Thread t2=new Thread(tr,"窗口B");
        Thread t3=new Thread(tr,"窗口C");
        Thread t4=new Thread(tr,"窗口D");

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        //主线程等待子线程执行完毕
        Thread.currentThread().join();
    }


   public class TicketRunnable implements Runnable{
        @Override
        public void run() {
            while(ticket>0){
                lock.lock();
                try {
                    if(ticket>0){
                        System.out.println(Thread.currentThread().getName()+"售出第"+(ticket--)+"张票,剩余"+ticket);
                    }
                }catch (Exception e){

                }finally {
                    lock.unlock();
                }

            }
        }
    }

}