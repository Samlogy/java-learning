// Parent class
public class Animal {
    private String name;

    public static void main(String[] args) {
        System.out.printf("Hello and welcome!");
    }

    public Animal(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void sleep() {
        System.out.println(name + " is sleeping.");
    }
    public void eat() {
        System.out.println(name + " is eating.");
    }
    public void run() {
        System.out.println(name + " is running.");
    }
}


// Polymorphism example using Shape interface
interface Feline {
    abstract void nightWatch();
    abstract void retractableClaws();
}

interface Canidae {
    abstract void nightBlind();
}

interface AnimalBehavior {
    abstract void behavior();
}


