package com.luchonicolosi.willitaapi.dto.Auth;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class LoginDto {
    @NotNull
    private String username;
    @NotNull
    private String password;
}