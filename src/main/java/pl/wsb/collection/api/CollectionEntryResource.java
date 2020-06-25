package pl.wsb.collection.api;


import pl.wsb.collection.api.consts.ApiEndpoints;
import pl.wsb.collection.api.handlers.ErrorHandler;
import pl.wsb.collection.exceptions.ValidationException;
import pl.wsb.collection.model.Item;
import pl.wsb.collection.model.ItemList;
import pl.wsb.collection.model.ItemRequest;
import pl.wsb.collection.repository.impl.CollectionEntryRepository;
import pl.wsb.collection.security.annotation.Authenticate;

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
        try
        {
            CollectionEntryRepository collectionEntryRepository = new CollectionEntryRepository();
            return Response.status(
                    Response.Status.OK
            ).entity(
                    new ItemList().createFromCollectryEntryList(collectionEntryRepository.findAll(limit,offset,search))
            ).build();
        } catch (Exception ex) {
            return Response.status(
                    Response.Status.INTERNAL_SERVER_ERROR
            ).entity(
                    ErrorHandler.getErrorResponse(ex)
            ).build();
        }
    }

    @GET
    @Path(ApiEndpoints.PENDING)
    public Response getAllPendingCollectionEntries(){
        try {
            CollectionEntryRepository collectionEntryRepository = new CollectionEntryRepository();
            return Response.status(
                    Response.Status.OK
            ).entity(
                    new ItemList().createFromCollectryEntryList(collectionEntryRepository.findByStatus("Pending"))
            ).build();
        } catch (Exception ex) {
            return Response.status(
                    Response.Status.INTERNAL_SERVER_ERROR
            ).entity(
                    ErrorHandler.getErrorResponse(ex)
            ).build();
        }
    }

    @GET
    @Path(ApiEndpoints.REJECT)
    public Response getAllRejectCollectionEntries(){
        try {
            CollectionEntryRepository collectionEntryRepository = new CollectionEntryRepository();
            return Response.status(
                    Response.Status.OK
            ).entity(
                    new ItemList().createFromCollectryEntryList(collectionEntryRepository.findByStatus("Rejected"))
            ).build();
        } catch (Exception ex) {
            return Response.status(
                    Response.Status.INTERNAL_SERVER_ERROR
            ).entity(
                    ErrorHandler.getErrorResponse(ex)
            ).build();
        }
    }

    @GET
    @Path(ApiEndpoints.ACCEPT)
    public Response getAllAcceptCollectionEntries(){
        try {
            CollectionEntryRepository collectionEntryRepository = new CollectionEntryRepository();
            return Response.status(
                    Response.Status.OK
            ).entity(
                    new ItemList().createFromCollectryEntryList(collectionEntryRepository.findByStatus("Accepted"))
            ).build();
        } catch (Exception ex) {
            return Response.status(
                    Response.Status.INTERNAL_SERVER_ERROR
            ).entity(
                    ErrorHandler.getErrorResponse(ex)
            ).build();
        }
    }

    @GET
    @Path(ApiEndpoints.PATH_PARAM_ID)
    public Response getCollectionEntry(@PathParam(ApiEndpoints.PARAM_ID) Integer id) {

        try {
            CollectionEntryRepository collectionEntryRepository = new CollectionEntryRepository();
            return Response.status(
                    Response.Status.OK
            ).entity(
                    new Item().getItemFromDB(collectionEntryRepository.find(id))
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
    @Authenticate
    public Response postCollectionEntry(ItemRequest body){
        try {
            CollectionEntryRepository collectionEntryRepository = new CollectionEntryRepository();
            return Response.status(
                    Response.Status.OK
            ).entity(
                    new Item().getItemFromDB(collectionEntryRepository.registerEntry(body))
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

    @DELETE
    @Path(ApiEndpoints.PATH_PARAM_ID)
    public Response deleteAllCollectionEntry(@PathParam("id") Integer id){
        try
        {
            CollectionEntryRepository collectionEntryRepository = new CollectionEntryRepository();
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

    @PUT
    @Path(ApiEndpoints.COLLECTION_ENTRY_ID_ACCEPT)
    public Response putCollectionEntryIdAccept(@PathParam(ApiEndpoints.PARAM_ID) Integer id){
        try
        {
            CollectionEntryRepository collectionEntryRepository = new CollectionEntryRepository();
            return Response.status(
                    Response.Status.OK
            ).entity(
                    new Item().getItemFromDB(collectionEntryRepository.acceptEntry(id))
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
    @Path(ApiEndpoints.COLLECTION_ENTRY_ID_REJECT)
    public Response putCollectionEntryIdReject(@PathParam(ApiEndpoints.PARAM_ID) Integer id){
        try
        {
            CollectionEntryRepository collectionEntryRepository = new CollectionEntryRepository();
            return Response.status(
                    Response.Status.OK
            ).entity(
                    new Item().getItemFromDB(collectionEntryRepository.rejectEntry(id))
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
    @Path(ApiEndpoints.COLLECTION_ENTRY_ID_ASSING_GENRE)
    public Response putAssignGenreToCollectionEntry(
            @PathParam(ApiEndpoints.PARAM_ID) Integer id,
            @PathParam(ApiEndpoints.PARAM_ABBR) String abbr)
    {
        try
        {
            CollectionEntryRepository collectionEntryRepository = new CollectionEntryRepository();
            return Response.status(
                    Response.Status.OK
            ).entity(
                    new Item().getItemFromDB(collectionEntryRepository.assingGenre(id, abbr))
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
    @Path(ApiEndpoints.COLLECTION_ENTRY_ID_UNASSING_GENRE)
    public Response putUnAssignGenreToCollectionEntry(
            @PathParam(ApiEndpoints.PARAM_ID) Integer id,
            @PathParam(ApiEndpoints.PARAM_ABBR) String abbr)
    {
        try
        {
            CollectionEntryRepository collectionEntryRepository = new CollectionEntryRepository();
            return Response.status(
                    Response.Status.OK
            ).entity(
                    new Item().getItemFromDB(collectionEntryRepository.unAssingGenre(id, abbr))
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
