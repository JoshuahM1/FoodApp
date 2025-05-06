import java.util.HashMap;
import java.util.Set;


public class Menu {
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

    public Set<String> getItemList() {
        Set<String> keys = menuMap.keySet();
        return keys;
    }

    public static void main(String[] args) {
        Menu inNOut = new Menu();
    }
}
