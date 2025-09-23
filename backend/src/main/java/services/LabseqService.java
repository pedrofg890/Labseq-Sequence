package services;

import jakarta.enterprise.context.ApplicationScoped;
import io.quarkus.cache.CacheResult;

import java.math.BigInteger;

@ApplicationScoped
public class LabseqService {

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

        switch (n) {
            case 0: return BigInteger.ZERO;
            case 1: return BigInteger.ONE;
            case 2: return BigInteger.ZERO;
            case 3: return BigInteger.ONE;
            default:
                return calculateLabSeq(n - 4).add(calculateLabSeq(n - 3));
        }
    }
}
