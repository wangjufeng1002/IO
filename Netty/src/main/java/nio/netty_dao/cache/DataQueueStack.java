package nio.netty_dao.cache;

import nio.netty_dao.bean.DataBean;

import java.util.concurrent.BlockingQueue;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by wangjufeng on 2017/9/21.
 */
public class DataQueueStack {
    private BlockingQueue<DataBean> dataQueue = null;

    public DataQueueStack() {
        dataQueue = new LinkedBlockingQueue<DataBean>(100);
    }

    /**
     * 添加数据到队列
     *
     * @param dataBean
     * @return
     */
    public boolean doOfferData(DataBean dataBean) {
        try {
            return dataQueue.offer(dataBean, 2, TimeUnit.SECONDS);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 弹出队列数据
     *
     * @return
     */
    public DataBean doPollData() {
        try {
            return dataQueue.poll(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获得队列数据个数
     *
     * @return
     */
    public int doGetQueueCount() {
        return dataQueue.size();
    }
}
