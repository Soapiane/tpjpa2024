package jpa;

import domain.Department;
import domain.Employee;
import jakarta.persistence.*;

import java.util.List;

public class JpaTest {

    private EntityManager manager;

    public JpaTest(EntityManager manager) {
        this.manager = manager;
    }

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("dev");
        EntityManager manager = factory.createEntityManager();
        JpaTest test = new JpaTest(manager);

        EntityTransaction tx = manager.getTransaction();
        tx.begin();
        try {
            test.createDepartmentsAndEmployees();
        } catch (Exception e) {
            e.printStackTrace();
        }
        tx.commit();

        test.listEmployees();
        test.listDepartments();

        manager.close();
        System.out.println(".. done");
    }

    private void createDepartmentsAndEmployees() {
        int numOfDepartments = manager.createQuery("Select d From Department d", Department.class).getResultList().size();
        if (numOfDepartments == 0) {
            Department javaDept = new Department("Java");
            Department dbDept = new Department("Databases");

            Employee emp1 = new Employee("Jakab Gipsz", javaDept);
            Employee emp2 = new Employee("Captain Nemo", javaDept);
            Employee emp3 = new Employee("Alice", dbDept);
            Employee emp4 = new Employee("Bob", dbDept);

            javaDept.getEmployees().add(emp1);
            javaDept.getEmployees().add(emp2);
            dbDept.getEmployees().add(emp3);
            dbDept.getEmployees().add(emp4);

            manager.persist(javaDept);
            manager.persist(dbDept);
            // Les employés seront persistés automatiquement grâce au cascade = PERSIST
        }
    }

    private void listEmployees() {
        List<Employee> employees = manager.createQuery("Select e From Employee e", Employee.class).getResultList();
        System.out.println("Num of employees: " + employees.size());
        for (Employee e : employees) {
            System.out.println(e);
        }
    }

    private void listDepartments() {
        List<Department> departments = manager.createQuery("Select d From Department d", Department.class).getResultList();
        System.out.println("Num of departments: " + departments.size());
        for (Department d : departments) {
            System.out.println(d);
            for (Employee e : d.getEmployees()) {
                System.out.println(" - " + e);
            }
        }
    }
}
