package dao;

import model.room;
import org.hibernate.Session;
import org.hibernate.Transaction;
import hibernate.Hibernatecfg;

import java.util.List;

public class RoomDAO {

    // Save a new room
    public void save(room room) {
        Transaction transaction = null;
        try (Session session = Hibernatecfg.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(room);
            transaction.commit();
            System.out.println("Room saved successfully: " + room.getRoomName());
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            System.err.println("Failed to save room: " + e.getMessage());
            e.printStackTrace(); // Consider using a logging framework
        }
    }

    // Update an existing room
    public void update(room room) {
        Transaction transaction = null;
        try (Session session = Hibernatecfg.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(room);
            transaction.commit();
            System.out.println("Room updated successfully: " + room.getRoomName());
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            System.err.println("Failed to update room: " + e.getMessage());
            e.printStackTrace(); // Consider using a logging framework
        }
    }

    // Delete a room by ID
    public void delete(Long roomId) {
        Transaction transaction = null;
        try (Session session = Hibernatecfg.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            @SuppressWarnings("deprecation")
			room room = session.get(room.class, roomId);
            if (room != null) {
                session.delete(room);
                transaction.commit(); // Commit after deletion
                System.out.println("Room deleted successfully: " + room.getRoomName());
            } else {
                transaction.rollback(); // Rollback if room not found
                System.out.println("Room not found for deletion: ID = " + roomId);
            }
        } catch (Exception e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            System.err.println("Failed to delete room: " + e.getMessage());
            e.printStackTrace(); // Consider using a logging framework
        }
    }

    // Find all rooms
    public List<room> findAll() {
        try (Session session = Hibernatecfg.getSessionFactory().openSession()) {
            return session.createQuery("FROM Room", room.class).list();
        } catch (Exception e) {
            System.err.println("Failed to retrieve rooms: " + e.getMessage());
            return null; // Optionally log the exception
        }
    }

    // Find room by ID
    @SuppressWarnings("deprecation")
	public room findById(Long roomId) {
        try (Session session = Hibernatecfg.getSessionFactory().openSession()) {
            return session.get(room.class, roomId);
        } catch (Exception e) {
            System.err.println("Failed to find room: " + e.getMessage());
            return null; // Optionally log the exception
        }
    }
}
