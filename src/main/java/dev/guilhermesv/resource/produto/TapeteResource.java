package dev.guilhermesv.resource.produto;

import java.util.List;

import javax.inject.Inject;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import dev.guilhermesv.application.Result;
import dev.guilhermesv.dto.produto.TapeteDTO;
import dev.guilhermesv.dto.produto.TapeteResponseDTO;
import dev.guilhermesv.service.produto.TapeteServiceImpl;


@Path("/tapetes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TapeteResource {

    @Inject
    private TapeteServiceImpl tapeteService;

    @GET
    public Response listAll() {
        List<TapeteResponseDTO> list = tapeteService.listAll();
        return Response.ok(list).build();
    }

    @GET
    @Path("/search/{name}")
    public Response findByName(@PathParam("name") String name) {   
        List<TapeteResponseDTO> list = tapeteService.findByDescription(name);
        return Response.ok(list).build();
    }

    @GET
    @Path("/{id}")
    public TapeteResponseDTO findById(@PathParam("id") Long id) {
        return tapeteService.findById(id);
    }

    @POST
    public Response persist(@RequestBody TapeteDTO receivedEntity) {
        try {
            TapeteResponseDTO entity =  tapeteService.persist(receivedEntity);
            return Response.status(Status.CREATED).entity(entity).build();
        }  catch(ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            return Response.status(Status.NOT_FOUND).entity(result).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, @RequestBody TapeteDTO receivedEntity) {
        try {
            TapeteResponseDTO entity = tapeteService.update(id, receivedEntity);;
            return Response.status(Status.NO_CONTENT).entity(entity).build();
        
        }  catch(ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            return Response.status(Status.NOT_FOUND).entity(result).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteById(@PathParam("id") Long id) {
        tapeteService.deleteById(id);
        return Response.status(Status.NO_CONTENT).build();
    }

    @GET
    @Path("/count")
    public Response count() {
        Long count = tapeteService.count();
        return Response.status(Status.OK).entity(count).build();
    }
}