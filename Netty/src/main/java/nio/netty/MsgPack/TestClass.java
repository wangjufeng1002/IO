package nio.netty.MsgPack;

import com.alibaba.fastjson.JSON;
import io.netty.handler.codec.marshalling.MarshallingEncoder;
import nio.netty.MsgPack.MarshallingCodeCFactory;
import nio.netty.demo04.UserInfo;
import org.junit.Test;
import org.msgpack.MessagePack;

import java.io.IOException;

/**
 * Created by wangjufeng on 2017/9/11.
 */

public class TestClass {


    @Test
    public void test01() throws IOException {
        UserInfo userInfo = new UserInfo();
        userInfo.setName("wjf");
        userInfo.setGender("男");
        userInfo.setAge(21);
        //序列化
        MessagePack encoder = new MessagePack();
        byte[] raw = encoder.write(userInfo);
        System.out.println("序列化后的byte长度：" + raw.length);
        //反序列化
        MessagePack decoder = new MessagePack();
        UserInfo user = decoder.read(raw, UserInfo.class);
        System.out.println("反序列化后的对象：" + user);
    }

    @Test
    public void test02() {
        UserInfo userInfo = new UserInfo();
        userInfo.setName("wjf");
        userInfo.setGender("男");
        userInfo.setAge(21);
        String s = JSON.toJSONString(userInfo);

        byte[] jsonByte = s.getBytes();
        System.out.println("json序列化byte长度：" + jsonByte.length);
    }

    @Test
    public void test03() {
        UserInfo userInfo = new UserInfo();
        userInfo.setName("王巨峰");
        userInfo.setGender("男");
        userInfo.setAge(21);
        MarshallingEncoder encoder = MarshallingCodeCFactory.buildMarshallingEncoder();


    }
}
