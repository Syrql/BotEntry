package fr.syrql.botentry.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

public class IOUtil {
    private static final ObjectMapper OBJECT_MAPPER;

    static {
        OBJECT_MAPPER = new ObjectMapper();
    }

    public static String toJson(Object o) {
        try {
            return OBJECT_MAPPER.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static <E> E fromJson(String json, TypeReference<E> typeReference) {
        try {
            return OBJECT_MAPPER.readValue(json, typeReference);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void writeObject(Path path, Object o) {
        try {
            if (!Files.exists(path)) {
                Files.createDirectories(path.getParent());
                Files.createFile(path);
            }

            Files.write(path, Objects.requireNonNull(toJson(o)).getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static <E> E readObject(Path path, TypeReference<E> typeReference) {
        try (BufferedReader bf = Files.newBufferedReader(path)) {
            StringBuilder sb = new StringBuilder();

            String line;

            while ((line = bf.readLine()) != null) {
                sb.append(line);
            }

            return fromJson(sb.toString(), typeReference);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
