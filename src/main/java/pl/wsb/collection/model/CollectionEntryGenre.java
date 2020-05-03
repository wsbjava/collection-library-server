package pl.wsb.collection.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;


/**
 * The persistent class for the collection_entry_genre database table.
 * 
 */
@Entity
@Table(name="collection_entry_genre", catalog = "collection_management", uniqueConstraints = @UniqueConstraint(columnNames = {"collection_entry_id","genre_id"}))
public class CollectionEntryGenre implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date created;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date modified;

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
	public CollectionEntryGenre(CollectionEntry collectionEntry, Genre genre, Date modified)
	{
		this.collectionEntry = collectionEntry;
		this.genre = genre;
		this.modified = modified;
	}

	public CollectionEntryGenre(CollectionEntry collectionEntry, Genre genre, Date created, Date modified)
	{
		this.collectionEntry = collectionEntry;
		this.genre = genre;
		this.created = created;
		this.modified = modified;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getModified() {
		return this.modified;
	}

	public void setModified(Date modified) {
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