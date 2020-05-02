package pl.wsb.collection.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the collection_entry_genre database table.
 * 
 */
@Entity
@Table(name="collection_entry_genre")
@NamedQuery(name="CollectionEntryGenre.findAll", query="SELECT c FROM CollectionEntryGenre c")
public class CollectionEntryGenre implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	private Timestamp created;

	@Column(nullable=false)
	private Timestamp modified;

	//bi-directional many-to-one association to CollectionEntry
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="collection_entry_id", nullable=false)
	private CollectionEntry collectionEntry;

	//bi-directional many-to-one association to Genre
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="genre_id", nullable=false)
	private Genre genre;

	public CollectionEntryGenre() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getCreated() {
		return this.created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public Timestamp getModified() {
		return this.modified;
	}

	public void setModified(Timestamp modified) {
		this.modified = modified;
	}

	public CollectionEntry getCollectionEntry() {
		return this.collectionEntry;
	}

	public void setCollectionEntry(CollectionEntry collectionEntry) {
		this.collectionEntry = collectionEntry;
	}

	public Genre getGenre() {
		return this.genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

}