package com.redisexampleapp.controller;

import com.redisexampleapp.model.UsersModel;
import com.redisexampleapp.service.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UsersController {

    private final UsersService usersService;

    @PostMapping("/save")
    public ResponseEntity<UsersModel> save(@RequestBody UsersModel user) {
        return ResponseEntity.ok(usersService.save(user));
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<UsersModel>> getAll() {
        return ResponseEntity.ok(usersService.findAll());
    }
}
