package pl.wsb.collection.security.annotation;


import org.apache.commons.lang.time.DateUtils;
import org.codehaus.plexus.util.StringUtils;
import pl.wsb.collection.api.handlers.ErrorHandler;
import pl.wsb.collection.exceptions.UnauthenticatedException;
import pl.wsb.collection.model.ApiToken;
import pl.wsb.collection.model.UserAccount;
import pl.wsb.collection.repository.EntityManagerHelper;
import pl.wsb.collection.repository.impl.ApiTokenRepository;
import pl.wsb.collection.security.CollectionAppSecurityContext;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;
import java.util.Date;


@Authenticate
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticateFilter implements ContainerRequestFilter {

    @Context
    UriInfo uriInfo;

    @Override
    public void filter(ContainerRequestContext containerRequestContext) {
        final String AUTH_HEADER_BEARER = "Bearer: ";
        try{
            String authorizationHeader = containerRequestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
            if(StringUtils.isBlank(authorizationHeader)){
                throw new UnauthenticatedException("No authorization data provided...");
            }

            if(!authorizationHeader.startsWith(AUTH_HEADER_BEARER)){
                throw new UnauthenticatedException("No valid format of authorization data...");
            }

            containerRequestContext.setSecurityContext(
                    new CollectionAppSecurityContext(
                        this.validateToken( authorizationHeader.substring(AUTH_HEADER_BEARER.length()).trim() ),
                        this.uriInfo
                    )
            );
        }
        catch (UnauthenticatedException ex)
        {
            containerRequestContext.abortWith(
                    Response.status(Response.Status.UNAUTHORIZED).entity(ErrorHandler.getErrorResponse(ex)).build()
            );
        }
    }

    private UserAccount validateToken(String accessToken) throws UnauthenticatedException
    {
        final int TOKEN_VALIDATION_MINUTES = 30;

        if (StringUtils.isBlank(accessToken)) {
            throw new UnauthenticatedException();
        }

        ApiToken apiToken = ApiTokenRepository.findByAccessToken(accessToken);
        if (apiToken == null) {
            throw new UnauthenticatedException();
        }

        if (apiToken.getUserAccount() == null) {
            throw new UnauthenticatedException();
        }

        EntityManagerHelper.startTransaction();
        ApiTokenRepository tokenRepository = new ApiTokenRepository();
        apiToken.setValidTo(
                DateUtils.addMinutes(
                        new Date(),
                        TOKEN_VALIDATION_MINUTES
                )
        );
        tokenRepository.merge(apiToken);
        EntityManagerHelper.commitTransaction();
        return apiToken.getUserAccount();
    }
}
