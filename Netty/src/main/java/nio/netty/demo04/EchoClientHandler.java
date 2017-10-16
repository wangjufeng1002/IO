package nio.netty.demo04;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

/**
 * Created by wangjufeng on 2017/9/11.
 */
public class EchoClientHandler extends ChannelHandlerAdapter {
    //发送个数
    private int sendNumber;

    public EchoClientHandler(int sendNumber) {
        this.sendNumber = sendNumber;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        UserInfo[] userInfos = getUserInfos(sendNumber);
        for (UserInfo user : userInfos) {
            System.out.println("Client send the msgpack message" + user);
            ctx.write(user);
        }
        ctx.flush();
      /*  String str = "Hello wangjufeng ,welcome to Netty!";
        byte[] bytes = str.getBytes();

        ByteBuf byteBuf = Unpooled.copiedBuffer(bytes);
        ctx.writeAndFlush(byteBuf);*/
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("Client receive the msgpack message : " + msg);
        ctx.write(msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }

    public UserInfo[] getUserInfos(int sendNumber) {
        UserInfo[] userInfos = new UserInfo[sendNumber];
        for (int i = 0; i < sendNumber; i++) {
            UserInfo userInfo = new UserInfo();
            userInfo.setName("wjf ---> " + i);
            userInfo.setAge(i);
            userInfo.setGender("男");
            userInfos[i] = userInfo;
        }
        return userInfos;
    }
}
