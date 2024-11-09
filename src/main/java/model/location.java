package model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "location")
public class location implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "location_id", nullable = false)
    private UUID locationId;

    @Column(name = "location_code", nullable = false)
    private String locationCode;

    @Column(name = "location_name", nullable = false)
    private String locationName;

    @Enumerated(EnumType.STRING)
    @Column(name = "location_type", nullable = false)
    private LocationType locationType;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private location parentLocation;

    @OneToMany(mappedBy = "parentLocation")
    private List<location> subLocations;

    @OneToMany(mappedBy = "location")
    private List<User> users;

	public UUID getLocationId() {
		return locationId;
	}

	public void setLocationId(UUID locationId) {
		this.locationId = locationId;
	}

	public String getLocationCode() {
		return locationCode;
	}

	public void setLocationCode(String locationCode) {
		this.locationCode = locationCode;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public LocationType getLocationType() {
		return locationType;
	}

	public void setLocationType(LocationType locationType) {
		this.locationType = locationType;
	}

	public location getParentLocation() {
		return parentLocation;
	}

	public void setParentLocation(location parentLocation) {
		this.parentLocation = parentLocation;
	}

	public List<location> getSubLocations() {
		return subLocations;
	}

	public void setSubLocations(List<location> subLocations) {
		this.subLocations = subLocations;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

    
}
