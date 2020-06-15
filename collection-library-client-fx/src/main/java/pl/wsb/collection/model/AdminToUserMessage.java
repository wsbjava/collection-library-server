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
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.*;

/**
 * AdminToUserMessage
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2020-06-15T21:16:59.062Z")
public class AdminToUserMessage   {
  @JsonProperty("id")
  private Integer id = null;

  @JsonProperty("user_id")
  private Integer userId = null;

  @JsonProperty("text")
  private String text = null;

  public AdminToUserMessage id(Integer id) {
    this.id = id;
    return this;
  }

  /**
   * Message id
   * @return id
   **/
  @JsonProperty("id")
  @ApiModelProperty(value = "Message id")
  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public AdminToUserMessage userId(Integer userId) {
    this.userId = userId;
    return this;
  }

  /**
   * Target user id
   * @return userId
   **/
  @JsonProperty("user_id")
  @ApiModelProperty(required = true, value = "Target user id")
  @NotNull
  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public AdminToUserMessage text(String text) {
    this.text = text;
    return this;
  }

  /**
   * Message
   * @return text
   **/
  @JsonProperty("text")
  @ApiModelProperty(required = true, value = "Message")
  @NotNull
  public String getText() {
    return text;
  }

  public void setText(String text) {
    this.text = text;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AdminToUserMessage adminToUserMessage = (AdminToUserMessage) o;
    return Objects.equals(this.id, adminToUserMessage.id) &&
        Objects.equals(this.userId, adminToUserMessage.userId) &&
        Objects.equals(this.text, adminToUserMessage.text);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, userId, text);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AdminToUserMessage {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    text: ").append(toIndentedString(text)).append("\n");
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

