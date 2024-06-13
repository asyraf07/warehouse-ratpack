package com.asyraf.whizware.util;

import com.asyraf.whizware.exception.BadRequestException;
import ratpack.core.path.PathTokens;

import java.util.UUID;

public class PathVariableUtil {
    public static Long getLong(PathTokens tokens, String key) throws BadRequestException {
        String token = tokens.get(key);
        if (token == null) {
            throw new BadRequestException("Token null: " + key);
        }
        try {
            return Long.valueOf(token);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }

    public static UUID getUUID(PathTokens tokens, String key) throws BadRequestException {
        String token = tokens.get(key);
        if (token == null) {
            throw new BadRequestException("Token null: " + key);
        }
        try {
            return UUID.fromString(token);
        } catch (Exception e) {
            throw new BadRequestException(e.getMessage());
        }
    }
}
