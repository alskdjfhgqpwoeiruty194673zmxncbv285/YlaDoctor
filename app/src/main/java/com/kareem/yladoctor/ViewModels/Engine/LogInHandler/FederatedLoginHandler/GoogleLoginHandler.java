package com.kareem.yladoctor.ViewModels.Engine.LogInHandler.FederatedLoginHandler;

import android.content.Intent;
import android.support.annotation.NonNull;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.GoogleAuthProvider;
import com.kareem.yladoctor.Models.Interfaces.ViewModelsInterfaces.UIEngine.LogInHandler.GoogleLoginHandlerInterface;
import com.kareem.yladoctor.R;

/**
 * Created by kareem on 6/12/2017 - YlaDoctor.
 * <br></br>
 * description goes here
 *
 * @author kareem
 * @version %I%
 */

public class GoogleLoginHandler extends FederatedIdentityLoginHandler {
	public GoogleLoginHandler ( GoogleLoginHandlerInterface GoogleLoginHandlerInterface ) {
		super(GoogleLoginHandlerInterface);
	}

	private GoogleApiClient getGoogleApiClient () {
		GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(generalLogInHandlerInterface.getAppCompatActivity().getString(R.string.web_client_id)).requestEmail().build();

		return new GoogleApiClient.Builder(generalLogInHandlerInterface.getAppCompatActivity()).enableAutoManage(generalLogInHandlerInterface.getAppCompatActivity(), new GoogleApiClient.OnConnectionFailedListener() {
			@Override
			public void onConnectionFailed ( @NonNull ConnectionResult connectionResult ) {
				((GoogleLoginHandlerInterface) generalLogInHandlerInterface).onLostConnection();
			}
		}).addApi(Auth.GOOGLE_SIGN_IN_API, gso).build();
	}

	public void GoogleAccountSignedInAndReturnedWithResults ( Intent data ) {
		generalLogInHandlerInterface.createAndShowLoadingDialog();
		GoogleSignInResult googleSignInResult = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
		if (googleSignInResult.isSuccess()) {
			firebaseAuthWithGoogle(googleSignInResult.getSignInAccount());

		} else
			generalLogInHandlerInterface.OnLogInIsFailed();
	}

	private void firebaseAuthWithGoogle ( GoogleSignInAccount googleSignInAccount ) {
		GoogleLoginHandler.super.firebaseAuthUsingCredential(GoogleAuthProvider.getCredential(googleSignInAccount.getIdToken(), null));
	}

	@Override
	void resetAuthProvidersForSafety () {
		return;
	}

	@Override
	public void signInUser () {
		((GoogleLoginHandlerInterface) generalLogInHandlerInterface).startCallGoogleWindowActivity(Auth.GoogleSignInApi.getSignInIntent(getGoogleApiClient()));
	}
}
