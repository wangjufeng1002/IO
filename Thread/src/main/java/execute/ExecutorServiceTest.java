package execute;

import org.junit.Test;

import java.text.DateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by wangjufeng on 2017/9/21.
 */
public class ExecutorServiceTest {
    @Test
    public void cachedThread() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        try {
            for (int i = 0; i < 10; i++) {
                final int index = i;
                //Thread.sleep(index * 1000);
                executorService.execute(new Runnable() {
                    public void run() {
                        System.out.println(index + ";" + Thread.currentThread().getId());
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void fixedThreadPool() {
        ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 10; i++) {
            final int index = i;
            fixedThreadPool.execute(new Runnable() {
                public void run() {
                    System.out.println(index);
                    try {
                        Thread.sleep(1000000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    @Test
    public void fixedThreadPool_02() {
        Random random = new Random();
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        int waitTime = 500;
        for (int i = 0; i < 10; i++) {
            String name = "线程 " + i;
            int time = random.nextInt(1000);
            waitTime += time;
            Runnable runner = new ExecutorThread(name, time);
            System.out.println("增加: " + name + " / " + time);
            executorService.execute(runner);
        }
        try {
            Thread.sleep(waitTime);
            executorService.shutdown();
            executorService.awaitTermination(waitTime, TimeUnit.MICROSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void scheduledExecut() {

    }

    class ExecutorThread implements Runnable {
        private final String name;
        private final int delay;

        public ExecutorThread(String name, int delay) {
            this.name = name;
            this.delay = delay;
        }

        public void run() {
            System.out.println("启动： " + name);
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("完成： " + name);
        }
    }
}
