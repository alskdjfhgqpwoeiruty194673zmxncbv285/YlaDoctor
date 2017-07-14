package com.kareem.yladoctor.Models.Modules.User;

import com.google.firebase.auth.FirebaseUser;
import com.kareem.yladoctor.Models.Enums.AccountType;
import com.kareem.yladoctor.Models.Modules.Attachments.Picture;

import java.util.HashMap;

/**
 * Created by kareem on 5/28/2017 - Akshef.
 * <br></br>
 * individual module represents users as stand alone without business
 * inherit it's data from user
 *
 * @author kareem
 * @version %I%
 * @see com.kareem.yladoctor.Models.Modules.User.User
 */

public class Individual extends User {
	//points related to the individual
	private int points;
	//profile picture for that individual
	private Picture profilePicture;
	//mobile number of the user
	private String mobileNumber;
	//name of that user
	private String name;

	/**
	 * constructor to create a new individual
	 *
	 * @param accountType account type for that individual
	 * @param email       email related for that individual
	 * @param password    password associated with the email
	 */
	public Individual ( AccountType accountType, String email, String password, String UID, String name, int points, Picture profilePicture ) {
		super(accountType, email, password, UID);
		this.profilePicture = profilePicture;
		this.points = points;
		this.name = name;
	}

	public Individual ( AccountType accountType, String UID ) {
		super(accountType, UID);
	}

	public Individual ( AccountType accountType, FirebaseUser firebaseUser ) {
		super(accountType, firebaseUser);
		if (firebaseUser!=null) {
			if (firebaseUser.getPhotoUrl()!=null)
			this.profilePicture = new Picture(firebaseUser.getPhotoUrl().toString());
			this.name = firebaseUser.getDisplayName();
			this.mobileNumber = firebaseUser.getPhoneNumber();
		}
	}

	/**
	 * empty constructor for firebase
	 */
	public Individual () {
	}

	public String getMobileNumber () {
		return mobileNumber;
	}

	public void setMobileNumber ( String mobileNumber ) {
		this.mobileNumber = mobileNumber;
	}

	public String getName () {
		return name;
	}

	public void setName ( String name ) {
		this.name = name;
	}

	public int getPoints () {
		return points;
	}

	public void setPoints ( int points ) {
		this.points = points;
	}

	public Picture getProfilePicture () {
		return profilePicture;
	}

	public void setProfilePicture ( Picture profilePicture ) {
		this.profilePicture = profilePicture;
	}
}
