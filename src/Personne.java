import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Personne {
    private String name;
    private String lastName;
    private LocalDate dateOfBirth;

    public Personne(String name,String lastName,LocalDate dateOfBirth) {
        this.name = name;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public static void main(String[] args) {
        Personne p1 = new Personne("sam", "sam", LocalDate.of(1995, 8, 22));
        Personne p2 = new Personne("toc", "toc", LocalDate.of(1981, 5, 15));
        Personne p3 = new Personne("toc", "toc", LocalDate.of(1980, 5, 15));
        Personne p4 = new Personne("toc", "toc", LocalDate.of(1990, 5, 15));
        Personne p5 = new Personne("rik", "rak", LocalDate.of(1992, 5, 15));


        ArrayList<Personne> personnes = new ArrayList<>();

        personnes.add(p1);
        personnes.add(p2);
        personnes.add(p3);
        personnes.add(p4);
        personnes.add(p5);

        System.out.println("List after --> ADD: \n" + personnes + "\n");

        final int AGE_LIMIT = 29;

        Map<Integer, ArrayList<String>> personnesWithAge = new HashMap<>();

        for (Personne p: personnes) {
            int age = LocalDate.now().getYear() - p.getDateOfBirth().getYear();
            if (age > AGE_LIMIT) {
                String fullName = p.getName().toUpperCase() + " " + p.getLastName().toUpperCase();
                try {
                    personnesWithAge.computeIfAbsent(age, k -> new ArrayList<>()).add(fullName);
                } catch(NullPointerException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }

//        Map<Integer, ArrayList<String>> personnesWithAge1 = personnes.stream().map(p -> {
//            int age = LocalDate.now().getYear() - p.getDateOfBirth().getYear();
//            if (age > AGE_LIMIT) {
//                String fullName = p.getName().toUpperCase() + " " + p.getLastName().toUpperCase();
//                personnesWithAge.computeIfAbsent(age, k -> new ArrayList<>()).add(fullName);
//            }
//        });

        System.out.println("Iterate: ");
        // FOR
//        for (Map.Entry<Integer, ArrayList<String>> entry : personnesWithAge.entrySet()) {
//            System.out.println(entry.getKey() + " - " + entry.getValue());
//        }

        // FOREACH
        personnesWithAge.forEach((key, value) -> System.out.println(key + " - " + value));
    }
}
