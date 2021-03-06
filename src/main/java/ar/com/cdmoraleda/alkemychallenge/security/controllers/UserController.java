package ar.com.cdmoraleda.alkemychallenge.security.controllers;

import ar.com.cdmoraleda.alkemychallenge.security.dto.ApiUserDto;
import ar.com.cdmoraleda.alkemychallenge.security.services.ApiUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
@RequestMapping("/auth/register")
public class UserController {

    final
    ApiUserService apiUserService;

    public UserController(ApiUserService apiUserService) {
        this.apiUserService = apiUserService;
    }

    @PostMapping()
    ResponseEntity<String> registerNewUser(@RequestBody ApiUserDto apiUserDto) throws IOException {
        System.out.println("apiUserDto = " + apiUserDto);
        return apiUserService.registerNewUser(apiUserDto);
    }
}
