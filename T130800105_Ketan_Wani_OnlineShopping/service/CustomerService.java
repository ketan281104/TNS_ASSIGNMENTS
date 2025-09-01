// Program to define CustomerService class
package service;

import driver.CustomerDAO;
import entities.Customer;
import java.util.ArrayList;
import java.util.List;

public class CustomerService {
    private List<Customer> customerList = new ArrayList<>();

    public void addCustomer(Customer customer) {
        customerList.add(customer);
        CustomerDAO.addCustomer(customer.getUserId(), customer.getUsername(), customer.getEmail(), customer.getAddress());

    }

    // Retrieve Customer by ID
    public Customer getCustomer(int userId) {
       CustomerDAO.GetCustomer(userId);
        return customerList.stream()
                .filter(customer -> customer.getUserId() == userId)
                .findFirst()
                .orElse(null);
    }

    public void getCustomers() {
        customerList=null;
        List demo=CustomerDAO.viewCustomers();
        customerList.addAll(demo);
        

    }

    // // ✅ New view method
    public void viewCustomers() {
        if (customerList == null || customerList.isEmpty()) {
            System.out.println("No customers available.");
        } else {
            System.out.println("\nRegistered Customers:");
            for (Customer customer : customerList) {
                System.out.println(customer);
            }
        }
    }
}
