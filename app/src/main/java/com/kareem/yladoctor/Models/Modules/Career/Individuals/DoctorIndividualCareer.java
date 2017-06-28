package com.kareem.yladoctor.Models.Modules.Career.Individuals;

import java.util.HashMap;

/**
 * Created by kareem on 5/28/2017 - Akshef.
 * <br></br>
 * career specific for doctors
 *
 * @author kareem
 * @version %I%
 */

public class DoctorIndividualCareer extends IndividualCareer {
	//if the doctor received the verification from us or not
	private boolean verifiedDoctor;
	//present experience in number of years
	private int experienceYears;
	// price of the doctor
	private int price;
	// average interval for each appointment
	private int interval;
	// id for the field doctor is in
	private String fieldID;
	private String[] listOfHealthInsuranceCompanies;

	public DoctorIndividualCareer ( HashMap<String, String> businessLocations, String businessMobileNumber, double rate, int votes, boolean verifiedDoctor, int experienceYears, int price, int interval, String fieldID, String[] listOfHealthInsuranceCompanies ) {
		super(businessLocations, businessMobileNumber, rate, votes);
		this.verifiedDoctor = verifiedDoctor;
		this.experienceYears = experienceYears;
		this.price = price;
		this.interval = interval;
		this.fieldID = fieldID;
		this.listOfHealthInsuranceCompanies = listOfHealthInsuranceCompanies;
	}

	/**
	 * empty constructor for firebase
	 */
	public DoctorIndividualCareer () {
	}

	public boolean isVerifiedDoctor () {
		return verifiedDoctor;
	}

	public void setVerifiedDoctor ( boolean verifiedDoctor ) {
		this.verifiedDoctor = verifiedDoctor;
	}

	public int getExperienceYears () {
		return experienceYears;
	}

	public void setExperienceYears ( int experienceYears ) {
		this.experienceYears = experienceYears;
	}

	public int getPrice () {
		return price;
	}

	public void setPrice ( int price ) {
		this.price = price;
	}

	public int getInterval () {
		return interval;
	}

	public void setInterval ( int interval ) {
		this.interval = interval;
	}

	public String getFieldID () {
		return fieldID;
	}

	public void setFieldID ( String fieldID ) {
		this.fieldID = fieldID;
	}

	public String[] getListOfHealthInsuranceCompanies () {
		return listOfHealthInsuranceCompanies;
	}

	public void setListOfHealthInsuranceCompanies ( String[] listOfHealthInsuranceCompanies ) {
		this.listOfHealthInsuranceCompanies = listOfHealthInsuranceCompanies;
	}
}
