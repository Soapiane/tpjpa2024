package domain;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Ticket> tickets = new ArrayList<>();

    public User() {}

    public User(String name) {
        this.name = name;
    }

    public List<Ticket> getTickets() {
        return tickets;
    }

    @Override
    public String toString() {
        return "User{id=" + id + ", name='" + name + "'}";
    }
}
