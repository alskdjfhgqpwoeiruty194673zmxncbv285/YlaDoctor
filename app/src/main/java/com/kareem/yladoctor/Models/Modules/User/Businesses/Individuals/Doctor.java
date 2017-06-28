package com.kareem.yladoctor.Models.Modules.User.Businesses.Individuals;


import com.google.firebase.auth.FirebaseUser;
import com.kareem.yladoctor.Models.Enums.AccountType;
import com.kareem.yladoctor.Models.Modules.Attachments.Picture;
import com.kareem.yladoctor.Models.Modules.Career.Individuals.DoctorIndividualCareer;
import com.kareem.yladoctor.Models.Modules.User.Individual;

import java.util.HashMap;

/**
 * Created by kareem on 5/28/2017 - Akshef.
 * <br></br>
 * doctor module
 * inherit his data from individual
 *
 * @author kareem
 * @version %I%
 * @see Individual
 */

public class Doctor extends IndividualBusiness {
	//career related with doctor
	private DoctorIndividualCareer doctorCareer;

	public Doctor ( String UID ) {
		super(AccountType.DOCTOR, UID);

	}

	public Doctor ( FirebaseUser firebaseUser ) {
		super(AccountType.DOCTOR, firebaseUser);
	}

	public Doctor ( String email, String password, String UID, HashMap<String, String> names, int points, Picture profilePicture, DoctorIndividualCareer doctorCareer ) {
		// account type is selected to be Doctor
		super(AccountType.DOCTOR, email, password, UID, names, points, profilePicture);
		this.doctorCareer = doctorCareer;
	}

	/**
	 * empty constructor for firebase
	 */
	public Doctor () {
	}

	public DoctorIndividualCareer getDoctorCareer () {
		return doctorCareer;
	}

	public void setDoctorCareer ( DoctorIndividualCareer doctorCareer ) {
		this.doctorCareer = doctorCareer;
	}
}
