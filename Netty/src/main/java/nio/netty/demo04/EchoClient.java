package nio.netty.demo04;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;


/**
 * Created by wangjufeng on 2017/9/9.
 */
public class EchoClient {
    private final String host;
    private final int port;
    private final int sendNumer;

    public EchoClient(String host, int port, int sendNumer) {
        this.host = host;
        this.port = port;
        this.sendNumer = sendNumer;
    }

    public void connect() {
        EventLoopGroup work = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(work)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel sc) throws Exception {
                       sc.pipeline().addLast("frameDecoder", new LengthFieldBasedFrameDecoder(65535, 0, 2, 0, 2));
                        sc.pipeline().addLast("msgpack decoder", new MsgpackDecoder());
                        sc.pipeline().addLast("frameEncoder", new LengthFieldPrepender(2));
                        sc.pipeline().addLast("msgpack encoder", new MsgpackEncoder());
                        sc.pipeline().addLast(new EchoClientHandler(sendNumer));
                    }
                });

        try {
            ChannelFuture future = bootstrap.connect(host, port).sync();
            //等待服务端监听端接口关闭
            future.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int port = 9999;
        if (args != null && args.length > 0) {
            port = Integer.parseInt(args[0]);
        }
        new EchoClient("127.0.0.1", port, 100).connect();
    }

}
