package br.com.fiap.resource;

import br.com.fiap.beans.Paciente;
import br.com.fiap.bo.PacienteBO;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/pacientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PacienteResource {

    @Inject
    PacienteBO pacienteBO;

    @GET
    public List<Paciente> listarTodos() {
        return pacienteBO.listarTodos();
    }

    @GET
    @Path("/{id}")
    public Paciente buscarPorId(@PathParam("id") Long id) {
        return pacienteBO.buscarPorId(id);
    }

    @POST
    public Response criar(Paciente paciente) {
        try {
            Paciente novoPaciente = pacienteBO.criar(paciente);
            return Response.status(Response.Status.CREATED).entity(novoPaciente).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    public Paciente atualizar(@PathParam("id") Long id, Paciente paciente) {
        return pacienteBO.atualizar(id, paciente);
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") Long id) {
        pacienteBO.deletar(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
