import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;


public class ResturantList {
    HashMap<String, Menu> resturantList;
    public ResturantList(){
        this.resturantList = new HashMap<String, Menu>();
    }

    public List<String> getResturantNames(){
        return resturantList.keySet().stream().toList();
    }

    public ResturantList(String fileName){
        this.resturantList = new HashMap<String, Menu>();
        try {
            String content = Files.readString(Path.of(fileName)); // read entire file as one string

            // Normalize line endings and split by double newline
            content = content.replace("\r\n", "\n"); // handles Windows-style newlines
            String[] sections = content.split("\n\\s*\n"); // allows blank lines with spaces

            // Print each section
            for (int i = 0; i < sections.length; i++) {
                String[] section = sections[i].split("\n");
                String resturantname = section[0];
                String strMenu = "";
                for(int x = 1; x < section.length;x++){
                    strMenu += section[x] +"\n";
                }
                Menu menu = new Menu(strMenu, resturantname);
                this.resturantList.put(resturantname, menu);
            }

        } catch (IOException e) {
            System.out.println("Failed to read file: " + e.getMessage());
        }
    }

    public void printAllMenues(){
        for(String resturantName : resturantList.keySet()){
            System.out.println("\n" + resturantName);
            resturantList.get(resturantName).displayMenu();
        }
    }

    public Menu getMenu(String resturantName){
        return resturantList.get(resturantName);
    }

}
