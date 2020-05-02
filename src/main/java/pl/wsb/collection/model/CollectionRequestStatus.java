package pl.wsb.collection.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the collection_request_status database table.
 * 
 */
@Entity
@Table(name="collection_request_status")
@NamedQuery(name="CollectionRequestStatus.findAll", query="SELECT c FROM CollectionRequestStatus c")
public class CollectionRequestStatus implements Serializable {
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

	//bi-directional many-to-one association to CollectionEntry
	@OneToMany(mappedBy="collectionRequestStatus")
	private List<CollectionEntry> collectionEntries;

	public CollectionRequestStatus() {
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

	public List<CollectionEntry> getCollectionEntries() {
		return this.collectionEntries;
	}

	public void setCollectionEntries(List<CollectionEntry> collectionEntries) {
		this.collectionEntries = collectionEntries;
	}

	public CollectionEntry addCollectionEntry(CollectionEntry collectionEntry) {
		getCollectionEntries().add(collectionEntry);
		collectionEntry.setCollectionRequestStatus(this);

		return collectionEntry;
	}

	public CollectionEntry removeCollectionEntry(CollectionEntry collectionEntry) {
		getCollectionEntries().remove(collectionEntry);
		collectionEntry.setCollectionRequestStatus(null);

		return collectionEntry;
	}

}