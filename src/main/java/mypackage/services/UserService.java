package mypackage.services;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import mypackage.model.User;
import mypackage.repository.IUserRepository;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	IUserRepository userrepo;

	@Override
	public UserDetails loadUserByUsername(String user_name) throws UsernameNotFoundException {

		User user=userrepo.findByUsername(user_name);
		if(user==null) {
			throw new UsernameNotFoundException("User not found with username = "+user_name); 
		}
		else {
			return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), Collections.emptyList());
		}

	}
}
