package cn.mylava._059_ArrayList;

/**
 * 16/3/15.
 */
public class Item {

    String  name;
    public Item(){}
    public Item(String name){
        this.name = name;
    }


    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                '}';
    }
}
