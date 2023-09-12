package br.com.fiap.challenge;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@SecurityScheme(
		name = "Bearer Authentication",
		scheme = "bearer",
		bearerFormat = "JWT",
		type = SecuritySchemeType.HTTP
)
public class ChallengeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChallengeApplication.class, args);

//		Map<String, PasswordEncoder> encoders = new HashMap<>();
//		Pbkdf2PasswordEncoder pbkdf2Encoder = new Pbkdf2PasswordEncoder("", 8, 18500, Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256);
//		encoders.put("pbkdf2", pbkdf2Encoder);
//		DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("pbkdf2", encoders);
//		passwordEncoder.setDefaultPasswordEncoderForMatches(pbkdf2Encoder);
//
//		String result1 = passwordEncoder.encode("admin123");
//		String result2 = passwordEncoder.encode("admin234");
//
//		System.out.println("Result1: " + result1);
//		System.out.println("Result2: " + result2);
	}

}
