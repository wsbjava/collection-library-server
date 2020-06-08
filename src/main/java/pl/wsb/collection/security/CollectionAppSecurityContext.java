package pl.wsb.collection.security;

import pl.wsb.collection.hibernate.UserAccount;

import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.UriInfo;
import java.security.Principal;

public class CollectionAppSecurityContext implements SecurityContext {

    private final UserAccount user;
    private final UriInfo uriInfo;

    private static final String DEFAULT_APP_AUTH_SCHEME = "Collection-App-Auth-Scheme";


    public CollectionAppSecurityContext(UserAccount user, UriInfo uriInfo){
        this.user = user;
        this.uriInfo = uriInfo;
    }

    @Override
    public Principal getUserPrincipal() {
        return new CollectionAppPrincipal(this.user);
    }

    @Override
    public boolean isUserInRole(String s) {
        return true;
    }

    @Override
    public boolean isSecure() {
        if(this.uriInfo == null){
            return false;
        }
        return this.uriInfo.getAbsolutePath().toString().startsWith("https");
    }

    @Override
    public String getAuthenticationScheme() {
        return CollectionAppSecurityContext.DEFAULT_APP_AUTH_SCHEME;
    }
}
