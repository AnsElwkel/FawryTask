import java.util.Date;

public class Biscuits extends ShippableProduct implements IExpirable {
    private Date expiryDate;

    public Biscuits(String name, double price, int quantity, double weight, Date expiryDate) {
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
