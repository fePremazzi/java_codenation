package com.challenge.endpoints;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.dto.SubmissionDTO;
import com.challenge.mappers.SubmissionMapper;
import com.challenge.service.impl.SubmissionService;

@RestController
@RequestMapping("/submission")
public class SubmissionController {
	
	@Autowired
	private SubmissionService submissionService;
	
	@Autowired
	private SubmissionMapper submissionMapper;
	
	@GetMapping
    public ResponseEntity<List<SubmissionDTO>> findByChallengeIdAndAccelerationId(
            @RequestParam(required = true, name = "challengeId") Long challengeId,
            @RequestParam(required = true, name = "accelerationId") Long accelerationId) {

		return ResponseEntity.ok(submissionMapper
				.map(submissionService.findByChallengeIdAndAccelerationId(challengeId, accelerationId)));
    }

}
