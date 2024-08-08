package com.project.model.entity;

import lombok.Data;

@Data
public class User {
    private Long id;
    private String account;
    private String password;
}
