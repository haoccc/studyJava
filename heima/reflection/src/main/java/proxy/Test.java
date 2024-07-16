package proxy;

public class Test {
    public static void main(String[] args) {
        Star star = ProxyUtils.createProxy(new BigStar("ikun"));
        String hello = star.sing("hello");
        String fuck = star.dance("fuck");
        System.out.println(hello + fuck);
    }
}
