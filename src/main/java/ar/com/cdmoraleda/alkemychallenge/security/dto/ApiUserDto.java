package ar.com.cdmoraleda.alkemychallenge.security.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiUserDto {
    private String name;
    private String email;
    private String userName;
    private String password;
}
