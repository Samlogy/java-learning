// Child class inheriting from Animal
public class Cat extends Animal implements Feline, AnimalBehavior {
    private String favoriteToy;

    public Cat(String name, String favoriteToy) {
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
    public void nightWatch() {
        System.out.println(getName() + " has a night watch vision :)");
    }

    @Override
    public void retractableClaws() {
        System.out.println(getName() + " has retractable Claws :)");
    }

    public String toString(String name) {
        return  "My cat's name is: " + name + " favorite toy is: " + getFavoriteToy();
    }

    @Override
    public void behavior() {
        System.out.println("my cat sleeps / plays all day, request food instead of hunting, refuses hugs, destroys stuff :( ");
    }
}



