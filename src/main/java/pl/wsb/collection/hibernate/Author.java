package pl.wsb.collection.hibernate;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * The persistent class for the author database table.
 * 
 */
@Entity
@Table(name="author", catalog = "collection_management")
public class Author implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date created;

	private int deleted;

	@Column(name="first_name", nullable=false, length=255)
	private String firstName;

	@Column(name="last_name", nullable=false, length=255)
	private String lastName;

	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date modified;

	//bi-directional many-to-one association to CollectionEntryAuthor
	@OneToMany(fetch = FetchType.LAZY, mappedBy="author")
	private Set<CollectionEntryAuthor> collectionEntryAuthors = new HashSet<>(0);

	public Author() {
	}

	public Author(Date modified)
	{
		this.modified = modified;
	}

	public Author(Date created, Date modified, String firstName, String lastName, Integer deleted, Set<CollectionEntryAuthor> collectionEntryAuthors)
	{
		this.created = created;
		this.modified = modified;
		this.firstName = firstName;
		this.lastName = lastName;
		this.deleted = deleted;
		this.collectionEntryAuthors = collectionEntryAuthors;

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

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getModified() {
		return this.modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public Set<CollectionEntryAuthor> getCollectionEntryAuthors(){
		return this.collectionEntryAuthors;
	}
	public void setCollectionEntryAuthors(Set<CollectionEntryAuthor> collectionEntryAuthors){
		this.collectionEntryAuthors = collectionEntryAuthors;
	}

}