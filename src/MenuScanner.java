import java.util.HashMap;

/*  
* This is a second version of the Menu class if we want to use the scanner tool
* to go through ordering, if that's the case. To print out the menu
* HashMap needs to be a LinkedList instead to support the ordering of the menu items.
*/

public class MenuScanner {
    private HashMap<String, Double> menuMap = new HashMap<>();
    private HashMap<String, String> comboDetails = new HashMap<>();

    // Sample data from In-n-out branch in Salinas back in 2023
    // https://www.yelp.com/biz/in-n-out-burger-salinas
    {  
        menuMap.put("Hamburger", 3.4);
        menuMap.put("Cheeseburger", 3.85);
        menuMap.put("Double-Double",5.4);
        menuMap.put("French Fries", 2.3);
        menuMap.put("Shakes", 2.85);
        menuMap.put("Soda (S)", 2.05);
        menuMap.put("Soda (M)", 2.2);
        menuMap.put("Soda (L)", 2.4);
        menuMap.put("Soda (XL)", 2.6);
        menuMap.put("Milk", 0.99);
        menuMap.put("Hot Cocoa", 2.2);
        menuMap.put("Coffee", 1.35);
        menuMap.put("Combo 01", 9.9);
        comboDetails.put("Combo 01", "Double-Double, French Fries, and Drink (M)");
        menuMap.put("Combo 02", 8.35);
        comboDetails.put("Combo 02", "Cheeseburger, French Fries, and Drink (M)");
        menuMap.put("Combo 03", 7.9);
        comboDetails.put("Combo 01", "Hamburger, French Fries, and Drink (M)");
    }

    public void printMenu() {
        System.out.println("---------------------------------------");
        menuMap.forEach((item, value) -> {
            System.out.println("| " + item);
        });
        System.out.println("---------------------------------------");
    }


    public static void main(String[] args) {
        MenuScanner inNOut = new MenuScanner();
        inNOut.printMenu();
    }
}
