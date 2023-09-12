package br.com.fiap.challenge.services;

import br.com.fiap.challenge.model.entities.Permission;
import br.com.fiap.challenge.model.entities.User;
import br.com.fiap.challenge.model.vo.UserRegisterVO;
import br.com.fiap.challenge.model.vo.security.AccountCredentialsVO;
import br.com.fiap.challenge.model.vo.security.TokenVO;
import br.com.fiap.challenge.repositories.PermissionRepository;
import br.com.fiap.challenge.repositories.UserRepository;
import br.com.fiap.challenge.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AuthServices {

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PermissionRepository permissionRepository;


    public ResponseEntity signIn(AccountCredentialsVO data) {
        try {
            var username = data.getUsername();
            var password = data.getPassword();
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password));
            var user = userRepository.findByUsername(username);
            var tokenResponse = new TokenVO();
            if (user.isPresent()) {
                tokenResponse = tokenProvider.createAcessToken(username, user.get().getRoles());
            } else {
                throw new UsernameNotFoundException("Username " + username + " not found.");
            }
            return ResponseEntity.ok(tokenResponse);
        } catch (Exception e) {
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }

    public ResponseEntity signUp(UserRegisterVO data) {
        if (userRepository.findByUsername(data.email()) != null) {
            System.out.println("Error");
        }
        User user = convertVoToEntity(data);
        return ResponseEntity.ok(userRepository.save(user));
    }

    public void deleteUser(AccountCredentialsVO data) {
        User user = userRepository.findByUsername(data.getUsername()).orElseThrow(() -> new UsernameNotFoundException("user not found"));
        if (user != null) {
            user.deleteUser();
            userRepository.save(user);
        }

    }

    public String passwordEncoder(String password) {
        Map<String, PasswordEncoder> encoders = new HashMap<>();
		Pbkdf2PasswordEncoder pbkdf2Encoder = new Pbkdf2PasswordEncoder("", 8, 18500, Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256);
		encoders.put("pbkdf2", pbkdf2Encoder);
		DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("pbkdf2", encoders);
		passwordEncoder.setDefaultPasswordEncoderForMatches(pbkdf2Encoder);
		return passwordEncoder.encode(password);
    }

    private User convertVoToEntity(UserRegisterVO vo) {
        User user = new User();
        user.setEmail(vo.email());
        user.setPassword(passwordEncoder(vo.password()).substring("{pbkdf2}".length()));
        user.setName(vo.name());
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(true);
        List<Permission> permissions = permissionRepository.findById(3L).stream().toList();
        user.setPermissions(permissions);
        return user;
    }
}
