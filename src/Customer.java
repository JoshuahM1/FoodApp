public class Customer extends Person {
    public Customer() {
        // no arg constructor
    }

    public String getOrderStatus(OrderDetail order) {
        return order.orderStatus;
    }

    public void giveDeliveryRating(Delivery driver, double rating, String comment) {
        driver.addCustomerRating(rating, comment, this.getName());
    }
}
