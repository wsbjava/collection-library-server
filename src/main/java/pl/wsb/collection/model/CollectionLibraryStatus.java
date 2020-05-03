package pl.wsb.collection.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * The persistent class for the collection_library_status database table.
 * 
 */
@Entity
@Table(name="collection_library_status", catalog = "collection_management", uniqueConstraints = @UniqueConstraint(columnNames = "abbr"))
public class CollectionLibraryStatus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(nullable=false, length=20)
	private String abbr;

	@Temporal(TemporalType.TIMESTAMP)
	private Date created;

	private int deleted;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date modified;

	@Column(length=255)
	private String name;

	//bi-directional many-to-one association to CollectionLibrary
	@OneToMany(fetch = FetchType.LAZY,  mappedBy="collectionLibraryStatus")
	private Set<CollectionLibrary> collectionLibraries = new HashSet<>(0);

	public CollectionLibraryStatus() {
	}

	public CollectionLibraryStatus(Date modified, String abbr){
		this.modified = modified;
		this.abbr = abbr;
	}

	public CollectionLibraryStatus(Date created, Date modified, String name, String abbr, int deleted, Set<CollectionLibrary> collectionLibraries) {
		this.created = created;
		this.modified = modified;
		this.name = name;
		this.abbr = abbr;
		this.deleted = deleted;
		this.collectionLibraries = collectionLibraries;

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

	public Set<CollectionLibrary> getCollectionLibraries() {
		return this.collectionLibraries;
	}

	public void setCollectionLibraries(Set<CollectionLibrary> collectionLibraries) {
		this.collectionLibraries = collectionLibraries;
	}

}