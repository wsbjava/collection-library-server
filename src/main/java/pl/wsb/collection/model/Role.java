package pl.wsb.collection.model;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the role database table.
 * 
 */
@Entity
@Table(name="role")
@NamedQuery(name="Role.findAll", query="SELECT r FROM Role r")
public class Role implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(nullable=false, length=20)
	private String abbr;

	private Timestamp created;

	private int deleted;

	@Column(nullable=false)
	private Timestamp modified;

	@Column(length=255)
	private String name;

	//bi-directional many-to-one association to UserAccountRole
	@OneToMany(mappedBy="role")
	private List<UserAccountRole> userAccountRoles;

	public Role() {
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

	public Timestamp getModified() {
		return this.modified;
	}

	public void setModified(Timestamp modified) {
		this.modified = modified;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<UserAccountRole> getUserAccountRoles() {
		return this.userAccountRoles;
	}

	public void setUserAccountRoles(List<UserAccountRole> userAccountRoles) {
		this.userAccountRoles = userAccountRoles;
	}

	public UserAccountRole addUserAccountRole(UserAccountRole userAccountRole) {
		getUserAccountRoles().add(userAccountRole);
		userAccountRole.setRole(this);

		return userAccountRole;
	}

	public UserAccountRole removeUserAccountRole(UserAccountRole userAccountRole) {
		getUserAccountRoles().remove(userAccountRole);
		userAccountRole.setRole(null);

		return userAccountRole;
	}

}