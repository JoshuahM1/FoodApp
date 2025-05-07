import java.util.HashMap;
import java.util.Map;

public class ExistingUsersData {
    Map<String,Person> ExistingUserData;

    public ExistingUsersData(){
        this.ExistingUserData = new HashMap<String, Person>();
    }

    public ExistingUsersData(HashMap<String, Person> savedUsers){
        this.ExistingUserData = savedUsers;
    }

    public boolean profileExists(String name){
        return ExistingUserData.containsKey(name);
    }

    public boolean checkPassword(String name, String password){
        return ExistingUserData.get(name).getPassword().equals(password);
    }

    public Person getProfile(String name){
        return this.ExistingUserData.get(name);
    }

    public void addUser(Person p){
        ExistingUserData.put(p.getName(), p);
    }
}
