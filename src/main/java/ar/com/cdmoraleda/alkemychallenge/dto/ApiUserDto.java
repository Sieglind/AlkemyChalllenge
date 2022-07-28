package ar.com.cdmoraleda.alkemychallenge.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

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
    @Length(min = 8,max = 20,message = "Password must be between 8 and 20 characters")
    @Pattern(regexp = "((?=.*\\d.*\\d.*\\d.*\\d)(?=^\\S+$)(?=.*[a-z])(?=.*[A-Z])(?=.*[¡!¿?#$%_-].*[¡!¿?#$%_-]).{8,20})",message = "Check password")
    @NotEmpty(message = "Must provide a password")
    private String password;
}
