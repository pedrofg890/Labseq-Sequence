package controllers;

import dto.ErrorResponse;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import services.LabSeqService;
import java.math.BigInteger;
import org.jboss.logging.Logger;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;

/**
 * REST controller for the LabSeq sequence API.
 * <p>
 * Exposes an endpoint to retrieve the LabSeq value for a given non-negative integer index.
 * Handles input validation, error responses, and logging for invalid or unexpected requests.
 * <p>
 * API documentation is provided via OpenAPI annotations for client consumption.
 */
@Path("/labseq")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LabSeqController {

    private static final Logger LOG = Logger.getLogger(LabSeqController.class);

    @Inject
    LabSeqService labSeqService;

    /**
     * Returns the LabSeq value for the given non-negative integer index.
     * <p>
     * Handles and logs invalid input (400) and unexpected errors (500), returning structured error responses.
     *
     * @param n the non-negative integer index for the LabSeq sequence
     * @return HTTP 200 with the LabSeq value, 400 for invalid input, or 500 for server errors
     */
    @GET
    @Path("/{n}")
    @Operation(summary = "Get Labseq Sequence Value", description = "Returns the Labseq Sequence value for a given non-negative integer n.")
    @APIResponses({
        @APIResponse(responseCode = "200", description = "Labseq value successfully calculated",
            content = @Content(schema = @Schema(implementation = BigInteger.class))),
        @APIResponse(responseCode = "400", description = "Invalid input provided",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
        @APIResponse(responseCode = "500", description = "Internal server error",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public Response getLabSeq(
            @Parameter(description = "Non-negative integer index for LabSeq", required = true, example = "10")
            @PathParam("n") int n) {
        try {
            BigInteger value = labSeqService.calculateLabSeq(n);
            return Response.ok(value).build();      // 200 OK
        } catch (IllegalArgumentException e) {      // 400 Bad Request
            LOG.warnf("Invalid input for n=%d: %s", n, e.getMessage());
            ErrorResponse error = new ErrorResponse(e.getMessage(), Response.Status.BAD_REQUEST.getStatusCode());
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(error)
                    .build();
        } catch (Exception e) {                     // 500 Internal Server Error
            LOG.errorf(e, "Unexpected error for n=%d", n);
            ErrorResponse error = new ErrorResponse("Internal server error: " + e.getMessage(), Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(error)
                    .build();
        }
    }
}