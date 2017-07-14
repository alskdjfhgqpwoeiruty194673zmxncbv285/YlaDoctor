package com.kareem.yladoctor.ViewModels.Engine.LogInHandler.FederatedLoginHandler;

import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.kareem.yladoctor.Views.Dialogs.MobileNumberVerificationDialog;
import com.kareem.yladoctor.Models.Interfaces.ViewModelsInterfaces.UIEngine.LogInHandler.GeneralLogInHandlerInterface;

import java.util.concurrent.TimeUnit;

/**
 * Created by kareem on 6/12/2017 - YlaDoctor.
 * <br></br>
 * description goes here
 *
 * @author kareem
 * @version %I%
 */

public class MobileNumberLoginHandler extends FederatedIdentityLoginHandler {
	private String mobileNumber;
	private String verificationCode;
	MobileNumberVerificationDialog mobileNumberVerificationDialog;

	public MobileNumberLoginHandler ( GeneralLogInHandlerInterface generalLogInHandlerInterface, String mobileNumber ) {
		super(generalLogInHandlerInterface);
		this.mobileNumber = mobileNumber;
	}

	@Override
	public void signInUser () {
		generalLogInHandlerInterface.createAndShowLoadingDialog();
		// TODO: 6/10/2017 present a input dialog for the user to insert the non-automatically detected verification code and also a counter for resending the code
		PhoneAuthProvider.getInstance().verifyPhoneNumber(mobileNumber, 60, TimeUnit.SECONDS, generalLogInHandlerInterface.getAppCompatActivity(), new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
			@Override
			public void onVerificationCompleted ( PhoneAuthCredential phoneAuthCredential ) {
				// TODO: 6/7/2017 restore the next line and add account type listener to user-account type node and write to settings file
//				callForAccountTypeInfo(task.getResult().getUser());
				MobileNumberLoginHandler.super.firebaseAuthUsingCredential(phoneAuthCredential);
			}

			@Override
			public void onVerificationFailed ( FirebaseException e ) {
				generalLogInHandlerInterface.OnLogInIsFailed();
			}

			@Override
			public void onCodeSent ( String s, PhoneAuthProvider.ForceResendingToken forceResendingToken ) {
				verificationCode = s;
				mobileNumberVerificationDialog = new MobileNumberVerificationDialog(generalLogInHandlerInterface.getAppCompatActivity(), MobileNumberLoginHandler.this);
			}
		});
	}

	public void authenticateWithInsertedCode ( String code ) {
		PhoneAuthCredential phoneAuthCredential = PhoneAuthProvider.getCredential(verificationCode, code);
		firebaseAuthUsingCredential(phoneAuthCredential);
	}

	@Override
	void resetAuthProvidersForSafety () {
	}


}
