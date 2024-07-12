import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * 获取 成员方法
 */
public class demo3GetMethod {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class clazz = Class.forName("Student");
        System.out.println(clazz);

        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            System.out.println(method);
        }

        Student s = new Student();
        // 方法运行 invoke
        /**
         * 参数一：表示方法的调用者（一个实例化的对象）
         * 参数二：表示调用方法实际传递的参数
         */
        Method m = clazz.getMethod("eat", String.class);
        m.invoke(s, "狗屎");

        // 私有方法，需要用declared 来获取
        Method m2 = clazz.getDeclaredMethod("eat", String.class, int.class);
        m2.setAccessible(true);
        m2.invoke(s, "汉堡包", 2);
    }
}
