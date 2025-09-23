package services;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import java.math.BigInteger;

/**
 * Unit tests for {@link LabSeqService}.
 * <p>
 * Tests base cases, recursive cases, edge cases, and input validation for the LabSeq sequence.
 */
class LabSeqServiceTest {
    private final LabSeqService service = new LabSeqService();

    //Tests that negative input throws IllegalArgumentException
    @Test
    void testCalculateLabSeq_NegativeInput_ThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> service.calculateLabSeq(-1));
    }

    //Tests base cases of the LabSeq sequence
    @ParameterizedTest
    @CsvSource({
        "0,0",
        "1,1",
        "2,0",
        "3,1"
    })
    void testCalculateLabSeq_BaseCases(int n, int expected) {
        assertEquals(BigInteger.valueOf(expected), service.calculateLabSeq(n));
    }

    //Tests boundary and small recursive cases of the LabSeq sequence
    @ParameterizedTest
    @CsvSource({
        "4,1",
        "5,1",
        "6,1",
        "7,2",
        "10,3"
    })
    void testCalculateLabSeq_RecursiveCases(int n, int expected) {
        assertEquals(BigInteger.valueOf(expected), service.calculateLabSeq(n));
    }

    //Tests edge cases for large n to ensure performance and correctness
    @ParameterizedTest
    @CsvSource({
        "100,182376579",
        "1000,167160955464809121248529556576412921573832214518206312831697195479802277127983539364995"
    })
    void testCalculateLabSeq_EdgeCases(int n, String expected) {
        assertEquals(new BigInteger(expected), service.calculateLabSeq(n));
    }
}