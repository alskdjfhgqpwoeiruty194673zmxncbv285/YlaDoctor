/*
 * Copyright (c) 2016. kareem elsayed aly,no one has the authority to edit,delete,copy any part without my permission
 */

package com.kareem.yladoctor.Models.Modules.Career.Individuals;


import java.util.HashMap;

/**
 * Created by kareem on 9/28/2016 - HealMe.
 * <br></br>
 * IndividualCareer module class,introduce all required elements
 *
 * @author kareem
 * @version %I%
 */

public class IndividualCareer {
	//location for the business in different languages
	private HashMap<String, String> businessLocations;
	//phone number for business
	private String businessMobileNumber;
	//business overall rate
	private double rate;
	//number of received votes
	private int votes;

	public IndividualCareer ( HashMap<String, String> businessLocations, String businessMobileNumber, double rate, int votes ) {
		this.businessLocations = businessLocations;
		this.businessMobileNumber = businessMobileNumber;
		this.rate = rate;
		this.votes = votes;
	}

	/**
	 * empty constructor for firebase
	 */
	public IndividualCareer () {
	}

	public HashMap<String, String> getBusinessLocations () {
		return businessLocations;
	}

	public void setBusinessLocations ( HashMap<String, String> businessLocations ) {
		this.businessLocations = businessLocations;
	}

	public String getBusinessMobileNumber () {
		return businessMobileNumber;
	}

	public void setBusinessMobileNumber ( String businessMobileNumber ) {
		this.businessMobileNumber = businessMobileNumber;
	}

	public double getRate () {
		return rate;
	}

	public void setRate ( double rate ) {
		this.rate = rate;
	}

	public int getVotes () {
		return votes;
	}

	public void setVotes ( int votes ) {
		this.votes = votes;
	}
}
