package cn.mylava._171_Thread;

/**
 * 静态代理设计模式
 * 实现静态代理的前提条件：
 * 1.有真实角色；
 * 2.有代理角色:帮真实角色做事情，所以需要持有真实角色的引用；
 * 3.二者实现相同接口
 * Created by lipengfei on 2017/3/15.
 */
public class StaticProxy {
    public static void main(String[] args) {
        Marry roleTrue = new RoleTrue();
        Marry roleProxy = new RoleProxy(roleTrue);
        roleProxy.marry();
    }

}

//3.共同接口,这里假设的场景是婚庆公司代理
interface Marry {
    /* public abstract 接口中的方法相当于抽象方法。*/ void marry();
}
//1.真实角色
class RoleTrue implements Marry {
    @Override
    public void marry() {
        System.out.println("我和赵敏结婚了！");
    }
}
//2.代理角色
class RoleProxy implements Marry {
    //持有真实角色的引用
    private Marry roleTrue;

    public RoleProxy() {
    }

    public RoleProxy(Marry roleTrue) {
        this.roleTrue = roleTrue;
    }

    private void before() {
        System.out.println("前置条件：布置婚房");
    }

    private void after() {
        System.out.println("后置条件：闹伴娘");
    }

    @Override
    public void marry() {
        before();
        roleTrue.marry();
        after();
    }
}
