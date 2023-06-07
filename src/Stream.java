import java.util.ArrayList;
import java.util.List;

public class Stream {
    public static void main(String[] args) {
        List<Integer> numbers = new ArrayList<>();

        // add numbers
        for(int i = 1; i<=10; i++) {
            numbers.add(i);
        }

        System.out.println(numbers);

        // STREAMS: allows powerful operations on data such as:
        // filtering, mapping, reducing, and sorting, among others.

        // Filter even numbers (functional programming)
        List<Integer> evenNumbers = numbers.stream()
                .filter(number -> number % 2 == 0)
                .toList();

        // Map numbers to their squares
        List<Integer> squares = numbers.stream()
                .map(number -> number * number)
                .toList();

        // Reduce to calculate the sum of numbers
        int sum = numbers.stream()
                .reduce(0, Integer::sum);

        // Check if any number is greater than 10
        boolean anyNumberGreaterThanTen = numbers.stream()
                .anyMatch(number -> number > 10);

        System.out.println("Even numbers: " + evenNumbers);
        System.out.println("Squares: " + squares);
        System.out.println("Sum: " + sum);
        System.out.println("Any number greater than 10? " + anyNumberGreaterThanTen);

        // lazy Evaluation
        // Intermediate and Terminal Operations
        // Pipelining (chaining multiple operations in a stream pipeline)
        // Parallel Execution
        int sumOfSquares = numbers.parallelStream()
                .map(number -> number * number)
                .reduce(0, Integer::sum);

        System.out.println("Sum of squares: " + sumOfSquares);
    }
}
