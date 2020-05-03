package pl.wsb.collection.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the genre_collection_type database table.
 * 
 */
@Entity
@Table(name="genre_collection_type", catalog = "collection_management", uniqueConstraints = @UniqueConstraint(columnNames = {"genre_id", "collection_type_id"}))
public class GenreCollectionType implements Serializable {
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

	public GenreCollectionType(CollectionType collectionType, Genre genre, Date modified){
		this.collectionType = collectionType;
		this.genre = genre;
		this.modified = modified;
	}

	public GenreCollectionType(CollectionType collectionType, Genre genre, Date created, Date modified){
		this.collectionType = collectionType;
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