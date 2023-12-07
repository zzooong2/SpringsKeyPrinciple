package hello.core;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class HelloLombok {

    private String name;
    private int age;

    public static void main(String[] args) {
        HelloLombok helloLombok = new HelloLombok();
        helloLombok.setName("김현중");
        helloLombok.setAge(30);

        String name = helloLombok.getName();
        int age = helloLombok.getAge();
        System.out.println("name = " + name);
        System.out.println("age = " + age + "살");

        System.out.println("helloLombok = " + helloLombok);
    }

}

