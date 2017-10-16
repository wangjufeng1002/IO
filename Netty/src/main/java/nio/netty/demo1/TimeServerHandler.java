package nio.netty.demo1;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * Created by wangjufeng on 2017/9/6.
 */
public class TimeServerHandler extends ChannelHandlerAdapter {

    Logger logger = LoggerFactory.getLogger(TimeServerHandler.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf buf = (ByteBuf) msg;
        byte[] bytes = new byte[buf.readableBytes()];
        buf.readBytes(bytes);
        String body = new String(bytes, "UTF-8");
        System.out.println("The time server receive body " + body);
        String currentTime = body.equalsIgnoreCase("QUERY TIME ORDER") ? new Date(System.currentTimeMillis()).toString() : "BAD QUERY";
        ByteBuf result = Unpooled.copiedBuffer(currentTime.getBytes());

        ctx.write(result);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
           logger.error("TimeServerHandler happend exception,error {}",cause);
           ctx.close();
    }
}
