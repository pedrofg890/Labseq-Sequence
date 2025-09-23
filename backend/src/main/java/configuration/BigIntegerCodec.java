package configuration;

import io.quarkus.redis.datasource.codecs.Codec;
import jakarta.enterprise.context.ApplicationScoped;

import java.lang.reflect.Type;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

/**
 * Redis codec that serializes BigInteger as UTF-8 decimal strings.
 * Registered as a CDI bean so Quarkus Redis will pick it up automatically.
 */
@ApplicationScoped
public class BigIntegerCodec implements Codec {

    @Override
    public boolean canHandle(Type clazz) {
        if (clazz instanceof Class<?>) {
            return BigInteger.class.isAssignableFrom((Class<?>) clazz);
        }
        return false;
    }

    @Override
    public byte[] encode(Object item) {
        if (item == null) {
            return null;
        }
        // item should be a BigInteger (canHandle was checked by caller)
        return item.toString().getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public Object decode(byte[] item) {
        if (item == null) {
            return null;
        }
        return new BigInteger(new String(item, StandardCharsets.UTF_8));
    }
}
