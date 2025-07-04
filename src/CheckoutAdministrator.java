import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckoutAdministrator {
    private static final double MIN_SHIPPING_FEE = 50.00;
    private static final double WEIGHT_RATE = 0.50; // per kg
    private static final double MAX_SHIPPING_FEE = 200.00;

    public static double checkout(Customer customer){
        double amount = calculateTotalAmount(customer.getCart());
        if (!validateEmptyCart(customer.getCart())
            || !validateExpiredItems(customer.getCart())
            || !validateCustomerBalance(customer.getBalance(), amount))
            return 0.0;

        Invoice invoice = createInvoice(customer.getUsername(),customer.getCart());

        InvoiceReporter.showInvoiceSummary(invoice);

        customer.getCart().clear();
        System.out.println("Checkout completed successfully!");
        return amount;
    }



    private static Invoice createInvoice(String customerUsername , HashMap<Product , Integer> cart) {
        Invoice invoice = new Invoice(customerUsername );

        for(Map.Entry<Product, Integer> item : cart.entrySet()) {
            Product product = item.getKey();
            int quantity = item.getValue();

            double itemPrice = product.getPrice() * quantity;
            double itemWeight = 0;

            if(product instanceof ShippableProduct) {
                itemWeight = ((ShippableProduct) product).getWeight() * quantity;
            }

            invoice.addItem(product.getName(), quantity, itemPrice, itemWeight);
        }

        // Calculate and set shipping fees
        double shippingFees = calculateShipping(invoice.getTotalWeight());
        invoice.setShippingFees(shippingFees);

        return invoice;
    }

    private static double calculateTotalAmount(HashMap<Product , Integer> cart) {
        double subtotal = 0;
        double totalWeight = 0;

        for (var item : cart.entrySet()) {
            Product product = item.getKey();
            int quantity = item.getValue();

            // Skip expired items in calculation
            if (product instanceof IExpirable && ((IExpirable) product).isExpired()) {
                continue;
            }

            subtotal += product.getPrice() * quantity;

            if (product instanceof ShippableProduct) {
                totalWeight += ((ShippableProduct) product).getWeight() * quantity;
            }
        }

        double shippingFees = calculateShipping(totalWeight);

        return subtotal + shippingFees;
    }

    ///The logic is that minimum shipping cost is 50 and increase it by total_weight * 0.5 with maximum shipping cost 200
    private static double calculateShipping(double totalWeight) {
        if (totalWeight == 0)
            return 0.0;

        double shippingCost = MIN_SHIPPING_FEE + (totalWeight * WEIGHT_RATE);

        return Math.min(shippingCost, MAX_SHIPPING_FEE);
    }

    private static double getTotalWeight(HashMap<Product , Integer> cart) {
        double totalWeight = 0;
        for (var entry : cart.entrySet()) {
            Product product = entry.getKey();
            int quantity = entry.getValue();

            if (product instanceof ShippableProduct) {
                totalWeight += ((ShippableProduct) product).getWeight() * quantity;
            }
        }
        return totalWeight;
    }



    /// Validation Function (another design choice => Checkout Validator class is responsible for validations)
    private static boolean validateEmptyCart(HashMap<Product , Integer> cart) {
        if (cart.isEmpty()) {
            System.out.println("Cart is Empty");
            return false;
        }
        return true;
    }

    private static boolean validateExpiredItems(HashMap<Product , Integer> cart) {
        ArrayList<String> expiredItems = new ArrayList<>();

        for (var item : cart.entrySet()) {
            Product product = item.getKey();

            if (product instanceof IExpirable
                && ((IExpirable) product).isExpired())
                expiredItems.add(product.getName());
        }

        if (!expiredItems.isEmpty()) {
            System.out.println("The following items have expired: " + String.join(", ", expiredItems));
            System.out.println("Please remove expired items from your cart before checkout.");
            return false;
        }

        return true;
    }

    private static boolean validateCustomerBalance(double customerBalance, double totalAmount) {
        if (customerBalance < totalAmount) {
            System.out.println("Insufficient balance. Required: " + totalAmount
                              + ", Available: " + customerBalance);
            return false;
        }
        return true;
    }
}