import java.util.regex.Pattern;
import java.util.stream.*;
import java.util.*;

public class App {

    private static void getCountNotZeroNumbers(int[] numbers) {
        // найти в массиве количество всех чисел, которые больше 0
        int count = 0;
        for (int n : numbers) {
            if (n > 0) count++;
        }
        System.out.println("Standart: " + count);

        // найти в массиве количество всех чисел, которые больше 0 (stream api)
        long countStreamAPI = Arrays.stream(numbers).filter(el -> el > 0).count(); //IntStream.of(-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5).filter(el -> el > 0).count();
        System.out.println("StreamAPI: " + countStreamAPI);
    }

    private static void getMaxNumberFromNumbers(int[] numbers) {
        OptionalInt maxNum = Arrays.stream(numbers).max();
        if (maxNum.isPresent()) {
            System.out.println(Arrays.stream(numbers).max().getAsInt());
        } else {
            System.out.println("Array is empty!");
        }
    }

    private static List<Person> sortPersonDefault(Iterable<Person> persons) {
        return StreamSupport.stream(persons.spliterator(), false)
                .sorted()
                .collect(Collectors.toList());
    }

    private static List<Person> sortPersonByAgeAndName(Iterable<Person> persons) {
        return StreamSupport.stream(persons.spliterator(), false)
                .sorted(new PersonComparatorByAgeAndName())
                .collect(Collectors.toList());
    }

    private static Map<Integer, List<Person>> getGroupedPersonsByAge(Iterable<Person> persons) {
        return StreamSupport.stream(persons.spliterator(), false)
                .sorted()
                .collect(Collectors.groupingBy(Person::getAge));
    }

    private static Map<Integer, Map<String, List<Person>>> getGroupedPersonsByAgeAndCity(Iterable<Person> persons) {
        return StreamSupport.stream(persons.spliterator(), false)
                .sorted()
                .collect(Collectors.groupingBy(Person::getAge, Collectors.groupingBy(el -> el.getAddress().getCity())));
    }

    private static Map<Integer, Integer> getCountPersonsByAge(Iterable<Person> persons) {
        return StreamSupport.stream(persons.spliterator(), false)
                .collect(HashMap::new,
                        (map, item) -> {
                            Integer age = item.getAge();
                            Integer ageCount = map.get(age);
                            map.put(age, ageCount != null ? ageCount + 1 : 1);
                        },
                        HashMap::putAll
                );
    }

    public static void main(String[] args) {
        final int[] numbers = {-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5};

        System.out.println("Hello world");

        final String separator = Pattern.quote("|");
        List<String> strings = new ArrayList<>();
        strings.add("Maxim Galkin|40|Moscow|Proletarskaya|10");
        strings.add("Vladimir Putin|70|Novosibirsk|Bolshevitskaya|999");
        strings.add("Alla Pugachova|70|Novosibirsk|Putina|123");
        strings.add("Alena Ivanova|77|Samara|Svetlaya|11");
        strings.add("Alena Ivanova|61|Tomsk|Fabrichnaya|5");
        strings.add("Ivan Petrov|70|Moscow|Proletarskaya|10");
        //Collections.sort(strings);

        List<Person> personList = strings.stream()
                .map(el -> new Person(el.split(separator)[0], Integer.valueOf(el.split(separator)[1]),
                        new Address(el.split(separator)[2], el.split(separator)[3], el.split(separator)[4])))
                .collect(Collectors.toList());

        System.out.println("Result of sortPersonDefault: ");
        System.out.println(sortPersonDefault(personList));

        System.out.println("Result of sortPersonByAgeAndName: ");
        System.out.println(sortPersonByAgeAndName(personList));

        System.out.println("Result of getGroupedPersonsByAge: ");
        System.out.println(getGroupedPersonsByAge(personList));

        System.out.println("Result of getCountPersonsByAge: ");
        System.out.println(getCountPersonsByAge(personList));

        System.out.println("Result of getCountNotZeroNumbers: ");
        getCountNotZeroNumbers(numbers);

        System.out.println("Result of getMaxNumberFromNumbers: ");
        getMaxNumberFromNumbers(numbers);

        System.out.println("Result of getGroupedPersonsByAgeAndCity: ");
        System.out.println(getGroupedPersonsByAgeAndCity(personList));
    }
}