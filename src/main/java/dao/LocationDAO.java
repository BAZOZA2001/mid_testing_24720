package dao;

import hibernate.Hibernatecfg;
import model.location; // Ensure your model class is named 'Location'
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class LocationDAO {

    // Save a new location
    public void save(location location) {
        Transaction transaction = null;
        try (Session session = Hibernatecfg.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(location);
            transaction.commit();
            System.out.println("Location saved successfully.");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Failed to save location: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Update an existing location
    public void update(location location) {
        Transaction transaction = null;
        try (Session session = Hibernatecfg.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(location);
            transaction.commit();
            System.out.println("Location updated successfully.");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Failed to update location: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Find location by ID
    @SuppressWarnings("deprecation")
	public location findById(Long id) {
        try (Session session = Hibernatecfg.getSessionFactory().openSession()) {
            return session.get(location.class, id);
        } catch (Exception e) {
            System.err.println("Failed to retrieve location: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    // Delete a location by ID
    public void delete(Long id) {
        Transaction transaction = null;
        try (Session session = Hibernatecfg.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            @SuppressWarnings("deprecation")
			location location = session.get(location.class, id);
            if (location != null) {
                session.delete(location);
                transaction.commit();
                System.out.println("Location deleted successfully.");
            } else {
                System.err.println("Location not found with ID: " + id);
            }
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Failed to delete location: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Find all locations
    public List<location> findAll() {
        try (Session session = Hibernatecfg.getSessionFactory().openSession()) {
            return session.createQuery("FROM Location", location.class).list();
        } catch (Exception e) {
            System.err.println("Failed to retrieve locations: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    // Find a location by userId
    public location findByUserId(String userId) {
        try (Session session = Hibernatecfg.getSessionFactory().openSession()) {
            String hql = "FROM Location WHERE userId = :userId";
            return session.createQuery(hql, location.class)
                          .setParameter("userId", userId)
                          .uniqueResult();
        } catch (Exception e) {
            System.err.println("Failed to retrieve location by userId: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    // Find a location by locationCode
    public location findByLocationCode(String locationCode) {
        try (Session session = Hibernatecfg.getSessionFactory().openSession()) {
            return session.createQuery("FROM Location WHERE locationCode = :locationCode", location.class)
                    .setParameter("locationCode", locationCode)
                    .uniqueResult();
        } catch (Exception e) {
            System.err.println("Failed to retrieve location by locationCode: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
