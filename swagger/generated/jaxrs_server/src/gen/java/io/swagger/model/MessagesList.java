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


package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.model.AdminToUserMessage;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.*;

/**
 * MessagesList
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2020-06-08T19:18:47.684Z")
public class MessagesList   {
  @JsonProperty("total")
  private Integer total = null;

  @JsonProperty("data")
  private List<AdminToUserMessage> data = new ArrayList<AdminToUserMessage>();

  public MessagesList total(Integer total) {
    this.total = total;
    return this;
  }

  /**
   * Size of the data
   * @return total
   **/
  @JsonProperty("total")
  @ApiModelProperty(required = true, value = "Size of the data")
  @NotNull
  public Integer getTotal() {
    return total;
  }

  public void setTotal(Integer total) {
    this.total = total;
  }

  public MessagesList data(List<AdminToUserMessage> data) {
    this.data = data;
    return this;
  }

  public MessagesList addDataItem(AdminToUserMessage dataItem) {
    this.data.add(dataItem);
    return this;
  }

  /**
   * One pagination page data
   * @return data
   **/
  @JsonProperty("data")
  @ApiModelProperty(required = true, value = "One pagination page data")
  @NotNull
  public List<AdminToUserMessage> getData() {
    return data;
  }

  public void setData(List<AdminToUserMessage> data) {
    this.data = data;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MessagesList messagesList = (MessagesList) o;
    return Objects.equals(this.total, messagesList.total) &&
        Objects.equals(this.data, messagesList.data);
  }

  @Override
  public int hashCode() {
    return Objects.hash(total, data);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MessagesList {\n");
    
    sb.append("    total: ").append(toIndentedString(total)).append("\n");
    sb.append("    data: ").append(toIndentedString(data)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

