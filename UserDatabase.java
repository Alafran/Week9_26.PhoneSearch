
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class UserDatabase {

    private Map<String, User> users;

    public UserDatabase() {
        this.users = new HashMap<String, User>();
    }

    public void addUser(User user) {
        this.users.put(user.getName(), user);
    }

    public void removeUser(User user) {
        this.users.remove(user.getName());
    }

    public boolean containsUser(String name) {
        return this.users.containsKey(name);
    }

    public User getUser(String name) {
        return this.users.get(name);
    }

    public String userWithNumber(String number) {
        //searches through each user in the database
        for (User user : this.users.values()) {
            //gets the set of numbers for each user
            Set<String> userNumbers = user.getNumbers();

            //searches the set of numbers for the number given in the method call
            for (String phoneNumber : userNumbers) {
                if (phoneNumber.equals(number)) {
                    return user.getName();
                }
            }
        }
        //if the number is not found it returns as such
        return "not found";
    }

    public ArrayList<User> listOfUsers() {
        ArrayList<User> userList = new ArrayList<User>();

        for (User user : this.users.values()) {
            userList.add(user);
        }
        return userList;
    }

    public void printInfo(String name) {
        if (this.users.get(name).getStreetName().equals("Unknown")) {
            System.out.println("address unknown");
        } else {
            System.out.print("  address: ");
            System.out.print(this.users.get(name).getStreetName());
            System.out.print(" " + this.users.get(name).getCityName());
            System.out.println("");
        }
        
        if(this.users.get(name).getNumbers().isEmpty()) {
            System.out.println("phone number not found");
        }
        else {
            System.out.println("  phone numbers: ");
            this.users.get(name).printNumbers();
        }
    }
}
