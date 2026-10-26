package com.api.stocktracker.controller;

import com.api.stocktracker.domain.users.dto.CreateUserRequest;
import com.api.stocktracker.domain.users.dto.CreateUserResponse;
import com.api.stocktracker.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.api.stocktracker.common.constants.Paths.CREATE_USER;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
@Slf4j
public class UserController {

    private UserService userService;

    @PostMapping(CREATE_USER)
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody CreateUserRequest request) {
        log.info("Request received for create user");
        return new ResponseEntity<>(userService.createUser(request), HttpStatus.CREATED);
    }
}
