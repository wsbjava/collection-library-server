package pl.wsb.collection.hibernate;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;


/**
 * The persistent class for the suggestion_status database table.
 * 
 */
@Entity
@Table(name="suggestion_status", catalog = "collection_management", uniqueConstraints = @UniqueConstraint(columnNames = "abbr"))
public class SuggestionStatus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(unique=true, nullable=false)
	private int id;

	@Column(nullable=false, length=20)
	private String abbr;

	@Temporal(TemporalType.TIMESTAMP)
	private Date created;

	private int deleted;

	@Column(nullable=false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date  modified;

	@Column(length=255)
	private String name;

	//bi-directional many-to-one association to Suggestion
	@OneToMany(mappedBy="suggestionStatus")
	private Set<Suggestion> suggestions;

	public SuggestionStatus() {
	}

	public SuggestionStatus(Date modified, String abbr){
		this.modified = modified;
		this.abbr = abbr;
	}

	public SuggestionStatus(Date created, Date modified, String name, String abbr, int deleted, Set<Suggestion> suggestions){
		this.created = created;
		this.modified = modified;
		this.name = name;
		this.abbr = abbr;
		this.deleted = deleted;
		this.suggestions = suggestions;
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

	public Set<Suggestion> getSuggestions() {
		return this.suggestions;
	}

	public void setSuggestions(Set<Suggestion> suggestions) {
		this.suggestions = suggestions;
	}



}