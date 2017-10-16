package execute;

/**
 * Created by wangjufeng on 2017/9/22.
 */
public class ExecutorThread implements Runnable {
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
