package com.api.stocktracker.service;

import com.api.stocktracker.common.exception.BadRequest;
import com.api.stocktracker.common.exception.ServerError;
import com.api.stocktracker.domain.users.dto.CreateUserRequest;
import com.api.stocktracker.domain.users.dto.CreateUserResponse;
import com.api.stocktracker.domain.users.model.User;
import com.api.stocktracker.domain.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public CreateUserResponse createUser(CreateUserRequest request) {

        if (!Objects.equals(request.getPassword(), request.getConfirmPassword())) {
            throw new BadRequest("Password does not match confirm password");
        }

        CreateUserResponse createUserResponse = new CreateUserResponse();
        User newUser = new User();
        newUser.setEmail(request.getEmail());
        newUser.setFirstName(request.getFirstName());
        newUser.setLastName(request.getLastName());
        newUser.setMobileNumber(request.getMobileNumber());
        newUser.setPassword(request.getPassword());

        try {
           userRepository.save(newUser);
        } catch (DataAccessException e) {
            throw new ServerError("Error saving user to the database: " + e.getMessage());
        }

        createUserResponse.setMessage("User successfully created");

        return createUserResponse;
    }

}
