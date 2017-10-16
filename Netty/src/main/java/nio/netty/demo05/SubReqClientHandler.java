package nio.netty.demo05;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import nio.netty.demo04.UserInfo;

/**
 * Created by wangjufeng on 2017/9/11.
 */
public class SubReqClientHandler extends ChannelHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("Client receive the jboss message " + msg);
       // ctx.write(msg);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        UserInfo[] userInfos = getUserInfos(10);
        for (UserInfo user : userInfos) {
            System.out.println("Client send the jobss message" + user);
            ctx.write(user);
        }
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
