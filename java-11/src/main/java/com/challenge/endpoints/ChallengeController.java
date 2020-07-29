package com.challenge.endpoints;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.entity.Challenge;
import com.challenge.service.impl.ChallengeService;

@RestController
@RequestMapping("/challenge")
public class ChallengeController {
	
	@Autowired
	private ChallengeService challengeService;
	
	@GetMapping
    public ResponseEntity<List<Challenge>> findByAccelerationIdAndUserId(
            @RequestParam(required = true, name = "accelerationId") Long accelerationId,
            @RequestParam(required = true, name = "userId") Long userId) {

		return ResponseEntity.ok(challengeService.findByAccelerationIdAndUserId(accelerationId, userId));
    }

}
