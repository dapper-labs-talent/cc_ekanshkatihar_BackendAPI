package com.ek.api.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ek.api.models.User;
import com.ek.api.payload.request.UpdateRequest;
import com.ek.api.payload.response.MessageResponse;
import com.ek.api.payload.response.UserResponse;
import com.ek.api.repository.UserRepository;
import com.ek.api.security.jwt.JwtUtils;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/")
public class UserController {

	@Autowired
	UserRepository userRepository;

	@Autowired
	JwtUtils jwtUtils;

	@GetMapping("users")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> userAccess() {
		List<User> users = userRepository.findAll();
		List<UserResponse> usersResponse = new ArrayList<>();
		for (User user : users) {
			UserResponse aUser = new UserResponse();
			aUser.setEmail(user.getEmail());
			aUser.setFirstName(user.getFirstName());
			aUser.setLastName(user.getLastName());
			usersResponse.add(aUser);
		}
		return ResponseEntity.ok(usersResponse);
	}

	@PutMapping("users")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> updateUser(@RequestBody UpdateRequest updateReq,
			@RequestHeader Map<String, String> headers) {
		final String AUTH_HEADER="x-authentication-token";
		final int TOKEN_START_INDEX=7;
		String token = headers.get(AUTH_HEADER);
		if (token ==null || token.trim().isEmpty() ) {
			return ResponseEntity.ok(new MessageResponse("Token can't be empty"));
		}
		token = token.substring(TOKEN_START_INDEX);
		String user = jwtUtils.getUserNameFromJwtToken(token);
		Optional<User> aUser = userRepository.findByEmail(user);
		if (!aUser.isPresent()) {
			return ResponseEntity.ok(new MessageResponse("Invalid token! Hence didn't find the user to update "));
		}
		User user2 = aUser.get();
		boolean doUpdate = false;
		if (updateReq.getFirstName() != null && !updateReq.getFirstName().trim().isEmpty()) {
			user2.setFirstName(updateReq.getFirstName());
			doUpdate = true;
		}
		if (updateReq.getLastName() != null && !updateReq.getLastName().trim().isEmpty()) {
			user2.setLastName(updateReq.getLastName());
			doUpdate = true;
		}
		if (doUpdate) {
			userRepository.save(user2);
		}
		UserResponse userResponse = new UserResponse();
		userResponse.setEmail(user2.getEmail());
		userResponse.setFirstName(user2.getFirstName());
		userResponse.setLastName(user2.getLastName());
		return ResponseEntity.ok(userResponse);
	}
}
