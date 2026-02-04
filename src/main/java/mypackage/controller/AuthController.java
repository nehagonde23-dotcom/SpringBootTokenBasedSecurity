package mypackage.controller;

 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;


import mypackage.model.User;
import mypackage.repository.IUserRepository;
import mypackage.security.JwtUtil;
@RestController
@RequestMapping("/api/auth")
 @CrossOrigin(origins = "*",exposedHeaders ="Authorization",methods = {RequestMethod.PUT,RequestMethod.GET,RequestMethod.POST,RequestMethod.OPTIONS},allowedHeaders = "*")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    IUserRepository userRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    JwtUtil jwtUtils;
    @PostMapping("/signin")
    public String authenticateUser(@RequestBody User user) {
    	try {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getUsername(),
                        user.getPassword()
                )
        );
        if(authentication.isAuthenticated()) {
        	System.out.println("Authenticated successfully");
        }
        else {
        	System.out.println("Not authenticated");
        }
        System.out.println(user.getUsername()+"\t"+user.getPassword());
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        System.out.println(user.getUsername()+"\t"+user.getPassword());
        return jwtUtils.generateToken(userDetails.getUsername());
    	}
    	catch(Exception ex) {
    		System.out.println(ex.getMessage());
    		return ex.getMessage();
    	}
    }
    @PostMapping("/signup")
    public String registerUser(@RequestBody User user) {
        try {
    	if (userRepository.existsByUsername(user.getUsername())) {
            return "Error: Username is already taken!";
        }
        // Create new user's account
        User newUser = new User(
                0,user.getFull_name(),
                user.getUsername(),
                encoder.encode(user.getPassword())
        );
        userRepository.save(newUser);
        return "User registered successfully!";
    }
        catch(Exception ex) {
        	System.out.println(ex.getMessage());
        	return ex.getMessage();
        }
    }
}
