import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class Customer {
    private static final double INIT_BALANCE = 20000.0; ///Assumption
    private String username , email , password , phoneNumber;
    private int age;
    private double balance;
    private HashMap<Product , Integer> cart;

    public Customer(String username, String email, String password, String phoneNumber ,  int age) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.balance = INIT_BALANCE;
        this.cart = new HashMap<>();
    }

    public void addProduct(Product product ,  int quantity) {
        if(quantity > product.getQuantity()) {
            System.out.println("Product quantity not enough");
            return ;
        }

        cart.put(product, quantity);
    }

    public void checkout() {
        double amount = CheckoutAdministrator.checkout(this);
        balance -= amount;
        System.out.println("Current Balance is " + balance);
    }

    public HashMap<Product, Integer> getCart() {
        return cart;
    }

    public String getUsername() {
        return username;
    }

    public double getBalance() {
        return balance;
    }
}
