import java.util.HashMap;
import java.util.Map;

public class ExistingCustomerData {
    Map<String,Customer> ExistingCustomerData;

    public ExistingCustomerData(){
        this.ExistingCustomerData = new HashMap<>();
    }

    public ExistingCustomerData(HashMap<String, Customer> savedCustomer){
        this.ExistingCustomerData = savedCustomer;
    }

    public boolean profileExists(String name){
        return ExistingCustomerData.containsKey(name);
    }

    public boolean checkPassword(String name, String password){
        return ExistingCustomerData.get(name).getPassword().equals(password);
    }

    public Customer getProfile(String name){
        return this.ExistingCustomerData.get(name);
    }

    public void addUser(Customer p){
        ExistingCustomerData.put(p.getName(), p);
    }
}
