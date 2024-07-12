public class Teacher {
    private String name;
    private int salary;

    public Teacher(String name, int salary) {
        this.name = name;
        this.salary = salary;
    }

    public Teacher() {
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + salary +
                '}';
    }

    public void teach(String thing){
        System.out.println("正在教书" + thing);
    }
    public void teach(){
        System.out.println("正在教书");
    }
    private void eat(String thing, int times){
        System.out.println("吃" + times + "个东西" + thing);
    }
}
