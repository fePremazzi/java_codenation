package com.challenge.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.challenge.entity.Candidate;
import com.challenge.entity.CandidateId;

public interface CandidateRepository extends JpaRepository<Candidate, Long>{

	Optional<Candidate> findByIdUserIdAndIdCompanyIdAndIdAccelerationId(Long userId, Long companyId, Long accelerationId);
	List<Candidate> findByIdCompanyId(Long companyId);
	List<Candidate> findByIdAccelerationId(Long accelerationId);
	Optional<Candidate> findById(CandidateId id);
}
