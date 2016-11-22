package cn.mylava.zkclient;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;

/**
 * Created by mylava on 2016/6/28.
 */
public class GetData {
    public static void main(String[] args) throws InterruptedException {
        String path = "/zk-book";
        ZkClient zkClient = new ZkClient("192.168.159.130:2181",5000);
        zkClient.createEphemeral(path,"123");

        zkClient.subscribeDataChanges(path, new IZkDataListener() {
            //change process
            public void handleDataChange(String dataPath, Object data) throws Exception {
                System.out.println("Node "+ dataPath+" changed, new data:" + data);
            }
            //delete process
            public void handleDataDeleted(String dataPath) throws Exception {
                System.out.println("Node "+ dataPath + " deleted!");
            }
        });

        System.out.println(zkClient.readData(path));
        zkClient.writeData(path,"456");
        Thread.sleep(3000);

        zkClient.delete(path);
        Thread.sleep(Integer.MAX_VALUE);
    }
}
