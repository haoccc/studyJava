package study;

// 实现一个多线程图片下载器
public class TestLambda{

    /**
     * 静态内部类型
     */
    static class Like1 implements Ilike{

        @Override
        public void lambda(String a) {
            System.out.println("静态内部类-->"+a);
        }
    }

    public static void main(String[] args) {
        Ilike like1 = new Like1();
        like1.lambda("静态内部类");

        /**
         * 局部内部类
         */
        class Like2 implements Ilike{

            @Override
            public void lambda(String a) {
                System.out.println("局部内部类--->"+a);
            }
        }
        Ilike like2 = new Like2();
        like2.lambda("局部内部类");


        /**
         * 匿名内部类
         */
        Ilike like3 = new Ilike() {
            @Override
            public void lambda(String a) {
                System.out.println("匿名内部类--->"+a);
            }
        };
        like3.lambda("匿名内部类");

        /**
         * lambda
         */
        Ilike like = (a)->{
            System.out.println("lambda简化--->"+a);
        };
        like.lambda("lamdba");
    }

}

/**
 * 函数式接口
 * 只有一个方法的接口，可以通过lambda
 */
interface Ilike{
    void lambda(String a);
}


/**
 * 外部类
 */
class Like implements Ilike{
    @Override
    public void lambda(String a) {
        System.out.println("外部类--->"+a);
    }
}

