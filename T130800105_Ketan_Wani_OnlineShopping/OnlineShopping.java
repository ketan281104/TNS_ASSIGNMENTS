//Program to demonstrate Online Shopping Application
import driver.CustomerDAO;
import entities.Admin;
import entities.Customer;
import entities.Order;
import entities.Product;
import java.util.Scanner;
import service.AdminService;
import service.CustomerService;
import service.OrderService;
import service.ProductService;




public class OnlineShopping {
    private static ProductService productService = new ProductService();
    private static CustomerService customerService = new CustomerService();
    private static OrderService orderService = new OrderService();
    private static AdminService adminService = new AdminService();

    public static void main(String[] args) {
        CustomerDAO.addCustomer(1, "ketan", "ketan@gmail.com", "Pune");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1. Admin Menu");
            System.out.println("2. Customer Menu");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1: // Admin Module
                    int adminChoice;
                    do {
                        System.out.println("\nAdmin Menu:");
                        System.out.println("1. Add Product");
                        System.out.println("2. Remove Product");
                        System.out.println("3. View Products");
                        System.out.println("4. Create Admin");
                        System.out.println("5. View Admins");
                        System.out.println("6. Update Order Status");
                        System.out.println("7. View Orders");
                        System.out.println("8. Return");
                        System.out.print("Choose an option: ");
                        adminChoice = scanner.nextInt();

                        switch (adminChoice) {
                            case 1:
                                addProduct(scanner);
                                break;
                            case 2:
                                removeProduct(scanner);
                                break;
                            case 3:
                                viewProducts();
                                break;
                            case 4:
                                createAdmin(scanner);
                                break;
                            case 5:
                                viewAdmins();
                                break;
                            case 6:
                                updateOrderStatus(scanner);
                                break;
                            case 7:
                                viewOrders();
                                break;
                            case 8:
                                System.out.println("Exiting Admin...");
                                break;
                            default:
                                System.out.println("Invalid choice! Please try again.");
                        }
                    } while (adminChoice != 8);
                    break;

                case 2: // Customer Module
                    int customerChoice;
                    do {
                        System.out.println("\nCustomer Menu:");
                        System.out.println("1. Create Customer");
                        System.out.println("2. View Customers");
                        System.out.println("3. Place Order");
                        System.out.println("4. View Orders");
                        System.out.println("5. View Products");
                        System.out.println("6. Return");
                        System.out.print("Choose an option: ");
                        customerChoice = scanner.nextInt();

                        switch (customerChoice) {
                            case 1:
                                createCustomer(scanner);
                                break;
                            case 2:
                                viewCustomers();
                                break;
                            case 3:
                                placeOrder(scanner);
                                break;
                            case 4:
                                viewOrders();
                                break;
                            case 5:
                                viewProducts();
                                break;
                            case 6:
                                System.out.println("Exiting Customer Menu...");
                                break;
                            default:
                                System.out.println("Invalid choice! Please try again.");
                        }
                    } while (customerChoice != 6);
                    break;

                case 3:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    // ------------------ Admin Methods ------------------

    private static void addProduct(Scanner scanner) {
        System.out.print("Enter Product ID: ");
        int productId = scanner.nextInt();
        System.out.print("Enter Product Name: ");
        String name = scanner.next();
        System.out.print("Enter Product Price: ");
        double price = scanner.nextDouble();
        System.out.print("Enter Stock Quantity: ");
        int stockQuantity = scanner.nextInt();

        Product product = new Product(productId, name, price, stockQuantity);
        productService.addProduct(product);
        System.out.println("Product added successfully!");
    }

    private static void removeProduct(Scanner scanner) {
        System.out.print("Enter Product ID: ");
        int productId = scanner.nextInt();
        productService.removeProduct(productId);
        System.out.println("Product removed successfully!");
    }

    private static void viewProducts() {
        productService.viewProducts();
    }

    private static void createAdmin(Scanner scanner) {
        System.out.print("Enter Admin ID: ");
        int adminId = scanner.nextInt();
        System.out.print("Enter Admin Name: ");
        String name = scanner.next();
        Admin admin = new Admin(adminId, name, "customer@email.com");
        adminService.addAdmin(admin);
        System.out.println("Admin created successfully!");
    }

    private static void viewAdmins() {
        adminService.viewAdmins();
    }

    private static void updateOrderStatus(Scanner scanner) {
        System.out.print("Enter Order ID: ");
        int orderId = scanner.nextInt();
        System.out.print("Enter new Status: ");
        String status = scanner.next();
        orderService.updateOrderStatus(orderId, status);
        System.out.println("Order status updated successfully!");
    }

    private static void viewOrders() {
        orderService.viewOrders();
    }

    // ------------------ Customer Methods ------------------

    private static void createCustomer(Scanner scanner) {
        System.out.print("Enter Customer ID: ");
        int customerId = scanner.nextInt();
        System.out.print("Enter Customer Name: ");
        String name = scanner.next();
        System.out.print("Enter Customer email: ");
        String email = scanner.next();
        System.out.print("Enter Customer address: ");
        String address = scanner.next();
        Customer customer = new Customer(customerId, name, email, address);

        customerService.addCustomer(customer);
        System.out.println("Customer created successfully!");
    }

    private static void viewCustomers() {
        CustomerDAO.viewCustomers();
    }

    private static void placeOrder(Scanner scanner) {
        System.out.print("Enter Order ID: ");
        int orderId = scanner.nextInt();
        System.out.print("Enter Customer ID: ");
        int customerId = scanner.nextInt();
        System.out.print("Enter Product ID: ");
        int productId = scanner.nextInt();
        System.out.print("Enter Quantity: ");
        int quantity = scanner.nextInt();
     // Get actual Customer and Product objects first
        Customer customer = (Customer) customerService.getCustomer(customerId);
        Product product = productService.getProductById(productId);

        // Now pass them into the Order constructor
        Order order = new Order(orderId, customer, product, quantity, "Placed");


        
        orderService.placeOrder(order);
        System.out.println("Order placed successfully!");
    }
}
