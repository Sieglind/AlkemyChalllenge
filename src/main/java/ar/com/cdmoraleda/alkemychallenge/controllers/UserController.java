package ar.com.cdmoraleda.alkemychallenge.controllers;

import ar.com.cdmoraleda.alkemychallenge.dto.ApiUserDto;
import ar.com.cdmoraleda.alkemychallenge.services.ApiUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.io.IOException;


@RestController
@RequestMapping("/auth")
public class UserController {
    final
    ApiUserService apiUserService;

    public UserController(ApiUserService apiUserService) {
        this.apiUserService = apiUserService;
    }

    @PostMapping("/register")
    ResponseEntity<String> registerNewUser(@Valid @RequestBody ApiUserDto apiUserDto) throws IOException {
        System.out.println("apiUserDto = " + apiUserDto);
        return apiUserService.registerNewUser(apiUserDto);
    }
}
