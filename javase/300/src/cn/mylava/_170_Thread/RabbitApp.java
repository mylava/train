package cn.mylava._170_Thread;

/**
 * Created by lipengfei on 2017/3/12.
 */
public class RabbitApp {
    public static void main(String[] args) {
        Rabbit rabbit = new Rabbit();
        Tortoise tortoise = new Tortoise();

        rabbit.start();
        tortoise.start();
    }
}