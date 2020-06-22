package pl.wsb.collection.api;

import com.google.protobuf.Api;
import pl.wsb.collection.api.consts.ApiEndpoints;
import pl.wsb.collection.api.handlers.ErrorHandler;
import pl.wsb.collection.repository.impl.CollectionTypeRepository;
import pl.wsb.collection.repository.impl.GenreRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path(ApiEndpoints.GENRE)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GenreResource {

    @GET
    public Response getAllGenres(
            @QueryParam(ApiEndpoints.PARAM_LIMIT) Integer limit,
            @QueryParam(ApiEndpoints.PARAM_OFFSET) Integer offset,
            @QueryParam(ApiEndpoints.PARAM_SEARCH) String search
    ){
        try
        {
            GenreRepository genreRepository  = new GenreRepository();
            return Response.status(
                    Response.Status.OK
            ).entity(
                    genreRepository.findAll(limit, offset, search)
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
    public Response getGenre(@PathParam(ApiEndpoints.PARAM_ID) Integer id){
        try
        {
            GenreRepository genreRepository  = new GenreRepository();
            return Response.status(
                    Response.Status.OK
            ).entity(
                    genreRepository.find(id)
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
    @Path(ApiEndpoints.ABBR + ApiEndpoints.PATH_PARAM_ABBR)
    public Response getGenreByAbbr(@PathParam(ApiEndpoints.PARAM_ABBR) String abbr){
        try
        {
            GenreRepository genreRepository  = new GenreRepository();
            return Response.status(
                    Response.Status.OK
            ).entity(
                    genreRepository.findByAbbr(abbr)
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

    /**
     *
     * @TODO - request na podstawie swaggera
     * @return
     */
    @POST
    public Response postGenre(){
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
    public Response putGenre(){
        return Response
                .status(Response.Status.OK)
                .entity("mock call ok...")
                .build();
    }

    @DELETE
    @Path(ApiEndpoints.PATH_PARAM_ID)
    public Response deleteGenre(@PathParam("id") Integer id){
        return Response
                .status(Response.Status.NO_CONTENT)
                .build();
    }

}
