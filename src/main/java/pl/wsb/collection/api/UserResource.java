package pl.wsb.collection.api;

import com.google.protobuf.Api;
import pl.wsb.collection.api.consts.ApiEndpoints;

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
    public Response postUser() {
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
        return Response
                .status(Response.Status.OK)
                .entity("mock call ok...")
                .build();
    }

    @DELETE
    @Path(ApiEndpoints.QUERY_PARAM_ID)
    public Response deleteUser(Integer id){
        return Response
                .status(Response.Status.NO_CONTENT)
                .build();
    }

    @GET
    @Path(ApiEndpoints.QUERY_PARAM_ID)
    public Response getUser(Integer id){
        return Response
                .status(Response.Status.OK)
                .entity("mock call ok...")
                .build();
    }

}
