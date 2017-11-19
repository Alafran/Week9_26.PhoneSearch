
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class UI {

    private Scanner reader;
    private UserDatabase userDatabase;

    public UI(Scanner reader) {
        this.reader = reader;
        this.userDatabase = new UserDatabase();
    }

    public void Start() {
        System.out.println("phone search");
        System.out.println("available operations:");
        System.out.println(" 1 add a number");
        System.out.println(" 2 search for a number");
        System.out.println(" 3 search for a person by phone number");
        System.out.println(" 4 add an address");
        System.out.println(" 5 search for personal information");
        System.out.println(" 6 delete personal information");
        System.out.println(" 7 filtered listing");
        System.out.println(" x quit");

        String userCommand = "";
        while (!userCommand.equals("x")) {
            System.out.print("command: ");
            userCommand = this.reader.nextLine();

            if (userCommand.equals("1")) {
                commandOne();
            } else if (userCommand.equals("2")) {
                commandTwo();
            } else if (userCommand.equals("3")) {
                commandThree();
            } else if (userCommand.equals("4")) {
                commandFour();
            } else if (userCommand.equals("5")) {
                commandFive();
            } else if (userCommand.equals("6")) {
                commandSix();
            } else if (userCommand.equals("7")) {
                commandSeven();
            }
        }
    }

    //method called for command #1
    public void commandOne() {
        System.out.print("whose number: ");
        String userName = reader.nextLine();
        System.out.print("number: ");
        String userPhoneNumber = reader.nextLine();

        //checks to see if database already has a user by the given name
        //if so adds the number to the set
        //if not adds a new user then adds a number to the set
        if (this.userDatabase.containsUser(userName)) {
            this.userDatabase.getUser(userName).addNumber(userPhoneNumber);
        } else {
            this.userDatabase.addUser(new User(userName));
            this.userDatabase.getUser(userName).addNumber(userPhoneNumber);
        }
    }

    //method called for command #2
    //method tries to retrieve the numbers of the user given
    //if no user is found, catches the exception and lets the user know
    public void commandTwo() {
        System.out.print("whose number: ");
        String userName = reader.nextLine();

        try {
            this.userDatabase.getUser(userName).printNumbers();
        } catch (Exception e) {
            System.out.println("  not found");
        }
    }

    //method called for command #3
    public void commandThree() {
        System.out.print("number: ");
        String userNumber = reader.nextLine();

        System.out.println(" " + this.userDatabase.userWithNumber(userNumber));
    }

    //method called for command #4
    //sets the address for the given user
    public void commandFour() {
        System.out.print("whose address: ");
        String userName = reader.nextLine();
        //creates a new user if there isn't one with given name
        if (!this.userDatabase.containsUser(userName)) {
            this.userDatabase.addUser(new User(userName));
        }

        System.out.println("street: ");
        String userStreetName = reader.nextLine();

        System.out.println("city: ");
        String userCityName = reader.nextLine();

        this.userDatabase.getUser(userName).setStreetName(userStreetName);
        this.userDatabase.getUser(userName).setCityName(userCityName);
    }

    public void commandFive() {
        System.out.print("whose information: ");
        String userName = reader.nextLine();
        if (this.userDatabase.containsUser(userName)) {
            this.userDatabase.printInfo(userName);
            //checks to make sure theres a user with the name given before setting address
            //the .equals("Unknown") is because when a user is created their address
            // is initialized as "Unknown"
            /*if (this.userDatabase.getUser(userName).getStreetName().equals("Unknown")) {
                System.out.println("address unknown");
            } else {
                System.out.print("  address: ");
                System.out.print(this.userDatabase.getUser(userName).getStreetName());
                System.out.print(" " + this.userDatabase.getUser(userName).getCityName());
                System.out.println("");
            }
            */
            //if the set of numbers is empty it prints the error
            /*if (this.userDatabase.getUser(userName).getNumbers().isEmpty()) {
                System.out.println("phone number not found");
            } else {
                System.out.println("  phone numbers: ");
                this.userDatabase.getUser(userName).printNumbers();
            }
*/
        } else {
            System.out.println("  not found");
        }

    }

    public void commandSix() {
        System.out.println("whose information: ");
        String userName = reader.nextLine();
        //makes sure there is a user in with the given name in the set
        try {
            this.userDatabase.removeUser(this.userDatabase.getUser(userName));
        }
        catch(Exception e) {
            System.out.println("No such user");
        }
    }

    //this is the method called for command #7
    public void commandSeven() {
        //requests a keyword first
        System.out.print("keyword (if empty, all listed): ");
        String userKeyword = reader.nextLine();
        
        //gets an arraylist of all the users
        //sorts the arraylist based on alphabetical order
        ArrayList<User> userList = this.userDatabase.listOfUsers();
        Collections.sort(userList);
        
        //initializes the boolean to false
        //only sets it to true if a keyword is found
        boolean containsKey = false;
        
        //searches each user for the keyword in their name or address
        //if it is found, name is printed along with numbers and address
        for(User user : userList) {
            if(user.getName().contains(userKeyword)) {
                System.out.println(user.getName());
                this.userDatabase.printInfo(user.getName());
                containsKey = true;
            }
            
            else if(user.getCityName().contains(userKeyword)) {
                System.out.println(user.getName());
                this.userDatabase.printInfo(user.getName());
                containsKey = true;
            }
            
            else if(user.getStreetName().contains(userKeyword)) {
                System.out.println(user.getName());
                this.userDatabase.printInfo(user.getName());
                containsKey = true;
            }
                       
        }
        if(containsKey == false) {
            System.out.println("not found");
        }
        
    }
    
}
