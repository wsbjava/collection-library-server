package pl.wsb.collection.hibernate;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * The persistent class for the collection_entry database table.
 * 
 */
@Entity
@Table(name="collection_entry", catalog = "collection_management")
public class CollectionEntry implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date created;

	private int deleted;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date modified;

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
	@OneToMany(fetch=FetchType.LAZY, mappedBy="collectionEntry")
	private Set<CollectionEntryAuthor> collectionEntryAuthors = new HashSet<>(0);

	//bi-directional many-to-one association to CollectionEntryGenre
	@OneToMany(fetch=FetchType.LAZY, mappedBy="collectionEntry")
	private Set<CollectionEntryGenre> collectionEntryGenres = new HashSet<>(0);

	//bi-directional many-to-one association to CollectionEntryPublisher
	@OneToMany(fetch=FetchType.LAZY, mappedBy="collectionEntry")
	private Set<CollectionEntryPublisher> collectionEntryPublishers = new HashSet<>(0);

	//bi-directional many-to-one association to CollectionLibrary
	@OneToMany(fetch=FetchType.LAZY, mappedBy="collectionEntry")
	private Set<CollectionLibrary> collectionLibraries = new HashSet<>(0);

	public CollectionEntry() {
	}

	public CollectionEntry(Date modified){
		this.modified = modified;
	}

	public CollectionEntry(Date created, int deleted, Date modified, int quantity, int releaseYear, String title,
						   CollectionRequestStatus collectionRequestStatus, CollectionType collectionType, Set<CollectionEntryAuthor> collectionEntryAuthors,
						   Set<CollectionEntryGenre> collectionEntryGenres, Set<CollectionEntryPublisher> collectionEntryPublishers, Set<CollectionLibrary> collectionLibraries){

		this.created = created;
		this.deleted = deleted;
		this.modified = modified;
		this.quantity = quantity;
		this.releaseYear = releaseYear;
		this.title = title;
		this.collectionRequestStatus = collectionRequestStatus;
		this.collectionType = collectionType;
		this.collectionEntryAuthors = collectionEntryAuthors;
		this.collectionEntryGenres = collectionEntryGenres;
		this.collectionEntryPublishers = collectionEntryPublishers;
		this.collectionLibraries = collectionLibraries;
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

	public int getDeleted() {
		return this.deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	public Date getModified() {
		return this.modified;
	}

	public void setModified(Date modified) {
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

	public Set<CollectionEntryAuthor> getCollectionEntryAuthors() {
		return this.collectionEntryAuthors;
	}

	public void setCollectionEntryAuthors(Set<CollectionEntryAuthor> collectionEntryAuthors) {
		this.collectionEntryAuthors = collectionEntryAuthors;
	}


	public Set<CollectionEntryGenre> getCollectionEntryGenres() {
		return this.collectionEntryGenres;
	}

	public void setCollectionEntryGenres(Set<CollectionEntryGenre> collectionEntryGenres) {
		this.collectionEntryGenres = collectionEntryGenres;
	}
	public Set<CollectionEntryPublisher> getCollectionEntryPublishers() {
		return this.collectionEntryPublishers;
	}

	public void setCollectionEntryPublishers(Set<CollectionEntryPublisher> collectionEntryPublishers) {
		this.collectionEntryPublishers = collectionEntryPublishers;
	}


	public Set<CollectionLibrary> getCollectionLibraries() {
		return this.collectionLibraries;
	}

	public void setCollectionLibraries(Set<CollectionLibrary> collectionLibraries) {
		this.collectionLibraries = collectionLibraries;
	}


}