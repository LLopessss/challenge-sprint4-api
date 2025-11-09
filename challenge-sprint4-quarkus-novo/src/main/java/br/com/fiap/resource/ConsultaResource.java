package br.com.fiap.resource;

import br.com.fiap.beans.Consulta;
import br.com.fiap.bo.ConsultaBO;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/consultas")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ConsultaResource {

    @Inject
    ConsultaBO consultaBO;

    @GET
    public List<Consulta> listarTodos() {
        return consultaBO.listarTodos();
    }

    @GET
    @Path("/{id}")
    public Consulta buscarPorId(@PathParam("id") Long id) {
        return consultaBO.buscarPorId(id);
    }

    @POST
    public Response criar(Consulta consulta) {
        try {
            Consulta novaConsulta = consultaBO.criar(consulta);
            return Response.status(Response.Status.CREATED).entity(novaConsulta).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Consulta atualizar(@PathParam("id") Long id, Consulta consulta) {
        return consultaBO.atualizar(id, consulta);
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") Long id) {
        consultaBO.deletar(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
