package com.kareem.yladoctor.Models.Modules.User.Businesses.Individuals;


import com.google.firebase.auth.FirebaseUser;
import com.kareem.yladoctor.Models.Enums.AccountType;
import com.kareem.yladoctor.Models.Modules.Attachments.Picture;
import com.kareem.yladoctor.Models.Modules.User.Individual;
import com.kareem.yladoctor.Models.Modules.User.User;

import java.util.HashMap;

/**
 * Created by kareem on 6/18/2017 - YlaCreateContent.
 * <br></br>
 * description goes here
 *
 * @author kareem
 * @version %I%
 */

public class IndividualBusiness extends User {
	//points related to the individual
	private int points;
	//profile picture for that individual
	private Picture profilePicture;

	//name of that user
	private HashMap<String, String> names;

	public IndividualBusiness ( AccountType accountType, String email, String password, String UID, HashMap<String, String> names, int points, Picture profilePicture ) {
		super(accountType, email, password, UID);
		this.points = points;
		this.names = names;
		this.profilePicture = profilePicture;
	}

	public IndividualBusiness ( AccountType accountType,String UID ) {
		super(accountType,UID);
	}

	public IndividualBusiness ( AccountType accountType, FirebaseUser firebaseUser ) {
		super(accountType, firebaseUser);
	}

	public IndividualBusiness () {
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

	public HashMap<String, String> getNames () {
		return names;
	}

	public void setNames ( HashMap<String, String> names ) {
		this.names = names;
	}
}
