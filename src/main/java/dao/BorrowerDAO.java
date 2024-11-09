package dao;

import hibernate.Hibernatecfg;
import model.Borrower; // Make sure your model class is named 'Borrower'
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class BorrowerDAO {

    // Save a new borrower
    public void saveBorrower(Borrower borrower) {
        Transaction transaction = null;
        try (Session session = Hibernatecfg.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(borrower);
            transaction.commit();
            System.out.println("Borrower saved successfully.");
        } catch (HibernateException e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Hibernate exception occurred: " + e.getMessage());
            e.printStackTrace();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println("General exception occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Update an existing borrower
    public void update(Borrower borrower) {
        Transaction transaction = null;
        try (Session session = Hibernatecfg.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(borrower);
            transaction.commit();
            System.out.println("Borrower updated successfully.");
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Failed to update borrower: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Find a borrower by ID
    @SuppressWarnings("deprecation")
	public Borrower findById(Long id) {
        try (Session session = Hibernatecfg.getSessionFactory().openSession()) {
            return session.get(Borrower.class, id);
        } catch (Exception e) {
            System.err.println("Failed to retrieve borrower: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    // Find all borrowers
    public List<Borrower> findAll() {
        try (Session session = Hibernatecfg.getSessionFactory().openSession()) {
            return session.createQuery("FROM Borrower", Borrower.class).list();
        } catch (Exception e) {
            System.err.println("Failed to retrieve borrowers: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    // Delete a borrower by ID
    public void delete(Long id) {
        Transaction transaction = null;
        try (Session session = Hibernatecfg.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            @SuppressWarnings("deprecation")
			Borrower borrower = session.get(Borrower.class, id);
            if (borrower != null) {
                session.delete(borrower);
                transaction.commit();
                System.out.println("Borrower deleted successfully.");
            } else {
                System.err.println("Borrower not found.");
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Failed to delete borrower: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
