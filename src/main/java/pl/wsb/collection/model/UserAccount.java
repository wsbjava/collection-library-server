package pl.wsb.collection.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the user_account database table.
 * 
 */
@Entity
@Table(name="user_account")
@NamedQuery(name="UserAccount.findAll", query="SELECT u FROM UserAccount u")
public class UserAccount implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	private Timestamp created;

	private int deleted;

	@Column(nullable=false, length=255)
	private String email;

	@Column(name="first_name", nullable=false, length=255)
	private String firstName;

	@Column(name="last_name", nullable=false, length=255)
	private String lastName;

	@Column(nullable=false)
	private Timestamp modified;

	@Column(name="pass_hash", nullable=false, length=255)
	private String passHash;

	@Column(name="pass_salt", nullable=false, length=255)
	private String passSalt;

	//bi-directional many-to-one association to ApiToken
	@OneToMany(mappedBy="userAccount")
	private List<ApiToken> apiTokens;

	//bi-directional many-to-one association to CollectionLibrary
	@OneToMany(mappedBy="userAccount")
	private List<CollectionLibrary> collectionLibraries;

	//bi-directional many-to-one association to UserAccountRole
	@OneToMany(mappedBy="userAccount")
	private List<UserAccountRole> userAccountRoles;

	public UserAccount() {
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

	public Timestamp getModified() {
		return this.modified;
	}

	public void setModified(Timestamp modified) {
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

	public List<ApiToken> getApiTokens() {
		return this.apiTokens;
	}

	public void setApiTokens(List<ApiToken> apiTokens) {
		this.apiTokens = apiTokens;
	}

	public ApiToken addApiToken(ApiToken apiToken) {
		getApiTokens().add(apiToken);
		apiToken.setUserAccount(this);

		return apiToken;
	}

	public ApiToken removeApiToken(ApiToken apiToken) {
		getApiTokens().remove(apiToken);
		apiToken.setUserAccount(null);

		return apiToken;
	}

	public List<CollectionLibrary> getCollectionLibraries() {
		return this.collectionLibraries;
	}

	public void setCollectionLibraries(List<CollectionLibrary> collectionLibraries) {
		this.collectionLibraries = collectionLibraries;
	}

	public CollectionLibrary addCollectionLibrary(CollectionLibrary collectionLibrary) {
		getCollectionLibraries().add(collectionLibrary);
		collectionLibrary.setUserAccount(this);

		return collectionLibrary;
	}

	public CollectionLibrary removeCollectionLibrary(CollectionLibrary collectionLibrary) {
		getCollectionLibraries().remove(collectionLibrary);
		collectionLibrary.setUserAccount(null);

		return collectionLibrary;
	}

	public List<UserAccountRole> getUserAccountRoles() {
		return this.userAccountRoles;
	}

	public void setUserAccountRoles(List<UserAccountRole> userAccountRoles) {
		this.userAccountRoles = userAccountRoles;
	}

	public UserAccountRole addUserAccountRole(UserAccountRole userAccountRole) {
		getUserAccountRoles().add(userAccountRole);
		userAccountRole.setUserAccount(this);

		return userAccountRole;
	}

	public UserAccountRole removeUserAccountRole(UserAccountRole userAccountRole) {
		getUserAccountRoles().remove(userAccountRole);
		userAccountRole.setUserAccount(null);

		return userAccountRole;
	}

}