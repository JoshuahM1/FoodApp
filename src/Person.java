
import java.util.UUID;

public class Person {
  public String name;
  public String userID;
  public String phoneNumber;
  public String location;
  public  String password;

  public Person(){
    createUserID();
  }

  public Person(String name, String phoneNumber, String location, String password){
    setName(name);
    setPhoneNumber(phoneNumber);
    setLocation(location);
    createUserID();
    setPassword(password);
  }


  protected void setPassword(String password){this.password = password;}

  public String getPassword(){return this.password;}

  protected void setName(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  protected void createUserID() {
    this.userID = UUID.randomUUID().toString();
  }

  public String getUserID() {
    return userID;
  }

  protected void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  protected void setLocation(String location) {
    this.location = location;
  }

  public String getLocation() {
    return location;
  }




}
