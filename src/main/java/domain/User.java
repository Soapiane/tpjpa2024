package domain;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    // 🔹 Constructeur vide OBLIGATOIRE pour JPA
    public User() {
    }

    // 🔹 Constructeur pratique
    public User(String name) {
        this.name = name;
    }

    // 🔹 Getters / Setters
    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
