package per_asyn_IO;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by wangjufeng on 2017/8/25.
 * 伪异步IO
 */
public class TimeServer {
    public static void main(String[] args) {
        int port = 8088;
        if (args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch (NumberFormatException e) {
                port = 8088;
            }
        }
        ServerSocket server  = null;

        try {
            server = new ServerSocket(port);
            System.out.println("The Time Server start");
            Socket socket = null;
            TimeServerHandlerExecutePool singleExecute = new TimeServerHandlerExecutePool(50,1000);

            while (true){
                socket = server.accept();
                singleExecute.execute(new TimeServerHandler(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(server != null){
                System.out.println("The time server close");
                try {
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                server = null;
            }
        }
    }
}
