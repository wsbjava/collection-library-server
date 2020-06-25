package pl.wsb.collection.api;

import org.apache.commons.lang.StringUtils;
//import org.apache.http.util.EntityUtils;
import pl.wsb.collection.api.consts.ApiEndpoints;
import pl.wsb.collection.api.handlers.ErrorHandler;
import pl.wsb.collection.exceptions.UnauthenticatedException;
import pl.wsb.collection.exceptions.ValidationException;
import pl.wsb.collection.hibernate.UserAccount;
import pl.wsb.collection.model.AuthenticationRequest;
import pl.wsb.collection.model.AuthenticationResponse;
import pl.wsb.collection.repository.impl.ApiTokenRepository;
import pl.wsb.collection.repository.impl.UserAccountRepository;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;


@Path(ApiEndpoints.AUTHENTICATE)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthenticateResource {

    /**
     *
     * @TODO Arguemnt request na podstawie klasy wygenrwanej ze swaggera
     *
     * @return
     */
    @POST
    public Response postAuthenticate(AuthenticationRequest body){

        try{
            if(body == null){
                throw new ValidationException("No request data provided..");
            }

            if ((StringUtils.isBlank(body.getEMail())) ||
                    (StringUtils.isBlank(body.getPassword()))) {
                throw new ValidationException("No credentials data provided...");
            }

            UserAccountRepository userAccountRepository = new UserAccountRepository();
            UserAccount userAccount = userAccountRepository.findByEmail(body.getEMail());
            if (userAccount == null) {
                throw new UnauthenticatedException();
            } //if
            if (!userAccount.validatePass(body.getPassword())) {
                throw new UnauthenticatedException();
            }

            ApiTokenRepository apiTokenRepository = new ApiTokenRepository();

            AuthenticationResponse authenticationResponse = AuthenticationResponse.createFromApiToken(
                    apiTokenRepository.generateApiToken(
                        userAccount
            ));


            Response response = Response.status(
                    Response.Status.OK
            ).entity(
                    authenticationResponse
            ).build();


            System.out.println(response.getStringHeaders());
            System.out.println(response.toString());
            System.out.println(response.getEntity().toString());
            return response;
        }
        catch (UnauthenticatedException ex) {
            return Response.status(
                    Response.Status.UNAUTHORIZED
            ).entity(
                    ErrorHandler.getErrorResponse(ex)
            ).build();
        } catch (ValidationException ex) {
            return Response.status(
                    Response.Status.BAD_REQUEST
            ).entity(
                    ErrorHandler.getErrorResponse(ex)
            ).build();
        } catch (Exception ex) {
            return Response.status(
                    Response.Status.INTERNAL_SERVER_ERROR
            ).entity(
                    ErrorHandler.getErrorResponse(ex)
            ).build();
        }
    }

}
