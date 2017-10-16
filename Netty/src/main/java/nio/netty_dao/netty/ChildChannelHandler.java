package nio.netty_dao.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import nio.netty_dao.bean.CommonBean;


/**
 * Created by wangjufeng on 2017/9/21.
 */
public class ChildChannelHandler extends ChannelInitializer<SocketChannel> {
    private CommonBean commonBean;

    public ChildChannelHandler(CommonBean commonBean) {
        this.commonBean = commonBean;
    }

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        System.out.println("报告");
        System.out.println("信息：有一客户端链接到本服务端");
        System.out.println("IP:" + socketChannel.localAddress().getHostName());
        System.out.println("Port:" + socketChannel.localAddress().getPort());
        System.out.println("报告完毕");

        //设置  LocalAddress
        commonBean.setLocalAddress(socketChannel.localAddress().toString() + "." + socketChannel.id());
        //解码器
        //基于换行符号
        socketChannel.pipeline().addLast(new LineBasedFrameDecoder(1024));

        //解码转String
        socketChannel.pipeline().addLast(new StringDecoder());

        //编码器String
        socketChannel.pipeline().addLast(new StringEncoder());

        //在管道中添加我们自己的接收数据实现方法
        socketChannel.pipeline().addLast();
    }
}
