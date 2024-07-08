package study.utiles;

public class Student {
    String name;
    Integer age;

    public Student(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public Student(String name) {
        String[] x = name.split(",");
        this.name = x[0];
        this.age = Integer.parseInt(x[1]);
    }
}
