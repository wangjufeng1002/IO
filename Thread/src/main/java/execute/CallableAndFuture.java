package execute;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * Created by wangjufeng on 2017/9/22.
 */
public class CallableAndFuture {
    public static void main(String[] args) {
        Callable<Integer> callable = new Callable<Integer>() {
            public Integer call() throws Exception {
                return new Random().nextInt(100);
            }
        };
        FutureTask<Integer> future = new FutureTask<Integer>(callable);
        new Thread(future).start();

        try {
            Thread.sleep(5000);
            System.out.println(future.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
