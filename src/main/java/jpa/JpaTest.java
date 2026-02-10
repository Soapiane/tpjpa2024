package jpa;

import domain.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class JpaTest {

    private EntityManager manager;

    public JpaTest(EntityManager manager) {
        this.manager = manager;
    }

    public static void main(String[] args) {

        EntityManager manager = EntityManagerHelper.getEntityManager();
        JpaTest test = new JpaTest(manager);

        EntityTransaction tx = manager.getTransaction();

        try {
            tx.begin();

            test.createUsersAndTickets();

            tx.commit();

            test.listAllTickets();
            test.namedQueryExample();

        } catch (Exception e) {
            e.printStackTrace();
            if (tx.isActive()) {
                tx.rollback();
            }
        } finally {
            EntityManagerHelper.closeEntityManager();
            EntityManagerHelper.closeEntityManagerFactory();
        }

        System.out.println(".. done");
    }

    /**
     * Création des utilisateurs et des tickets (héritage)
     */
    private void createUsersAndTickets() {

        Long nbUsers = manager.createQuery(
                        "SELECT COUNT(u) FROM User u", Long.class)
                .getSingleResult();

        if (nbUsers == 0) {

            User u1 = new User("Alice");
            User u2 = new User("Bob");

            Ticket t1 = new PremiumTicket(120.0, "A12", u1);
            Ticket t2 = new LastMinuteTicket(50.0, u1);
            Ticket t3 = new PremiumTicket(200.0, "VIP-1", u2);

            u1.getTickets().add(t1);
            u1.getTickets().add(t2);
            u2.getTickets().add(t3);

            manager.persist(u1);
            manager.persist(u2);
        }
    }

    /**
     * Requête JPQL classique
     */
    private void listAllTickets() {

        List<Ticket> tickets =
                manager.createQuery(
                                "SELECT t FROM Ticket t",
                                Ticket.class)
                        .getResultList();

        System.out.println("\n=== LISTE DES TICKETS ===");
        for (Ticket t : tickets) {
            System.out.println(t + " | User: " + t.getUser());
        }
    }

    /**
     * Requête nommée
     */
    private void namedQueryExample() {

        List<Ticket> tickets =
                manager.createNamedQuery(
                                "Ticket.findByPriceGreaterThan",
                                Ticket.class)
                        .setParameter("price", 100.0)
                        .getResultList();

        System.out.println("\n=== TICKETS AVEC PRIX > 100 ===");
        for (Ticket t : tickets) {
            System.out.println(t);
        }
    }
}
