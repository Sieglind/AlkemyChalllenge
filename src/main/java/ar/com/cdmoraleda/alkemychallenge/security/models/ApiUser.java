package ar.com.cdmoraleda.alkemychallenge.security.models;

import ar.com.cdmoraleda.alkemychallenge.security.dto.ApiUserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "USERS")
public class ApiUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer userId;
    private String name;
    private String email;
    private String userName;
    private String password;
    private String userRole;

    public ApiUser(ApiUserDto apiUserDto) {
        this.name = apiUserDto.getName();
        this.email = apiUserDto.getEmail();
        this.userName = apiUserDto.getUserName();
        this.password = apiUserDto.getPassword();
        this.userRole = "ROLE_USER";
    }
}
