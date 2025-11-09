package br.com.fiap.resource;

import br.com.fiap.beans.Especialidade;
import br.com.fiap.bo.EspecialidadeBO;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/especialidades")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EspecialidadeResource {

    @Inject
    EspecialidadeBO especialidadeBO;

    @GET
    public List<Especialidade> listarTodos() {
        return especialidadeBO.listarTodos();
    }

    @GET
    @Path("/{id}")
    public Especialidade buscarPorId(@PathParam("id") Long id) {
        return especialidadeBO.buscarPorId(id);
    }

    @POST
    public Response criar(Especialidade especialidade) {
        Especialidade novaEspecialidade = especialidadeBO.criar(especialidade);
        return Response.status(Response.Status.CREATED).entity(novaEspecialidade).build();
    }

    @PUT
    @Path("/{id}")
    public Especialidade atualizar(@PathParam("id") Long id, Especialidade especialidade) {
        return especialidadeBO.atualizar(id, especialidade);
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") Long id) {
        especialidadeBO.deletar(id);
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
