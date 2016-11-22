package cn.mylava._101_MyArrayList;

/**
 * Created by lpf on 16/8/8.
 */
public class MyArrayList {

    private Object[] elementData;
    private int size;


    public MyArrayList() {
        this(10);
    }

    public MyArrayList(int initialCapacity) {
        if (initialCapacity<0) {
            try {
                throw new Exception();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        elementData = new Object[initialCapacity];
    }

    public int size(){
        return size;
    }
    public void add(Object obj){
        //数组扩容
        ensureCapacity();
        elementData[size++] = obj;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public Object get(int index){
        rangeCheck(index);
        return elementData[index];
    }

    public void remove(int index){
        rangeCheck(index);
        int numMoved = size - index -1;
        if (numMoved > 0){
            System.arraycopy(elementData,index+1,elementData, index,numMoved);
        }
        elementData[--size] = null;
    }

    private void rangeCheck(int index){
        if (index<0 || index>=size){
            try {
                throw new Exception();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void remove(Object obj){
        if (obj == null) {
            for (int index = 0; index < size; index++)
                if (elementData[index] == null) {
                    remove(index);
                    return;
                }
        } else {
            for (int index = 0; index < size; index++)
                if (obj.equals(elementData[index])) {
                    remove(index);
                    return;
                }
        }
    }

    public Object set(int index,Object obj){
        rangeCheck(index);
        Object old = elementData[index];
        elementData[index] = obj;
        return old;
    }

    public void add(int index,Object obj){
        rangeCheck(index);
        ensureCapacity();
        System.arraycopy(elementData,index,elementData,index+1,size-index);
        elementData[index] = obj;
        size++;
    }
    /**数组扩容*/
    private void ensureCapacity(){
        if (size==elementData.length){
            Object[] tmp = new Object[size*2+1];
            System.arraycopy(elementData,0,tmp,0,elementData.length);
            /*for (int i = 0; i < elementData.length; i++) {
                newArray[i] = elementData[i];
            }*/
            elementData = tmp;
        }
    }

    public static void main(String[] args) {
        MyArrayList list = new MyArrayList(2);
        list.add("1111");
        list.add("2222");
        list.add("3333");
        System.out.println(list.size());
        System.out.println(list.get(1));
    }

}
