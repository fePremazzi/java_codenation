package com.challenge.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;

@Data
@Entity
@Table(name = "user")
@EntityListeners(AuditingEntityListener.class)
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@NotNull
	@NotBlank
	@Size(max = 100)
	@Column(name = "full_name", length = 100, nullable = false)
	private String fullName;
	
	@NotNull
	@NotBlank
	@Size(max = 100)
	@Email
	@Column(length = 100, nullable = false)
	private String email;
	
	@NotNull
	@NotBlank
	@Size(max = 50)
	@Column(length = 50, nullable = false)
	private String nickname;
	
	@NotNull
	@NotBlank
	@Size(max = 255)
	@Column(nullable = false)
	private String password;
	
	@NotNull
	@NotBlank
	@CreatedDate
	@Column(name = "created_at", nullable = false)
	private LocalDateTime createdAt;
	
	@OneToMany
	private List<Submission> listSubmissions;
	
	@OneToMany
	private List<Candidate> listCandidates;
	
}
