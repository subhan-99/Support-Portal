package com.supportportal.resource;


import com.supportportal.exception.domain.ExceptionHandling;
import com.supportportal.exception.domain.UserNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
public class UserResource  extends ExceptionHandling {

    @GetMapping("/home")
    public String showUser() throws UserNotFoundException {
        //return "application works";
        throw new UserNotFoundException("THe user was not found");
    }
}
