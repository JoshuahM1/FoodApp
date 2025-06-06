import java.util.LinkedList;

public class OrderQueue {
    LinkedList<OrderDetail> orderQueue; // Queue to hold orders

    public void addOrder(OrderDetail order) {
        // Add order to the queue
        orderQueue.add(order);
    }
    public OrderQueue(){
        this.orderQueue = new LinkedList<>();
    }

    public OrderDetail removeOrder() {
        // Remove the first order in the queue
        return orderQueue.remove();
    }
    public boolean isEmpty() {
        return orderQueue.isEmpty();
    }
}

