package io.kang.bank.Repository.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.SerializationUtils;

public class SnapshotUtils {
    public static final ObjectMapper objectMapper = new ObjectMapper();

    public static<T> T snapshot(T aggregate) {
        return (T) SerializationUtils.deserialize(SerializationUtils.serialize(aggregate));
    }
}