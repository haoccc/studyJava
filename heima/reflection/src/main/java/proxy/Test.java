package proxy;

public class Test {
    public static void main(String[] args) {
        Star star = ProxyUtils.createProxy(new BigStar("ikun"));
        star.sing("hello");
        star.dance("fuck");
    }
}
