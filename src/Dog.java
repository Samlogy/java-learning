// Child class inheriting from Animal
public class Dog extends Animal implements Canidae, AnimalBehavior {
    private String favoriteToy;

    public Dog(String name, String favoriteToy) {
        super(name);
        this.favoriteToy = favoriteToy;
    }

    public String getFavoriteToy() {
        return favoriteToy;
    }
    public String getFavoriteToy(String favoriteToy) {
        return favoriteToy;
    }
    public void setFavoriteToy(String favoriteToy) {
        this.favoriteToy = favoriteToy;
    }

    public void play() {
        System.out.println(getName() + " is playing with " + favoriteToy);
    }

    @Override
    public void nightBlind() {
        System.out.println(getName() + " has no night watch vision :(");
    }

    // redefine toString()
    public String toString() {
        return  "My dog's name is: "  + " favorite toy is: " + getFavoriteToy() + " :(";
    }

    // methods that copy an object
    public void copy(Dog d) {
        this.setFavoriteToy(d.getFavoriteToy());
    }

    // overloaded method (share same name but difference in data type, number of parameters)
    public int todoSomething(int a, int b) {
        return a + b;
    }
    public int todoSomething(int a, int b, int c) {
        return a + b + c;
    }

    @Override
    public void behavior() {
        System.out.println("my dog plays all day, very clumpsy, but very affective :) ");
    }
}



