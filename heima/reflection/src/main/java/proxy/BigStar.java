package proxy;

public class BigStar implements Star {
    private String name;

    public BigStar() {
    }

    public BigStar(String name) {
        this.name = name;
    }

    @Override
    public String sing(String singName) {
        System.out.println(this.name + "正在唱歌:"+singName);
        return "thanks";
    }

    @Override
    public String dance(String danceName) {
        System.out.println(this.name + "正在跳舞:"+danceName);
        return "thanks";
    }
}
