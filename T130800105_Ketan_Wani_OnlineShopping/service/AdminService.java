// Program to define AdminService class
package service;

import java.util.ArrayList;
import java.util.List;
import entities.Admin;

public class AdminService {
    private List<Admin> adminList = new ArrayList<>();

    public void addAdmin(Admin admin) {
        adminList.add(admin);
    }

    public List<Admin> getAdmins() {
        return adminList;
    }

    // ✅ New view method
    public void viewAdmins() {
        if (adminList == null || adminList.isEmpty()) {
            System.out.println("No admins available.");
        } else {
            System.out.println("\nRegistered Admins:");
            for (Admin admin : adminList) {
                System.out.println(admin);
            }
        }
    }
}
