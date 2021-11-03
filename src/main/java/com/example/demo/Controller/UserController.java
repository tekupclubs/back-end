package com.example.demo.Controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Repository.UserRepository;
import com.example.demo.models.Sponsor;
import com.example.demo.models.User;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class UserController {
	@Autowired
	private UserRepository userRepository;
	@GetMapping("/user")
	public List<User> getAlluser() {
		return userRepository.findAll();
	}
	@GetMapping("/user/{id}")
	public ResponseEntity<User> getuserById(@PathVariable(value = "id") Integer userId)
			throws com.example.demo.exception.ResourceNotFoundException {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new com.example.demo.exception.ResourceNotFoundException("User not found for this id :: " + userId));
		return ResponseEntity.ok().body(user);
	}
	@PostMapping("/user")
	public User createuser(@Validated @RequestBody User user) {
		return userRepository.save(user);
	}
	@PutMapping("/user/{id}")
	public ResponseEntity<User> updateuser(@PathVariable(value = "id") Integer userid,
			@Validated @RequestBody User userDetails) throws com.example.demo.exception.ResourceNotFoundException {
		User  user = userRepository.findById(userid)
				.orElseThrow(() -> new com.example.demo.exception.ResourceNotFoundException("user not found for this id :: " + userid));
  user.setUserName(userDetails.getUserName());
  user.setEmail(userDetails.getEmail());
  user.setPassword(userDetails.getPassword());
  user.setTelephone(userDetails.getTelephone());
  user.setRoles(userDetails.getRoles());
		

		final User updateduser = userRepository.save(user);
		return ResponseEntity.ok(updateduser);
	}
	@DeleteMapping("/user/{id}")
	public Map<String, Boolean> deleteuser(@PathVariable(value = "id")Integer userid)
			throws com.example.demo.exception.ResourceNotFoundException {
		User user = userRepository.findById(userid)
				.orElseThrow(() -> new com.example.demo.exception.ResourceNotFoundException("user not found for this id :: " + userid));

		userRepository.delete(user);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}


}
