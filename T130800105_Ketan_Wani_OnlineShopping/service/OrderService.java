// Program to define Order Service - place the order, update the status, retrieve order by ID and List all orders
package service;

import entities.Order;
import entities.Product;
import entities.ProductQuantityPair;
import java.util.ArrayList;
import java.util.List;
import driver.OrderDAO;

public class OrderService {
    private List<Order> orderList = new ArrayList<>();

    public OrderService() {
    }

    public void placeOrder(Order order) {
       OrderDAO.placeOrder(order);
    }

    public void updateOrderStatus(int orderId, String status) {
        Order order = getOrder(orderId);
        OrderDAO.updateOrderStatus(orderId, status);

        if (order != null) {
            if ("Completed".equalsIgnoreCase(status) && "Pending".equalsIgnoreCase(order.getStatus())) {
                for (ProductQuantityPair pair : order.getProducts()) {
                    Product product = pair.getProduct();
                    int quantity = pair.getQuantity();

                    if (product.getStockQuantity() >= quantity) {
                        product.setStockQuantity(product.getStockQuantity() - quantity);
                    } else {
                        System.out.println("Insufficient stock for product: " + product.getName());
                        return;
                    }
                }
            } else if ("Cancelled".equalsIgnoreCase(status)) {
                if ("Completed".equalsIgnoreCase(order.getStatus()) || "Pending".equalsIgnoreCase(order.getStatus())) {
                    for (ProductQuantityPair pair : order.getProducts()) {
                        Product product = pair.getProduct();
                        int quantity = pair.getQuantity();
                        product.setStockQuantity(product.getStockQuantity() + quantity);
                    }
                }
            } else if ("Delivered".equalsIgnoreCase(status) && "Completed".equalsIgnoreCase(order.getStatus())) {
                // only update status, no stock change
            } else {
                System.out.println("Invalid Order");
                return;
            }
            order.setStatus(status);
        }
    }

    public Order getOrder(int orderId) {
        return orderList.stream().filter(order -> order.getOrderId() == orderId).findFirst().orElse(null);
    }

    public List<Order> getOrders() {
        return orderList;
    }


    public void viewOrders() {
        if (orderList == null || orderList.isEmpty()) {
            System.out.println("No orders available.");
        } else {
            System.out.println("\nAll Orders:");
            for (Order order : orderList) {
                System.out.println(order);
            }
        }
    }
}
