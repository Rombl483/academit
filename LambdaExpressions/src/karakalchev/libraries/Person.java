package karakalchev.libraries;

public class Person {
    String name;
    int age;

    public Person(String name, int age) {
        if ((name == null) || (name == "")) {
            throw new IllegalArgumentException("Не задано имя.");
        }

        if (age <= 0) {
            throw new IllegalArgumentException("Возраст должен быть больше нуля.");
        }

        this.name = name;
        this.age = age;
    }

    public void setName(String name) {
        if ((name == null) || (name == "")) {
            throw new IllegalArgumentException("Не задано имя.");
        }

        this.name = name;
    }

    public void setAge(int age) {
        if (age <= 0) {
            throw new IllegalArgumentException("Возраст должен быть больше нуля.");
        }

        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return String.format("[%s, %d]", name, age);
    }
}
