package pl.wsb.collection.hibernate;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * The persistent class for the collection_type database table.
 * 
 */
@Entity
@Table(name="collection_type", catalog = "collection_management", uniqueConstraints = @UniqueConstraint(columnNames = "abbr"))
public class CollectionType implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(unique = true, nullable=false, length=20)
	private String abbr;

	@Temporal(TemporalType.TIMESTAMP)
	private Date created;

	private int deleted;


	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date modified;

	@Column(length=255)
	private String name;

	//bi-directional many-to-one association to CollectionEntry
	@OneToMany(fetch = FetchType.LAZY,  mappedBy="collectionType")
	private Set<CollectionEntry> collectionEntries = new HashSet<>(0);

	//bi-directional many-to-one association to GenreCollectionType
	@OneToMany(fetch = FetchType.LAZY, mappedBy="collectionType")
	private Set<GenreCollectionType> genreCollectionTypes = new HashSet<>(0);

	public CollectionType() {
	}

	public CollectionType(Date modified, String abbr){
		this.modified = modified;
		this.abbr = abbr;
	}

	public CollectionType(Date created, Date modified, String name, String abbr, int deleted, Set<CollectionEntry> collectionEntries, Set<GenreCollectionType> genreCollectionTypes){
		this.created = created;
		this.modified = modified;
		this.name = name;
		this.abbr = abbr;
		this.deleted = deleted;
		this.collectionEntries = collectionEntries;
		this.genreCollectionTypes = genreCollectionTypes;
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

	public Set<CollectionEntry> getCollectionEntries() {
		return this.collectionEntries;
	}

	public void setCollectionEntries(Set<CollectionEntry> collectionEntries) {
		this.collectionEntries = collectionEntries;
	}


	public Set<GenreCollectionType> getGenreCollectionTypes() {
		return this.genreCollectionTypes;
	}

	public void setGenreCollectionTypes(Set<GenreCollectionType> genreCollectionTypes) {
		this.genreCollectionTypes = genreCollectionTypes;
	}

}