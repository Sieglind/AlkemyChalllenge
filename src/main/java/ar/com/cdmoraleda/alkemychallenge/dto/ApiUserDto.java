package ar.com.cdmoraleda.alkemychallenge.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiUserDto {
    @NotEmpty(message = "Must provide a name")
    private String name;
    @NotEmpty(message = "Must provide an email")
    @Email(message = "Must provide a valid email")
    private String email;
    @NotEmpty(message = "Must provide an username")
    private String username;
    @NotEmpty(message = "Must provide a password")
    private String password;
}
