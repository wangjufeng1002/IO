package nio.netty_dao.start;

import nio.netty_dao.bean.CommonBean;
import nio.netty_dao.cache.DataQueueStack;
import nio.netty_dao.dao.DataDao;
import nio.netty_dao.netty.NettyServer;
import nio.netty_dao.service.CollectDataStorageThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by wangjufeng on 2017/9/21.
 */
public class WorkThreadStart {
    public static void main(String[] args) {
        //实例化基础数据
        DataDao dataDao = new DataDao();
        DataQueueStack dataQueueStack = new DataQueueStack();

        CommonBean commonBean = new CommonBean();
        commonBean.setDataDao(dataDao);
        commonBean.setDataQueueStack(dataQueueStack);
        commonBean.setPort(7397);

        //线程池
        ExecutorService es = Executors.newCachedThreadPool();

        //初始化数据接收线程
        NettyServer nettyServer = new NettyServer(commonBean);

        //初始化数据存储线程
        CollectDataStorageThread collectDataStorageThread = new CollectDataStorageThread(commonBean);

        //启动数据接收线程
        es.execute(nettyServer);
        //启动数据存储线程
        es.execute(collectDataStorageThread);
    }

}
