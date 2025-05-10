import java.util.*;

public class Main {
    public static ExistingUsersData usersData = new ExistingUsersData();
    public static ResturantList resturantList;
    public static OrderQueue orderQueue = new OrderQueue();

    public static void main(String[] args) {
        resturantList = new ResturantList("src/menus.txt");
        //this is are main driver function could even be made into driver class for future stuff
        processGeneralUser();
    }

    //this is the main function that recursivly loops that processs users in It does a lot and is the default exit location where the user
    //experince starts this should be made into multiple meathods as when add driver functionality it will get messy
    //in general alot of things in this class if possible should be implemented in other classes if its fitting and also when doing so
    //its best practice to not have to pass many arguments thorugh as the data should be avaiilable in the class that the meathod is in
    //idealy unless it doesn't make sense arguments passed through methods should be things that are options for how the meathod will operate
    //hopefully all this makes sense basicaly we dont want all these public static voids and they shouldnt be so complicated because the objects
    //should be able to act like the objects they are for oop
    public static void processGeneralUser() {//main function where we process the user through the delivery/ordering process
        Person curUser = getUser();
        if (curUser.isDriver()) {//code here could be made into a process driver meathod and implemented in driver
            System.out.println("Hi driver " + curUser.getName());
            System.out.println("");
            //add code to processs driver by checkiing if driver has no orders currently and if so process one order from order que to be set order status to Outfordelivery
            //if driver has current order for delivery give option of completing order
        }
        //code here could be made into proccess customer in person class or customer class could be made to hold future meathods
        System.out.println("Welcome " + curUser.getName() + "!"); //part could be seperated into process customer class
        System.out.println("Type view to view past orders or type place to place a new order");
        if(listCheck(Arrays.asList("view", "place")).equals("view")){
            orderQueue.printCustomersOrder(curUser);
        }else {
            processOrder(curUser);
        }
        System.out.println("Thank you " + curUser.getName() + " have a nice day");
        //add code to check if completed orders and give option to leave rating for driver
        processGeneralUser();
    }




    public static void processOrder(Person customer) {//Could be implemented into order detail and order detail holds Person already so person dosent need to get passed through
        //System.out.println("Welcome " + customer.getName());
        System.out.println("Where would you like to order from the options are");
        for (String c : resturantList.getResturantNames()) {
            System.out.println(c);
        }
        String resturantName = listCheck(resturantList.getResturantNames());
        Menu curMenu = resturantList.getMenu(resturantName);
        System.out.println("Here is the menu for " + resturantName);
        curMenu.displayMenu();
        OrderDetail curOrder = new OrderDetail(customer.getName(), curMenu);
        System.out.println("To add items to order type the item name and then type done when complete or type exit to exit");
        Scanner scanner = new Scanner(System.in);
        String item = "";
        while (!item.equals("done")) {
            System.out.println("Please select an item from the menu, type done when complete. type exit to exit");
            item = scanner.nextLine();
            if (curOrder.itemInOrder(item)) {
                System.out.println("How many " + item + "would you like? please type a number type 0 to remove item");
                int ItemQuantiy = readPositiveInt();
                if (ItemQuantiy == 0) {
                    curOrder.removeItem(item);
                } else {
                    curOrder.modifyOrder(item, ItemQuantiy);
                }
            } else if (curMenu.containsItem(item)) {
                System.out.println("How many " + item + " would you like please type a number");
                int ItemQuantiy = readPositiveInt();
                curOrder.addItemAndQuantity(item, ItemQuantiy);
            } else if (item.equals("exit")) {
                processGeneralUser();
            } else if (curOrder.isEmpty() && item == "done") {
                System.out.println("The current order is empty please add an item or type exit");
            }
        }
        if (yesNoCheck("Would you like to comfirm your order?")) {
            curOrder.createOrderID();
            curOrder.setComfirmed();
            orderQueue.addOrder(curOrder);
            curOrder.printReceipt();
        }
        if (yesNoCheck("Would you like to order from somewhere else or exit?")) {
            processOrder(customer);
        } else {
            processGeneralUser();
        }
    }


    //write code to get customors order and then add order to que
    //when done whith customer reprompt to see other order and then leave to process general user

    public static int readPositiveInt() {
        Scanner scanner = new Scanner(System.in);
        int x = -100;
        while (x < 0) {
            try {
                x = scanner.nextInt();
                if (x < 0) {
                    System.out.println("Please type a positive number");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input; please enter a integer.");
                scanner.next();//random fix
            }
        }
        return x;
    }


//checks a list of strings for input and returns if they are in the list
    public static String listCheck(List<String> val) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String userPrompt = scanner.next();
            for (String c : val) {
                if (userPrompt.equals(c)) {
                    return c;
                }
                if (userPrompt.equals("exit")) {
                    processGeneralUser();
                }
            }
            //print out here could be changed so instead of not found it takes in a string for the prompt
            System.out.println("Not found please chose from " + String.join(",", val) +
                    " or type exit");
        }
    }

    public static boolean yesNoCheck(String question) {//bool true if yes false if no takes in a question to keep asking
        Scanner scanner = new Scanner(System.in);
        System.out.print(question + " y/n\n");
        while (true) {
            String status = scanner.nextLine();
            if (status.equals("y")) {
                return true;
            } else if (status.equals("n")) {
                return false;
            } else if (status.equals("exit")) {
                processGeneralUser();
            } else {
                System.out.println(question + " Please reply with either y or n or exit");
            }
        }
    }

    public static Person getUser() {//creates a new user or retrives user from user list and chekc password
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome!");
        String name;
        if (yesNoCheck("Do you already have profile with us?")) {
            System.out.println("Please input your username: ");
            //this part could be implemented in Existing user data part where it takes in a username and returns person if they exist
            while (true) {//checks if a profile with the username exists
                String username = scanner.nextLine();
                if (usersData.profileExists(username)) {
                    name = username;
                    break;
                }
                if (username.equals("exit")) {
                    processGeneralUser();
                }
                System.out.println("No profile with that username found. try again or type exit");
            }
            System.out.println("Please enter you password: ");
            String password = scanner.nextLine();
            if (usersData.checkPassword(name, password)) {
                return usersData.getProfile(name);
            }
            for (int i = 5; i > 0; i--) {//makes the user enter their password 5 chances then it exits
                System.out.println("Incorrect password please retry " + i + "attempts remaining");
                if (usersData.checkPassword(name, scanner.nextLine())) {
                    return usersData.getProfile(name);
                }
            }
        }
        return createNewProfile();

    }

    public static Person createNewProfile() {//Maybe could be implemendted in person but fine here because it needs accsess to user data and that
        //probably shouldnt exist in person
        System.out.println("Let's make you a new profile");
        Scanner scanner = new Scanner(System.in);
        //String name, String phoneNumber, String location, String password
        System.out.println("Please pick a username");
        String name = scanner.nextLine();
        if (usersData.profileExists(name)) {
            while (!usersData.profileExists(name)) {
                System.out.println("Username already exists please pick a different username or type exit");
                name = scanner.nextLine();
            }
        }
        System.out.println("Please pick a password");
        String password = scanner.nextLine();
        System.out.println("What is the address for delivery?");
        String adress = scanner.nextLine();
        System.out.println("Whats the best phone nubmer for contatct?");
        String phoneNumber = scanner.nextLine();
        Person newProfile = new Person(name, phoneNumber, adress, password);
        if (yesNoCheck("Would you like to sign up as a driver?")) {//if they want to be a driver set tag
            //should be implemented to create driver class and not sure if this will mess up other parts with the person
            // return so might take some restructuring if classes are not set up right
            newProfile.setDriver();
        }
        usersData.addUser(newProfile);
        return newProfile;
    }
}
