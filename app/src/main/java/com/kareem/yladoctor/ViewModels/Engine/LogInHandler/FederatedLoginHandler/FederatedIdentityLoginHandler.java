package com.kareem.yladoctor.ViewModels.Engine.LogInHandler.FederatedLoginHandler;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseUser;
import com.kareem.yladoctor.Models.Interfaces.ViewModelsInterfaces.UIEngine.LogInHandler.GeneralLogInHandlerInterface;
import com.kareem.yladoctor.ViewModels.Engine.LogInHandler.GeneralLoginHandler;

/**
 * Created by kareem on 6/12/2017 - YlaDoctor.
 * <br></br>
 * description goes here
 *
 * @author kareem
 * @version %I%
 */

public abstract class FederatedIdentityLoginHandler extends GeneralLoginHandler {
	public FederatedIdentityLoginHandler ( GeneralLogInHandlerInterface generalLogInHandlerInterface ) {
		super(generalLogInHandlerInterface);
	}

	void firebaseAuthUsingCredential ( AuthCredential authCredential ) {
		getFirebaseAuth().signInWithCredential(authCredential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete ( @NonNull Task<AuthResult> task ) {
				generalLogInHandlerInterface.dismissLoadingDialog();
				if (task.isSuccessful()) {
					checkUserIfExistOnlineOrNot(task.getResult().getUser());
					// TODO: 6/7/2017 restore the next line and add account type listener to user-account type node and write to settings file
//				callForAccountTypeInfo(task.getResult().getUser());

				} else {
					generalLogInHandlerInterface.OnLogInIsFailed();
					task.getException().printStackTrace();
				}
			}
		});
	}

	abstract void resetAuthProvidersForSafety ();
}
