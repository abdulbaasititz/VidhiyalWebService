package com.itz.vidhiyal.usecases.auth.dao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthRequestDao {
    private String userId;
    private String password;
}
