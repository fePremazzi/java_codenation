package com.challenge.endpoints;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.challenge.entity.Acceleration;
import com.challenge.service.impl.AccelerationService;

@RestController
@RequestMapping("/acceleration")
public class AccelerationController {
	
	@Autowired
	private AccelerationService accelerationService;
	
	@GetMapping("/{accelerationId}")
	public ResponseEntity<Acceleration> findById(@PathVariable Long accelerationId){
		Optional<Acceleration> accelerationSearched = accelerationService.findById(accelerationId);
		
		return accelerationSearched.isPresent() ? 
				ResponseEntity.ok(accelerationSearched.get()) :
				ResponseEntity.notFound().build();
	}
	
	@GetMapping
	public List<Acceleration> findByCompanyId(
			@RequestParam(required = false, name = "companyId") Long companyId){
		
		return accelerationService.findByCompanyId(companyId);

	}

}
