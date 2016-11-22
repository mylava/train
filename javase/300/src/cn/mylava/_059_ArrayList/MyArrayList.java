package cn.mylava._059_ArrayList;

/**
 * 16/3/15.
 *
 * 模拟AbstractStringBuilder,编写ArrayList
 */
public class MyArrayList {
    /**
     * The value is used for object storage.
     */
    private Object[] value;
    /**
     * The size is the number of objects used.
     */
    private int size;
    /**
     * 无参构造器
     */
    public MyArrayList() {
        this(10);
    }
    /**
     * 带参数的构造器
     * @param size
     */
    public MyArrayList(int size) {
        if(size<0){
            try {
                throw new Exception("数组越界");
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        value = new Object[size];
    }
    /**
     * 获取容器保存的所有对象
     * @return
     */
    public Object[] getValue() {
        return value;
    }
    /**
     * 获取容器保存的对象的个数
     * @return
     */
    public int getSize() {
        return size;
    }
    /**
     * 将对象添加到容器中
     * @param obj
     */
    public void add(Object obj){
        value[size] = obj;
        size++;
        if(size>=value.length) {
            int newCapacity = value.length*2;
            Object[] newlist = new Object[newCapacity];
            for(int i=0;i<value.length;i++){
                newlist[i]=value[i];
            }
            value = newlist;
        }
    }

    /**
     * 从容器中获取对应索引位置的对象
     * @param index
     * @return
     */
    public Object get(int index) {
        rangeCheck(index);
        return value[index];
    }
    /**
     * 判断容器是否为空
     * @return
     */
    public boolean isEmpty(){
        return size == 0;
    }
    /**
     * 获取对象在容器头部的索引,如果不存在则返回-1
     * @param obj
     * @return
     */
    public int indexOf(Object obj){
        if(obj == null){
            return -1;
        }else {
            for (int i=0;i<value.length;i++){
                if(obj == value[i]){
                    return i;
                }
            }
            return -1;
        }
    }
    /**
     * 获取对象在容器尾部的索引
     * @param obj
     * @return
     */
    public int lastIndexOf(Object obj){
        if(obj == null){
            return -1;
        }else {
            for (int i=value.length-1;i>=0;i--){
                if(obj == value[i]){
                    return i;
                }
            }
            return -1;
        }
    }
    /**
     * 设置指定索引位置的值
     * @param index
     * @param obj
     * @return
     */
    public Object set(int index,Object obj){
        rangeCheck(index);
        Object old = value[index];
        value[index] = obj;
        return old;
    }
    /**
     * 检查index是否有效
     * @param index
     */
    private void rangeCheck(int index){
        if(index<0 || index>size-1){
            try {
                throw new Exception("越界了");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
