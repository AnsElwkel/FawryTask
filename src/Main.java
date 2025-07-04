import java.util.Calendar;
import java.util.Date;

/* Product Design discussion:
    -I was tried to make shipping and expiring properties as interfaces
     but they should have fields (like expiry data and weight ..),
     and shouldn't make both of them abstract classes (cause diamond problem in C++ or java/C# prevent the multiple inheritance
     and to enhance the reusability => finally, I went to make the shipping property as abstract class which is more common and expiring as interface
* */

/*Notes:
My assumption of balance for each customer is 20000 egp
default unit of weight by grams

-I was tried to handle some cases with Single Responsibility Principle
* */

public class Main {
    public static void main(String[] args) {
        test();
    }

    public static void test(){
        /// Some Product Samples
        Calendar cal = Calendar.getInstance();
        TV samsungTV = new TV("Samsung 55", 1300, 25, 4000);
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
        cust.addProduct(samsungTV, 20);
        cust.checkout(); //Insufficient balance

        cust.addProduct(samsungTV, 1);
        cust.addProduct(ulker, 2);
        cust.checkout();

    }

    public static void testShoppingSystem() {
        System.out.println("\n===== Testing Shopping System =====\n");



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