package pl.wsb.collection.api;


import com.google.protobuf.Api;
import pl.wsb.collection.api.consts.ApiEndpoints;
import pl.wsb.collection.api.handlers.ErrorHandler;
import pl.wsb.collection.repository.impl.RoleRepository;

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

        try
        {
            RoleRepository roleRepository = new RoleRepository();
            return Response.status(
                    Response.Status.OK
            ).entity(
                    roleRepository.findAll(limit, offset, search)
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
    public Response getRole(@PathParam(ApiEndpoints.PARAM_ID) Integer id){
        try
        {
            RoleRepository roleRepository = new RoleRepository();
            return Response.status(
                    Response.Status.OK
            ).entity(
                    roleRepository.find(id)
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
    public Response getRoleByAbbr(@PathParam(ApiEndpoints.PARAM_ABBR) String abbr){
        try
        {
            RoleRepository roleRepository = new RoleRepository();
            return Response.status(
                    Response.Status.OK
            ).entity(
                    roleRepository.findByAbbr(abbr)
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

}
