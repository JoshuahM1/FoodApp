import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;


public class RestaurantList {
    HashMap<String, Menu> restaurantList;
    public RestaurantList(){
        this.restaurantList = new HashMap<String, Menu>();
    }

    public List<String> getRestaurantNames(){
        return restaurantList.keySet().stream().toList();
    }

    public RestaurantList(String fileName){
        this.restaurantList = new HashMap<String, Menu>();
        try {
            String content = Files.readString(Path.of(fileName)); // read entire file as one string

            // Normalize line endings and split by double newline
            content = content.replace("\r\n", "\n"); // handles Windows-style newlines
            String[] sections = content.split("\n\\s*\n"); // allows blank lines with spaces

            // Print each section
            for (int i = 0; i < sections.length; i++) {
                String[] section = sections[i].split("\n");
                String restaurantname = section[0];
                String strMenu = "";
                for(int x = 1; x < section.length;x++){
                    strMenu += section[x] +"\n";
                }
                Menu menu = new Menu(strMenu, restaurantname);
                this.restaurantList.put(restaurantname, menu);
            }

        } catch (IOException e) {
            System.out.println("Failed to read file: " + e.getMessage());
        }
    }

    public void printAllMenues(){
        for(String restaurantName : restaurantList.keySet()){
            System.out.println("\n" + restaurantName);
            restaurantList.get(restaurantName).displayMenu();
        }
    }

    public Menu getMenu(String restaurantName){
        return restaurantList.get(restaurantName);
    }

}
