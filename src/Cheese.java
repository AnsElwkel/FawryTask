import java.util.Date;

public class Cheese extends ShippableProduct implements IExpirable {
    private Date expiryDate;

    public Cheese(String name, double price, int quantity, double weight, Date expiryDate) {
        super(name, price, quantity, weight);
        this.expiryDate = expiryDate;
    }

    @Override
    public Date getExpiryDate() {
        return expiryDate;
    }

    @Override
    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Override
    public boolean isExpired() {
        return new Date().after(expiryDate);
    }
}
