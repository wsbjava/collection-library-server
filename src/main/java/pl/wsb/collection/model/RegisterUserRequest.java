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
import java.util.Date;
import javax.validation.constraints.*;

/**
 * RegisterUserRequest
 */
@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaJerseyServerCodegen", date = "2020-06-08T19:18:47.684Z")
public class RegisterUserRequest   {
  @JsonProperty("email")
  private String email = null;

  @JsonProperty("password")
  private String password = null;

  @JsonProperty("name")
  private String name = null;

  @JsonProperty("surname")
  private String surname = null;

  @JsonProperty("date_of_birth")
  private Date dateOfBirth = null;

  public RegisterUserRequest email(String email) {
    this.email = email;
    return this;
  }

  /**
   * User email address
   * @return email
   **/
  @JsonProperty("email")
  @ApiModelProperty(required = true, value = "User email address")
  @NotNull
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public RegisterUserRequest password(String password) {
    this.password = password;
    return this;
  }

  /**
   * User password
   * @return password
   **/
  @JsonProperty("password")
  @ApiModelProperty(required = true, value = "User password")
  @NotNull
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public RegisterUserRequest name(String name) {
    this.name = name;
    return this;
  }

  /**
   * User&#39;s name
   * @return name
   **/
  @JsonProperty("name")
  @ApiModelProperty(required = true, value = "User's name")
  @NotNull
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public RegisterUserRequest surname(String surname) {
    this.surname = surname;
    return this;
  }

  /**
   * User&#39;s surname
   * @return surname
   **/
  @JsonProperty("surname")
  @ApiModelProperty(required = true, value = "User's surname")
  @NotNull
  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public RegisterUserRequest dateOfBirth(Date dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
    return this;
  }

  /**
   * User&#39;s date of birth
   * @return dateOfBirth
   **/
  @JsonProperty("date_of_birth")
  @ApiModelProperty(required = true, value = "User's date of birth")
  @NotNull
  public Date getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(Date dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RegisterUserRequest registerUserRequest = (RegisterUserRequest) o;
    return Objects.equals(this.email, registerUserRequest.email) &&
        Objects.equals(this.password, registerUserRequest.password) &&
        Objects.equals(this.name, registerUserRequest.name) &&
        Objects.equals(this.surname, registerUserRequest.surname) &&
        Objects.equals(this.dateOfBirth, registerUserRequest.dateOfBirth);
  }

  @Override
  public int hashCode() {
    return Objects.hash(email, password, name, surname, dateOfBirth);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class RegisterUserRequest {\n");
    
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    password: ").append(toIndentedString(password)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    surname: ").append(toIndentedString(surname)).append("\n");
    sb.append("    dateOfBirth: ").append(toIndentedString(dateOfBirth)).append("\n");
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
