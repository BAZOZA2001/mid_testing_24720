package model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "book")
public class Book implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "book_id", nullable = false)
    private UUID bookId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "edition")
    private int edition;

    @Column(name = "isbn_code", nullable = false, unique = true)
    private String isbnCode;

    @Column(name = "publisher_name", nullable = false)
    private String publisherName;

    @Column(name = "publication_year")
    private java.sql.Date publicationYear;

    @Enumerated(EnumType.STRING)
    @Column(name = "book_status", nullable = false)
    private BookStatus bookStatus;

    @ManyToOne
    @JoinColumn(name = "shelf_id")
    private Shelf shelf;

	public UUID getBookId() {
		return bookId;
	}

	public void setBookId(UUID bookId) {
		this.bookId = bookId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getEdition() {
		return edition;
	}

	public void setEdition(int edition) {
		this.edition = edition;
	}

	public String getIsbnCode() {
		return isbnCode;
	}

	public void setIsbnCode(String isbnCode) {
		this.isbnCode = isbnCode;
	}

	public String getPublisherName() {
		return publisherName;
	}

	public void setPublisherName(String publisherName) {
		this.publisherName = publisherName;
	}

	public java.sql.Date getPublicationYear() {
		return publicationYear;
	}

	public void setPublicationYear(java.sql.Date publicationYear) {
		this.publicationYear = publicationYear;
	}

	public BookStatus getBookStatus() {
		return bookStatus;
	}

	public void setBookStatus(BookStatus bookStatus) {
		this.bookStatus = bookStatus;
	}

	public Shelf getShelf() {
		return shelf;
	}

	public void setShelf(Shelf shelf) {
		this.shelf = shelf;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

    
}
