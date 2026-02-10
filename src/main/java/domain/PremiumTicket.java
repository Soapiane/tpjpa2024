package domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("PREMIUM")
public class PremiumTicket extends Ticket {

    private String seatNumber;

    public PremiumTicket() {}

    public PremiumTicket(double price, String seatNumber, User user) {
        super(price, user);
        this.seatNumber = seatNumber;
    }

    @Override
    public String toString() {
        return "PremiumTicket{id=" + getId() +
                ", price=" + price +
                ", seat=" + seatNumber + "}";
    }
}
