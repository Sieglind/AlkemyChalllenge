package ar.com.cdmoraleda.alkemychallenge.models;

import ar.com.cdmoraleda.alkemychallenge.dto.ApiUserDto;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter

@Entity
@Table(name = "USERS")
public class ApiUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userId;
    private String name;
    private String email;
    private String username;
    private String password;
    private String userRole;

    public ApiUser(ApiUserDto apiUserDto) {
        this.name = apiUserDto.getName();
        this.email = apiUserDto.getEmail();
        this.username = apiUserDto.getUsername();
        this.password = apiUserDto.getPassword();
        this.userRole = "ROLE_USER";
    }
}
