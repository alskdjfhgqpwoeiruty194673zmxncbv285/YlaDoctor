package com.kareem.yladoctor.ViewModels.Engine.LogInHandler;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.kareem.yladoctor.Factories.DatabasePathFactory;
import com.kareem.yladoctor.MainApplication;
import com.kareem.yladoctor.Models.Contracts.FirebaseContracts;
import com.kareem.yladoctor.Models.Enums.AccountType;
import com.kareem.yladoctor.Models.Helpers.FirebaseListener;
import com.kareem.yladoctor.Models.Helpers.SetValueListener;
import com.kareem.yladoctor.Models.Interfaces.FirebaseListeners;
import com.kareem.yladoctor.Models.Interfaces.ViewModelsInterfaces.UIEngine.LogInHandler.GeneralLogInHandlerInterface;
import com.kareem.yladoctor.Models.Interfaces.newValueListener;
import com.kareem.yladoctor.Models.Modules.User.Businesses.Individuals.Patient;
import com.kareem.yladoctor.ViewModels.Engine.UserAccountTypeManager;
import com.sdsmdg.tastytoast.TastyToast;

/**
 * Created by kareem on 6/12/2017 - YlaDoctor.
 * <br></br>
 * description goes here
 *
 * @author kareem
 * @version %I%
 */

public abstract class GeneralLoginHandler implements FirebaseListeners, newValueListener {
	protected GeneralLogInHandlerInterface generalLogInHandlerInterface;

	public GeneralLoginHandler ( GeneralLogInHandlerInterface generalLogInHandlerInterface ) {
		this.generalLogInHandlerInterface = generalLogInHandlerInterface;
	}

	public static void getUserSignOut () {
		getFirebaseAuth().signOut();
	}

	public abstract void signInUser ();

	protected static FirebaseAuth getFirebaseAuth () {
		return FirebaseAuth.getInstance();
	}

	public static FirebaseUser getFirebaseUser () {
		return getFirebaseAuth().getCurrentUser();
	}

	public static boolean checkForUserAlreadySignedIn () {
		return getFirebaseUser() != null;
	}

	public void checkUserIfExistOnlineOrNot ( FirebaseUser firebaseUser ) {
		new FirebaseListener(this).initSingleValueEventListener(null, DatabasePathFactory.pathTo_User_UserUID_AccountType(firebaseUser.getUid()), FirebaseContracts.PATH_TO_USERS_USERUID_ACCOUNTTYPE);
	}

	@Override
	public void ValueEventListener ( Object data, DataSnapshot dataSnapshot, String ID ) {

	}

	@Override
	public void SingleValueEventListener ( Object data, DataSnapshot dataSnapshot, String ID ) {
		if (ID.equals(FirebaseContracts.PATH_TO_USERS_USERUID_ACCOUNTTYPE)) {
			if (dataSnapshot.getValue() != null) {
				handleLoginSteps_StoreAccountTypeOffline_CreateProperUserOnMainApplication(dataSnapshot.getValue(AccountType.class));
			} else
				CreateNewUserDataOnDatabaseForFirstTime();
		}
	}

	private void handleLoginSteps_StoreAccountTypeOffline_CreateProperUserOnMainApplication ( AccountType value ) {
		WriteUserAccountOnDatabase(value);
		WriteUserTypeOnMainApplication(value);
		onSucceed(null, "");
	}

	private void WriteUserTypeOnMainApplication ( AccountType accountType ) {
		((MainApplication) generalLogInHandlerInterface.getAppCompatActivity().getApplication()).setUser(UserAccountTypeManager.createUserAccordingToUserAccountType(getFirebaseUser(), accountType));
	}

	private void WriteUserAccountOnDatabase ( AccountType accountType ) {
		UserAccountTypeManager.setAccountType(generalLogInHandlerInterface.getAppCompatActivity(), accountType);
	}


	private void CreateNewUserDataOnDatabaseForFirstTime () {
		String UID = getFirebaseUser().getUid();
		Patient patient = new Patient(UID);
		new SetValueListener(this).setNewValue(null, patient, DatabasePathFactory.pathTo_User_UserUID(UID), FirebaseContracts.PATH_TO_USERS);
	}

	@Override
	public void onSucceed ( Object data, String ID ) {
		if (ID.equals(FirebaseContracts.PATH_TO_USERS)) {
			handleLoginSteps_StoreAccountTypeOffline_CreateProperUserOnMainApplication(AccountType.PATIENT);
			TastyToast.makeText(generalLogInHandlerInterface.getAppCompatActivity(), "Your new account is ready", TastyToast.LENGTH_SHORT, TastyToast.SUCCESS);
		}
		generalLogInHandlerInterface.onLogInCompleted();
	}

	@Override
	public void onFailure ( Object data, Throwable error, String ID ) {
		String errorMSG = "";
		switch (ID) {
			case FirebaseContracts.PATH_TO_USERS:
				errorMSG = "Error creating new account";
				break;
			case FirebaseContracts.PATH_TO_USERS_USERUID_ACCOUNTTYPE:
				errorMSG = "Your data couldn't be retrieved";
		}
		TastyToast.makeText(generalLogInHandlerInterface.getAppCompatActivity(), errorMSG, TastyToast.LENGTH_SHORT, TastyToast.ERROR);
		generalLogInHandlerInterface.OnLogInIsFailed();
	}
}
