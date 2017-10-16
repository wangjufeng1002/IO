package nio.netty_dao.bean;

/**
 * Created by wangjufeng on 2017/9/21.
 */
public class DataBean {
    private String localAddress;    //分拣线名称
    private String data;            //数据json串

    public String getLocalAddress() {
        return localAddress;
    }

    public void setLocalAddress(String localAddress) {
        this.localAddress = localAddress;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "DataBean [localAddress=" + localAddress + ", data= " + data + "]";
    }
}
