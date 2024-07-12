import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

/**
 * 获取 构造方法
 */
public class demo2 {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class clazz = Class.forName("Student");
        System.out.println(clazz);

        // 获取构造方法
        Constructor[] constructors = clazz.getConstructors();
        System.out.println("获取public的所有构造方法:"+Arrays.toString(constructors));

        constructors = clazz.getDeclaredConstructors();
        System.out.println("获取所有构造方法:"+Arrays.toString(constructors));

        // 获取单个构造方法
        Constructor constructor1 = clazz.getDeclaredConstructor(String.class);
        Constructor constructor2 = clazz.getDeclaredConstructor(int.class);
        Constructor constructor3 = clazz.getDeclaredConstructor(String.class, int.class);
        System.out.println("获取单个构造方法:"+constructor1 + "\t" + constructor2 + "\t" + constructor3);

        // 获取权限修饰符
        System.out.println(constructor3.getModifiers());
        System.out.println(Arrays.toString(constructor3.getParameters()));

        // 创建对象
        // 暴力反射 临时取消权限校验
        constructor2.setAccessible(true);
        Student student = (Student) constructor2.newInstance(20);
        System.out.println(student.toString());
    }
}
