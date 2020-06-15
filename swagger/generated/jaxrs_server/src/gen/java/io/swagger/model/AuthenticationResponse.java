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
import javax.validation.constraints.*;

/**
 * AuthenticationResponse
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2020-06-15T21:16:59.062Z")
public class AuthenticationResponse   {
  @JsonProperty("access_token")
  private String accessToken = null;

  @JsonProperty("expires_in")
  private Integer expiresIn = null;

  @JsonProperty("user_id")
  private Integer userId = null;

  @JsonProperty("email_address")
  private String emailAddress = null;

  public AuthenticationResponse accessToken(String accessToken) {
    this.accessToken = accessToken;
    return this;
  }

  /**
   * Get accessToken
   * @return accessToken
   **/
  @JsonProperty("access_token")
  @ApiModelProperty(required = true, value = "")
  @NotNull
  public String getAccessToken() {
    return accessToken;
  }

  public void setAccessToken(String accessToken) {
    this.accessToken = accessToken;
  }

  public AuthenticationResponse expiresIn(Integer expiresIn) {
    this.expiresIn = expiresIn;
    return this;
  }

  /**
   * Get expiresIn
   * @return expiresIn
   **/
  @JsonProperty("expires_in")
  @ApiModelProperty(required = true, value = "")
  @NotNull
  public Integer getExpiresIn() {
    return expiresIn;
  }

  public void setExpiresIn(Integer expiresIn) {
    this.expiresIn = expiresIn;
  }

  public AuthenticationResponse userId(Integer userId) {
    this.userId = userId;
    return this;
  }

  /**
   * Get userId
   * @return userId
   **/
  @JsonProperty("user_id")
  @ApiModelProperty(required = true, value = "")
  @NotNull
  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }

  public AuthenticationResponse emailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
    return this;
  }

  /**
   * Get emailAddress
   * @return emailAddress
   **/
  @JsonProperty("email_address")
  @ApiModelProperty(required = true, value = "")
  @NotNull
  public String getEmailAddress() {
    return emailAddress;
  }

  public void setEmailAddress(String emailAddress) {
    this.emailAddress = emailAddress;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    AuthenticationResponse authenticationResponse = (AuthenticationResponse) o;
    return Objects.equals(this.accessToken, authenticationResponse.accessToken) &&
        Objects.equals(this.expiresIn, authenticationResponse.expiresIn) &&
        Objects.equals(this.userId, authenticationResponse.userId) &&
        Objects.equals(this.emailAddress, authenticationResponse.emailAddress);
  }

  @Override
  public int hashCode() {
    return Objects.hash(accessToken, expiresIn, userId, emailAddress);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class AuthenticationResponse {\n");
    
    sb.append("    accessToken: ").append(toIndentedString(accessToken)).append("\n");
    sb.append("    expiresIn: ").append(toIndentedString(expiresIn)).append("\n");
    sb.append("    userId: ").append(toIndentedString(userId)).append("\n");
    sb.append("    emailAddress: ").append(toIndentedString(emailAddress)).append("\n");
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
