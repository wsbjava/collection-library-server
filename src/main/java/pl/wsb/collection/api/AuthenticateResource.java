package pl.wsb.collection.api;

import pl.wsb.collection.api.consts.ApiEndpoints;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.*;


@Path(ApiEndpoints.AUTHENTICATE)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthenticateResource {

    /**
     *
     * @TODO Arguemnt request na podstawie klasy wygenrwanej ze swaggera
     *
     * @return
     */
    @POST
    public Response postAuthenticate(){
        return Response
                .status(Response.Status.OK)
                .entity("mock call ok")
                .build();
    }

}
