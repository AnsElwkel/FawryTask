import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class Invoice {
    private String customerName;
    private Date invoiceDate;
    private List<ItemSummary> items;
    private double subtotal;
    private double totalWeight;
    private double shippingFees;

    public Invoice(String customerName) {
        this.customerName = customerName;
        this.invoiceDate = new Date();
        this.items = new ArrayList<>();
        this.subtotal = 0;
        this.totalWeight = 0;
        this.shippingFees = 0;
    }

    public void addItem(String product, int quantity, double itemPrice, double itemWeight) {
        items.add(new ItemSummary(product, quantity, itemPrice, itemWeight));
        subtotal += itemPrice ;
        totalWeight += itemWeight;
    }

    public void setShippingFees(double shippingFees) {
        this.shippingFees = shippingFees;
    }

    public String getCustomerName() { return customerName; }
    public Date getInvoiceDate() { return invoiceDate; }
    public List<ItemSummary> getItems() { return items; }
    public double getSubtotal() { return subtotal; }
    public double getTotalWeight() { return totalWeight; }
    public double getShippingFees() { return shippingFees; }


    public static class ItemSummary {
        private String productName;
        private int quantity;
        private double itemPrice;
        private double itemWeight;

        public ItemSummary(String productName, int quantity, double itemPrice, double itemWeight) {
            this.productName = productName;
            this.quantity = quantity;
            this.itemPrice = itemPrice;
            this.itemWeight = itemWeight;
        }

        public String getProductName() { return productName; }
        public int getQuantity() { return quantity; }
        public double getItemPrice() { return itemPrice; }
        public double getItemWeight() { return itemWeight; }
    }
}
