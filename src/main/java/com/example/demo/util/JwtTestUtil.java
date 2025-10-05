package com.example.demo.util;

import com.example.demo.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.lang.model.type.ArrayType;
import java.util.Arrays;
import java.util.List;

@Component
public class JwtTestUtil {

    @Autowired
    private JwtUtil jwtUtil;

    public String generateUserToken() {
        List<String> roles = Arrays.asList("ROLE_USER");
        return jwtUtil.generateToken("alice2@example.com", roles);
    }

    public String generateAdminToken() {
        List<String> roles = Arrays.asList("ROLE_USER", "ROLE_ADMIN");
        return jwtUtil.generateToken("santhosh@example.com", roles);
    }

    public String generateInvalidToken() {
        return "invalid.jwt.token";
    }
}
