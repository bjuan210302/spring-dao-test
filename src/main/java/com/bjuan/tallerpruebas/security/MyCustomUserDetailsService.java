package com.bjuan.tallerpruebas.security;

import com.bjuan.tallerpruebas.model.user.MyUser;
import com.bjuan.tallerpruebas.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//import co.edu.icesi.ci.thymeval.model.UserApp;
//import co.edu.icesi.ci.thymeval.repository.UserRepository;

@Service
public class MyCustomUserDetailsService implements UserDetailsService {
	
	private UserRepository userRepository;

	@Autowired
	public MyCustomUserDetailsService(UserRepository userrRepository){
		this.userRepository = userrRepository;
	}

	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MyUser user = userRepository.findByUsername(username);
		if (user != null) {
			User.UserBuilder builder = User.withUsername(username).password(user.getPassword()).roles(user.getUsertype().toString());
			return builder.build();
		} else {
			throw new UsernameNotFoundException("User not found.");
		}
	}


}