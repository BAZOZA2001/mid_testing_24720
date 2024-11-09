package model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "room")
public class room implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "room_id", nullable = false)
    private UUID roomId;

    @Column(name = "room_code", nullable = false)
    private String roomCode;

    @Column(name = "description")
    private String description;

    @OneToMany(mappedBy = "room")
    private List<Shelf> shelves;

	public UUID getRoomId() {
		return roomId;
	}

	public void setRoomId(UUID roomId) {
		this.roomId = roomId;
	}

	public String getRoomCode() {
		return roomCode;
	}

	public void setRoomCode(String roomCode) {
		this.roomCode = roomCode;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Shelf> getShelves() {
		return shelves;
	}

	public void setShelves(List<Shelf> shelves) {
		this.shelves = shelves;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getRoomName() {
		// TODO Auto-generated method stub
		return null;
	}

    
}
