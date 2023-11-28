import java.util.Comparator;
import java.util.Objects;

public class PersonComparatorByAgeAndName implements Comparator<Person> {
    public int compare(Person a, Person b) {
        if (!Objects.equals(a.getAge(), b.getAge())) {
            return a.getAge() - b.getAge();
        }
        return a.getName().toUpperCase().compareTo(b.getName().toUpperCase());
    }
}
