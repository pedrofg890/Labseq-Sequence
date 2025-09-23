package services;

import jakarta.enterprise.context.ApplicationScoped;
import io.quarkus.cache.CacheResult;

import java.math.BigInteger;

/**
 * Service for calculating values in the LabSeq sequence.
 * <p>
 * The LabSeq sequence is defined as:
 * <ul>
 *   <li>labseq(0) = 0</li>
 *   <li>labseq(1) = 1</li>
 *   <li>labseq(2) = 0</li>
 *   <li>labseq(3) = 1</li>
 *   <li>labseq(n) = labseq(n-4) + labseq(n-3) for n &gt;= 4</li>
 * </ul>
 * This service uses caching for performance and throws an IllegalArgumentException for negative indices.
 */
@ApplicationScoped
public class LabSeqService {

    /**
     * Calculates the LabSeq value at the given index n.
     * <p>
     * Uses an iterative approach and caches results for efficiency.
     *
     * @param n the non-negative index in the LabSeq sequence
     * @return the LabSeq value as a BigInteger
     * @throws IllegalArgumentException if n is negative
     */
    @CacheResult(cacheName = "labSeq-cache")
    public BigInteger calculateLabSeq(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Index must be non-negative");
        }

        // Initial Conditions
        if (n == 0) return BigInteger.ZERO;
        if (n == 1) return BigInteger.ONE;
        if (n == 2) return BigInteger.ZERO;
        if (n == 3) return BigInteger.ONE;

        // Iterative Calculation
        BigInteger[] labSeq = new BigInteger[n + 1];
        labSeq[0] = BigInteger.ZERO;
        labSeq[1] = BigInteger.ONE;
        labSeq[2] = BigInteger.ZERO;
        labSeq[3] = BigInteger.ONE;

        for (int i = 4; i <= n; i++) {
            labSeq[i] = labSeq[i - 4].add(labSeq[i - 3]);
        }

        return labSeq[n];
    }
}
