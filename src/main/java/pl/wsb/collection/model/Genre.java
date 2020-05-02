package pl.wsb.collection.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the genre database table.
 * 
 */
@Entity
@Table(name="genre")
@NamedQuery(name="Genre.findAll", query="SELECT g FROM Genre g")
public class Genre implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(nullable=false, length=20)
	private String abbr;

	private Timestamp created;

	private int deleted;

	@Column(nullable=false)
	private Timestamp modified;

	@Column(length=255)
	private String name;

	//bi-directional many-to-one association to CollectionEntryGenre
	@OneToMany(mappedBy="genre")
	private List<CollectionEntryGenre> collectionEntryGenres;

	//bi-directional many-to-one association to GenreCollectionType
	@OneToMany(mappedBy="genre")
	private List<GenreCollectionType> genreCollectionTypes;

	public Genre() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAbbr() {
		return this.abbr;
	}

	public void setAbbr(String abbr) {
		this.abbr = abbr;
	}

	public Timestamp getCreated() {
		return this.created;
	}

	public void setCreated(Timestamp created) {
		this.created = created;
	}

	public int getDeleted() {
		return this.deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	public Timestamp getModified() {
		return this.modified;
	}

	public void setModified(Timestamp modified) {
		this.modified = modified;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<CollectionEntryGenre> getCollectionEntryGenres() {
		return this.collectionEntryGenres;
	}

	public void setCollectionEntryGenres(List<CollectionEntryGenre> collectionEntryGenres) {
		this.collectionEntryGenres = collectionEntryGenres;
	}

	public CollectionEntryGenre addCollectionEntryGenre(CollectionEntryGenre collectionEntryGenre) {
		getCollectionEntryGenres().add(collectionEntryGenre);
		collectionEntryGenre.setGenre(this);

		return collectionEntryGenre;
	}

	public CollectionEntryGenre removeCollectionEntryGenre(CollectionEntryGenre collectionEntryGenre) {
		getCollectionEntryGenres().remove(collectionEntryGenre);
		collectionEntryGenre.setGenre(null);

		return collectionEntryGenre;
	}

	public List<GenreCollectionType> getGenreCollectionTypes() {
		return this.genreCollectionTypes;
	}

	public void setGenreCollectionTypes(List<GenreCollectionType> genreCollectionTypes) {
		this.genreCollectionTypes = genreCollectionTypes;
	}

	public GenreCollectionType addGenreCollectionType(GenreCollectionType genreCollectionType) {
		getGenreCollectionTypes().add(genreCollectionType);
		genreCollectionType.setGenre(this);

		return genreCollectionType;
	}

	public GenreCollectionType removeGenreCollectionType(GenreCollectionType genreCollectionType) {
		getGenreCollectionTypes().remove(genreCollectionType);
		genreCollectionType.setGenre(null);

		return genreCollectionType;
	}

}