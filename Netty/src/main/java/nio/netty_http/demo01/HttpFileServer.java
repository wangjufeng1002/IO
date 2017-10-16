package nio.netty_http.demo01;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;
import io.netty.handler.stream.ChunkedWriteHandler;

/**
 * Created by wangjufeng on 2017/9/11.
 */
public class HttpFileServer {
    private static final String DEFAULT_URL = "/src/nio";

    public void run(final int port, final String url) {

        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();

        ServerBootstrap b = new ServerBootstrap();
        try {
            b.group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel sc) throws Exception {
                            sc.pipeline().addLast("http-decoder", new HttpRequestDecoder());
                            sc.pipeline().addLast("http-aggregator", new HttpObjectAggregator(65536));
                            sc.pipeline().addLast("http-encoder", new HttpResponseEncoder());
                            sc.pipeline().addLast("http-chunked", new ChunkedWriteHandler());
                           // sc.pipeline().addLast("fileServerHandler",new Http);
                        }
                    });
            ChannelFuture future = b.bind("127.0.0.1", port).sync();
            System.out.println("HTTP 文件目录服务器启动，网址是 : " + "http://127.0.0.1:" + port + url);
            future.channel().closeFuture().sync();
        } catch (Exception e) {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        int port = 9999;
        if (args != null && args.length > 0) {
            port = Integer.parseInt(args[0]);
        }
        String url = DEFAULT_URL;
        if (args.length > 1) {
            url = args[1];
        }
        new HttpFileServer().run(port, url);
    }
}
