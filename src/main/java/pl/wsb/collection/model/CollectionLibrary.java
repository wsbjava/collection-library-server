package pl.wsb.collection.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the collection_library database table.
 * 
 */
@Entity
@Table(name="collection_library")
@NamedQuery(name="CollectionLibrary.findAll", query="SELECT c FROM CollectionLibrary c")
public class CollectionLibrary implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	private Timestamp created;

	@Column(name="entry_return")
	private Timestamp entryReturn;

	@Column(nullable=false)
	private Timestamp modified;

	//bi-directional many-to-one association to CollectionEntry
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="collection_entry_id", nullable=false)
	private CollectionEntry collectionEntry;

	//bi-directional many-to-one association to CollectionLibraryStatus
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="collection_library_status_id")
	private CollectionLibraryStatus collectionLibraryStatus;

	//bi-directional many-to-one association to UserAccount
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_account_id", nullable=false)
	private UserAccount userAccount;

	public CollectionLibrary() {
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

	public Timestamp getEntryReturn() {
		return this.entryReturn;
	}

	public void setEntryReturn(Timestamp entryReturn) {
		this.entryReturn = entryReturn;
	}

	public Timestamp getModified() {
		return this.modified;
	}

	public void setModified(Timestamp modified) {
		this.modified = modified;
	}

	public CollectionEntry getCollectionEntry() {
		return this.collectionEntry;
	}

	public void setCollectionEntry(CollectionEntry collectionEntry) {
		this.collectionEntry = collectionEntry;
	}

	public CollectionLibraryStatus getCollectionLibraryStatus() {
		return this.collectionLibraryStatus;
	}

	public void setCollectionLibraryStatus(CollectionLibraryStatus collectionLibraryStatus) {
		this.collectionLibraryStatus = collectionLibraryStatus;
	}

	public UserAccount getUserAccount() {
		return this.userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}

}