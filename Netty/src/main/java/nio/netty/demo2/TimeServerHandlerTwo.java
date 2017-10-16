package nio.netty.demo2;

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
public class TimeServerHandlerTwo extends ChannelHandlerAdapter {

    Logger logger = LoggerFactory.getLogger(TimeServerHandlerTwo.class);
    private static int counnt = 0;

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
      /*  ByteBuf buf = (ByteBuf) msg;
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        String body = new String(req, "UTF-8").substring(0,req.length - System.getProperty("line.separator").length());
        System.out.println("The time server receive body " + body + "; this counter is " + ++counnt);
        String currentTime = body.equalsIgnoreCase("QUERY TIME ORDER") ? new Date(System.currentTimeMillis()).toString() : "BAD QUERY";
        currentTime =  currentTime + System.getProperty("line.separator");
        ByteBuf result = Unpooled.copiedBuffer(currentTime.getBytes());
        ctx.write(result);*/
        System.out.println("Time server channelRead");
        String body = (String) msg;
        System.out.println("The time server receive body " + body + "; this counter is " + ++counnt);
        String currentTime = body.equalsIgnoreCase("QUERY TIME ORDER") ? new Date(System.currentTimeMillis()).toString() : "BAD QUERY";
        currentTime = currentTime /*+ System.getProperty("line.separator")*/;
        ByteBuf byteBuf = Unpooled.copiedBuffer(currentTime.getBytes());
        ctx.writeAndFlush(byteBuf);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.error("TimeServerHandler happend exception,error {}", cause);
        ctx.close();
    }
}
