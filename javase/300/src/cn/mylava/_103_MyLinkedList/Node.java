package cn.mylava._103_MyLinkedList;

/**
 * 用来表示链表中的一个节点
 */
public class Node{
    private Node previous;
    private Object data;
    private Node next;

    public Node getPrevious() {
        return previous;
    }

    public void setPrevious(Node previous) {
        this.previous = previous;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node() {
    }

    public Node(Node previous, Object data, Node next) {
        this.previous = previous;
        this.data = data;
        this.next = next;
    }
}
