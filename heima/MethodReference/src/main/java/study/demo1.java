package study;


import study.utiles.StringOperation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class demo1 {
    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        Collections.addAll(list, "1", "2", "3", "4", "5");

        list.stream().map(new Function<String, Integer>() {

            @Override
            public Integer apply(String s) {
                return Integer.valueOf(s);
            }
        }).forEach(System.out::println);


        System.out.println("方法引用：静态方法");
        list.stream().map(Integer::parseInt).forEach(System.out::println);

        System.out.println("成员方法引用");
        List<String> list2 = new ArrayList<>();
        Collections.addAll(list2, "asdf", "bscd", "asdddd", "aqweq", "vfndfj");
//        list2.stream().filter(s -> s.startsWith("a")).forEach(System.out::println);
        StringOperation stringOperation = new StringOperation();

        // 在静态方法中引用 本类中的方法只能创建本类
        list2.stream().filter(new demo1()::stringJudge).forEach(System.out::println);

        // 变成大写后输出
        System.out.println("变成大写后输出");
        list2.stream().map(String::toUpperCase).forEach(System.out::println);


    }

    public boolean stringJudge(String s){
        return s.startsWith("a");
    }
}
