package cn.mylava.mapreduce.datacount;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * 自定义Bean需要实现hadoop序列化接口
 * 实现借口的序列化和反序列化方法需要注意两点：1.order 2.type
 * Created by lipengfei on 2017/5/9.
 */
public class DataBean implements Writable{
    //电话号码
    private String telNo;
    //上行流量
    private long upPayLoad;
    //下行流量
    private long downPayLoad;
    //总流量
    private long totalPayLoad;

    //serialize
    @Override
    public void write(DataOutput out) throws IOException {
        out.writeUTF(telNo);
        out.writeLong(upPayLoad);
        out.writeLong(downPayLoad);
        out.writeLong(totalPayLoad);
    }
    //deserialize
    @Override
    public void readFields(DataInput in) throws IOException {
        this.telNo = in.readUTF();
        this.upPayLoad = in.readLong();
        this.downPayLoad = in.readLong();
        this.totalPayLoad = in.readLong();
    }

    @Override
    public String toString() {
        return "DataBean{" +
                "telNo='" + telNo + '\'' +
                ", upPayLoad=" + upPayLoad +
                ", downPayLoad=" + downPayLoad +
                ", totalPayLoad=" + totalPayLoad +
                '}';
    }

    public DataBean() {
    }

    public DataBean(String telNo, long upPayLoad, long downPayLoad) {
        this.telNo = telNo;
        this.upPayLoad = upPayLoad;
        this.downPayLoad = downPayLoad;
        this.totalPayLoad = upPayLoad + downPayLoad;
    }

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    public long getUpPayLoad() {
        return upPayLoad;
    }

    public void setUpPayLoad(long upPayLoad) {
        this.upPayLoad = upPayLoad;
    }

    public long getDownPayLoad() {
        return downPayLoad;
    }

    public void setDownPayLoad(long downPayLoad) {
        this.downPayLoad = downPayLoad;
    }

    public long getTotalPayLoad() {
        return totalPayLoad;
    }

    public void setTotalPayLoad(long totalPayLoad) {
        this.totalPayLoad = totalPayLoad;
    }
}
