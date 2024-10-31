package com.example.pokersistem.dtos;

import com.example.pokersistem.Enum.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {
}
