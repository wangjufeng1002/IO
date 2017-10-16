package nio.netty_dao.dao;

import nio.netty_dao.bean.DataBean;

/**
 * Created by wangjufeng on 2017/9/21.
 */
public class DataDao {
    /**
     * 数据存库操作[其余写入数据库操作直接处理就可以了]
     *
     * @param dataBean
     * @return
     */
    public int doSaveData(DataBean dataBean) {

        try {

            System.out.println("==== 数据存库开始 ====\r\n");

            System.out.println("数据内容：");
            System.out.println("localAddress：\t" + dataBean.getLocalAddress());
            System.out.println("data：\t\t" + dataBean.getData());

            System.out.println("\r\n==== 数据存库结束 ====");

            return 1;
        } catch (Exception e) {
            return 0;
        }
    }
}
