package nio.netty_http.protocolStack;


import io.netty.buffer.ByteBuf;
import org.jboss.marshalling.ByteInput;
import org.jboss.marshalling.Unmarshaller;

import java.io.IOException;

/**
 * Created by wangjufeng on 2017/9/28.
 */
public class MarshallingDecoder {
    private final Unmarshaller unmarshaller;

    public MarshallingDecoder() throws IOException {
        unmarshaller = MarshallingCodecFactory.buildUnMarshalling();
    }

    protected Object decode(ByteBuf in) throws IOException, ClassNotFoundException {
        int obejctSize = in.readInt();
        ByteBuf buf = in.slice(in.readerIndex(), obejctSize);
        ByteInput input = new ChannelBufferByteInput(buf);

        try {
            unmarshaller.start(input);
            Object object = unmarshaller.readObject();
            unmarshaller.finish();
            in.readerIndex(in.readerIndex() + obejctSize);
            return object;
        } finally {
            unmarshaller.close();
        }
    }

}
