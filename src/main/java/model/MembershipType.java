package model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "membership_type")
public class MembershipType implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "membership_type_id", nullable = false)
    private UUID membershipTypeId;

    @Column(name = "type_name", nullable = false, unique = true)
    @Enumerated(EnumType.STRING)
    private String typeName;

    @Column(name = "max_books", nullable = false)
    private int maxBooks;

    @Column(name = "price_per_day", nullable = false)
    private int pricePerDay;
    
    @ManyToOne
    @JoinColumn(name = "person_id")
    private User user; // Many-to-One relationship with User

    // Getters and Setters
    public UUID getMembershipTypeId() {
        return membershipTypeId;
    }

    public void setMembershipTypeId(UUID membershipTypeId) {
        this.membershipTypeId = membershipTypeId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String type) {
        this.typeName = type;
    }

    public int getMaxBooks() {
        return maxBooks;
    }

    public void setMaxBooks(int maxBooks) {
        this.maxBooks = maxBooks;
    }

    public int getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(int pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

	public void setDescription(String description) {
		// TODO Auto-generated method stub
		
	}

	public void setFee(double fee) {
		// TODO Auto-generated method stub
		
	}

	public void setDuration(int duration) {
		// TODO Auto-generated method stub
		
	}
}
