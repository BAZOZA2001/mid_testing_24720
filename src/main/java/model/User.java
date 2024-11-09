package model;

import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "app_user")
public class User extends person {
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "user_name", nullable = false, unique = true)
    private String userName;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    @ManyToOne
    @JoinColumn(name = "village_id", referencedColumnName = "location_id")
    private location location;

    @OneToMany(mappedBy = "reader")
    private List<Borrower> borrowings;

    @OneToMany(mappedBy = "user")
    private List<MembershipType> memberships;

    // Constructors, Getters, and Setters
    public User() {
        super();
    }

    public User(String firstName, String lastName, Gender gender, String phoneNumber, String userName, String password, Role role) {
        super();
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public location getLocation() {
        return location;
    }

    public void setLocation(location location) {
        this.location = location;
    }

    public List<Borrower> getBorrowings() {
        return borrowings;
    }

    public void setBorrowings(List<Borrower> borrowings) {
        this.borrowings = borrowings;
    }

    public List<MembershipType> getMemberships() {
        return memberships;
    }

    public void setMemberships(List<MembershipType> memberships) {
        this.memberships = memberships;
    }

	public String getUserId() {
		// TODO Auto-generated method stub
		return null;
	}
}
