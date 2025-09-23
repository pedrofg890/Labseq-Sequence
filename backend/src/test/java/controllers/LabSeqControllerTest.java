package controllers;

import dto.ErrorResponse;
import org.junit.jupiter.api.*;
import services.LabSeqService;

import jakarta.ws.rs.core.Response;
import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class LabSeqControllerTest {
    private LabSeqService labSeqService;
    private LabSeqController controller;

    @BeforeEach
    void setUp() {
        labSeqService = mock(LabSeqService.class);
        controller = new LabSeqController();
        controller.labSeqService = labSeqService; // inject mock service
    }

    @Test
    void testGetLabSeq_OK() {
        // Arrange
        when(labSeqService.calculateLabSeq(5)).thenReturn(BigInteger.valueOf(42));
        // Act
        Response response = controller.getLabSeq(5);
        // Assert
        assertEquals(200, response.getStatus());
        assertEquals(BigInteger.valueOf(42), response.getEntity());
    }

    @Test
    void testGetLabSeq_BadRequest() {
        // Arrange
        when(labSeqService.calculateLabSeq(-1)).thenThrow(new IllegalArgumentException("Index must be non-negative"));
        // Act
        Response response = controller.getLabSeq(-1);
        // Assert
        assertEquals(400, response.getStatus());
        assertTrue(response.getEntity() instanceof ErrorResponse);
        ErrorResponse error = (ErrorResponse) response.getEntity();
        assertEquals("Index must be non-negative", error.getMessage());
    }

    @Test
    void testGetLabSeq_InternalServerError() {
        // Arrange
        when(labSeqService.calculateLabSeq(99)).thenThrow(new RuntimeException("Unexpected failure"));
        // Act
        Response response = controller.getLabSeq(99);
        // Assert
        assertEquals(500, response.getStatus());
        assertTrue(response.getEntity() instanceof ErrorResponse);
        ErrorResponse error = (ErrorResponse) response.getEntity();
        assertTrue(error.getMessage().contains("Internal server error"));
    }
}
