import java.util.HashMap;
import java.util.Set;


public class Menu {
    private HashMap<String, Double> menuMap;


    public Menu(){//default empty menu for inNout
        menuMap = buildMenu("Hamburger:3.4\n" +
                "Cheeseburger:3.85\n" +
                "Double-Double:5.4\n" +
                "French Fries:2.3\n" +
                "Shakes:2.85\n" +
                "Soda (S):2.05\n" +
                "Soda (M):2.2\n" +
                "Soda (L):2.4\n" +
                "Soda (XL):2.6\n" +
                "Milk:0.99\n" +
                "Hot Cocoa:2.2\n" +
                "Coffee:1.35\n" +
                "Double-Double, French Fries, and Drink (M):9.9\n" +
                "Cheeseburger, French Fries, and Drink (M):8.35\n" +
                "Hamburger, French Fries, and Drink (M):7.9\n");

    }
    public Menu(String menuCSV){//CSV with item,cost,item,cost
        this.menuMap = buildMenu(menuCSV);
    }

    private HashMap<String, Double> buildMenu(String menuCSV) {
        HashMap<String, Double> menu = new HashMap<>();
        String[] menuComponents = menuCSV.split("\n");
        for (String s : menuComponents) {//optional add if statemnts for if list is odd error or not set up correctly
            String[] ItemAndCost = s.split(":");
            System.out.println("item" + ItemAndCost[0]);
            System.out.println("cost" + ItemAndCost[1]);
            menu.put(ItemAndCost[0], Double.parseDouble(ItemAndCost[1]));
        }
        return menu;
    }

    public HashMap<String, Double> getMenu(){
        return this.menuMap;
    }


    public Set<String> getItemList() {
        Set<String> keys = this.menuMap.keySet();
        return keys;
    }

    public double getItemCost(String item){
        return this.menuMap.get(item);
    }

    public boolean containsItem(String item){
        return this.menuMap.containsKey(item);
    }

    public void displayMenu(){
        for(String itemName : this.menuMap.keySet()){
            System.out.println(itemName + "  --- $" + this.menuMap.get(itemName));
        }
    }

}
