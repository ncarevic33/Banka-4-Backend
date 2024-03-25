package rs.edu.raf.security;

import org.springframework.security.access.PermissionEvaluator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;

public class CustomPermissionEvaluator implements PermissionEvaluator {
    @Override
    public boolean hasPermission(Authentication authentication, Object targetDomainObject, Object permission) {
        if (authentication == null || !authentication.isAuthenticated() || !(permission instanceof String)) {
            return false;
        }
        System.out.println(authentication.getPrincipal().getClass());
        String userDetails = (String) authentication.getPrincipal();
        System.out.println("String userDetails " + userDetails);
        var a = authentication.getAuthorities();
        System.out.println("authorities " + a);
        long requiredPermission = Long.parseLong((String)permission);
        for(var b: a) {
            String c= b.getAuthority();
            long d = Long.parseLong(c);
            return (d & requiredPermission) == requiredPermission;
        }
        return false;
    }

    @Override
    public boolean hasPermission(Authentication authentication, Serializable targetId, String targetType, Object permission) {
        return false;
    }
}
