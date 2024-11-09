package model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "membership")
public class membership implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "membership_id", nullable = false)
    private UUID membershipId;

    @Column(name = "membership_code", nullable = false, unique = true)
    private String membershipCode;

    @ManyToOne
    @JoinColumn(name = "membership_type_id")
    private MembershipType membershipType;

    @ManyToOne
    @JoinColumn(name = "reader_id")
    private User reader;

    @Column(name = "expiring_time")
    private java.sql.Date expiringTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "membership_status")
    private Status membershipStatus;

    @Column(name = "registration_date", nullable = false)
    private java.sql.Date registrationDate;

	public UUID getMembershipId() {
		return membershipId;
	}

	public void setMembershipId(UUID membershipId) {
		this.membershipId = membershipId;
	}

	public String getMembershipCode() {
		return membershipCode;
	}

	public void setMembershipCode(String membershipCode) {
		this.membershipCode = membershipCode;
	}

	public MembershipType getMembershipType() {
		return membershipType;
	}

	public void setMembershipType(MembershipType membershipType) {
		this.membershipType = membershipType;
	}

	public User getReader() {
		return reader;
	}

	public void setReader(User reader) {
		this.reader = reader;
	}

	public java.sql.Date getExpiringTime() {
		return expiringTime;
	}

	public void setExpiringTime(java.sql.Date expiringTime) {
		this.expiringTime = expiringTime;
	}

	public Status getMembershipStatus() {
		return membershipStatus;
	}

	public void setMembershipStatus(Status membershipStatus) {
		this.membershipStatus = membershipStatus;
	}

	public java.sql.Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(java.sql.Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

    
}
