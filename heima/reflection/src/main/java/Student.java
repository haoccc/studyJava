public class Student {
    private String name;
    private int age;
    private int height;
    private String hobby;

    public Student() {
    }

    public Student(String name, int age, int height, String hobby) {
        this.name = name;
        this.age = age;
        this.height = height;
        this.hobby = hobby;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public void eat(String thing){
        System.out.println("吃一个东西" + thing);
    }

    public void eat(){
        System.out.println("正在吃东西");
    }

    private void eat(String thing, int times){
        System.out.println("吃" + times + "个东西" + thing);
    }
}
