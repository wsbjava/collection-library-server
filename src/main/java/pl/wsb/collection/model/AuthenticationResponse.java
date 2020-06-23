package pl.wsb.collection.model;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import pl.wsb.collection.exceptions.ApiException;
import pl.wsb.collection.hibernate.ApiToken;
import pl.wsb.collection.hibernate.UserAccount;
import pl.wsb.collection.hibernate.Role;
import pl.wsb.collection.hibernate.UserAccountRole;

import javax.validation.constraints.*;

public class AuthenticationResponse {
    @JsonProperty("access_token")
    private String accessToken = null;
    @JsonProperty("expires_in")
    private Integer expiresIn = null;
    @JsonProperty("user_id")
    private Integer userId = null;
    @JsonProperty("email_address")
    private String emailAddress = null;
    @JsonProperty("roles")
    private List<RoleResponse> roles = new ArrayList<RoleResponse>();
    public AuthenticationResponse accessToken(String accessToken) {
        this.accessToken = accessToken;
        return this;
    }

    public AuthenticationResponse roles(List<RoleResponse> roles) {
        this.roles = roles;
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

    /**
     * Get role
     * @return role
     **/
    @JsonProperty("roles")
    @ApiModelProperty(value = "")
    public List<RoleResponse> getRole() {
        return roles;
    }
    public void setRole(List<RoleResponse> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
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
                Objects.equals(this.emailAddress, authenticationResponse.emailAddress) &&
                Objects.equals(this.roles, authenticationResponse.roles);
    }
    @Override
    public int hashCode() {
        return Objects.hash(accessToken, expiresIn, userId, emailAddress, roles);
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class AuthenticationResponse {\n");

        sb.append(" accessToken: ").append(toIndentedString(accessToken)).append("\n");
        sb.append(" expiresIn: ").append(toIndentedString(expiresIn)).append("\n");
        sb.append(" userId: ").append(toIndentedString(userId)).append("\n");
        sb.append(" emailAddress: ").append(toIndentedString(emailAddress)).append("\n");
        sb.append(" roles: ").append(toIndentedString(roles)).append("\n");
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
        return o.toString().replace("\n", "\n ");
    }

    public static AuthenticationResponse createFromApiToken(ApiToken token) throws
            ApiException {
        if (token == null) {
            throw new ApiException("Token is null...");
        }
        if (token.getUserAccount() == null) {
            throw new ApiException("Token user information data is null...");
        }

        List<RoleResponse> roles = new ArrayList<RoleResponse>();
        for(UserAccountRole userAccountRole : token.getUserAccount().getUserAccountRoles())
        {
            Role role = userAccountRole.getRole();
            RoleResponse roleResponse = new RoleResponse(
                    role.getCreated(), role.getModified(), role.getName(), role.getAbbr(), role.getDeleted()
            );
            roles.add(roleResponse);
        }
        return new AuthenticationResponse()
                .accessToken(token.getAccessToken())
                .emailAddress(token.getUserAccount().getEmail())
                .expiresIn(1800)
                .userId(token.getUserAccount().getId())
                .roles(roles);
    }



}

