package pl.wsb.collection.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * The persistent class for the publisher database table.
 * 
 */
@Entity
@Table(name="publisher", catalog = "collection_management")
public class Publisher implements Serializable {
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

	@Column(length=255)
	private String name;

	//bi-directional many-to-one association to CollectionEntryPublisher
	@OneToMany(fetch = FetchType.LAZY, mappedBy="publisher")
	private Set<CollectionEntryPublisher> collectionEntryPublishers = new HashSet<>(0);

	public Publisher() {
	}

	public Publisher(Date modified, String name){
		this.modified = modified;
		this.name = name;
	}
	public Publisher(Date created, Date modified, String name, int deleted, Set<CollectionEntryPublisher> collectionEntryPublishers){
		this.created = created;
		this.modified = modified;
		this.name = name;
		this.deleted = deleted;
		this.collectionEntryPublishers = collectionEntryPublishers;
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<CollectionEntryPublisher> getCollectionEntryPublishers() {
		return this.collectionEntryPublishers;
	}

	public void setCollectionEntryPublishers(Set<CollectionEntryPublisher> collectionEntryPublishers) {
		this.collectionEntryPublishers = collectionEntryPublishers;
	}

}