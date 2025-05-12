import java.util.HashMap;
import java.util.Map;

public class ExistingDeliveryData {
    Map<String,Delivery> ExistingDeliveryData;

    public ExistingDeliveryData(){
        this.ExistingDeliveryData = new HashMap<>();
    }

    public ExistingDeliveryData(HashMap<String, Delivery> savedDelivery){
        this.ExistingDeliveryData = savedDelivery;
    }

    public boolean profileExists(String name){
        return ExistingDeliveryData.containsKey(name);
    }

    public boolean checkPassword(String name, String password){
        return ExistingDeliveryData.get(name).getPassword().equals(password);
    }

    public Delivery getProfile(String name){
        return this.ExistingDeliveryData.get(name);
    }

    public void addUser(Delivery p){
        ExistingDeliveryData.put(p.getName(), p);
    }
}
