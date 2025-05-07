import java.util.Scanner;
import java.util.List;

public class Main {
    public static ExistingUsersData usersData = new ExistingUsersData();
    public static ResturantList resturantList;
     public static void main(String[] args) {
         resturantList = new ResturantList("menus.txt");
         processGeneralUser();
     }

     public static void processGeneralUser(){//main function where we process the user through the delivery/ordering process
         Person curUser = getUser();
         if(curUser.isDriver()){
             processDriver(curUser);
         }
         processOrder(curUser);
     }

     public static void processDriver(Person driver){
        //implement a driver subclass to person that has acsess to pull orders from list ie change status ect
     }

     public static void processOrder(Person customer){
         System.out.println("Welcome " + customer.getName());
         System.out.println("Where would you like to order from the options are");
         for(String c : resturantList.getResturantNames()){
             System.out.println(c);
         }
         String resturantName = listCheck(resturantList.getResturantNames());
         Menu curMenu = resturantList.getMenu(resturantName);
         System.out.println("Here is the menu for " + resturantName);
         curMenu.displayMenu();
            //write code to get customors order and then add order to que
         //when done whith customer reprompt to see other order and then leave to process general user
         }




     public static String listCheck(List<String> val){
         Scanner scanner = new Scanner(System.in);
         while(true){
             String userPrompt = scanner.next();
             for(String c : val){
                 if(userPrompt.equals(c)){
                     return c;
                 }
                 if(userPrompt.equals("exit")){
                     processGeneralUser();
                 }
             }
             System.out.println("Not A valid option retry or type exit");
         }
     }

     public static boolean yesNoCheck(String question){
         Scanner scanner = new Scanner(System.in);
         System.out.print(question + " y/n\n");
         while(true) {
             String status = scanner.nextLine();
             if (status.equals("y")) {
                 return true;
             }else if (status.equals("n")) {
                 return false;
             }else if (status.equals("exit")){
                 processGeneralUser();
             }else {
                 System.out.println(question + " Please reply with either y or n or exit");
             }
         }
     }
     public static Person getUser(){
         Scanner scanner = new Scanner(System.in);
         System.out.println("Welcome!");
         String name;
         if(yesNoCheck("Do you already have profile with us?")){
             System.out.println("Please input your username: ");
             while(true){//checks if a profile with the username exists
                 String username = scanner.nextLine();
                 if(usersData.profileExists(username)){
                     name = username;
                     break;
                 }
                 if(username.equals("exit")){
                     processGeneralUser();
                 }
                 System.out.println("No profile with that username found. try again or type exit");
             }
             System.out.println("Please enter you password: ");
             String password = scanner.nextLine();
             if(usersData.checkPassword(name, password)){
                 return usersData.getProfile(name);
             }
             for(int i = 5; i > 0; i--){//makes the user enter their password 5 chances then it exits
                 System.out.println("Incorrect password please retry " + i + "attempts remaining");
                 if(usersData.checkPassword(name,scanner.nextLine())){
                     return usersData.getProfile(name);
                 }
             }
         }
         return createNewProfile();

     }
     public static Person createNewProfile(){
         System.out.println("Let's make you a new profile");
         Scanner scanner = new Scanner(System.in);
         //String name, String phoneNumber, String location, String password
         System.out.println("Please pick a username");
         String name = scanner.nextLine();
         if(usersData.profileExists(name)) {
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
         Person newProfile = new Person(name,phoneNumber,adress,password);
         if(yesNoCheck("Would you like to sign up as a driver?")){//if they want to be a driver set tag
             newProfile.setDriver();
         }
         return newProfile;
     }
}
