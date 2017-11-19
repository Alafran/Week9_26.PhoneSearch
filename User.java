
import java.util.HashSet;
import java.util.Set;


public class User implements Comparable<User>{
    
    //One user has a name, a set of unique numbers, a street name, and a city name
    private String name;
    private Set<String> numbers;
    private String streetName;
    private String cityName;
    
    //when a user is created they have to have a name
    //the set of numbers will be empty to start
    //street name will be unknown
    //city name will be unknown
    public User(String name) {
        this.name = name;
        this.numbers = new HashSet<String>();
        this.streetName = "Unknown";
        this.cityName = "Unknown";
    }
    
    public void addNumber(String numberToAdd) {
        this.numbers.add(numberToAdd);
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreetName() {
        return this.streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getCityName() {
        return this.cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
    
    public void printNumbers() {
        for(String numbers : this.numbers) {
            System.out.println(" " + numbers);
        }
    }
    
    public Set<String> getNumbers() {
        return this.numbers;
    }

    @Override
    public int compareTo(User t) {
        return this.name.compareTo(t.name);
    }
    
}
