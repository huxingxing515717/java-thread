package com.huawei.l00379880.mythread.Chapter06AQS.Section2CountDownLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/***********************************************************
 * @note      : CountDownLatch的例子
 * @author    : l00379880 梁山广
 * @version   : V1.0 at 2019/9/3 14:54
 ***********************************************************/
public class Example1 {
    private final static int THREAD_COUNT = 10;

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        final CountDownLatch countDownLatch = new CountDownLatch(THREAD_COUNT);

        for (int i = 0; i < THREAD_COUNT; i++) {
            final int threadNum = i;
            executorService.execute(() -> {
                try {
                    test(threadNum);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // 为防止出现异常，放在finally里更保险一些
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        System.out.println("程序运行完毕！");
        executorService.shutdown();
    }

    private static void test(int threadNum) throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(100);
        System.out.println(threadNum);
        TimeUnit.MILLISECONDS.sleep(100);
    }
}
/**
 * 输出结果(等所有子线程跑完主线程才会结束)
 * 1
 * 0
 * 3
 * 4
 * 6
 * 5
 * 2
 * 9
 * 7
 * 8
 * 程序运行完毕！
 */
