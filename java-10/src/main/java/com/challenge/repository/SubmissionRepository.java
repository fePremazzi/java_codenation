package com.challenge.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.challenge.entity.Submission;

public interface SubmissionRepository extends JpaRepository<Submission, Long> {

	
	@Query("SELECT max(submission.score) FROM Submission submission " +
            "JOIN submission.id.challenge challenge " +
            "WHERE challenge.id = :challengeId")
	Optional<BigDecimal> findHigherScoreByChallengeId(@Param("challengeId") Long challengeId);

	@Query(value = "select * from submission s " +
             "inner join challenge c " +
             "on s.challenge_id = c.id " +
             "inner join acceleration a " +
             "on a.challenge_id = c.id " +
             "where a.challenge_id = :challengeId " +
             "and a.id = :acellerationId ", nativeQuery = true)
	List<Submission> findByChallengeIdAndAccelerationId(@Param("challengeId") Long challengeId, @Param("acellerationId") Long accelerationId);
	
	
}
