package pl.wsb.collection.api;

import pl.wsb.collection.api.consts.ApiEndpoints;
import pl.wsb.collection.api.handlers.ErrorHandler;
import pl.wsb.collection.repository.impl.CollectionTypeRepository;
import pl.wsb.collection.repository.impl.RoleRepository;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path(ApiEndpoints.COLLECTION_TYPE)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CollectionTypeResource {


    @GET
    public Response getAllCollectionTypes(
            @QueryParam(ApiEndpoints.PARAM_LIMIT) Integer limit,
            @QueryParam(ApiEndpoints.PARAM_OFFSET) Integer offset,
            @QueryParam(ApiEndpoints.PARAM_SEARCH) String search
    ){
        try
        {
            CollectionTypeRepository collectionTypeRepository  = new CollectionTypeRepository();
            return Response.status(
                    Response.Status.OK
            ).entity(
                    collectionTypeRepository.findAll(limit, offset, search)
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
    public Response getCollectionType(@PathParam(ApiEndpoints.PARAM_ID) Integer id){
        try
        {
            CollectionTypeRepository collectionTypeRepository  = new CollectionTypeRepository();
            return Response.status(
                    Response.Status.OK
            ).entity(
                    collectionTypeRepository.find(id)
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
    public Response postCollectionType(){
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
    public Response putCollectionType(){
        return Response
                .status(Response.Status.OK)
                .entity("mock call ok...")
                .build();
    }

    @DELETE
    @Path(ApiEndpoints.PATH_PARAM_ID)
    public Response deleteCollectionType(@PathParam(ApiEndpoints.PARAM_ID) Integer id){
        return Response
                .status(Response.Status.NO_CONTENT)
                .build();
    }
}
