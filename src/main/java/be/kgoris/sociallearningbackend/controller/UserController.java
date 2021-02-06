package be.kgoris.sociallearningbackend.controller;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/users")
@AllArgsConstructor
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @RequestMapping(method = RequestMethod.GET)
    public String testEndpoint(){
        return "Hello";
    }
}
