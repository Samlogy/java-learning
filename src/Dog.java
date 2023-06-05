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
    public void getFavoriteToy(String favoriteToy) {
        this.favoriteToy = favoriteToy;
    }

    public void play() {
        System.out.println(getName() + " is playing with " + favoriteToy);
    }

    @Override
    public void nightBlind() {
        System.out.println(getName() + " has no night watch vision :(");
    }
    public String toString(String name) {
        return  "My dog's name is: " + name + " favorite toy is: " + getFavoriteToy() + " :(";
    }

    @Override
    public void behavior() {
        System.out.println("my dog plays all day, very clumpsy, but very affective :) ");
    }
}



