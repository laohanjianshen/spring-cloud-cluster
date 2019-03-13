package com.qdsg.gateway.dto;

import lombok.Data;

@Data
public class AuthRequest {
    private String userName;
    private String password;
}
