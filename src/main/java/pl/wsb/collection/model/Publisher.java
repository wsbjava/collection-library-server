package pl.wsb.collection.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the publisher database table.
 * 
 */
@Entity
@Table(name="publisher")
@NamedQuery(name="Publisher.findAll", query="SELECT p FROM Publisher p")
public class Publisher implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	private Timestamp created;

	private int deleted;

	@Column(nullable=false)
	private Timestamp modified;

	@Column(length=255)
	private String name;

	//bi-directional many-to-one association to CollectionEntryPublisher
	@OneToMany(mappedBy="publisher")
	private List<CollectionEntryPublisher> collectionEntryPublishers;

	public Publisher() {
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

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<CollectionEntryPublisher> getCollectionEntryPublishers() {
		return this.collectionEntryPublishers;
	}

	public void setCollectionEntryPublishers(List<CollectionEntryPublisher> collectionEntryPublishers) {
		this.collectionEntryPublishers = collectionEntryPublishers;
	}

	public CollectionEntryPublisher addCollectionEntryPublisher(CollectionEntryPublisher collectionEntryPublisher) {
		getCollectionEntryPublishers().add(collectionEntryPublisher);
		collectionEntryPublisher.setPublisher(this);

		return collectionEntryPublisher;
	}

	public CollectionEntryPublisher removeCollectionEntryPublisher(CollectionEntryPublisher collectionEntryPublisher) {
		getCollectionEntryPublishers().remove(collectionEntryPublisher);
		collectionEntryPublisher.setPublisher(null);

		return collectionEntryPublisher;
	}

}