package cn.mylava.hadoop.mobilecount;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Created by mylava on 2016/11/18.
 *
 * 用于封装Mapper的输出value
 * 因为需要写入文件中，此类需要实现Writable接口
 */
public class DataBean implements Writable{

    private String telNo;
    //上行流量
    private Long upPayLoad;
    //下行流量
    private Long downPayLoad;
    private Long totalPayLoad;

    public String getTelNo() {
        return telNo;
    }
    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }
    public Long getUpPayLoad() {
        return upPayLoad;
    }
    public void setUpPayLoad(Long upPayLoad) {
        this.upPayLoad = upPayLoad;
    }
    public Long getDownPayLoad() {
        return downPayLoad;
    }
    public void setDownPayLoad(Long downPayLoad) {
        this.downPayLoad = downPayLoad;
    }
    public Long getTotalPayLoad() {
        return totalPayLoad;
    }
    public void setTotalPayLoad(Long totalPayLoad) {
        this.totalPayLoad = totalPayLoad;
    }

    //默认空构造
    public DataBean() {
    }

    public DataBean(String telNo, Long upPayLoad, Long downPayLoad) {
        this.telNo = telNo;
        this.upPayLoad = upPayLoad;
        this.downPayLoad = downPayLoad;
        this.totalPayLoad = upPayLoad + downPayLoad;
    }

    //serialize 注意顺序和类型
    public void write(DataOutput out) throws IOException {
        out.writeUTF(telNo);
        out.writeLong(upPayLoad);
        out.writeLong(downPayLoad);
        out.writeLong(totalPayLoad);
    }
    //deserialize
    public void readFields(DataInput in) throws IOException {
        this.telNo = in.readUTF();
        this.upPayLoad = in.readLong();
        this.downPayLoad = in.readLong();
        this.totalPayLoad = in.readLong();
    }

    @Override
    public String toString() {
        return this.upPayLoad + "\t" + this.downPayLoad + "\t" + this.totalPayLoad;
    }
}
