package pl.wsb.collection.api;

import com.google.protobuf.Api;
import pl.wsb.collection.api.consts.ApiEndpoints;
import pl.wsb.collection.api.handlers.ErrorHandler;
import pl.wsb.collection.exceptions.ValidationException;
import pl.wsb.collection.model.ItemRentalRequest;
import pl.wsb.collection.repository.impl.CollectionEntryRepository;
import pl.wsb.collection.repository.impl.CollectionLibraryRepository;
import pl.wsb.collection.security.annotation.Authenticate;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path(ApiEndpoints.COLLECTION_LIBRARY)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CollectionLibraryResource {

    @GET
    @Authenticate
    public Response getAllCollectionEntries(
            @QueryParam(ApiEndpoints.PARAM_ID) Integer id,
            @QueryParam(ApiEndpoints.PARAM_LIMIT) Integer limit,
            @QueryParam(ApiEndpoints.PARAM_OFFSET) Integer offset,
            @QueryParam(ApiEndpoints.PARAM_SEARCH) String search
    ){
        try
        {

            CollectionLibraryRepository collectionLibraryRepository = new CollectionLibraryRepository();
            return Response.status(
                    Response.Status.OK
            ).entity(
                    collectionLibraryRepository.findAllByUser(id, 0,0, "")
            ).build();
        } catch (Exception ex) {
            return Response.status(
                    Response.Status.INTERNAL_SERVER_ERROR
            ).entity(
                    ErrorHandler.getErrorResponse(ex)
            ).build();
        }
    }

    @PUT
    @Path(ApiEndpoints.COLLECTION_LIBRARY_RENT)
    @Authenticate
    public Response rentCollectionEntry( @QueryParam(ApiEndpoints.PARAM_ID) Integer id, ItemRentalRequest body){
        try {
            CollectionLibraryRepository collectionLibraryRepository = new CollectionLibraryRepository();
            return Response.status(
                    Response.Status.OK
            ).entity(
                    collectionLibraryRepository.rentEntry(id, body)
            ).build();
        }
        catch (ValidationException ex) {

            System.out.println(ex.getMessage());

            return Response.status(
                    Response.Status.BAD_REQUEST
            ).entity(
                    ErrorHandler.getErrorResponse(ex)
            ).build();
        }
        catch (Exception ex) {
            return Response.status(
                    Response.Status.INTERNAL_SERVER_ERROR
            ).entity(
                    ErrorHandler.getErrorResponse(ex)
            ).build();
        }
    }

    @PUT
    @Path(ApiEndpoints.COLLECTION_LIBRARY_RETURN)
    @Authenticate
    public Response returnCollectionEntry( @QueryParam(ApiEndpoints.PARAM_ID) Integer id, ItemRentalRequest body){
        try {
            CollectionLibraryRepository collectionLibraryRepository = new CollectionLibraryRepository();
            return Response.status(
                    Response.Status.OK
            ).entity(
                    collectionLibraryRepository.returnEntry(id, body)
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
