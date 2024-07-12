import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.List;

public class demo1 {
    public static void main(String[] args) throws ClassNotFoundException {
        /**
         * 反射三种类型
         * ● Class.forname("全类名")
         * ● 类名.class()
         * ● 对象.getClass()
         */
        System.out.println("hi");

        // 包名加类名
        Class clazz = Class.forName("Student");
        System.out.println(clazz);

        //类名.class
        clazz = Student.class;
        System.out.println(clazz);

    }
}
