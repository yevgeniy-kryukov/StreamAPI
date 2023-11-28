public class Address {
    private String city;
    private String street;
    private String house;

    public Address(String city, String street, String house) {
        this.city = city;
        this.street = street;
        this.house = house;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Address address)) return false;

        if (!city.equals(address.city)) return false;
        if (!street.equals(address.street)) return false;
        return house.equals(address.house);
    }

    @Override
    public int hashCode() {
        int result = city.hashCode();
        result = 31 * result + street.hashCode();
        result = 31 * result + house.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Address{" +
                "city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", house='" + house + '\'' +
                '}';
    }
}
