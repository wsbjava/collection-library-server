package pl.wsb.collection.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the genre_collection_type database table.
 * 
 */
@Entity
@Table(name="genre_collection_type")
@NamedQuery(name="GenreCollectionType.findAll", query="SELECT g FROM GenreCollectionType g")
public class GenreCollectionType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	private Timestamp created;

	@Column(nullable=false)
	private Timestamp modified;

	//bi-directional many-to-one association to CollectionType
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="collection_type_id", nullable=false)
	private CollectionType collectionType;

	//bi-directional many-to-one association to Genre
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="genre_id", nullable=false)
	private Genre genre;

	public GenreCollectionType() {
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

	public CollectionType getCollectionType() {
		return this.collectionType;
	}

	public void setCollectionType(CollectionType collectionType) {
		this.collectionType = collectionType;
	}

	public Genre getGenre() {
		return this.genre;
	}

	public void setGenre(Genre genre) {
		this.genre = genre;
	}

}