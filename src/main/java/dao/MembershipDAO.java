package dao;

import model.membership; // Change class name to Membership
import model.User; // Change class name to User
import model.MembershipType;
import org.hibernate.Session;
import org.hibernate.Transaction;
import hibernate.Hibernatecfg;

import java.util.List;

public class MembershipDAO {

    // Save a new membership
    public void save(membership membership) {
        Transaction transaction = null;
        try (Session session = Hibernatecfg.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(membership);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace(); // Consider using a logger
        }
    }

    // Find user by ID
    @SuppressWarnings("deprecation")
	public User findUserById(Long userId) {
        try (Session session = Hibernatecfg.getSessionFactory().openSession()) {
            return session.get(User.class, userId); // Fetch user by ID
        } catch (Exception e) {
            e.printStackTrace(); // Consider using a logger
            return null;
        }
    }

    // Find membership type by name
    public MembershipType findMembershipTypeByName(String membershipTypeName) {
        try (Session session = Hibernatecfg.getSessionFactory().openSession()) {
            return session.createQuery("FROM MembershipType WHERE typeName = :typeName", MembershipType.class)
                    .setParameter("typeName", membershipTypeName)
                    .uniqueResult();
        } catch (Exception e) {
            e.printStackTrace(); // Consider using a logger
            return null; // Handle not found scenario
        }
    }

    // Update an existing membership
    public void update(membership membership) {
        Transaction transaction = null;
        try (Session session = Hibernatecfg.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(membership);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace(); // Consider using a logger
        }
    }

    // Find membership by ID
    @SuppressWarnings("deprecation")
	public membership findById(Long id) {
        try (Session session = Hibernatecfg.getSessionFactory().openSession()) {
            return session.get(membership.class, id);
        } catch (Exception e) {
            e.printStackTrace(); // Consider using a logger
            return null;
        }
    }

    // Delete a membership by ID
    public void delete(Long id) {
        Transaction transaction = null;
        try (Session session = Hibernatecfg.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            @SuppressWarnings("deprecation")
			membership membership = session.get(membership.class, id);
            if (membership != null) {
                session.delete(membership);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace(); // Consider using a logger
        }
    }

    // Get all memberships
    public List<membership> findAll() {
        try (Session session = Hibernatecfg.getSessionFactory().openSession()) {
            return session.createQuery("FROM Membership", membership.class).list();
        } catch (Exception e) {
            e.printStackTrace(); // Consider using a logger
            return null;
        }
    }
}
