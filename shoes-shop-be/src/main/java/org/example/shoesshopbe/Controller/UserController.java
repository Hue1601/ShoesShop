package org.example.shoesshopbe.Controller;

import jakarta.validation.Valid;
import org.example.shoesshopbe.Exception.AuthenticationException;
import org.example.shoesshopbe.Exception.MessageError;
import org.example.shoesshopbe.Model.Users;
import org.example.shoesshopbe.Request.LoginRequest;
import org.example.shoesshopbe.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login( @RequestBody @Valid LoginRequest request) {

       Users user = userService.login(request.username, request.password);
        if (user == null) {
            throw new AuthenticationException(MessageError.INVALID_USERNAME, HttpStatus.UNAUTHORIZED);
        }
        return  ResponseEntity.status(HttpStatus.OK).body(MessageError.SUCCESSFUL);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid Users user, BindingResult result) {
        if (result.hasErrors()) {
            // return error list in json format
            Map<String, String> errors = new HashMap<>();
            for (FieldError error : result.getFieldErrors()) {
                errors.put(error.getField(), error.getDefaultMessage());
            }
            return ResponseEntity.badRequest().body(errors);
        }

        Users savedUser = userService.register(user);
        return ResponseEntity.ok(savedUser);
    }

}

