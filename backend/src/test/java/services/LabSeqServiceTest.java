package services;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.math.BigInteger;

class LabSeqServiceTest {
    private final LabSeqService service = new LabSeqService();

    @Test
    public void testNegativeInputThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> service.calculateLabSeq(-1));
    }

    @Test
    void testCalculateLabSeqZero() {
        assertEquals(BigInteger.ZERO, service.calculateLabSeq(0));
    }

    @Test
    void testCalculateLabSeqOne() {
        assertEquals(BigInteger.ONE, service.calculateLabSeq(1));
    }

    @Test
    void testCalculateLabSeqTwo() {
        assertEquals(BigInteger.ZERO, service.calculateLabSeq(2));
    }

    @Test
    void testCalculateLabSeqThree() {
        assertEquals(BigInteger.ONE, service.calculateLabSeq(3));
    }

    @Test
    void testCalculateLabSeqFour() {
        assertEquals(BigInteger.ONE, service.calculateLabSeq(4));
    }

    @Test
    void testCalculateLabSeqFive() {
        assertEquals(BigInteger.ONE, service.calculateLabSeq(5));
    }

    @Test
    void testCalculateLabSeqTen() {
        assertEquals(BigInteger.valueOf(3), service.calculateLabSeq(10));
    }
}