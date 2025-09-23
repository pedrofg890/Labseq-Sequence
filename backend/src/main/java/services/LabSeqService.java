package services;

import jakarta.enterprise.context.ApplicationScoped;
import io.quarkus.cache.CacheResult;

import java.math.BigInteger;

@ApplicationScoped
public class LabSeqService {

    /**
     * Calculate LabSeq value at index n with caching.
     *
     * @param n non-negative index
     * @return LabSeq value as BigInteger
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
