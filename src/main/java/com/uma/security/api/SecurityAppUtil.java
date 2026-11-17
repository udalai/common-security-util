package com.uma.security.api;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Map;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class SecurityAppUtil {

    private static Map<String, String> getUserDetailsFromSecurityContext() {
        return (Map<String, String>) SecurityContextHolder.getContext().getAuthentication().getDetails();
    }

    public static String getEmail() {
        return getUserDetailsFromSecurityContext().get("email");
    }


    public static boolean isInternalUser() {
        return getEmail().matches("(?i)^[\\w+]+@umarica\\.com$");
    }

    public static String getUserId() {
        return getUserDetailsFromSecurityContext().get("userid");
    }

    public static String getUserName() {
        return getUserDetailsFromSecurityContext().get("username");
    }

    public static String getRoles() {
        return getUserDetailsFromSecurityContext().get("roles");
    }

    public static boolean isExternal() {
        return !isInternalUser();
    }


}