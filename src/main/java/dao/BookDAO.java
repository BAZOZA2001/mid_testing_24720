package dao;

import hibernate.Hibernatecfg;
import model.Book; 
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;
import java.util.UUID;

public class BookDAO {
    public void save(Book book) {
        Transaction transaction = null;
        try (Session session = Hibernatecfg.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(book);
            transaction.commit();
            System.out.println("Book saved successfully.");
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Failed to save book: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void update(Book book) {
        Transaction transaction = null;
        try (Session session = Hibernatecfg.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.update(book);
            transaction.commit();
            System.out.println("Book updated successfully.");
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Failed to update book: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void delete(UUID bookId) {
        Transaction transaction = null;
        try (Session session = Hibernatecfg.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            @SuppressWarnings("deprecation")
			Book book = session.get(Book.class, bookId);
            if (book != null) {
                session.delete(book);
                System.out.println("Book deleted successfully.");
            } else {
                System.err.println("Book not found.");
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Failed to delete book: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @SuppressWarnings("deprecation")
	public Book findById(UUID bookId) {
        try (Session session = Hibernatecfg.getSessionFactory().openSession()) {
            return session.get(Book.class, bookId);
        } catch (Exception e) {
            System.err.println("Failed to retrieve book: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    public static List<Book> findAll() {
        try (Session session = Hibernatecfg.getSessionFactory().openSession()) {
            return session.createQuery("FROM Book", Book.class).list();
        } catch (Exception e) {
            System.err.println("Failed to retrieve books: " + e.getMessage());
            return null;
        }
    }

    public Book findByIsbnCode(String isbnCode) {
        Transaction transaction = null;
        try (Session session = Hibernatecfg.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Query<Book> query = session.createQuery("FROM Book WHERE isbnCode = :isbnCode", Book.class);
            query.setParameter("isbnCode", isbnCode);
            Book book = query.uniqueResult();
            transaction.commit();
            return book;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            System.err.println("Failed to find book by ISBN code: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
