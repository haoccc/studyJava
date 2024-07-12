package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 创建一个代理
 */
public class ProxyUtils {
    /**
     * 方法的作用: 给一个明星的对象，创建一个代理
     *
     * 形参：被代理的明星对象
     *
     * 返回值：给明星创建的代理
     *
     *
     * 需求
     *  外面的人想要大明星唱歌
     *  1.获取代理对象
     *  2.再调用代理的唱歌方法
     *
     */
//    public static void main(String[] args) {
//        Proxy.newProxyInstance()
//    }

    public static Star createProxy(BigStar bigStar){
        /**
         * 参数一：用于指定用哪个类加载器，去加载生成的代理类
         * 参数二：指定接口，这些接口用于指定生成的代理，也就是有哪些方法
         * 参数三：用来指定生成的代理对象要干什么事情
         */
        Star star = (Star) Proxy.newProxyInstance(ProxyUtils.class.getClassLoader(),
                new Class[]{Star.class},    // 接口的字节码， 可以多个接口
                new InvocationHandler() {   // 生成的代理要干哪些事情
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        /**
                         * 代理的对象
                         * 要运行的方法
                         * 调用方法时传递的实参
                         */
                        if ("sing".equals(method.getName())){
                            System.out.println("准备话筒收钱");
                        } else if ("dance".equals(method.getName())){
                            System.out.println("准备场地， 收钱");
                        }

                        return method.invoke(bigStar, args);
                    }
                });
        return star;
    }
}
