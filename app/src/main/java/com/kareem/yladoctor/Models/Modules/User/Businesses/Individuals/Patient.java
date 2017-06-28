/*
 * Copyright (c) 2016. KAREEM ElSAYED ALY  ALL RIGHTS RESERVED.
 */

package com.kareem.yladoctor.Models.Modules.User.Businesses.Individuals;

import android.graphics.Bitmap;

import com.google.firebase.auth.FirebaseUser;
import com.kareem.yladoctor.Models.Enums.AccountType;
import com.kareem.yladoctor.Models.Modules.Attachments.Picture;
import com.kareem.yladoctor.Models.Modules.User.Individual;

/**
 * Created by kareem on 8/8/2016 - HealMe.
 * <br></br>
 * introduce Patient module and all it's required variables
 *
 * @author kareem
 * @version %I%
 */

public class Patient extends Individual {

	private String emergencyNumber;

	public Patient ( String uid, String email, String name, Picture profilePicture ) {
		super(AccountType.PATIENT, email, "", uid, name, 0, profilePicture);
//        setter = new SetValueListener(this);
	}

	public Patient ( String UID ) {
		super(AccountType.PATIENT, UID);
	}

	public Patient ( FirebaseUser firebaseUser ) {
		super(AccountType.PATIENT, firebaseUser);
	}

	public Patient () {
	}

	public String getEmergencyNumber () {
		return emergencyNumber;
	}

	public void setEmergencyNumber ( String emergencyNumber ) {
		this.emergencyNumber = emergencyNumber;
	}


}
