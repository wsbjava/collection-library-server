package pl.wsb.collection.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;


/**
 * The persistent class for the api_token database table.
 * 
 */
@Entity
@Table(name="api_token", catalog = "collection_management", uniqueConstraints = @UniqueConstraint(columnNames = "access_token"))
public class ApiToken implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(name="access_token", unique = true, nullable=false, length=255)
	private String accessToken;

	@Temporal(TemporalType.TIMESTAMP)
	private Date created;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable=false)
	private Date modified;

	@Column(name="refresh_token", length=255)
	private String refreshToken;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="valid_to")
	private Date validTo;

	//bi-directional many-to-one association to UserAccount
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_account_id", nullable=false)
	private UserAccount userAccount;

	public ApiToken() {
	}

	public ApiToken(UserAccount userAccount, Date modified, String accessToken)	{
		this.userAccount = userAccount;
		this.modified = modified;
		this.accessToken = accessToken;
	}

	public ApiToken(UserAccount userAccount, Date created, Date modified, String accessToken, String refreshToken, Date validTo ){
		this.userAccount = userAccount;
		this.created = created;
		this.modified = modified;
		this.accessToken = accessToken;
		this.refreshToken = refreshToken;
		this.validTo = validTo;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAccessToken() {
		return this.accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
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

	public String getRefreshToken() {
		return this.refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public Date getValidTo() {
		return this.validTo;
	}

	public void setValidTo(Date validTo) {
		this.validTo = validTo;
	}

	public UserAccount getUserAccount() {
		return this.userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}

}