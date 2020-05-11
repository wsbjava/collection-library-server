package pl.wsb.collection.api;


import pl.wsb.collection.api.consts.ApiEndpoints;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path(ApiEndpoints.COLLECTION_ENTRY)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CollectionEntryResource {

    @GET
    public Response getAllCollectionEntries(
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
    public Response getAllCollectionEntry(Integer id){
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
    public Response postCollectionEntry(){
        return Response
                .status(Response.Status.OK)
                .entity("mock call ok...")
                .build();
    }

    @DELETE
    @Path(ApiEndpoints.QUERY_PARAM_ID)
    public Response deleteAllCollectionEntry(Integer id){
        return Response
                .status(Response.Status.OK)
                .entity("mock call ok...")
                .build();
    }

    @PUT
    @Path(ApiEndpoints.COLLECTION_ENTRY_ID_ACCEPT)
    public Response putCollectionEntryIdAccept(Integer id){
        return Response
                .status(Response.Status.OK)
                .entity("mock call ok...")
                .build();
    }

    @PUT
    @Path(ApiEndpoints.COLLECTION_ENTRY_ID_REJECT)
    public Response putCollectionEntryIdReject(Integer id){
        return Response
                .status(Response.Status.OK)
                .entity("mock call ok...")
                .build();
    }


}
