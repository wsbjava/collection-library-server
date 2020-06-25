package pl.wsb.collection.api;

import pl.wsb.collection.api.consts.ApiEndpoints;
import pl.wsb.collection.api.handlers.ErrorHandler;
import pl.wsb.collection.exceptions.ValidationException;
import pl.wsb.collection.model.Author;
import pl.wsb.collection.model.AuthorRequest;
import pl.wsb.collection.model.User;
import pl.wsb.collection.repository.impl.AuthorRepository;
import pl.wsb.collection.repository.impl.UserAccountRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path(ApiEndpoints.AUTHOR)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthorResource {

    @GET
    public Response getAllAuthors(
            @QueryParam(ApiEndpoints.PARAM_LIMIT) Integer limit,
            @QueryParam(ApiEndpoints.PARAM_OFFSET) Integer offset,
            @QueryParam(ApiEndpoints.PARAM_SEARCH) String search
    ){
        try
        {
            AuthorRepository authorRepository = new AuthorRepository();
            return Response.status(
                    Response.Status.OK
            ).entity(
                    authorRepository.findAll(limit, offset, search)
            ).build();
        } catch (Exception e) {
            return Response.status(
                    Response.Status.INTERNAL_SERVER_ERROR
            ).entity(
                    ErrorHandler.getErrorResponse(e)
            ).build();
        }
    }

    @GET
    @Path(ApiEndpoints.PATH_PARAM_ID)
    public Response getAuthor(@PathParam(ApiEndpoints.PARAM_ID) Integer id){

        try
        {
            AuthorRepository authorRepository = new AuthorRepository();
            return Response.status(
                    Response.Status.OK
            ).entity(
                    new Author().getAuthorFromDB(authorRepository.find(id))
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
    @POST
    public Response postAuthor(AuthorRequest body){

        try
        {
            AuthorRepository authorRepository = new AuthorRepository();
            return Response.status(
                    Response.Status.OK
            ).entity(
                    new Author().getAuthorFromDB(authorRepository.registerBody(body))
            ).build();
        } catch (ValidationException e) {
            e.printStackTrace();
        }

        return Response
                .status(Response.Status.OK)
                .entity("mock call ok...")
                .build();
    }

    /**
     *
     * @TODO - request na podstawie swaggera
     * @return
     */
    @PUT
    public Response putAuthor(){
        return Response
                .status(Response.Status.OK)
                .entity("mock call ok...")
                .build();
    }

    @DELETE
    @Path(ApiEndpoints.PATH_PARAM_ID)
    public Response deleteAuthor(@QueryParam(ApiEndpoints.PARAM_ID) Integer id){

        try
        {
            AuthorRepository authorRepository = new AuthorRepository();
            authorRepository.delete(authorRepository.find(id));
            return Response.status(
                    Response.Status.OK
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
