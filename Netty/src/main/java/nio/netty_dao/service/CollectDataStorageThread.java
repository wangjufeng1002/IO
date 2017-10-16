package nio.netty_dao.service;

import nio.netty_dao.bean.CommonBean;
import nio.netty_dao.bean.DataBean;

import java.util.Random;

/**
 * Created by wangjufeng on 2017/9/21.
 */
public class CollectDataStorageThread implements Runnable {
    private CommonBean commonBean;
    private static final int DEFAULT_RANGE_FOR_SLEEP = 1000;    // 随机休眠时间

    private CollectDataStorageThread() {
    }

    public CollectDataStorageThread(CommonBean commonBean) {
        this.commonBean = commonBean;
    }

    public void run() {
        System.out.println("启动数据存库线程... ...");
        Random r = new Random();
        boolean isRunning = true;

        try {
            while (isRunning) {
                //从队列弹出数据
                DataBean dataBean = null;
                if (commonBean.getDataQueueStack().doGetQueueCount() > 0) {
                    dataBean = commonBean.getDataQueueStack().doPollData();
                } else {
                    Thread.sleep(r.nextInt(DEFAULT_RANGE_FOR_SLEEP));
                    continue;

                }
                if (null != dataBean) {
                    //数据存库
                    commonBean.getDataDao().doSaveData(dataBean);
                    //打印数据
                    System.out.println("队列剩余数据量" + commonBean.getDataQueueStack().doGetQueueCount());
                    Thread.sleep(r.nextInt(DEFAULT_RANGE_FOR_SLEEP));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
