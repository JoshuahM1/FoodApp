import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
  public static ExistingCustomerData customerData = new ExistingCustomerData();
  public static ExistingDeliveryData deliveryData = new ExistingDeliveryData();
  public static RestaurantList restaurantList;
  public static OrderQueue orderQueue = new OrderQueue();

  // Track completed orders and which driver delivered them
  public static List<OrderDetail> completedOrders = new ArrayList<>();
  public static Map<String, String> orderDriverMap = new HashMap<>();

  public static void main(String[] args) {
    restaurantList = new RestaurantList("./src/menus.txt");
    Scanner scanner = new Scanner(System.in);

    while (true) {
      System.out.println("\n=== Welcome to Ez J's ===");
      System.out.println("1) Customer mode");
      System.out.println("2) Driver   mode");
      System.out.println("3) Exit");
      System.out.print("Choose an option: ");
      String choice = scanner.nextLine().trim();

      if (choice.equals("1")) {
        // Customer mode
        Customer customer = createOrLoginCustomer();
        processReviews(customer);
        if (yesNoCheck("Would you like to place a new order? (y/n)")) {
          processOrder(customer);
        }

      } else if (choice.equals("2")) {
        // Driver mode
        Delivery delivery = createOrLoginDelivery();
        processDriver(delivery);

      } else if (choice.equals("3")) {
        System.out.println("Goodbye!");
        break;

      } else {
        System.out.println("Invalid option. Please select 1, 2, or 3.");
      }
    }

    scanner.close();
  }

  /**
   * Prompt customer to review any delivered-but-unreviewed orders.
   */
  public static void processReviews(Customer customer) {
    Scanner scanner = new Scanner(System.in);
    boolean found = false;
    for (OrderDetail ord : completedOrders) {
      if (ord.customerNameString.equals(customer.getName())
          && !"Reviewed".equals(ord.orderStatus)) {
        found = true;
        System.out.println("\nOrder #" + ord.getOrderID() + " delivered! Please review:");
        double rating;
        while (true) {
          System.out.print("Rating (1.0-5.0): ");
          try {
            rating = Double.parseDouble(scanner.nextLine().trim());
            if (rating >= 1.0 && rating <= 5.0) break;
          } catch (NumberFormatException e) {}
          System.out.println("Invalid rating. Enter a number between 1.0 and 5.0.");
        }
        System.out.print("Comments: ");
        String comment = scanner.nextLine().trim();
        String driverName = orderDriverMap.get(ord.getOrderID());
        Delivery driver = deliveryData.getProfile(driverName);
        customer.giveDeliveryRating(driver, rating, comment);
        ord.orderStatus = "Reviewed";
        System.out.println("Thanks for your feedback!");
      }
    }
    if (!found) {
      System.out.println("No new reviews to write.");
    }
  }

  /**
   * Handles login or new Customer creation;
   */
  public static Customer createOrLoginCustomer() {
    Scanner scanner = new Scanner(System.in);

    while (true) {
      System.out.println("Welcome!");
      boolean hasProfile = yesNoCheck("Do you already have a profile with us? (y/n)");
      if (!hasProfile) {
        // user wants to sign up
        return createNewCustomer();
      }

      // user says “yes” → attempt a login
      System.out.print("Please enter your username:");
      String username = scanner.nextLine().trim();

      if (!customerData.profileExists(username)) {
        System.out.println("No profile found. Let’s try again.\n");
        continue;
      }

      // we have a username in the map → ask for password
      System.out.print("Please enter your password: ");
      String pw = scanner.nextLine();

      if (customerData.checkPassword(username, pw)) {
        return customerData.getProfile(username);
      } else {
        System.out.println("Incorrect password. Let’s start over.\n");
      }
    }
  }

  public static Delivery createOrLoginDelivery() {
    Scanner scanner = new Scanner(System.in);

    while (true) {
        System.out.println("Welcome!");
        boolean hasProfile = yesNoCheck("Do you already have a profile with us? (y/n)");
        if (!hasProfile) {
            return createNewDelivery();
        }
        System.out.print("Please enter your username: ");
        String username = scanner.nextLine().trim();

        if (!deliveryData.profileExists(username)) {
            System.out.println("No profile found. Let’s try again.\n");
            continue;
        }

        System.out.print("Please enter your password: ");
        String pw = scanner.nextLine();

        if (deliveryData.checkPassword(username, pw)) {
            return deliveryData.getProfile(username);
        } else {
            System.out.println("Incorrect password. Let’s start over.\n");
        }
    }
}


  public static Customer createNewCustomer() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Let's make you a new profile.");
    System.out.print("Please pick a username: ");
    String name = scanner.nextLine().trim();
    while (customerData.profileExists(name)) {
      System.out.print("Username already exists. Pick another or type 'exit': ");
      name = scanner.nextLine().trim();
      if (name.equalsIgnoreCase("exit")) return null;
    }
    System.out.print("Please pick a password: ");
    String password = scanner.nextLine().trim();
    System.out.print("What is the address for delivery? ");
    String address = scanner.nextLine().trim();

    System.out.print("What's the best phone number for contact? ");
    String phone = scanner.nextLine().trim();
    Customer c = new Customer();
      c.setName(name);
      c.setPhoneNumber(phone);
      c.setLocation(address);
      c.setPassword(password);
      customerData.addUser(c);
    return c;
  }

  public static Delivery createNewDelivery() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Let's make you a new profile.");
    System.out.print("Please pick a username: ");
    String name = scanner.nextLine().trim();
    while (deliveryData.profileExists(name)) {
      System.out.print("Username already exists. Pick another or type 'exit': ");
      name = scanner.nextLine().trim();
      if (name.equalsIgnoreCase("exit")) return null;
    }
    System.out.print("Please pick a password: ");
    String password = scanner.nextLine().trim();
    System.out.print("What's the best phone number for contact? ");
    String phone = scanner.nextLine().trim();
    Delivery d = new Delivery();
      d.setName(name);
      d.setPhoneNumber(phone);
      d.setLocation(null);
      d.setPassword(password);
      deliveryData.addUser(d);
    return d;
  }

  public static void processDriver(Delivery driver) {
    Scanner scanner = new Scanner(System.in);
    boolean keepGoing = true;
    while (keepGoing) {
      System.out.println("\nDriver menu:");
      System.out.println("1) Pick up next order");
      System.out.println("2) View my ratings");
      System.out.println("3) Exit driver mode");
      System.out.print("Enter choice: ");
      String opt = scanner.nextLine().trim();

      if (opt.equals("1")) {
        if (orderQueue.isEmpty()) {
          System.out.println("No pending orders at the moment.");
        } else {
          OrderDetail currOrder = driver.acceptOrder(orderQueue, orderDriverMap);
          if (yesNoCheck("Mark this order delivered? (y/n)")) {
            currOrder.setDelivered();
            completedOrders.add(currOrder);
            System.out.println("Order #" + currOrder.getOrderID() + " completed!");
          } else {
            orderQueue.addOrder(currOrder);
            System.out.println("Order re-queued.");
          }
        }
      } else if (opt.equals("2")) {
        System.out.println("Your average rating: " + driver.getAverageRating());
        System.out.println("All reviews:\n" + driver.getPastRatings());
      } else if (opt.equals("3")) {
        keepGoing = false;
      } else {
        System.out.println("Invalid choice. Please enter 1, 2, or 3.");
      }
    }
    System.out.println("Logging out of driver mode.\n");
  }

  public static void processOrder(Customer customer) {
    Scanner scanner = new Scanner(System.in);
    System.out.println("Welcome " + customer.getName() + "!");
    System.out.println("Where would you like to order from? Options are:");
    for (String name : restaurantList.getRestaurantNames()) {
      System.out.println(" - " + name);
    }
    String restName = listCheck(restaurantList.getRestaurantNames());
    if (restName == null) return;
    Menu menu = restaurantList.getMenu(restName);
    System.out.println("Here is the menu for " + restName + ":");
    menu.displayMenu();

    OrderDetail order = new OrderDetail(customer.getName(), menu);
    while (yesNoCheck("Would you like to add an item to your order? (y/n)")) {
      System.out.print("Enter item name: ");
      String itemName = scanner.nextLine().trim();
      if (!menu.containsItem(itemName)) {
        System.out.println("Item not found. Please choose from the menu.");
        continue;
      }
      System.out.print("Enter quantity for " + itemName + ": ");
      int qty;
      try {
        qty = Integer.parseInt(scanner.nextLine().trim());
      } catch (NumberFormatException e) {
        System.out.println("Invalid number. Please try again.");
        continue;
      }
      order.addItemAndQuantity(itemName, qty, menu);
    }
    System.out.println("\nYour order summary:");
    order.printReceipt();

    orderQueue.addOrder(order);
    System.out.println("Your order #" + order.getOrderID() + " has been queued for delivery.");
  }

  public static String listCheck(List<String> options) {
    Scanner scanner = new Scanner(System.in);
    while (true) {
      String input = scanner.nextLine().trim();
      for (String opt : options) {
        if (opt.equalsIgnoreCase(input)) {
          return opt;
        }
      }
      if (input.equalsIgnoreCase("exit")) return null;
      System.out.println("Not a valid option. Please retry or type 'exit'.");
    }
  }

  public static boolean yesNoCheck(String prompt) {
    Scanner scanner = new Scanner(System.in);
    System.out.print(prompt + " ");
    while (true) {
      String resp = scanner.nextLine().trim().toLowerCase();
      if (resp.equals("y")) return true;
      if (resp.equals("n") || resp.equals("exit")) return false;
      System.out.print("Please reply with 'y' or 'n' (or 'exit'): ");
    }
  }
}
