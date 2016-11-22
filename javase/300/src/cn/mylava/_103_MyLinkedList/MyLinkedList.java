package cn.mylava._103_MyLinkedList;

/**
 * Created by lpf on 16/8/9.
 */
public class MyLinkedList {
    private Node first;
    private Node last;

    private int size;

    public void add(Object obj){
        Node node = new Node();
        if (first == null) {
            node.setPrevious(null);
            node.setData(obj);
            node.setNext(null);
            first = node;
            last = node;
        } else {
            node.setPrevious(last);
            node.setData(obj);
            node.setNext(null);

            last.setNext(node);

            last = node;
        }
        size++;
    }

    public int size(){
        return size();
    }

    public Object get(int index) {
        rangeCheck(index);
        Node temp = node(index);
        if (temp!=null){
            return temp.getData();
        }
        return null;
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

    public void remove(int index){
        rangeCheck(index);
        Node temp = node(index);
        if (temp!=null) {
            Node pre = temp.getPrevious();
            Node next = temp.getNext();
            pre.setNext(next);
            next.setPrevious(pre);
            size--;
        }

    }

    public Node node(int index) {
        Node temp = null;
        if (first != null){
            //二分查找,提高效率
            if (index<(size>>1)) {
                temp = first;
                for (int i=0;i<index;i++) {
                    temp = temp.getNext();
                }
            } else {
                temp = last;
                for (int i=size-1;i>index;i--){
                    temp = temp.getPrevious();
                }


            }

        }
        return temp;
    }

    public void add(int index,Object obj){
        rangeCheck(index);
        Node temp = node(index);
        Node newNode = new Node();
        newNode.setData(obj);
        if (temp!=null){
            Node pre = temp.getPrevious();

            pre.setNext(newNode);
            newNode.setPrevious(pre);

            newNode.setNext(temp);
            temp.setPrevious(newNode);
            size++;
        }
    }


    public static void main(String[] args) {
        MyLinkedList list = new MyLinkedList();
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");
        list.remove(1);
        System.out.println(list.get(1));
    }
}
