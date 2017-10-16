package nio.netty.demo3;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.FixedLengthFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;

/**
 * Created by wangjufeng on 2017/9/7.
 */
public class EchoClient {
    public void connect(int port, String host) {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel sc) throws Exception {
                        //ByteBuf delimiter = Unpooled.copiedBuffer("$_".getBytes());
                        //sc.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, delimiter));
                        sc.pipeline().addLast(new FixedLengthFrameDecoder(20));
                        sc.pipeline().addLast(new StringDecoder());
                        sc.pipeline().addLast(new EchoClientHandler());
                    }
                });

        try {
            ChannelFuture f =  bootstrap.connect(host,port).sync();
            f.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            group.shutdownGracefully();
        }

    }

    public static void main(String[] args) throws Exception {
        int port = 9999;
        if (args != null && args.length > 0) {
            port = Integer.valueOf(args[0]);
        }

         new EchoClient().connect(port, "127.0.0.1");
    }
}
