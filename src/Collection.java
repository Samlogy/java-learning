import java.util.ArrayList;
import java.util.LinkedList;
import java.util.HashMap;
import java.util.Map;

public class Collection {
    public static void main(String[] args) {
        System.out.println("\n\n*********************** ARRAY LIST ***********************\n\n");
        // CREATE
        ArrayList<String> fruits = new ArrayList<>();

        // ADD
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Orange");
        fruits.add("Strawberry");
        fruits.add("Kiwi");
        fruits.add("coconut");

        System.out.println("Fruits: " + fruits);

        // GET ITEM
        System.out.println("Second fruit: " + fruits.get(1));

        // REMOVE ITEM
        fruits.remove("Banana"); // --> value
        fruits.remove(0); // --> index
        System.out.println("Fruits after removal: " + fruits);

        // SIZE
        System.out.println("Number of fruits: " + fruits.size());

        // CONTAINS
        System.out.println("Contains Orange? " + fruits.contains("Orange"));

        // Iterate
        // 1/
        System.out.println("Iterating For:");
        for (String fruit : fruits) {
            System.out.printf(fruit + " - ");
        }

        System.out.println("Iterating ForEach:");
        // 2/
        fruits.forEach(fruit ->  System.out.printf(fruit + " - "));

        // CLEAR ALL ITEMS
        fruits.clear(); // --> index
        System.out.println("Fruits after removal: " + fruits);

        // IsEmpty
        System.out.println("Fruits is Empty?: " + fruits.isEmpty());


        System.out.println("\n\n*********************** LINKED LIST ***********************\n\n");
        // CREATE
        LinkedList<String> names = new LinkedList<>();

        // ADD
        names.add("John");
        names.add("Alice");
        names.add("Bob");
        names.add("Sam");
        names.add("Ghiles");
        names.add("Rachid");

        System.out.println("Names: " + names);

        // GET ITEM
        System.out.println("First person: " + names.getFirst()); // --> 1st ITEM
        System.out.println("LAST person: " + names.getLast()); // --> LAST ITEM

        // REMOVE ITEM
        names.removeLast(); // --> LAST ITEM
        names.removeFirst(); // --> 1ST ITEM
        names.remove(3); // --> INDEX ITEM
        System.out.println("Names after removal: " + names);

        // isEMPTY
        System.out.println("Is empty? " + names.isEmpty());

        // CONTAINS
        System.out.println("Contains Bob? " + names.contains("Bob"));

        // SIZE
        System.out.println("Number of Names: " + names.size());

        // Iterate
        // 1/
        System.out.println("Iterating For:");
        for (String name : names) {
            System.out.printf(name + " - ");
        }
        System.out.println("");

        System.out.println("Iterating ForEach:");
        // 2/
        names.forEach(name ->  System.out.printf(name + " - "));
        System.out.println("");

        System.out.println("Iterating WHILE:");
        // 3/
        int i = 0;
        while (i<names.size()) {
            System.out.printf(names.get(i) + " - ");
            i++;
        }
        System.out.println("");

        // CLEAR ALL ITEMS
        names.clear();
        System.out.println("Names after clearing: " + names);


        System.out.println("\n\n *********************** HASH MAP ***********************\n\n");
        // CREATE
        HashMap<String, Integer> scores = new HashMap<>();

        // ADD ITEM
        scores.put("Alice", 90);
        scores.put("Bob", 80);
        scores.put("Charlie", 95);
        scores.put("Sam", 100);
        scores.put("Ghiles", 105);
        scores.put("Rachid", 125);

        // ADD ITEMS


        System.out.println("Scores: " + scores);

        // GET ITEM
        System.out.println("Alice's score: " + scores.get("Alice"));

        // MODIFY ITEM VALUE
        scores.put("Bob", 85);
        System.out.println("Scores after update: " + scores);

        // CONTAINS ITEM
        System.out.println("Contains key 'Charlie'? " + scores.containsKey("Charlie"));

        // ITEMS SIZE
        System.out.println("Number of entries: " + scores.size());

        // Iterate
        // 1/
        System.out.println("Iterate --> entrySet:");
        for (Map.Entry<String, Integer> entry : scores.entrySet()) {
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
        System.out.println("");

        // 2/
        System.out.println("Iterate --> keySet:");
        for (String name : scores.keySet()) {
            System.out.println(name + " - " + scores.get(name));
        }
        System.out.println("");

        // IsEMPTY
        System.out.println("Is empty? " + scores.isEmpty());

        // CLEAR ALL ITEMS
        scores.clear();
        System.out.println("Scores after clearing: " + scores);
    }
}

