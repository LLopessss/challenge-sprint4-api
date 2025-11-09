package br.com.fiap.resource;

import br.com.fiap.beans.ProfissionalSaude;
import br.com.fiap.bo.ProfissionalSaudeBO;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/profissionais")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProfissionalSaudeResource {

    @Inject
    ProfissionalSaudeBO profissionalSaudeBO;

    @GET
    public List<ProfissionalSaude> listarTodos() {
        return profissionalSaudeBO.listarTodos();
    }

    @GET
    @Path("/{id}")
    public ProfissionalSaude buscarPorId(@PathParam("id") Long id) {
        return profissionalSaudeBO.buscarPorId(id);
    }

    @POST
    public Response criar(ProfissionalSaude profissionalSaude) {
        try {
            ProfissionalSaude novoProfissional = profissionalSaudeBO.criar(profissionalSaude);
            return Response.status(Response.Status.CREATED).entity(novoProfissional).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    public ProfissionalSaude atualizar(@PathParam("id") Long id, ProfissionalSaude profissionalSaude) {
        return profissionalSaudeBO.atualizar(id, profissionalSaude);
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") Long id) {
        profissionalSaudeBO.deletar(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
