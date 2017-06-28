package com.kareem.yladoctor.ViewModels.Engine.FirebaseDataGrabbers;

import com.kareem.yladoctor.Models.Contracts.FirebaseContracts;

/**
 * Created by kareem on 6/12/2017 - YlaDoctor.
 * <br></br>
 * description goes here
 *
 * @author kareem
 * @version %I%
 */

public abstract class UserAccountTypeGrabber extends GeneralFirebaseDataGrabber {
	@Override
	public String pathToDataOnFirebase () {
		return FirebaseContracts.PATH_TO_USERS+getFirebaseUser().getUid()+FirebaseContracts.PATH_TO_USERS_USERUID_ACCOUNTTYPE;
	}
}
