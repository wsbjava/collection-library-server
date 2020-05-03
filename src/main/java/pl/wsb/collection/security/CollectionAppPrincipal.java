package pl.wsb.collection.security;

import pl.wsb.collection.model.UserAccount;

import java.security.Principal;

public class CollectionAppPrincipal implements Principal {

    private final UserAccount user;

    private static final String DEFAULT_EMAIL = "---";

    public CollectionAppPrincipal(UserAccount user){
        this.user = user;
    }

    @Override
    public String getName() {
        if(this.user != null){
            return this.user.getEmail();
        }
        return CollectionAppPrincipal.DEFAULT_EMAIL;
    }
}
