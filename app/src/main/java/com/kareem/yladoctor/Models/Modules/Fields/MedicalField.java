package com.kareem.yladoctor.Models.Modules.Fields;

/**
 * Created by kareem on 6/17/2017 - YlaCreateContent.
 * <br></br>
 * description goes here
 *
 * @author kareem
 * @version %I%
 */

public class MedicalField {
	private String UID;
	private String[] doctors;


	public MedicalField ( String UID, String[] doctors ) {
		this.UID = UID;
		this.doctors = doctors;
	}

	public MedicalField () {
	}

	public String getUID () {
		return UID;
	}

	public void setUID ( String UID ) {
		this.UID = UID;
	}

	public String[] getDoctors () {
		return doctors;
	}

	public void setDoctors ( String[] doctors ) {
		this.doctors = doctors;
	}
}
