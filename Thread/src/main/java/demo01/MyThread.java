package demo01;

/**
 * Created by wangjufeng on 2017/9/28.
 */
public class MyThread extends Thread {
    @Override
    public void run() {
        super.run();
    }

    public static void main(String[] args) {
        new MyThread().getPriority();
    }

}
