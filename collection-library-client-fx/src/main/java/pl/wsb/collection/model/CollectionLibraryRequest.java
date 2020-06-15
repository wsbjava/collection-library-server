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

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;

/**
 * CollectionLibraryRequest
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2020-06-15T21:16:59.062Z")
public class CollectionLibraryRequest   {
  @JsonProperty("item_id")
  private Integer itemId = null;

  /**
   * Shelf on which item is added
   */
  public enum ShelfEnum {
    HAVE("Have"),
    
    WANT_TO_HAVE("Want to have");

    private String value;

    ShelfEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static ShelfEnum fromValue(String text) {
      for (ShelfEnum b : ShelfEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }

  @JsonProperty("shelf")
  private ShelfEnum shelf = null;

  public CollectionLibraryRequest itemId(Integer itemId) {
    this.itemId = itemId;
    return this;
  }

  /**
   * Item id to add
   * @return itemId
   **/
  @JsonProperty("item_id")
  @ApiModelProperty(required = true, value = "Item id to add")
  @NotNull
  public Integer getItemId() {
    return itemId;
  }

  public void setItemId(Integer itemId) {
    this.itemId = itemId;
  }

  public CollectionLibraryRequest shelf(ShelfEnum shelf) {
    this.shelf = shelf;
    return this;
  }

  /**
   * Shelf on which item is added
   * @return shelf
   **/
  @JsonProperty("shelf")
  @ApiModelProperty(required = true, value = "Shelf on which item is added")
  @NotNull
  public ShelfEnum getShelf() {
    return shelf;
  }

  public void setShelf(ShelfEnum shelf) {
    this.shelf = shelf;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    CollectionLibraryRequest collectionLibraryRequest = (CollectionLibraryRequest) o;
    return Objects.equals(this.itemId, collectionLibraryRequest.itemId) &&
        Objects.equals(this.shelf, collectionLibraryRequest.shelf);
  }

  @Override
  public int hashCode() {
    return Objects.hash(itemId, shelf);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class CollectionLibraryRequest {\n");
    
    sb.append("    itemId: ").append(toIndentedString(itemId)).append("\n");
    sb.append("    shelf: ").append(toIndentedString(shelf)).append("\n");
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
}
