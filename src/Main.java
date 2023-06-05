


// Main class to demonstrate the concepts
public class Main {
    public static void main(String[] args) {
        // Encapsulation / Inheritance / Implement
        Animal animal1 = new Animal("Lion");
        System.out.println("Animal Name: " + animal1.getName());
        animal1.sleep();
        animal1.eat();
        animal1.run();
        System.out.println("*****************************");

        Cat cat1 = new Cat("Tom", "Mouse");
        System.out.println("Cat Name: " + cat1.getName());
        cat1.sleep();
        cat1.eat();
        cat1.run();
        System.out.println(cat1.getName()+ " Favorite Toy: " + cat1.getFavoriteToy());
        cat1.play();
        cat1.nightWatch();
        cat1.retractableClaws();
        System.out.println("*****************************");

        Canidae dog1 = new Dog("Aqjun", "my sockets");
        dog1.nightBlind();
        System.out.println("*****************************");

        // Polymorphism
        // another class object can define a different nightWatch() function version (polymorphism)
        AnimalBehavior cat = new Cat("Bob", "Rats");
        AnimalBehavior dog = new Dog("chien Chien", "everything ...");
        cat.behavior();
        dog.behavior();

        // Exceptions handling
        try {
            // run code try can throw an exception
            System.out.println("Result: " + divide(10, 0));
        } catch (ArithmeticException e) {
            // catches exception of type "ArithmeticException" & display details
            System.out.println("Error: " + e.getMessage());
        } finally {
            // run this block anyway
            System.out.println("Finally block executed.");
        }
    }

    public static int divide(int dividend, int divisor) {
        if (divisor == 0) {
            throw new ArithmeticException("Division by zero is not allowed.");
        }
        return dividend / divisor;
    }
}