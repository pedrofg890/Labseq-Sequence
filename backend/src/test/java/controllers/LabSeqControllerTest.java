package controllers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import services.LabSeqService;

import java.math.BigInteger;

class LabSeqControllerTest {
    private LabSeqService labSeqService;
    private LabSeqController controller;

    @BeforeEach
    void setUp() {
        labSeqService = mock(LabSeqService.class);
        controller = new LabSeqController(labSeqService);
    }

    @Test
    void testNegativeInput() {
        int n = -1;
        when(labSeqService.calculateLabSeq(n)).thenThrow(new IllegalArgumentException("Index must be non-negative"));
        Response response = controller.getLabSeq(n);
        assertEquals(Response.Status.BAD_REQUEST.getStatusCode(), response.getStatus());
        assertEquals("Index must be non-negative", response.getEntity());
    }

    @Test
    void testZero() {
        int n = 0;
        when(labSeqService.calculateLabSeq(n)).thenReturn(BigInteger.ZERO);
        Response response = controller.getLabSeq(n);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(BigInteger.ZERO, response.getEntity());
    }

    @Test
    void testOne() {
        int n = 1;
        when(labSeqService.calculateLabSeq(n)).thenReturn(BigInteger.ONE);
        Response response = controller.getLabSeq(n);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(BigInteger.ONE, response.getEntity());
    }

    @Test
    void testTwo() {
        int n = 2;
        when(labSeqService.calculateLabSeq(n)).thenReturn(BigInteger.ZERO);
        Response response = controller.getLabSeq(n);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(BigInteger.ZERO, response.getEntity());
    }

    @Test
    void testThree() {
        int n = 3;
        when(labSeqService.calculateLabSeq(n)).thenReturn(BigInteger.ONE);
        Response response = controller.getLabSeq(n);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(BigInteger.ONE, response.getEntity());
    }

    @Test
    void testFour() {
        int n = 4;
        when(labSeqService.calculateLabSeq(n)).thenReturn(BigInteger.ZERO);
        Response response = controller.getLabSeq(n);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(BigInteger.ZERO, response.getEntity());
    }

    @Test
    void testFive() {
        int n = 5;
        when(labSeqService.calculateLabSeq(n)).thenReturn(BigInteger.ONE);
        Response response = controller.getLabSeq(n);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(BigInteger.ONE, response.getEntity());
    }

    @Test
    void testTen() {
        int n = 10;
        BigInteger expected = BigInteger.valueOf(6);
        when(labSeqService.calculateLabSeq(n)).thenReturn(expected);
        Response response = controller.getLabSeq(n);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(expected, response.getEntity());
    }
}