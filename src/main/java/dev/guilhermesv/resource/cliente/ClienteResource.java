package dev.guilhermesv.resource.cliente;

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
import dev.guilhermesv.dto.cliente.ClienteDTO;
import dev.guilhermesv.dto.cliente.ClienteResponseDTO;
import dev.guilhermesv.service.cliente.ClienteServiceImpl;

@Path("/clientes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ClienteResource {

    @Inject
    private ClienteServiceImpl clienteService;

    @GET
    public Response listAll() {
        List<ClienteResponseDTO> list = clienteService.listAll();
        return Response.ok(list).build();
    }

    @GET
    @Path("/search/{name}")
    public Response findByName(@PathParam("name") String name) {   
        List<ClienteResponseDTO> list = clienteService.findByName(name);
        return Response.ok(list).build();
    }

    @GET
    @Path("/{id}")
    public ClienteResponseDTO findById(@PathParam("id") Long id) {
        return clienteService.findById(id);
    }

    @POST
    public Response persist(@RequestBody ClienteDTO receivedEntity) {
        try {
            ClienteResponseDTO entity =  clienteService.persist(receivedEntity);
            return Response.status(Status.CREATED).entity(entity).build();
        }  catch(ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            return Response.status(Status.NOT_FOUND).entity(result).build();
        }

    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, @RequestBody ClienteDTO receivedEntity) {
        try {
            ClienteResponseDTO entity = clienteService.update(id, receivedEntity);;
            return Response.status(Status.NO_CONTENT).entity(entity).build();
        
        }  catch(ConstraintViolationException e) {
            Result result = new Result(e.getConstraintViolations());
            return Response.status(Status.NOT_FOUND).entity(result).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteById(@PathParam("id") Long id) {
        clienteService.deleteById(id);
        return Response.status(Status.NO_CONTENT).build();
    }

    @GET
    @Path("/count")
    public Response count() {
        Long count = clienteService.count();
        return Response.status(Status.OK).entity(count).build();
    }
}