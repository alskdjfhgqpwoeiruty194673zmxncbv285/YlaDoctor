package com.kareem.yladoctor.ViewModels.UIModels.Activities.general;

import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.kareem.yladoctor.Factories.DatabasePathFactory;
import com.kareem.yladoctor.MainApplication;
import com.kareem.yladoctor.Models.Contracts.FirebaseContracts;
import com.kareem.yladoctor.Models.Enums.AccountType;
import com.kareem.yladoctor.Models.Helpers.FirebaseListener;
import com.kareem.yladoctor.Models.Interfaces.FirebaseListeners;
import com.kareem.yladoctor.Models.Modules.User.Businesses.Individuals.Doctor;
import com.kareem.yladoctor.Models.Modules.User.Businesses.Individuals.Patient;
import com.kareem.yladoctor.Models.Modules.User.User;
import com.kareem.yladoctor.ViewModels.Engine.LogInHandler.GeneralLoginHandler;
import com.kareem.yladoctor.ViewModels.Engine.LogInHandler.LoginUserManager;
import com.kareem.yladoctor.ViewModels.Engine.UserAccountTypeManager;
import com.sdsmdg.tastytoast.TastyToast;


/**
 * Created by kareem on 6/5/2017 - YlaDoctor.
 * <br></br>
 * description goes here
 *
 * @author kareem
 * @version %I%
 */

public class LoginFragmentViewModel {
	//	private GeneralLogInHandlerInterface generalLogInHandlerInterface;
	private LoginUserManager loginUserManager;

	public LoginFragmentViewModel ( LoginUserManager loginUserManager ) {
		this.loginUserManager = loginUserManager;
//		FirebaseAuth.getInstance().signOut();
		initializeStartingPointForAlreadyLoggedInOrNeedToLogIn();
	}

	private void initializeStartingPointForAlreadyLoggedInOrNeedToLogIn () {
		loginUserManager.createAndShowLoadingDialog();
		if (GeneralLoginHandler.checkForUserAlreadySignedIn()) {
			ConnectivityManager connectivityManager = (ConnectivityManager) loginUserManager.getAppCompatActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
			NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
			if (networkInfo != null && networkInfo.isConnected()) {
				new FirebaseListener(new FirebaseListeners() {
					@Override
					public void ValueEventListener ( Object data, DataSnapshot dataSnapshot, String ID ) {

					}


					@Override
					public void SingleValueEventListener ( Object data, DataSnapshot dataSnapshot, String ID ) {
						if (dataSnapshot.getValue() != null) {
							User user = null;
							switch (dataSnapshot.child(FirebaseContracts.PATH_TO_USERS_USERUID_ACCOUNTTYPE).getValue(AccountType.class)) {
								case DOCTOR:
									user = dataSnapshot.getValue(Doctor.class);
									break;
								case PATIENT:
									user = dataSnapshot.getValue(Patient.class);
									break;
								default:
									user = dataSnapshot.getValue(User.class);
							}
							((MainApplication) loginUserManager.getAppCompatActivity().getApplication()).setUser(user);
							UserAccountTypeManager.setAccountType(loginUserManager.getAppCompatActivity(), user.getAccountType());
							triggerConnectedUser();
						} else
							TastyToast.makeText(loginUserManager.getAppCompatActivity(), "Your data couldn't be retrieved!", TastyToast.LENGTH_LONG, TastyToast.ERROR);
					}

					@Override
					public void onFailure ( Object data, Throwable error, String ID ) {

						TastyToast.makeText(loginUserManager.getAppCompatActivity(), error.getMessage(), TastyToast.LENGTH_LONG, TastyToast.ERROR);
					}
				}).initSingleValueEventListener(null, DatabasePathFactory.pathTo_User_UserUID(FirebaseAuth.getInstance().getCurrentUser().getUid()), FirebaseContracts.PATH_TO_USERS);
			} else
				new AlertDialog.Builder(loginUserManager.getAppCompatActivity()).setTitle("No Available Internet Connection").setPositiveButton("Continue anyWay!", new DialogInterface.OnClickListener() {
					@Override
					public void onClick ( DialogInterface dialog, int which ) {
						triggerUnConnectedUser();
					}
				}).show();
		} else
			loginUserManager.dismissLoadingDialog();
	}

	private void triggerUnConnectedUser () {
		AccountType accountType = UserAccountTypeManager.getAccountType(loginUserManager.getAppCompatActivity());
		if (accountType != AccountType.UNKNOWN) {
			((MainApplication) loginUserManager.getAppCompatActivity().getApplication()).setUser(UserAccountTypeManager.createUserAccordingToUserAccountType(GeneralLoginHandler.getFirebaseUser(), accountType));
			loginUserManager.onLogInCompleted();
		} else {
			GeneralLoginHandler.getUserSignOut();
			loginUserManager.dismissLoadingDialog();
			loginUserManager.OnLogInIsFailed();
		}
	}

	private void triggerConnectedUser () {
		User user = ((MainApplication) loginUserManager.getAppCompatActivity().getApplication()).getUser();
		if (user.getAccountType() != AccountType.UNKNOWN)
			loginUserManager.onLogInCompleted();
		else {
			TastyToast.makeText(loginUserManager.getAppCompatActivity(), "User Type couldn't be identified!", TastyToast.LENGTH_LONG, TastyToast.ERROR);
			GeneralLoginHandler.getUserSignOut();
			loginUserManager.dismissLoadingDialog();
			loginUserManager.OnLogInIsFailed();
		}
	}

	public boolean checkEditTextInputIsEmptyOrNot ( @NonNull EditText editText ) {
		editText.setError(null);
		String text = returnEditTextString(editText);
		if (text.isEmpty() || text.equals("") || text.equals(" ")) {
			editText.setError("Cannot be empty");
			return false;
		}
		return true;
	}

	public String returnEditTextString ( @NonNull EditText editText ) {
		return editText.getText().toString();
	}
}
