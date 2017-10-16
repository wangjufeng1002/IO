package nio.netty.demo1;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by wangjufeng on 2017/9/6.
 */
public class TimeClientHandler extends ChannelHandlerAdapter {
    Logger logger = LoggerFactory.getLogger(TimeServerHandler.class);

    private final ByteBuf firstMessage;

    public TimeClientHandler() {
        byte[] bytes = "QUERY TIME ORDER".getBytes();
        firstMessage = Unpooled.buffer(bytes.length);
        firstMessage.writeBytes(bytes);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ctx.writeAndFlush(firstMessage);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        byte[] bytes = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(bytes);
        String receive = new String(bytes, "UTF-8");
        System.out.println("TimeClient receive time : " + receive);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.error("TimeClientHandler happend exception ,error{}", cause);
        ctx.close();
    }


}
