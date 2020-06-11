package pl.wsb.collection.hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * The persistent class for the genre database table.
 * 
 */
@Entity
@Table(name="messages",  catalog = "collection_management")
public class Messages implements Serializable {
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

	@Column(nullable=false)
	private String message;

	//bi-directional many-to-one association to CollectionType
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="from_user", nullable=false)
	private UserAccount fromUser;

	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="to_user", nullable=false)
	private UserAccount toUser;


	@Column(nullable=false)
	private int deleted;

	//bi-directional many-to-one association to CollectionEntryGenre
	@OneToMany(fetch = FetchType.LAZY, mappedBy="messages")
	private Set<UserMessageStatus> userMessageStatuses = new HashSet<>(0);


	public Messages() {
	}

	public Messages(Date modified, String message){
		this.modified = modified;
		this.message = message;
	}

	public Messages(Date created, Date modified, String message, UserAccount fromUser, UserAccount toUser, Integer deleted, Set<UserMessageStatus> userMessageStatus){
		this.created = created;
		this.modified = modified;
		this.message = message;
		this.fromUser = fromUser;
		this.toUser = toUser;
		this.deleted = deleted;
		this.userMessageStatuses = userMessageStatus;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
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

	public UserAccount getFromUser() { return this.fromUser; }

	public void setFromUser(UserAccount fromUser) {this.fromUser = fromUser; }

	public UserAccount getToUser() { return this.toUser; }

	public void setToUser(UserAccount toUser) {this.toUser = toUser; }

	public Set<UserMessageStatus> getMessageStatus() {
		return this.userMessageStatuses;
	}

	public void setMessageStatus(Set<UserMessageStatus> userMessageStatuses) {
		this.userMessageStatuses = userMessageStatuses;
	}





}