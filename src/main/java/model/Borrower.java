package model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "borrow")
public class Borrower implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "borrow_id", nullable = false)
    private String borrowId;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false)
    private String book;

    @ManyToOne
    @JoinColumn(name = "reader_id", nullable = false)
    private User reader;

    @Column(name = "issue_date", nullable = false)
    private Date issueDate;

    @Column(name = "pickup_date")
    private Date pickupDate;

    @Column(name = "return_date")
    private Date returnDate;

    @Column(name = "late_charge_fees", nullable = false)
    private int lateChargeFees = 0;

	public String getBorrowId() {
		return borrowId;
	}

	public void setBorrowId(String borrowingDate) {
		this.borrowId = borrowingDate;
	}

	public String getBook() {
		return book;
	}

	public void setBook(String bookTitle) {
		this.book = bookTitle;
	}

	public User getReader() {
		return reader;
	}

	public void setReader(User reader) {
		this.reader = reader;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public Date getPickupDate() {
		return pickupDate;
	}

	public void setPickupDate(Date pickupDate) {
		this.pickupDate = pickupDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public int getLateChargeFees() {
		return lateChargeFees;
	}

	public void setLateChargeFees(int lateChargeFees) {
		this.lateChargeFees = lateChargeFees;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	

    
}
