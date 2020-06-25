/*
 * collection-library-server-API
 * API for collection-library-server
 *
 * OpenAPI spec version: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by the swagger code generator program.
 * https://github.com/swagger-api/swagger-codegen.git
 * Do not edit the class manually.
 */


package pl.wsb.collection.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import pl.wsb.collection.hibernate.CollectionEntry;
import pl.wsb.collection.hibernate.CollectionEntryAuthor;
import pl.wsb.collection.hibernate.CollectionEntryGenre;

import java.util.Date;
import javax.validation.constraints.*;

/**
 * Item
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2020-06-08T19:18:47.684Z")
public class Item   {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("title")
  private String title = null;

  @JsonProperty("author")
  private Author author = null;

  @JsonProperty("date_of_release")
  private Date dateOfRelease = null;

  @JsonProperty("publisher")
  private String publisher = null;

  @JsonProperty("type")
  private String type = null;

  @JsonProperty("genre")
  private String genre = null;

  public Item id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * Item id
   * @return id
   **/
  @JsonProperty("id")
  @ApiModelProperty(value = "Item id")
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Item title(String title) {
    this.title = title;
    return this;
  }

  /**
   * Get title
   * @return title
   **/
  @JsonProperty("title")
  @ApiModelProperty(required = true, value = "")
  @NotNull
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Item author(Author author) {
    this.author = author;
    return this;
  }

  /**
   * Get author
   * @return author
   **/
  @JsonProperty("author")
  @ApiModelProperty(required = true, value = "")
  @NotNull
  public Author getAuthor() {
    return author;
  }

  public void setAuthor(Author author) {
    this.author = author;
  }

  public Item dateOfRelease(Date dateOfRelease) {
    this.dateOfRelease = dateOfRelease;
    return this;
  }

  /**
   * Get dateOfRelease
   * @return dateOfRelease
   **/
  @JsonProperty("date_of_release")
  @ApiModelProperty(required = true, value = "")
  @NotNull
  public Date getDateOfRelease() {
    return dateOfRelease;
  }

  public void setDateOfRelease(Date dateOfRelease) {
    this.dateOfRelease = dateOfRelease;
  }

  public Item publisher(String publisher) {
    this.publisher = publisher;
    return this;
  }

  /**
   * Get publisher
   * @return publisher
   **/
  @JsonProperty("publisher")
  @ApiModelProperty(required = true, value = "")
  @NotNull
  public String getPublisher() {
    return publisher;
  }

  public void setPublisher(String publisher) {
    this.publisher = publisher;
  }

  public Item type(String type) {
    this.type = type;
    return this;
  }

  /**
   * Get type
   * @return type
   **/
  @JsonProperty("type")
  @ApiModelProperty(required = true, value = "")
  @NotNull
  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Item genre(String genre) {
    this.genre = genre;
    return this;
  }

  /**
   * Get genre
   * @return genre
   **/
  @JsonProperty("genre")
  @ApiModelProperty(required = true, value = "")
  @NotNull
  public String getGenre() {
    return genre;
  }

  public void setGenre(String genre) {
    this.genre = genre;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Item item = (Item) o;
    return Objects.equals(this.id, item.id) &&
        Objects.equals(this.title, item.title) &&
        Objects.equals(this.author, item.author) &&
        Objects.equals(this.dateOfRelease, item.dateOfRelease) &&
        Objects.equals(this.publisher, item.publisher) &&
        Objects.equals(this.type, item.type) &&
        Objects.equals(this.genre, item.genre);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, title, author, dateOfRelease, publisher, type, genre);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Item {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    author: ").append(toIndentedString(author)).append("\n");
    sb.append("    dateOfRelease: ").append(toIndentedString(dateOfRelease)).append("\n");
    sb.append("    publisher: ").append(toIndentedString(publisher)).append("\n");
    sb.append("    type: ").append(toIndentedString(type)).append("\n");
    sb.append("    genre: ").append(toIndentedString(genre)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }

  public static Item getItemFromDB(CollectionEntry collectionEntry) throws ParseException {

    Item item = new Item();
    item.id(collectionEntry.getId());
    item.publisher("Publisher");
    item.title(collectionEntry.getTitle());
    String genre = "";
    for(CollectionEntryGenre collectionEntryGenre : collectionEntry.getCollectionEntryGenres()){
      genre += collectionEntryGenre.getGenre().getName() + ", ";
    }

    item.genre(genre);

    pl.wsb.collection.hibernate.Author author = new pl.wsb.collection.hibernate.Author();
    for(CollectionEntryAuthor collectionEntryAuthor : collectionEntry.getCollectionEntryAuthors()){
      author = collectionEntryAuthor.getAuthor();
    }

    item.author(new Author().getAuthorFromDB(author));


    SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
    Date date1 = formatter.parse("2012");
    item.dateOfRelease(date1);
    item.type(collectionEntry.getCollectionType().getName());

    return item;
  }
}

