public class Person implements Comparable<Person> {
    private String name;
    private Integer age;
    private Address address;

    public Person(String name, Integer age, Address address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    public String toString() {
        return "{" +
                "name='" + this.name + "', " +
                "age=" + this.age + "', " +
                "address=" + this.address +
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

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Address getAddress() {
        return address;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person person)) return false;

        if (!name.equals(person.name)) return false;
        if (!address.equals(person.address)) return false;
        return age.equals(person.age);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + age.hashCode();
        result = 31 * result + address.hashCode();
        return result;
    }
}
