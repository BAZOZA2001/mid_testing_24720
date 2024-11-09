package model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.UUID;
import java.util.List;

@Entity
@Table(name = "shelf")
public class Shelf implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "shelf_id", nullable = false)
    private UUID shelfId;

    @Column(name = "available_stock", nullable = false)
    private int availableStock;

    @Column(name = "initial_stock", nullable = false)
    private int initialStock;

    @Column(name = "book_category", nullable = false)
    private String bookCategory;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private room room;

    @OneToMany(mappedBy = "shelf")
    private List<Book> books;

	public UUID getShelfId() {
		return shelfId;
	}

	public void setShelfId(UUID shelfId) {
		this.shelfId = shelfId;
	}

	public int getAvailableStock() {
		return availableStock;
	}

	public void setAvailableStock(int availableStock) {
		this.availableStock = availableStock;
	}

	public int getInitialStock() {
		return initialStock;
	}

	public void setInitialStock(int initialStock) {
		this.initialStock = initialStock;
	}

	public String getBookCategory() {
		return bookCategory;
	}

	public void setBookCategory(String bookCategory) {
		this.bookCategory = bookCategory;
	}

	public room getRoom() {
		return room;
	}

	public void setRoom(room room) {
		this.room = room;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getShelfName() {
		// TODO Auto-generated method stub
		return null;
	}

    
}
