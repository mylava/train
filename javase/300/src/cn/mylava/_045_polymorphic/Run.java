package cn.mylava._045_polymorphic;

/**
 * 16/3/10.
 *
 */
public class Run {
    /**
     * 如果没有多态,则需要创建三个方法

    public static void testVoice(Cat cat){
        cat.voice();
    }
    public static void testVoice(Dog dog){
        dog.voice();
    }
    public static void testVoice(Pig pig){
        pig.voice();
    }
     */


    /**
     * 使用多态,只需要一个方法就可以实现对三个方法的调用
     * @param animal
     */
    public static void testVoice(Animal animal){
        animal.voice();
    }

    public static void main(String[] args) {
        //声明类型为Animal
        Animal animal = new Cat();
        testVoice(animal);
        if(animal instanceof Cat) {
            ((Cat) animal).catchMouse();
        }

        Cat cat = new Cat();
        testVoice(cat);
        cat.catchMouse();
    }
}
