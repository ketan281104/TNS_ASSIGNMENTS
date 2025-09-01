package driver;
import java.sql.*;

import entities.Order;
import entities.ProductQuantityPair;

public class OrderDAO {
    public static void placeOrder(Order order) {
        String insertOrder = "INSERT INTO orders (orderId, customerId, status) VALUES (?, ?, ?)";
        String insertOrderProduct = "INSERT INTO order_products (orderId, productId, quantity) VALUES (?, ?, ?)";
        String updateStock = "UPDATE product SET stockQuantity = stockQuantity - ? WHERE productId = ?";

        try (Connection con = DBConnection.getConnection()) {
            con.setAutoCommit(false);

            try (PreparedStatement ps1 = con.prepareStatement(insertOrder);
                 PreparedStatement ps2 = con.prepareStatement(insertOrderProduct);
                 PreparedStatement ps3 = con.prepareStatement(updateStock)) {

                // Insert order row
                ps1.setInt(1, order.getOrderId());
                ps1.setInt(2, order.getCustomer().getUserId());
                ps1.setString(3, order.getStatus());
                ps1.executeUpdate();

                // Insert products + update stock
                for (ProductQuantityPair pq : order.getProducts()) {
                    ps2.setInt(1, order.getOrderId());
                    ps2.setInt(2, pq.getProduct().getProductId());
                    ps2.setInt(3, pq.getQuantity());
                    ps2.executeUpdate();

                    ps3.setInt(1, pq.getQuantity());
                    ps3.setInt(2, pq.getProduct().getProductId());
                    ps3.executeUpdate();
                }

                con.commit();
                System.out.println("✅ Order placed successfully!");
            } catch (SQLException e) {
                con.rollback();
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void viewOrders(int customerId) {
        String query = "SELECT o.orderId, o.status, p.name, op.quantity " +
                       "FROM orders o " +
                       "JOIN order_products op ON o.orderId=op.orderId " +
                       "JOIN product p ON op.productId=p.productId " +
                       "WHERE o.customerId=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, customerId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("Order ID: " + rs.getInt("orderId") +
                                   ", Status: " + rs.getString("status") +
                                   ", Product: " + rs.getString("name") +
                                   ", Quantity: " + rs.getInt("quantity"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void updateOrderStatus(int orderId, String status) {
    String sql = "UPDATE orders SET status=? WHERE orderId=?";
    try (Connection con = DBConnection.getConnection();
         PreparedStatement ps = con.prepareStatement(sql)) {
        ps.setString(1, status);
        ps.setInt(2, orderId);
        int rows = ps.executeUpdate();
        if (rows > 0) {
            System.out.println("✅ Order " + orderId + " updated to " + status);
        } else {
            System.out.println("❌ Order not found: " + orderId);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
}
