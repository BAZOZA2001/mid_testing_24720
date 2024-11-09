package dao;

import model.User; // Ensure the class name is 'User'
import org.hibernate.Session;
import org.hibernate.query.Query;
import hibernate.Hibernatecfg;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class LoginDAO {

    // Method to verify login credentials
    public User authenticate(String username, String password) {
        Session session = Hibernatecfg.getSessionFactory().openSession();
        User personUser = null;

        try {
            // Hash the input password
            String hashedPassword = hashPassword(password);

            // Query to authenticate the user
            String hql = "FROM User WHERE username = :username AND password = :password"; // Ensure your table is 'User'
            Query<User> query = session.createQuery(hql, User.class);
            query.setParameter("username", username);
            query.setParameter("password", hashedPassword); // Use the hashed password

            // Get a unique result
            personUser = query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception for debugging
        } finally {
            session.close(); // Ensure session is closed
        }

        return personUser; // This will be null if authentication fails
    }

    // Helper method to hash passwords
    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();

            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString(); // Return the hashed password
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e); // Handle the exception appropriately
        }
    }
}
