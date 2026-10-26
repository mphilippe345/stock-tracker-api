package com.api.stocktracker.service;

import com.api.stocktracker.domain.users.dto.CreateUserRequest;
import com.api.stocktracker.domain.users.dto.CreateUserResponse;

public interface UserService {

    CreateUserResponse createUser(CreateUserRequest request);

}
