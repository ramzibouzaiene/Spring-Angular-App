package com.ramzi.workshop.enums;

import lombok.Getter;

@Getter
public enum RoleEnum {
    USER;

    public static RoleEnum getByName(String name) {
        for (RoleEnum role : RoleEnum.values()) {
            if (role.name().equalsIgnoreCase(name)) {
                return role;
            }
        }
        throw new IllegalArgumentException("Invalid role name: " + name);
    }
}
