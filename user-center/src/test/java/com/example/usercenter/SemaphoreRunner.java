package com.example.usercenter;

import java.util.concurrent.Semaphore;

/**
 * @Author ninan
 * @Description
 * @Date  2021/4/17
 **/
public class SemaphoreRunner {
    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(2);
        for (int i = 0; i < 5; i++) {
            new Thread(new Task(semaphore,"zzc"+i)).start();
        }
    }

    static class Task implements Runnable{
        Semaphore semaphore;
        String name;

        public Task(Semaphore semaphore ,String name){
            this.semaphore= semaphore;
            this.name= name;
        }

        @Override
        public void run() {
            try {
                semaphore.acquire();
                System.out.println(Thread.currentThread().getName()+"拿到锁啦~~~~~~~~~~~~~~~~~~~~");
                Thread.sleep(1000);
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}
