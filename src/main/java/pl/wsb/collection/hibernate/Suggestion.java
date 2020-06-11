package pl.wsb.collection.hibernate;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the suggestions database table.
 * 
 */
@Entity
@Table(name="suggestions", catalog = "collection_management")
public class Suggestion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date created;

	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date modified;

	//bi-directional many-to-one association to CollectionEntry
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="collection_entry_id", nullable=false)
	private CollectionEntry collectionEntry;

	//bi-directional many-to-one association to SuggestionStatus
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="status_id", nullable=false)
	private SuggestionStatus suggestionStatus;

	//bi-directional many-to-one association to UserAccount
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_account_id", nullable=false)
	private UserAccount userAccount;

	public Suggestion() {
	}

	public Suggestion(Date created, Date modified, CollectionEntry collectionEntry, SuggestionStatus suggestionStatus, UserAccount userAccount){
		this.created = created;
		this.modified = modified;
		this.collectionEntry = collectionEntry;
		this.suggestionStatus = suggestionStatus;
		this.userAccount = userAccount;
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


	public CollectionEntry getCollectionEntry() {
		return this.collectionEntry;
	}

	public void setCollectionEntry(CollectionEntry collectionEntry) {
		this.collectionEntry = collectionEntry;
	}

	public SuggestionStatus getSuggestionStatus() {
		return this.suggestionStatus;
	}

	public void setSuggestionStatus(SuggestionStatus suggestionStatus) {
		this.suggestionStatus = suggestionStatus;
	}

	public UserAccount getUserAccount() {
		return this.userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}

}