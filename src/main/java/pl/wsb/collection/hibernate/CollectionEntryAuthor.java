package pl.wsb.collection.hibernate;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the collection_entry_author database table.
 * 
 */
@Entity
@Table(name="collection_entry_author", catalog = "collection_management", uniqueConstraints = @UniqueConstraint(columnNames = {"collection_entry_id", "author_id"}))
public class CollectionEntryAuthor implements Serializable {
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

	//bi-directional many-to-one association to Author
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="author_id", nullable=false)
	@JsonBackReference
	private Author author;

	//bi-directional many-to-one association to CollectionEntry
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="collection_entry_id", nullable=false)
	@JsonBackReference
	private CollectionEntry collectionEntry;

	public CollectionEntryAuthor() {
	}

	public CollectionEntryAuthor(Author author, CollectionEntry collectionEntry, Date modified){
		this.author = author;
		this.collectionEntry = collectionEntry;
		this.modified = modified;
	}

	public CollectionEntryAuthor(Author author, CollectionEntry collectionEntry, Date created, Date modified){
		this.author = author;
		this.collectionEntry = collectionEntry;
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

	public Author getAuthor() {
		return this.author;
	}

	public void setAuthor(Author author) {
		this.author = author;
	}

	public CollectionEntry getCollectionEntry() {
		return this.collectionEntry;
	}

	public void setCollectionEntry(CollectionEntry collectionEntry) {
		this.collectionEntry = collectionEntry;
	}

}