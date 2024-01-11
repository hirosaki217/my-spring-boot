package org.myapp.service.dto;

public class AuthDto {
    public record LoginRequest(String username, String password){};
    public record Response(String message, String token){}
}
