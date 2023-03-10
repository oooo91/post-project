package com.portfolio.postproject.user.enums;


import lombok.Getter;

@Getter
public enum UserRole {

    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER"),
    SOCIAL("ROLE_SOCIAL");

    private String userRole;

    UserRole(String userRole) {
        this.userRole = userRole;
    }
}
