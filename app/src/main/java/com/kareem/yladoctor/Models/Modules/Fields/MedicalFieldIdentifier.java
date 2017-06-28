package com.kareem.yladoctor.Models.Modules.Fields;

import java.util.HashMap;

/**
 * Created by kareem on 5/29/2017 - Akshef.
 * <br></br>
 * doctor's fields such as General surgery
 *
 * @author kareem
 * @version %I%
 */

public class MedicalFieldIdentifier extends FieldIdentifier {
	//number of doctors within that field
	private int numberOfDoctors;
	private String URL;

	/**
	 * empty constructor for firebase
	 */
	public MedicalFieldIdentifier () {
	}

	/**
	 * constructor to create new doctor's field
	 *
	 * @param nameInDifferentLanguages name of that field
	 * @param UID                      id for that field
	 * @param descriptions             brief description of that field
	 * @param URL                      url of the icon of that medical field
	 */
	public MedicalFieldIdentifier ( String UID, HashMap<String, String> descriptions, HashMap<String, String> nameInDifferentLanguages, String URL ) {
		super(UID, descriptions, nameInDifferentLanguages);
		this.URL = URL;
		numberOfDoctors = 0;
	}

	public String getURL () {
		return URL;
	}

	public void setURL ( String URL ) {
		this.URL = URL;
	}

	public int getNumberOfDoctors () {
		return numberOfDoctors;
	}

	public void setNumberOfDoctors ( int numberOfDoctors ) {
		this.numberOfDoctors = numberOfDoctors;
	}
}
