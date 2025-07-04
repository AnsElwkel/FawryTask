import java.util.Calendar;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        test();
    }

    public static void test(){
        /// Some Product Samples
        Calendar cal = Calendar.getInstance();
        TV samsungTV = new TV("Samsung 55", 1300, 10, 4000);
        Mobile iphone = new Mobile("iPhone 15 Pro", 100, 15, 300);

        cal.add(Calendar.MONTH, 6);
        Date biscuitsExpiry = cal.getTime();
        Biscuits ulker = new Biscuits("Ulker Cookies", 5, 50, 50, biscuitsExpiry);

        cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 30);
        Date cheeseExpiry = cal.getTime();
        Cheese domty = new Cheese("Domty Cheese", 20, 25, 0.4, cheeseExpiry);

        cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, 1);
        Date cardExpiry = cal.getTime();
        MobileScratchCards prepaidCard = new MobileScratchCards("$50 Prepaid Card", 50.0, 100, cardExpiry);


        Customer cust = new Customer("AnasElwkel", "anshesh039@gmail.com", "anas123", "+201117581803", 20);
        cust.checkout(); //empty cart

        cust.addProduct(samsungTV, 1);
        cust.addProduct(ulker, 2);
        cust.checkout();
    }

    public static void testShoppingSystem() {
        System.out.println("\n===== Testing Shopping System =====\n");


        // Test case 1: Empty cart checkout
//        System.out.println("\n----- Test Case 1: Empty Cart -----\n");
//        Customer customer1 = new Customer("john_doe", "john@example.com", "password123", "555-1234", 30);
//        // Try to checkout with empty cart
//        CheckoutAdministrator.checkout(customer1);

        // Test case 2: Expired items in cart
        System.out.println("\n----- Test Case 2: Expired Items -----\n");
        Customer customer2 = new Customer("jane_smith", "jane@example.com", "pass456", "555-5678", 25);
//        customer2.addProduct(samsungTV, 1);  // Valid product
//        customer2.addProduct(expiredBiscuits, 2);  // Expired product
        // Try checkout with expired items
        CheckoutAdministrator.checkout(customer2);

        // Test case 3: Insufficient balance
        System.out.println("\n----- Test Case 3: Insufficient Balance -----\n");
        Customer customer3 = new Customer("bob_johnson", "bob@example.com", "bob123", "555-9876", 40);
//        customer3.addProduct(iphone, 1);  // Expensive product
//        customer3.addProduct(validBiscuits, 3);
        // Customer has zero balance by default
        CheckoutAdministrator.checkout(customer3);

        // Test case 4: Successful checkout with both shippable and non-shippable items
        System.out.println("\n----- Test Case 4: Successful Mixed Items Checkout -----\n");
        Customer customer4 = new Customer("alice_wong", "alice@example.com", "alice456", "555-4321", 35);
//        customer4.addProduct(samsungTV, 1);  // Shippable
//        customer4.addProduct(validBiscuits, 2);  // Shippable + Expirable
//        customer4.addProduct(scratchCard, 1);  // Non-shippable + Expirable

        // Add sufficient balance
        // Access the balance field through reflection since we don't have a setter
        try {
            java.lang.reflect.Field balanceField = Customer.class.getDeclaredField("balance");
            balanceField.setAccessible(true);
            balanceField.set(customer4, 2000.0);
        } catch (Exception e) {
            System.out.println("Couldn't set balance: " + e.getMessage());
        }

        // Should succeed
        CheckoutAdministrator.checkout(customer4);

        System.out.println("\n===== Testing Complete =====\n");
    }
}