import java.util.LinkedList;
import java.util.Map;

public class Delivery extends Person {
    private LinkedList<Double> ratings = new LinkedList<>();
    private LinkedList<String> comments = new LinkedList<>();

    public Delivery() {
        // No-arg constructor
    }

    public OrderDetail acceptOrder(OrderQueue orderQueue, Map<String,String> orderDriverMap) {
        // 1) remove the next order
        OrderDetail curOrder = orderQueue.removeOrder();

        // 2) mark it Out for delivery
        curOrder.setPickedUp();

        // 3) log to console, using the getter instead of direct field access
        System.out.println(
            "Picked up order #"
            + curOrder.getOrderID()
            + " for "
            + curOrder.getCustomerNameString()
        );

        // 4) record which driver grabbed it
        orderDriverMap.put(curOrder.getOrderID(), this.getName());
        return curOrder;
    }

    public void addCustomerRating(double rating, String comment, String customerName) {
        // Keep only the most recent 10 reviews
        if (ratings.size() >= 10) {
            ratings.removeFirst();
            comments.removeFirst();
        }
        ratings.add(rating);
        comments.add(customerName + ": " + comment);
    }

    /**
     * Get average rating as a double value.
     */
    public String getAverageRating() {
        if (ratings.isEmpty()) return "No ratings yet";
        double total = 0;
        for (double r : ratings) {
            total += r;
        }
        double avg = total / ratings.size();
        return String.format("%.1f", avg);
    }

    /**
     * Return all past ratings with comments, grouped per entry.
     */
    public String getPastRatings() {
        if (ratings.isEmpty()) return "No ratings yet";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < ratings.size(); i++) {
            sb.append(String.format("%.1f", ratings.get(i)))
              .append(" - ")
              .append(comments.get(i))
              .append("\n");
        }
        return sb.toString();
    }
}
