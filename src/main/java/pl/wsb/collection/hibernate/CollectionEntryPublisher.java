package pl.wsb.collection.hibernate;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the collection_entry_publisher database table.
 * 
 */
@Entity
@Table(name="collection_entry_publisher", catalog = "collection_management", uniqueConstraints = @UniqueConstraint(columnNames = {"collection_entry_id", "publisher_id"}))
public class CollectionEntryPublisher implements Serializable {
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

	//bi-directional many-to-one association to Publisher
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="publisher_id", nullable=false)
	private Publisher publisher;

	public CollectionEntryPublisher() {
	}

	public CollectionEntryPublisher(CollectionEntry collectionEntry, Publisher publisher, Date modified){
		this.collectionEntry = collectionEntry;
		this.publisher = publisher;
		this.modified = modified;
	}

	public CollectionEntryPublisher(CollectionEntry collectionEntry, Publisher publisher, Date created, Date modified){
		this.collectionEntry = collectionEntry;
		this.publisher = publisher;
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

	public Publisher getPublisher() {
		return this.publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

}