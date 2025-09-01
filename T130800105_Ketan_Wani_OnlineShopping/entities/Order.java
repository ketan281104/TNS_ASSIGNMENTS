// Program to define Order entity
package entities;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private int orderId;
    private Customer customer;                   // Who placed the order
    private List<ProductQuantityPair> products;  // Products + quantities
    private String status;                       // Placed / Pending / Completed / Cancelled / Delivered

    // Constructor with single product
    public Order(int orderId, Customer customer, Product product, int quantity, String status) {
        this.orderId = orderId;
        this.customer = customer;
        this.products = new ArrayList<>();
        this.products.add(new ProductQuantityPair(product, quantity));
        this.status = status;
    }

    // Constructor for multiple products
    public Order(int orderId, Customer customer, List<ProductQuantityPair> products, String status) {
        this.orderId = orderId;
        this.customer = customer;
        this.products = products;
        this.status = status;
    }

    // Add another product to the order
    public void addProduct(Product product, int quantity) {
        this.products.add(new ProductQuantityPair(product, quantity));
    }

    // Getters and Setters
    public int getOrderId() { return orderId; }
    public void setOrderId(int orderId) { this.orderId = orderId; }

    public Customer getCustomer() { return customer; }
    public void setCustomer(Customer customer) { this.customer = customer; }

    public List<ProductQuantityPair> getProducts() { return products; }
    public void setProducts(List<ProductQuantityPair> products) { this.products = products; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    @Override
    public String toString() {
        return "Order [orderId=" + orderId +
               ", customer=" + customer.getUsername() +
               ", products=" + products +
               ", status=" + status + "]";
    }
}
