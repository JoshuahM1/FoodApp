
import java.util.UUID;

public class Person {
  public String name;
  public String userID;
  public String phoneNumber;
  public String location;

  public Person(){
    createUserID();
  }

  public Person(String name, String phoneNumber, String location){
    setName(name);
    setPhoneNumber(phoneNumber);
    setLocation(location);
    createUserID();
  }

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
