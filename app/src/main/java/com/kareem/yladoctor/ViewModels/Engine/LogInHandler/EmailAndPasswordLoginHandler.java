package com.kareem.yladoctor.ViewModels.Engine.LogInHandler;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.kareem.yladoctor.Models.Interfaces.ViewModelsInterfaces.UIEngine.LogInHandler.GeneralLogInHandlerInterface;
import com.sdsmdg.tastytoast.TastyToast;

/**
 * Created by kareem on 6/12/2017 - YlaDoctor.
 * <br></br>
 * description goes here
 *
 * @author kareem
 * @version %I%
 */

public class EmailAndPasswordLoginHandler extends GeneralLoginHandler {
	private String email, password;

	public EmailAndPasswordLoginHandler ( GeneralLogInHandlerInterface generalLogInHandlerInterface, String email, String password ) {
		super(generalLogInHandlerInterface);
		this.email = email;
		this.password = password;
	}

	@Override
	public void signInUser () {
		generalLogInHandlerInterface.createAndShowLoadingDialog();
		getFirebaseAuth().signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
			@Override
			public void onComplete ( @NonNull Task<AuthResult> task ) {
				if (task.isSuccessful()) {
					checkUserIfExistOnlineOrNot(task.getResult().getUser());
				} else {
					try {
						throw task.getException();
					} catch (FirebaseAuthInvalidUserException f) {
						if (f.getErrorCode().equals("ERROR_USER_NOT_FOUND")) {
							TastyToast.makeText(generalLogInHandlerInterface.getAppCompatActivity(), "NewUser is detected", TastyToast.LENGTH_SHORT, TastyToast.DEFAULT);
							getFirebaseAuth().createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
								@Override
								public void onComplete ( @NonNull Task<AuthResult> task ) {
									if (task.isSuccessful()) {
										checkUserIfExistOnlineOrNot(task.getResult().getUser());
									} else {
										try {
											throw task.getException();
										} catch (Exception e) {
											e.printStackTrace();
											generalLogInHandlerInterface.OnLogInIsFailed();
										}
									}
								}
							});
						}
					} catch (Exception e) {
						e.printStackTrace();
						generalLogInHandlerInterface.OnLogInIsFailed();
					}
				}
			}
		});
	}

}
