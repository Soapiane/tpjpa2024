package domain;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("LAST_MINUTE")
public class LastMinuteTicket extends Ticket {

    private boolean noSeat;

    public LastMinuteTicket() {}

    public LastMinuteTicket(double price, User user) {
        super(price, user);
        this.noSeat = true;
    }

    @Override
    public String toString() {
        return "LastMinuteTicket{id=" + getId() +
                ", price=" + price +
                ", noSeat=" + noSeat + "}";
    }
}
