package pl.wsb.collection.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the collection_library_status database table.
 * 
 */
@Entity
@Table(name="collection_library_status")
@NamedQuery(name="CollectionLibraryStatus.findAll", query="SELECT c FROM CollectionLibraryStatus c")
public class CollectionLibraryStatus implements Serializable {
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

	//bi-directional many-to-one association to CollectionLibrary
	@OneToMany(mappedBy="collectionLibraryStatus")
	private List<CollectionLibrary> collectionLibraries;

	public CollectionLibraryStatus() {
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

	public List<CollectionLibrary> getCollectionLibraries() {
		return this.collectionLibraries;
	}

	public void setCollectionLibraries(List<CollectionLibrary> collectionLibraries) {
		this.collectionLibraries = collectionLibraries;
	}

	public CollectionLibrary addCollectionLibrary(CollectionLibrary collectionLibrary) {
		getCollectionLibraries().add(collectionLibrary);
		collectionLibrary.setCollectionLibraryStatus(this);

		return collectionLibrary;
	}

	public CollectionLibrary removeCollectionLibrary(CollectionLibrary collectionLibrary) {
		getCollectionLibraries().remove(collectionLibrary);
		collectionLibrary.setCollectionLibraryStatus(null);

		return collectionLibrary;
	}

}