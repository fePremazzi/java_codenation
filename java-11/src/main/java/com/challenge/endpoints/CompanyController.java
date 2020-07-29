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

import com.challenge.entity.Company;
import com.challenge.service.impl.CompanyService;

@RestController
@RequestMapping("/company")
public class CompanyController {
	
	@Autowired
	private CompanyService companyService;
	
	@GetMapping("/{companyId}")
	public ResponseEntity<Company> findById(@PathVariable Long companyId){
		Optional<Company> companySearched = companyService.findById(companyId);

		return companySearched.isPresent() ? 
				ResponseEntity.ok(companySearched.get()):
				ResponseEntity.notFound().build();		
	}
	
	@GetMapping
    public ResponseEntity<List<Company>> findByAccelerationIdOrUserId(
            @RequestParam(required = false, name = "accelerationId") Long accelerationId,
            @RequestParam(required = false, name = "userId") Long userId) {

        List<Company> list = Optional.ofNullable(accelerationId).isPresent() ?
                companyService.findByAccelerationId(accelerationId) :
                (Optional.ofNullable(userId).isPresent() ? companyService.findByUserId(userId) : new ArrayList<>());

        return new ResponseEntity<List<Company>>(list, HttpStatus.OK);

    }

}
