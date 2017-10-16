package nio.netty_dao.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import nio.netty_dao.bean.CommonBean;

/**
 * Created by wangjufeng on 2017/9/21.
 */
public class NettyServer implements Runnable {
    private CommonBean commonBean;

    public NettyServer(CommonBean commonBean) {
        this.commonBean = commonBean;
    }

    public NettyServer() {
    }

    public void run() {
        System.out.println("启动Netty服务端port：" + commonBean.getPort() + "... ... ");
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try {

            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workGroup);
            b.channel(NioServerSocketChannel.class);
            b.option(ChannelOption.SO_BACKLOG, 1024);
            b.childHandler(new ChildChannelHandler(commonBean));

            // 绑定端口
            ChannelFuture f = b.bind(commonBean.getPort()).sync();

            // 等待服务端监听端口关闭
            f.channel().closeFuture().sync();

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 优雅的退出
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }

    }
}
