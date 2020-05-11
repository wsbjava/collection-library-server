package pl.wsb.collection.api;


import pl.wsb.collection.api.consts.ApiEndpoints;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path(ApiEndpoints.ROLE)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RoleResource {

    @GET
    public Response getAllRoles(
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
    public Response getRole(Integer id){
        return Response
                .status(Response.Status.OK)
                .entity("mock call ok...")
                .build();
    }

}
