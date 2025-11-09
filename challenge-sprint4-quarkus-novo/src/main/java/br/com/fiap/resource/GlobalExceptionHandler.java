package br.com.fiap.resource;

import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.HashMap;
import java.util.Map;

@Provider
public class GlobalExceptionHandler implements ExceptionMapper<Exception> {

    @Override
    public Response toResponse(Exception exception) {
        Map<String, Object> body = new HashMap<>();

        if (exception instanceof NotFoundException) {
            body.put("status", Response.Status.NOT_FOUND.getStatusCode());
            body.put("error", "Not Found");
            body.put("message", exception.getMessage());
            return Response.status(Response.Status.NOT_FOUND).entity(body).build();
        }

        if (exception instanceof IllegalArgumentException) {
            body.put("status", Response.Status.BAD_REQUEST.getStatusCode());
            body.put("error", "Bad Request");
            body.put("message", exception.getMessage());
            return Response.status(Response.Status.BAD_REQUEST).entity(body).build();
        }

        // Para outras exceções não tratadas
        body.put("status", Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
        body.put("error", "Internal Server Error");
        body.put("message", "Ocorreu um erro inesperado no servidor.");
        
        // Logar a exceção completa para debug
        exception.printStackTrace();

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(body).build();
    }
}
