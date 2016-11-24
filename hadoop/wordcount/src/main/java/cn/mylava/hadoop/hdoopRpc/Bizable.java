package cn.mylava.hadoop.hdoopRpc;

/**
 * Created by mylava on 2016/11/17.
 *
 * 代理共同实现的接口
 */
public interface Bizable {
    public static final long versionID = 100;
    public String sayHi(String name);
}
