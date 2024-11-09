package dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import hibernate.Hibernatecfg;
import model.Borrower;
import model.MembershipType;
import model.User;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

public class UserDAO {

    // Helper method to hash password using SHA-256
    public String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hashedBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not found", e);
        }
    }

    // Save a new user with hashed password
    public void save(User personUser) {
        try (Session session = Hibernatecfg.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            personUser.setPassword(hashPassword(personUser.getPassword()));
            session.persist(personUser);
            transaction.commit();
            System.out.println("User saved successfully: " + personUser.getUserName());
        } catch (Exception e) {
            System.err.println("Failed to save user: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Authenticate user by username and password
    public User authenticateUser(String username, String password) {
        try (Session session = Hibernatecfg.getSessionFactory().openSession()) {
            String hashedPassword = hashPassword(password); // Hash the input password
            return session.createQuery("FROM User WHERE userName = :username AND password = :password", User.class)
                    .setParameter("username", username)
                    .setParameter("password", hashedPassword)
                    .uniqueResult();
        } catch (Exception e) {
            System.err.println("Authentication failed for user: " + username);
            e.printStackTrace();
            return null;
        }
    }

    // Other CRUD methods...

    // Placeholder for retrieving borrowings for a user
    public List<Borrower> getBorrowings(User user) {
        // TODO: Implement functionality
        return null;
    }

    // Retrieve all users
    public List<User> getAllUsers() {
        try (Session session = Hibernatecfg.getSessionFactory().openSession()) {
            return session.createQuery("FROM User", User.class).list();
        } catch (Exception e) {
            System.err.println("Failed to retrieve users: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    // Placeholder for retrieving memberships for a user
    public List<MembershipType> getMemberships(User user) {
        // TODO: Implement functionality
        return null;
    }

    // Placeholder for deleting a membership by ID
    public void deleteMembership(Long membershipId) {
        // TODO: Implement functionality
    }

    // Placeholder for adding a membership
    public void addMembership(MembershipType membership) {
        // TODO: Implement functionality
    }

    public User getUserById(UUID uuid) {
        // TODO Auto-generated method stub
        return null;
    }

    public User getUserbyAuth(String string) {
        // TODO Auto-generated method stub
        return null;
    }

    public void update(User user) {
        // TODO Auto-generated method stub
    }

    public void delete(UUID fromString) {
        // TODO Auto-generated method stub
    }

    public User getUserById(User newUser) {
        // TODO Auto-generated method stub
        return null;
    }

    public MembershipType getMemberships(Long membershipId) {
        // TODO Auto-generated method stub
        return null;
    }

    public void updateMembership1(MembershipType membership) {
        // TODO Auto-generated method stub
    }

    public void deleteBorrowing(Long borrowingId) {
        // TODO Auto-generated method stub
    }

    public User getUserById(String string) {
        // TODO Auto-generated method stub
        return null;
    }

    public void addBorrowing(Borrower borrower) {
        // TODO Auto-generated method stub
    }

    public Borrower getBorrowingById(Long borrowingId) {
        try (Session session = Hibernatecfg.getSessionFactory().openSession()) {
            return session.get(Borrower.class, borrowingId);
        } catch (Exception e) {
            System.err.println("Failed to retrieve borrowing: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public void updateBorrowing(Borrower borrower) {
        try (Session session = Hibernatecfg.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(borrower);
            transaction.commit();
            System.out.println("Borrowing updated successfully.");
        } catch (Exception e) {
            System.err.println("Failed to update borrowing: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // Retrieve a membership by ID
    public MembershipType getMembershipById(Long membershipId) {
        try (Session session = Hibernatecfg.getSessionFactory().openSession()) {
            return session.get(MembershipType.class, membershipId);
        } catch (Exception e) {
            System.err.println("Failed to retrieve membership: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    // Update an existing membership
    public void updateMembership(MembershipType membership) {
        try (Session session = Hibernatecfg.getSessionFactory().openSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(membership);
            transaction.commit();
            System.out.println("Membership updated successfully.");
        } catch (Exception e) {
            System.err.println("Failed to update membership: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
