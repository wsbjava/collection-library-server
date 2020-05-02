package pl.wsb.collection.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the collection_entry database table.
 * 
 */
@Entity
@Table(name="collection_entry")
@NamedQuery(name="CollectionEntry.findAll", query="SELECT c FROM CollectionEntry c")
public class CollectionEntry implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	private Timestamp created;

	private int deleted;

	@Column(nullable=false)
	private Timestamp modified;

	private int quantity;

	@Column(name="release_year", nullable=false)
	private int releaseYear;

	@Column(length=255)
	private String title;

	//bi-directional many-to-one association to CollectionRequestStatus
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="request_status_id")
	private CollectionRequestStatus collectionRequestStatus;

	//bi-directional many-to-one association to CollectionType
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="collection_type_id")
	private CollectionType collectionType;

	//bi-directional many-to-one association to CollectionEntryAuthor
	@OneToMany(mappedBy="collectionEntry")
	private List<CollectionEntryAuthor> collectionEntryAuthors;

	//bi-directional many-to-one association to CollectionEntryGenre
	@OneToMany(mappedBy="collectionEntry")
	private List<CollectionEntryGenre> collectionEntryGenres;

	//bi-directional many-to-one association to CollectionEntryPublisher
	@OneToMany(mappedBy="collectionEntry")
	private List<CollectionEntryPublisher> collectionEntryPublishers;

	//bi-directional many-to-one association to CollectionLibrary
	@OneToMany(mappedBy="collectionEntry")
	private List<CollectionLibrary> collectionLibraries;

	public CollectionEntry() {
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

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getReleaseYear() {
		return this.releaseYear;
	}

	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public CollectionRequestStatus getCollectionRequestStatus() {
		return this.collectionRequestStatus;
	}

	public void setCollectionRequestStatus(CollectionRequestStatus collectionRequestStatus) {
		this.collectionRequestStatus = collectionRequestStatus;
	}

	public CollectionType getCollectionType() {
		return this.collectionType;
	}

	public void setCollectionType(CollectionType collectionType) {
		this.collectionType = collectionType;
	}

	public List<CollectionEntryAuthor> getCollectionEntryAuthors() {
		return this.collectionEntryAuthors;
	}

	public void setCollectionEntryAuthors(List<CollectionEntryAuthor> collectionEntryAuthors) {
		this.collectionEntryAuthors = collectionEntryAuthors;
	}

	public CollectionEntryAuthor addCollectionEntryAuthor(CollectionEntryAuthor collectionEntryAuthor) {
		getCollectionEntryAuthors().add(collectionEntryAuthor);
		collectionEntryAuthor.setCollectionEntry(this);

		return collectionEntryAuthor;
	}

	public CollectionEntryAuthor removeCollectionEntryAuthor(CollectionEntryAuthor collectionEntryAuthor) {
		getCollectionEntryAuthors().remove(collectionEntryAuthor);
		collectionEntryAuthor.setCollectionEntry(null);

		return collectionEntryAuthor;
	}

	public List<CollectionEntryGenre> getCollectionEntryGenres() {
		return this.collectionEntryGenres;
	}

	public void setCollectionEntryGenres(List<CollectionEntryGenre> collectionEntryGenres) {
		this.collectionEntryGenres = collectionEntryGenres;
	}

	public CollectionEntryGenre addCollectionEntryGenre(CollectionEntryGenre collectionEntryGenre) {
		getCollectionEntryGenres().add(collectionEntryGenre);
		collectionEntryGenre.setCollectionEntry(this);

		return collectionEntryGenre;
	}

	public CollectionEntryGenre removeCollectionEntryGenre(CollectionEntryGenre collectionEntryGenre) {
		getCollectionEntryGenres().remove(collectionEntryGenre);
		collectionEntryGenre.setCollectionEntry(null);

		return collectionEntryGenre;
	}

	public List<CollectionEntryPublisher> getCollectionEntryPublishers() {
		return this.collectionEntryPublishers;
	}

	public void setCollectionEntryPublishers(List<CollectionEntryPublisher> collectionEntryPublishers) {
		this.collectionEntryPublishers = collectionEntryPublishers;
	}

	public CollectionEntryPublisher addCollectionEntryPublisher(CollectionEntryPublisher collectionEntryPublisher) {
		getCollectionEntryPublishers().add(collectionEntryPublisher);
		collectionEntryPublisher.setCollectionEntry(this);

		return collectionEntryPublisher;
	}

	public CollectionEntryPublisher removeCollectionEntryPublisher(CollectionEntryPublisher collectionEntryPublisher) {
		getCollectionEntryPublishers().remove(collectionEntryPublisher);
		collectionEntryPublisher.setCollectionEntry(null);

		return collectionEntryPublisher;
	}

	public List<CollectionLibrary> getCollectionLibraries() {
		return this.collectionLibraries;
	}

	public void setCollectionLibraries(List<CollectionLibrary> collectionLibraries) {
		this.collectionLibraries = collectionLibraries;
	}

	public CollectionLibrary addCollectionLibrary(CollectionLibrary collectionLibrary) {
		getCollectionLibraries().add(collectionLibrary);
		collectionLibrary.setCollectionEntry(this);

		return collectionLibrary;
	}

	public CollectionLibrary removeCollectionLibrary(CollectionLibrary collectionLibrary) {
		getCollectionLibraries().remove(collectionLibrary);
		collectionLibrary.setCollectionEntry(null);

		return collectionLibrary;
	}

}