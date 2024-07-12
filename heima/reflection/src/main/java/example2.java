import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

/**
 * 跟配置文件偶尔u和，动态创建对象，并调用方法
 */
public class example2 {
    public static void saveObject(Object o) throws IllegalAccessException {
        Class  clzz = o.getClass();

        // 获取所有的成员变量
        Field[] fields = clzz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            // 获取成员变量的名字和值
            String name = field.getName();
            Object value = field.get(o);
            System.out.println(name + ": " +value);
        }
    }

    public static void loadObject(String className, String methodName) throws IllegalAccessException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException {
        Class clzz = Class.forName(className);

        // 获取构造方法
        Constructor constructor = clzz.getDeclaredConstructor();
        // 创建对象
        Object o = constructor.newInstance();

        Method method = clzz.getDeclaredMethod(methodName);

        // 执行方法
        method.invoke(o);


//        // 获取所有的成员变量
//        Field[] fields = clzz.getDeclaredFields();
//        for (Field field : fields) {
//            field.setAccessible(true);
//            // 获取成员变量的名字和值
//            String name = field.getName();
//            Object value = field.get(o);
//            System.out.println(name + ": " +value);
//        }

    }

    // 把对象里面所有的成员变量名和值保存到本地文件中
    public static void main(String[] args) throws IllegalAccessException, IOException, ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException {
        // 1. 读取配置文件
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream("F:\\myjava\\studyJava\\heima\\reflection\\src\\main\\resources\\prop.properties");
        properties.load(fileInputStream);
        fileInputStream.close();
        System.out.println(properties);

        // 获取全类名和方法名
        String classname = (String) properties.get("classname");
        String methodName = (String) properties.get("method");
        loadObject(classname, methodName);

//
//        Student student = new Student("dean",18, 181, "sleep");
//        Teacher teacher = new Teacher();
//        saveObject(student);
//        saveObject(teacher);
    }
}
