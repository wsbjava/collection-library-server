package pl.wsb.collection.api;

import pl.wsb.collection.api.consts.ApiEndpoints;
import pl.wsb.collection.api.handlers.ErrorHandler;
import pl.wsb.collection.exceptions.ValidationException;
import pl.wsb.collection.model.RegisterUserRequest;
import pl.wsb.collection.model.User;
import pl.wsb.collection.repository.impl.UserAccountRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path(ApiEndpoints.USER)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    /**
     *
     * @TODO - request na podstawie swaggera
     * @return
     */
    @POST
    public Response postUser(RegisterUserRequest body) {
        try
        {
            UserAccountRepository userAccountRepository = new UserAccountRepository();
            return Response.status(
                    Response.Status.OK
            ).entity(
                    User.createFromUserAccount(
                            userAccountRepository.registerUser(body)
                    )
            ).build();
        }
        catch (ValidationException ex) {
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

    /**
     *
     * @TODO - request na podstawie swaggera
     * @return
     */
    @PUT
    public Response putUser() {
        return Response
                .status(Response.Status.OK)
                .entity("mock call ok...")
                .build();
    }

    @GET
    public Response getAllUser(
            @QueryParam(ApiEndpoints.PARAM_LIMIT) Integer limit,
            @QueryParam(ApiEndpoints.PARAM_OFFSET) Integer offset,
            @QueryParam(ApiEndpoints.PARAM_SEARCH) String search
    ){

        try
        {
            UserAccountRepository userAccountRepository = new UserAccountRepository();
            return Response.status(
                    Response.Status.OK
            ).entity(
                    userAccountRepository.findAll(limit, offset, search)
            ).build();
        } catch (Exception e) {
            return Response.status(
                    Response.Status.INTERNAL_SERVER_ERROR
            ).entity(
                    ErrorHandler.getErrorResponse(e)
            ).build();
        }
    }

    @DELETE
    @Path(ApiEndpoints.PATH_PARAM_ID)
    public Response deleteUser(@PathParam(ApiEndpoints.PARAM_ID) Integer id){

        try
        {
            UserAccountRepository userAccountRepository = new UserAccountRepository();
            userAccountRepository.delete(userAccountRepository.find(id));
            return Response.status(
                    Response.Status.OK
            ).build();
        }
        catch (Exception ex)
        {
            return Response.status(
                    Response.Status.INTERNAL_SERVER_ERROR
            ).entity(
                    ErrorHandler.getErrorResponse(ex)
            ).build();
        }
    }

    @GET
    @Path(ApiEndpoints.PATH_PARAM_ID)
    public Response getUser(@PathParam(ApiEndpoints.PARAM_ID) Integer id){
        try
        {
            UserAccountRepository userAccountRepository = new UserAccountRepository();
            return Response.status(
                    Response.Status.OK
            ).entity(
                    userAccountRepository.find(id)
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
