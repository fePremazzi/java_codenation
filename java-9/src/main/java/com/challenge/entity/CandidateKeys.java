package com.challenge.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class CandidateKeys implements Serializable{

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User userId;
	
	@ManyToOne
	@JoinColumn(name = "acceleration_id")
	private Acceleration accelerationId;
	
	@ManyToOne
	@JoinColumn(name = "company_id")
	private Company companyId;
	
}
