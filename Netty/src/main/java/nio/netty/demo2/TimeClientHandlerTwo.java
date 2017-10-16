package nio.netty.demo2;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import nio.netty.demo1.TimeServerHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by wangjufeng on 2017/9/6.
 */
public class TimeClientHandlerTwo extends ChannelHandlerAdapter {
    Logger logger = LoggerFactory.getLogger(TimeServerHandler.class);

    private final ByteBuf firstMessage;

    private int counter;
    private byte[] req;

    public TimeClientHandlerTwo() {
        req = ("QUERY TIME ORDER" + System.getProperty("line.separator")).getBytes();
        firstMessage = Unpooled.buffer(req.length);
        firstMessage.writeBytes(req);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ByteBuf message = null;
        for (int i = 0; i < 100; i++) {
            message = Unpooled.buffer(req.length);
            message.writeBytes(req);
            ctx.writeAndFlush(message);
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

        /* ByteBuf byteBuf = (ByteBuf) msg;
        byte[] bytes = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(bytes);
        String receive = new String(bytes, "UTF-8");
        System.out.println("TimeClient receive time : " + receive + "and this count is "+ ++counter);*/
        String receiveString  = (String)msg;
        System.out.println("TimeClient receive time : " + receiveString + "and this count is "+ ++counter);


    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.error("TimeClientHandler happend exception ,error{}", cause);
        System.out.println("TimeClientHandler happend exception ,error{}");
        ctx.close();
    }


}
