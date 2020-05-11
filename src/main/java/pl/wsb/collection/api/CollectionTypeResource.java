package pl.wsb.collection.api;

import pl.wsb.collection.api.consts.ApiEndpoints;

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
        return Response
                .status(Response.Status.OK)
                .entity("mock call ok...")
                .build();
    }

    @GET
    @Path(ApiEndpoints.QUERY_PARAM_ID)
    public Response getCollectionType(Integer id){
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
    @Path(ApiEndpoints.QUERY_PARAM_ID)
    public Response deleteCollectionType(Integer id){
        return Response
                .status(Response.Status.NO_CONTENT)
                .build();
    }
}
