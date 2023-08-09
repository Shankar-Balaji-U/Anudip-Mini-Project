package com.anudipgroupproject.socialize.utils;

//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Component;

@Component
public class PasswordHash {
//    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//
//    public static String make(String plainPassword) {
//        // Hash the password using the BCryptPasswordEncoder
//        return passwordEncoder.encode(plainPassword);
//    }
//
//    public static boolean check(String providedPassword, String hashedPassword) {
//        // Check if a provided password matches the hashed password using the BCryptPasswordEncoder
//        return passwordEncoder.matches(providedPassword, hashedPassword);
//    }
	public static String make(String plainPassword) {
		// Generate a salt for the password (recommended to use BCrypt.gensalt() to generate a secure salt)
        String salt = BCrypt.gensalt();
        return BCrypt.hashpw(plainPassword, salt);
	}
	public static boolean check(String plainPassword, String hashedPassword) {
		// Check if a provided password matches the hashed password
		return BCrypt.checkpw(plainPassword, hashedPassword);
	}
}