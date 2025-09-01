package driver;
import entities.Customer;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {
    public static void addCustomer(int id, String username, String email, String address) {
        String query = "INSERT INTO customer (userId, username, email, address) VALUES (?, ?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.setString(2, username);
            ps.setString(3, email);
            ps.setString(4, address);
            ps.executeUpdate();
            System.out.println("Customer created successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

   
    public static List<Customer> viewCustomers() {
        List<Customer> list = new ArrayList<>();
        String sql = "SELECT * FROM customer";
        try (Connection conn = DBConnection.getConnection();
             Statement st = conn.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Customer c = new Customer(
                        rs.getInt("userId"),
                        rs.getString("username"),
                        rs.getString("email"),
                        rs.getString("address")
                );
                list.add(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

     public static void GetCustomer(int id) {
        String query = "SELECT * FROM customer where userId="+id;
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(query)) {
            while (rs.next()) {
                System.out.println("User ID: " + rs.getInt("userId") +
                                   ", Username: " + rs.getString("username") +
                                   ", Email: " + rs.getString("email") +
                                   ", Address: " + rs.getString("address"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
