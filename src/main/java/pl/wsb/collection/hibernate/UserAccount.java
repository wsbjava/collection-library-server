package pl.wsb.collection.hibernate;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import pl.wsb.collection.exceptions.ValidationException;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * The persistent class for the user_account database table.
 * 
 */
@Entity
@Table(name="user_account", catalog = "collection_management", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class UserAccount implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date created;

	private int deleted;

	@Column(unique = true, nullable=false, length=255)
	private String email;

	@Column(name="first_name", nullable=false, length=255)
	private String firstName;

	@Column(name="last_name", nullable=false, length=255)
	private String lastName;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date modified;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="date_of_birth", nullable=false)
	private Date dateOfBirth;

	@Column(name="pass_hash", nullable=false, length=255)
	private String passHash;

	@Column(name="pass_salt", nullable=false, length=255)
	private String passSalt;

	//bi-directional many-to-one association to ApiToken
	@OneToMany(fetch = FetchType.LAZY, mappedBy="userAccount")
	private Set<ApiToken> apiTokens = new HashSet<>(0);

	//bi-directional many-to-one association to CollectionLibrary
	@OneToMany(fetch = FetchType.LAZY, mappedBy="userAccount")
	private Set<CollectionLibrary> collectionLibraries = new HashSet<>(0);

	@OneToMany(mappedBy="userAccount")
	private Set<Suggestion> suggestions;

	//bi-directional many-to-one association to UserAccountRole
	@OneToMany(fetch = FetchType.LAZY, mappedBy="userAccount")
	private Set<UserAccountRole> userAccountRoles = new HashSet<>(0);

	@OneToMany(fetch = FetchType.LAZY, mappedBy="userAccount")
	private Set<UserMessageStatus> userMessageStatuses;

	public UserAccount() {
	}

	public UserAccount(Date modified, String email, String passHash, String passSalt) {
		this.modified = modified;
		this.email = email;
		this.passHash = passHash;
		this.passSalt = passSalt;
	}

	public UserAccount(Date modified, String firstName, String lastName) {
		this.modified = modified;
		this.firstName = firstName;
		this.lastName = lastName;
	}


	public UserAccount(Date created, Date modified, String email, String firstName, String lastName, String passHash, String passSalt, Date dateOfBirth, Integer deleted,
					   Set<CollectionLibrary> collectionLibraries,Set<UserAccountRole> userAccountRoles, Set<ApiToken> apiTokens, Set<UserMessageStatus> userMessageStatuses, Set<Suggestion> suggestions) {
		this.created = created;
		this.modified = modified;
		this.dateOfBirth = dateOfBirth;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
		this.passHash = passHash;
		this.passSalt = passSalt;
		this.deleted = deleted;
		this.collectionLibraries = collectionLibraries;
		this.userAccountRoles = userAccountRoles;
		this.apiTokens = apiTokens;
		this.userMessageStatuses = userMessageStatuses;
		this.suggestions = suggestions;
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

	public Date getDateOfBirth() {return this.dateOfBirth;}

	public void setDateOfBirth(Date dateOfBirth) { this.dateOfBirth = dateOfBirth;}

	public int getDeleted() {
		return this.deleted;
	}

	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getPassHash() {
		return this.passHash;
	}

	public void setPassHash(String passHash) {
		this.passHash = passHash;
	}

	public String getPassSalt() {
		return this.passSalt;
	}

	public void setPassSalt(String passSalt) {
		this.passSalt = passSalt;
	}

	public Set<ApiToken> getApiTokens() {
		return this.apiTokens;
	}

	public void setApiTokens(Set<ApiToken> apiTokens) {
		this.apiTokens = apiTokens;
	}

	public Set<CollectionLibrary> getCollectionLibraries() {
		return this.collectionLibraries;
	}

	public void setCollectionLibraries(Set<CollectionLibrary> collectionLibraries) {
		this.collectionLibraries = collectionLibraries;
	}

	public Set<UserAccountRole> getUserAccountRoles() {
		return this.userAccountRoles;
	}


	public void setSuggestions(Set<Suggestion> suggestions) {
		this.suggestions = suggestions;
	}

	public Set<Suggestion> getSuggestions() {
		return this.suggestions;
	}


	public void setUserMessageStatuses(Set<UserMessageStatus> userMessageStatuses) {
		this.userMessageStatuses = userMessageStatuses;
	}

	public Set<UserMessageStatus> getUserMessageStatuses() {
		return this.userMessageStatuses;
	}

	public void setUserAccountRoles(Set<UserAccountRole> userAccountRoles) {
		this.userAccountRoles = userAccountRoles;
	}

	public boolean validatePass(String password) throws ValidationException{
		if(StringUtils.isBlank(password)){
			return false;
		}
		return this.generatePassHash(password, this.passSalt).equalsIgnoreCase(this.passHash);
	}

	public String generatePassHash(String password, String salt) throws ValidationException{
		if(StringUtils.isBlank(password)){
			throw new ValidationException("Password is empty");
		}

		if(StringUtils.isBlank(salt)){
			throw new ValidationException("Salt is empty");
		}

		return DigestUtils.sha256Hex(
				String.format("%s%s", password, salt)
		);

	}

}