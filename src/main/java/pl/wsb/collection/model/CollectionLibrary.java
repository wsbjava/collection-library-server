package pl.wsb.collection.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;


/**
 * The persistent class for the collection_library database table.
 * 
 */
@Entity
@Table(name="collection_library", catalog = "collection_management", uniqueConstraints = @UniqueConstraint(columnNames = {"collection_entry_id", "user_account_id"}))
public class CollectionLibrary implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date created;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="entry_return")
	private Date entryReturn;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date modified;

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

	public CollectionLibrary(CollectionEntry collectionEntry, CollectionLibraryStatus collectionLibraryStatus, UserAccount userAccount, Date modified){
		this.collectionEntry = collectionEntry;
		this.collectionLibraryStatus = collectionLibraryStatus;
		this.userAccount = userAccount;
		this.modified = modified;
	}

	public CollectionLibrary(CollectionEntry collectionEntry, CollectionLibraryStatus collectionLibraryStatus, UserAccount userAccount, Date created, Date modified){
		this.collectionEntry = collectionEntry;
		this.collectionLibraryStatus = collectionLibraryStatus;
		this.userAccount = userAccount;
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

	public Date getEntryReturn() {
		return this.entryReturn;
	}

	public void setEntryReturn(Date entryReturn) {
		this.entryReturn = entryReturn;
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