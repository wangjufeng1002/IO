package demo01;

/**
 * Created by wangjufeng on 2017/9/2.
 */
public class TimeClient {


    public static void main(String[] args) {

        int port = 8088;

        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            }catch (NumberFormatException e){
                port = 8088;
            }
        }
        new Thread(new TimeClientHandle("127.0.0.1",port),"TimeClient-001").start();

    }
}
