import java.util.regex.Pattern;
import java.util.stream.*;
import java.util.*;

public class StreamAPI {
    private static void sample1() {
        // найти в массиве количество всех чисел, которые больше 0
        int[] numbers = {-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5};
        int count = 0;
        for (int n : numbers) {
            if (n > 0) count++;
        }
        System.out.println("Standart: " + count);

        // найти в массиве количество всех чисел, которые больше 0 (stream api)
        long countStreamAPI = Arrays.stream(numbers).filter(el -> el > 0).count(); //IntStream.of(-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5).filter(el -> el > 0).count();
        System.out.println("StreamAPI: " + countStreamAPI);
    }

    public static void sample2() {
        final String separator = Pattern.quote("|");
        List<String> strings = new ArrayList<>();
        strings.add("Maxim Galkin|40");
        strings.add("Vladimir Putin|70");
        strings.add("Alla Pugachova|70");
        strings.add("Alena Ivanova|77");
        strings.add("Alena Ivanova|61");
        Collections.sort(strings);
        System.out.println(strings);

        List<Person> personsNew = strings.stream()
                .map(el -> new Person(el.split(separator)[0], Integer.valueOf(el.split(separator)[1])))
                .sorted(new PersonComparator())
                .collect(Collectors.toList());
        System.out.println(personsNew);

        List<Person> personsNew2 = strings.stream()
                .map(el -> new Person(el.split(separator)[0], Integer.valueOf(el.split(separator)[1])))
                .sorted()
                .collect(Collectors.toList());
        System.out.println(personsNew2);
    }

    public static void main(String[] args) {
        System.out.println("Hello world");
        sample1();
        sample2();
    }

    static class Person implements Comparable<Person> {
        private String name;
        private Integer age;

        public Person(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        public String toString() {
            return "{" +
                    "name='" + this.name + "', " +
                    "age=" + this.age +
                    "}";
        }

        @Override
        public int compareTo(Person person) {
            int result = this.name.toUpperCase().compareTo(person.name.toUpperCase());
            if (result == 0) {
                result = this.age.compareTo(person.age);
            }
            return result;
        }
    }

    static class PersonComparator implements Comparator<Person> {
        public int compare(Person a, Person b) {
            if (a.age != b.age) {
                return a.age - b.age;
            }
            return a.name.toUpperCase().compareTo(b.name.toUpperCase());
        }
    }
}