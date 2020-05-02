package pl.wsb.collection.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the author database table.
 * 
 */
@Entity
@Table(name="author")
@NamedQuery(name="Author.findAll", query="SELECT a FROM Author a")
public class Author implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	private Timestamp created;

	private int deleted;

	@Column(name="first_name", nullable=false, length=255)
	private String firstName;

	@Column(name="last_name", nullable=false, length=255)
	private String lastName;

	@Column(nullable=false)
	private Timestamp modified;

	//bi-directional many-to-one association to CollectionEntryAuthor
	@OneToMany(mappedBy="author")
	private List<CollectionEntryAuthor> collectionEntryAuthors;

	public Author() {
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

	public Timestamp getModified() {
		return this.modified;
	}

	public void setModified(Timestamp modified) {
		this.modified = modified;
	}

	public List<CollectionEntryAuthor> getCollectionEntryAuthors() {
		return this.collectionEntryAuthors;
	}

	public void setCollectionEntryAuthors(List<CollectionEntryAuthor> collectionEntryAuthors) {
		this.collectionEntryAuthors = collectionEntryAuthors;
	}

	public CollectionEntryAuthor addCollectionEntryAuthor(CollectionEntryAuthor collectionEntryAuthor) {
		getCollectionEntryAuthors().add(collectionEntryAuthor);
		collectionEntryAuthor.setAuthor(this);

		return collectionEntryAuthor;
	}

	public CollectionEntryAuthor removeCollectionEntryAuthor(CollectionEntryAuthor collectionEntryAuthor) {
		getCollectionEntryAuthors().remove(collectionEntryAuthor);
		collectionEntryAuthor.setAuthor(null);

		return collectionEntryAuthor;
	}

}