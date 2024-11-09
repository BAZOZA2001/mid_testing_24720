package dao;

import model.MembershipType;
import org.hibernate.Session;
import org.hibernate.Transaction;
import hibernate.Hibernatecfg;

import java.util.List;

public class MembershipTypeBookDAO {

    // Save a new MembershipTypeBook
    public void save(MembershipType membershipTypeBook) {
        Transaction transaction = null;
        try (Session session = Hibernatecfg.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(membershipTypeBook);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace(); // Consider using a logging framework
        }
    }

    // Update an existing MembershipTypeBook
    public void update(MembershipType membershipTypeBook) {
        Transaction transaction = null;
        try (Session session = Hibernatecfg.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(membershipTypeBook);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace(); // Consider using a logging framework
        }
    }

    // Delete a MembershipTypeBook by ID
    public void delete(Long id) {
        Transaction transaction = null;
        try (Session session = Hibernatecfg.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            @SuppressWarnings("deprecation")
			MembershipType membershipTypeBook = session.get(MembershipType.class, id);
            if (membershipTypeBook != null) {
                session.delete(membershipTypeBook);
                transaction.commit();
            } else {
                transaction.rollback(); // Rollback if not found
            }
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace(); // Consider using a logging framework
        }
    }

    // Find a MembershipTypeBook by ID
    @SuppressWarnings("deprecation")
	public MembershipType findById(Long id) {
        try (Session session = Hibernatecfg.getSessionFactory().openSession()) {
            return session.get(MembershipType.class, id); // Fetch by ID
        } catch (Exception e) {
            e.printStackTrace(); // Consider using a logging framework
            return null;
        }
    }

    // Retrieve all MembershipTypeBook records
    public List<MembershipType> findAll() {
        try (Session session = Hibernatecfg.getSessionFactory().openSession()) {
            return session.createQuery("FROM MembershipTypeBook", MembershipType.class).list(); // Use HQL
        } catch (Exception e) {
            e.printStackTrace(); // Consider using a logging framework
            return null;
        }
    }
}
