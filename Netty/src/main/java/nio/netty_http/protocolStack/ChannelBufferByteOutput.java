package nio.netty_http.protocolStack;

import io.netty.buffer.ByteBuf;
import org.jboss.marshalling.ByteOutput;

import java.io.IOException;

/**
 * Created by wangjufeng on 2017/9/28.
 */
public class ChannelBufferByteOutput implements ByteOutput {
    private final ByteBuf byteBuf;

    public ChannelBufferByteOutput(ByteBuf byteBuf) {
        this.byteBuf = byteBuf;
    }

    public void close() throws IOException {

    }

    public void flush() throws IOException {

    }

    public void write(int i) throws IOException {
        byteBuf.writeByte(i);
    }

    public void write(byte[] bytes) throws IOException {
        byteBuf.writeBytes(bytes);
    }

    public void write(byte[] bytes, int srcIndex, int length) throws IOException {
        byteBuf.writeBytes(bytes, srcIndex, length);
    }

    ByteBuf getByteBuf() {
        return byteBuf;
    }
}
