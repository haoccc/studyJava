package study;


import study.utiles.Student;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class demo2 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        Collections.addAll(list, "张无忌,20", "张无忌2,21", "张忌,10", "无忌,20", "li无忌,19");


        // 方法一
        List<Student> students = list.stream().map(new Function<String, Student>() {
            @Override
            public Student apply(String s) {
                String[] arrayList = s.split(",");
                return new Student(arrayList[0], Integer.parseInt(arrayList[1]));
            }
        }).toList();

        // 直接引用构造方法
        List<Student> students2 = list.stream().map(Student::new).toList();
        System.out.println("直接引用构造方法"+students2.toString());


    }
}
