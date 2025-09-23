package controllers;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import services.LabSeqService;
import java.math.BigInteger;

@Path("/labseq")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LabSeqController {

    private final LabSeqService labSeqService;

    public LabSeqController(LabSeqService labSeqService) {
        this.labSeqService = labSeqService;
    }

    @GET
    @Path("/{n}")
    public Response getLabSeq(@PathParam("n") int n) {
        try {
            BigInteger value = labSeqService.calculateLabSeq(n);
            return Response.ok(value).build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(e.getMessage())
                    .build();
        }
    }
}
