package com.asyraf.whizware.infrastructure.security;

import com.asyraf.whizware.exception.ForbiddenException;
import com.asyraf.whizware.infrastructure.constant.Role;
import ratpack.core.handling.Context;

import java.util.Collection;

public class AuthUtil {
    public static void authorize(Context context, Collection<Role> roles) {
        CustomClaims customClaims = context.getRequest().get(CustomClaims.class);
        if (roles.contains(customClaims.getRole())) return;
        throw new ForbiddenException("You have no access!");
    }

}
