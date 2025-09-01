// Program to define ProductService = add, remove, retrieve products
package service;

import entities.Product;
import java.util.ArrayList;
import java.util.List;

public class ProductService {
    private List<Product> productList = new ArrayList<>();

    public void addProduct(Product product) {
        driver.ProductDAO.addProduct(product.getProductId(), product.getName(), product.getPrice(), product.getStockQuantity());
        productList.add(product);
    }

    public void removeProduct(int productId) {
        driver.ProductDAO.removeProduct(productId);
        productList.removeIf(product -> product.getProductId() == productId);
    }

    public List<Product> getProducts() {
        return productList;
    }

    public Product getProductById(int productId) {
        return productList.stream()
                .filter(product -> product.getProductId() == productId)
                .findFirst()
                .orElse(null);
    }
    public void viewProducts() {
        productList=null;
        productList=driver.ProductDAO.viewProducts();
        if (productList == null || productList.isEmpty()) {
            System.out.println("No products available.");
        } else {
            System.out.println("\nAvailable Products:");
            for (Product product : productList) {
                System.out.println(product);
            }
        }
    }

}
