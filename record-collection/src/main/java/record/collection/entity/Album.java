package record.collection.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
public class Album {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long albumId;
	@EqualsAndHashCode.Exclude
	private String albumTitle;
	@EqualsAndHashCode.Exclude
	private int yearReleased;

	@ManyToOne
	@JoinColumn(name = "artist_id")
	private Artist artist;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "contributor_id", nullable = false)
	private Contributor contributor;

	@ManyToMany
	@JoinTable(name = "genre_ablum", joinColumns = @JoinColumn(name = "album_id"), inverseJoinColumns = @JoinColumn(name = "genre_id"))
	private Set<Genre> genres = new HashSet<>();
	
}