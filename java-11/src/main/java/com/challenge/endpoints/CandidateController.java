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

import com.challenge.dto.CandidateDTO;
import com.challenge.entity.Candidate;
import com.challenge.mappers.CandidateMapper;
import com.challenge.service.impl.CandidateService;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

	@Autowired
	private CandidateService candidateService;

	@Autowired
	private CandidateMapper candidateMapper;

	@GetMapping("/{userId}/{companyId}/{accelerationId}")
	public ResponseEntity<CandidateDTO> findById(@PathVariable("userId") Long userId,
			@PathVariable("companyId") Long companyId,
			@PathVariable("accelerationId") Long accelerationId) {
		Optional<Candidate> optionalCandidate = this.candidateService.findById(userId, companyId, accelerationId);
		return optionalCandidate.isPresent() ?
				new ResponseEntity<CandidateDTO>(toDTO(optionalCandidate.get()), HttpStatus.OK) :
				new ResponseEntity<CandidateDTO>(new CandidateDTO(), HttpStatus.OK);

	}

	@GetMapping
	public ResponseEntity<List<CandidateDTO>> findByCompanyIdOrAccelerationId(
			@RequestParam(required = false, name = "companyId") Long companyId,
			@RequestParam(required = false, name = "accelerationId") Long accelerationId) {

		List<CandidateDTO> list = Optional.ofNullable(companyId).isPresent()
				? toCollectionDTO(candidateService.findByCompanyId(companyId))
				: (Optional.ofNullable(accelerationId).isPresent()
						? toCollectionDTO(candidateService.findByCompanyId(companyId))
						: new ArrayList<>());

		return new ResponseEntity<List<CandidateDTO>>(list, HttpStatus.OK);

	}

	private CandidateDTO toDTO(Candidate candidate) {
		return candidateMapper.map(candidate);
	}

	private List<CandidateDTO> toCollectionDTO(List<Candidate> candidates) {
		return candidateMapper.map(candidates);
	}

}
