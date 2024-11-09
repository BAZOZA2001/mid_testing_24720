package dao;

import model.Shelf;
import org.hibernate.Session;
import org.hibernate.Transaction;
import hibernate.Hibernatecfg;

import java.util.List;

public class ShelfDAO {

    // Save a new shelf
    public void save(Shelf shelf) {
        Transaction transaction = null;
        try (Session session = Hibernatecfg.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(shelf);
            transaction.commit();
            System.out.println("Shelf saved successfully: " + shelf.getShelfName()); // Assuming shelf has a getShelfName() method
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            System.err.println("Failed to save shelf: " + e.getMessage());
            e.printStackTrace(); // Consider using a logging framework
        }
    }

    // Find a shelf by ID
    @SuppressWarnings("deprecation")
	public Shelf findById(Long shelfId) {
        try (Session session = Hibernatecfg.getSessionFactory().openSession()) {
            return session.get(Shelf.class, shelfId);
        } catch (Exception e) {
            System.err.println("Failed to find shelf: " + e.getMessage());
            e.printStackTrace(); // Consider using a logging framework
            return null;
        }
    }

    // Get all shelves
    public List<Shelf> findAll() {
        try (Session session = Hibernatecfg.getSessionFactory().openSession()) {
            return session.createQuery("FROM Shelf", Shelf.class).list(); // Changed to use generics
        } catch (Exception e) {
            System.err.println("Failed to retrieve shelves: " + e.getMessage());
            e.printStackTrace(); // Consider using a logging framework
            return null;
        }
    }

    // Update an existing shelf
    public void update(Shelf shelf) {
        Transaction transaction = null;
        try (Session session = Hibernatecfg.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(shelf);
            transaction.commit();
            System.out.println("Shelf updated successfully: " + shelf.getShelfName()); // Assuming shelf has a getShelfName() method
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            System.err.println("Failed to update shelf: " + e.getMessage());
            e.printStackTrace(); // Consider using a logging framework
        }
    }

    // Delete a shelf by ID
    public void delete(Long shelfId) {
        Transaction transaction = null;
        try (Session session = Hibernatecfg.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            @SuppressWarnings("deprecation")
			Shelf shelf = session.get(Shelf.class, shelfId);
            if (shelf != null) {
                session.delete(shelf);
                System.out.println("Shelf deleted successfully: " + shelf.getShelfName()); // Assuming shelf has a getShelfName() method
            } else {
                System.out.println("Shelf not found for deletion: ID = " + shelfId);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            System.err.println("Failed to delete shelf: " + e.getMessage());
            e.printStackTrace(); // Consider using a logging framework
        }
    }
}
