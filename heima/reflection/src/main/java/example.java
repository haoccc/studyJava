import java.lang.reflect.Field;

public class example {
    // 把对象里面所有的成员变量名和值保存到本地文件中
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

    // 把对象里面所有的成员变量名和值保存到本地文件中
    public static void main(String[] args) throws IllegalAccessException {
        Student student = new Student("dean",18, 181, "sleep");
        Teacher teacher = new Teacher();
        saveObject(student);
        saveObject(teacher);
    }
}
