


// Main class to demonstrate the concepts
public class Main {

    static int value;
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

        // Polymorphism (another class object can define a different nightWatch() function version (polymorphism))
        AnimalBehavior cat = new Cat("Bob", "Rats");
        AnimalBehavior dog = new Dog("chien Chien", "everything ...");
        cat.behavior();
        dog.behavior();
        System.out.println(dog);

        // Exceptions handling
        try {
            // run code try can throw an exception
            System.out.println("Result: " + divide(10, 0) );
        } catch (ArithmeticException e) {
            // catches exception of type "ArithmeticException" & display details
            System.out.println("Error: " + e.getMessage());
        } finally {
            // run this block anyway
            System.out.println("Finally block executed.");
        }

        // Primitives Types: (hold simple values)
        // --> byte/short/int/float/double/long/char

        // Reference Types: (hold complex values)
        // --> String/Integer/Double/Character/Boolean
        Boolean isVisible = true;
        String str = "Hello there !";
        Integer number = 123;
        Double phi = 1.618;
        Character c = '@';

        boolean isVisible2 = true;
        int number2 = 123;
        double phi2 = 1.618;
        char c2 = '@';
        float phi3 = 1.618F;

        // double & float: for floating numbers, double can hold larger numbers & hold more digital precision.
        // double 308F / float 30F.

        // int & long: long holds larger numbers than int

        // constant
        final double PI = 3.14;

        // Array
        String names[] = new String[2];
        names[0] = "bob";
        names[1] = "alice";

        for (int i=0;i <names.length;i++) {
            System.out.printf(names[i] + " - ");
        }
        System.out.println("");

        int day = 3;
        String dayName;

        switch (day) {
            case 1:
                dayName = "Monday";
                break;
            case 2:
                dayName = "Tuesday";
                break;
            case 3:
                dayName = "Wednesday";
                break;
            case 4:
                dayName = "Thursday";
                break;
            case 5:
                dayName = "Friday";
                break;
            default:
                dayName = "Invalid day";
                break;
        }

        System.out.println("Day: " + dayName);

        // Abstract: is class that can not be instanciated, but can have subclass that can declared it abstract methods
        // encapsulation: no midifier / public / protected / private (use getters / setters for access)
        // cloning object (create a new object / point it to the same memory address)
        // Conditional statements: if/else - switch
        // generics: provide a type-safety --> b y specifing the type stored inside the data structure.
        // Serialization: converts objects into byte stream (communication between systems), then desualization to came back to the previous state (object).
        // Static: means belongs to the class itself (attributes / methods) can be static --> no need to create an instance to call them.
                // static class: can only define static (attributes/methods).
                // static method: can only accept static (attributes/methods).
        // casting: convertin a data type to another data type
                // implicit: automatically (from narrower --> larger data type)
                // explicit: specify it by programmer (from larger --> narrower data type)
        float val = (float) 4.5; // double --> float
        double val2 = (double) val; // float --> double
        int val3 = (int) val2; // double --> int (data loss)

        // Break & Continue:
                // continue:
                // break: exits the loop regardless of the conditions

        for (int i = 0; i <= 10 ; i++) {
            if (i%2==0) continue;
            System.out.println(i + " ");
        }
    }

    public static int divide(int dividend, int divisor) {
        if (divisor == 0) {
            throw new ArithmeticException("Division by zero is not allowed.");
        }
        return dividend / divisor;
    }
}