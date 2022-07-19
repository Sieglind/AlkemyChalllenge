package ar.com.cdmoraleda.alkemychallenge.security.services;

import ar.com.cdmoraleda.alkemychallenge.security.dto.ApiUserDto;
import ar.com.cdmoraleda.alkemychallenge.security.models.ApiUser;
import ar.com.cdmoraleda.alkemychallenge.security.repositories.IApiUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

@Service
@RequiredArgsConstructor
public class ApiUserService implements UserDetailsService {

    private final IApiUserRepository apiUserRepository;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        ApiUser apiUser = apiUserRepository.findByUserName(userName);
        if(apiUser == null){
            throw new UsernameNotFoundException("User not found");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(apiUser.getUserRole()));
        return new User(apiUser.getUserName(),apiUser.getPassword(),authorities);
    }

    public ResponseEntity<String> registerNewUser(ApiUserDto apiUserDto) throws IOException {
        if (apiUserRepository.findByUserName(apiUserDto.getUserName()) != null) {
            return ResponseEntity.ok("Username already use");
        } else if (apiUserRepository.findByEmail(apiUserDto.getEmail()) != null) {
            return ResponseEntity.ok("Email already in use");
        } else {
            apiUserDto.setPassword(passwordEncoder.encode(apiUserDto.getPassword()));
            apiUserRepository.save(new ApiUser(apiUserDto));
            emailService.sendConfirmation(apiUserDto);
            return ResponseEntity.ok("Username correctly created");
        }
    }
}
