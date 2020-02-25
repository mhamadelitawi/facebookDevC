package com.example.facebook.model;

public enum AccessType {

    OWNER("OWNER"),
    GRANT("GRANT"),
    DENY("DENY");

    private String name;

    AccessType(String name) {
        this.name = name;
    }
}
