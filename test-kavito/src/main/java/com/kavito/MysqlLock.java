
package com.kavito;

import com.kavito.mapper.MysqlLockMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @author kavito
 * @date 2019/7/13 13:55
 */

@Component
public class MysqlLock implements Lock {

    @Autowired
    private MysqlLockMapper mysqlLockMapper;
    //所有线程都向数据库插入主键值相同的数据，插入成功表示加锁成功，否则失败
    private static final int LOCK_ID=1;

    //阻塞式加锁
    @Override
    public void lock() {
        if(tryLock()){
            return;
        }
        waitForLock();
        //递归调用尝试加锁
        lock();

    }
    //当前线程sleep10毫秒
    private void  waitForLock(){
        try {
            Thread.currentThread().sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //非阻塞式加锁
    @Override
    public boolean tryLock() {
        try {
            mysqlLockMapper.insert(LOCK_ID);
        }catch (Exception e){
            return false;
        }
        return true;
    }



    @Override
    public void unlock() {
        mysqlLockMapper.deleteByPrimarykey(LOCK_ID);
    }
//------------------数据库解决分布式锁下面用不到-------------------------
    @Override
    public Condition newCondition() {
        return null;
    }
    @Override
    public void lockInterruptibly() throws InterruptedException {

    }
    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }
}

