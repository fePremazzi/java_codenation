package com.challenge.endpoints;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.entity.User;
import com.challenge.service.impl.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/{userId}")
	public ResponseEntity<User> findById(@PathVariable Long userId){
		Optional<User> userSearched = userService.findById(userId);
		
		if (userSearched.isPresent()) {
			return ResponseEntity.ok(userSearched.get());
		}
		return ResponseEntity.notFound().build();
	}
	
	@GetMapping
    public ResponseEntity<List<User>> findByAccelerationNameOrByCompanyId(
            @RequestParam(required = false, name = "accelerationName") String name,
            @RequestParam(required = false, name = "companyId") Long companyId) {

        List<User> list = Optional.ofNullable(name).isPresent() ?
                this.userService.findByAccelerationName(name) :
                (Optional.ofNullable(companyId).isPresent() ? this.userService.findByCompanyId(companyId) : new ArrayList<>());

        return new ResponseEntity<List<User>>(list, HttpStatus.OK);

    }
	
	

}
