public class InvoiceReporter {
    public static void showInvoiceSummary(Invoice invoice) {
        System.out.println("==================== INVOICE ====================");

        System.out.println("Customer: " + invoice.getCustomerName());
        System.out.println("Date: " + invoice.getInvoiceDate());

        System.out.println("================ Shipment Notice =================");
        for(var item : invoice.getItems())
            System.out.println(item.getQuantity()+"x " + item.getProductName() + "\t\t" +  item.getItemWeight() + "g");
        System.out.println("Total Package Weight " + invoice.getTotalWeight()/1000 + "kg" );

        System.out.print("\n");

        System.out.println("================ Checkout Receipt ================");
        for(var item : invoice.getItems())
            System.out.println(item.getQuantity()+"x " + item.getProductName() + "\t\t" +  item.getItemPrice());
        System.out.println("--------------------------------------------------");
        System.out.println("Subtotal\t\t" + invoice.getSubtotal());
        System.out.println("Shipping\t\t" + invoice.getShippingFees());
        double amount = invoice.getSubtotal() + invoice.getShippingFees();
        System.out.println("Amount\t\t" + amount);
    }
}
