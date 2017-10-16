package execute;

import java.sql.Time;
import java.text.DateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by wangjufeng on 2017/9/22.
 */
public class TestNewScheduledThreadPool {
    public static void main(String[] args) {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(2);

        scheduleAtFiexedRate(service, 1000);
        scheduleAtFiexedRate(service, 6000);
        scheduleWithFixedDelay(service, 1000);
        scheduleWithFixedDelay(service, 6000);
    }

    private static void scheduleAtFiexedRate(ScheduledExecutorService service, final int sleepTime) {
        service.scheduleAtFixedRate(new Runnable() {
            public void run() {
                long start = new Date().getTime();
                System.out.println("scheduleAtFixedRate 开始执行时间：" + DateFormat.getTimeInstance().format(new Date()) + " id: " + Thread.currentThread().getId());

                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                long end = new Date().getTime();
                System.out.println("scheduleAtFixedRate 执行花费时间=" + (end - start) / 1000 + "m");
                System.out.println("scheduleAtFixedRate 执行完成时间：" + DateFormat.getTimeInstance().format(new Date()));
                System.out.println("=============================================================================");
            }
        }, 1000, 5000, TimeUnit.MILLISECONDS);
    }

    private static void scheduleWithFixedDelay(final ScheduledExecutorService service, final int sleepTime) {
        service.scheduleWithFixedDelay(new Runnable() {
            public void run() {
                long start = new Date().getTime();
                System.out.println("scheduleWithFixedDelay 开始执行时间:" +
                        DateFormat.getTimeInstance().format(new Date()));

                try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                long end = new Date().getTime();
                System.out.println("scheduleWithFixedDelay执行花费时间=" + (end - start) / 1000 + "m");
                System.out.println("scheduleWithFixedDelay执行完成时间："
                        + DateFormat.getTimeInstance().format(new Date()));
                System.out.println("======================================");
            }
        }, 1000, 5000, TimeUnit.MILLISECONDS);
    }
}
