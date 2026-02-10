package domain;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "ticket_type")
@NamedQuery(
        name = "Ticket.findByPriceGreaterThan",
        query = "SELECT t FROM Ticket t WHERE t.price > :price"
)
public abstract class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    protected double price;

    @ManyToOne
    private User user;

    public Ticket() {}

    public Ticket(double price, User user) {
        this.price = price;
        this.user = user;
    }

    public Long getId() { return id; }
    public double getPrice() { return price; }
    public User getUser() { return user; }
}
