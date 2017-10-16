package nio.netty_dao.bean;

import nio.netty_dao.cache.DataQueueStack;
import nio.netty_dao.dao.DataDao;

/**
 * Created by wangjufeng on 2017/9/21.
 */
public class CommonBean {
    private int port;             //端口
    private String localAddress;   //服务端分配客户端的端口与客户端的IP
    private DataQueueStack dataQueueStack;        // 队列
    private DataDao dataDao;                    // 数据DAO

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getLocalAddress() {
        return localAddress;
    }

    public void setLocalAddress(String localAddress) {
        this.localAddress = localAddress;
    }

    public DataQueueStack getDataQueueStack() {
        return dataQueueStack;
    }

    public void setDataQueueStack(DataQueueStack dataQueueStack) {
        this.dataQueueStack = dataQueueStack;
    }

    public DataDao getDataDao() {
        return dataDao;
    }

    public void setDataDao(DataDao dataDao) {
        this.dataDao = dataDao;
    }
}
