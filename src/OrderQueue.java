import java.util.LinkedList;

public class OrderQueue {
    LinkedList<OrderDetail> orderQueue = new LinkedList<>(); // Queue to hold orders

    public void addOrder(OrderDetail order) {
        // Add order to the queue
        orderQueue.add(order);
    }

    public OrderDetail removeOrder() {
        // Remove the first order in the queue
        return orderQueue.remove();
    }
}