package com.kareem.yladoctor.ViewModels.UIModels.Activities.general;

import android.support.annotation.NonNull;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.kareem.yladoctor.MainApplication;
import com.kareem.yladoctor.Models.Enums.AccountType;
import com.kareem.yladoctor.ViewModels.Engine.LogInHandler.GeneralLoginHandler;
import com.kareem.yladoctor.ViewModels.Engine.LogInHandler.LoginUserManager;
import com.kareem.yladoctor.ViewModels.Engine.UserAccountTypeManager;


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
			AccountType accountType = UserAccountTypeManager.getAccountType(loginUserManager.getAppCompatActivity());
			if (accountType != AccountType.UNKNOWN) {
				((MainApplication) loginUserManager.getAppCompatActivity().getApplication()).setUser(UserAccountTypeManager.createUserAccordingToUserAccountType(GeneralLoginHandler.getFirebaseUser(), accountType));
				loginUserManager.onLogInCompleted();
			} else {
				GeneralLoginHandler.getUserSignOut();
				loginUserManager.dismissLoadingDialog();
				loginUserManager.OnLogInIsFailed();
			}
		} else {
			loginUserManager.dismissLoadingDialog();
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
