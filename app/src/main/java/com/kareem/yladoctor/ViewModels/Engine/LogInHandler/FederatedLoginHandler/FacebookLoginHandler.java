package com.kareem.yladoctor.ViewModels.Engine.LogInHandler.FederatedLoginHandler;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.kareem.yladoctor.Models.Interfaces.ViewModelsInterfaces.UIEngine.LogInHandler.FacebookLoginHandlerInterface;

import java.util.Arrays;

/**
 * Created by kareem on 6/12/2017 - YlaDoctor.
 * <br></br>
 * description goes here
 *
 * @author kareem
 * @version %I%
 */

public class FacebookLoginHandler extends FederatedIdentityLoginHandler {
	public FacebookLoginHandler ( FacebookLoginHandlerInterface facebookLoginHandlerInterface ) {
		super(facebookLoginHandlerInterface);
	}

	@Override
	void resetAuthProvidersForSafety () {
		((FacebookLoginHandlerInterface)generalLogInHandlerInterface).setFacebookCallBackManager(null);
	}

	@Override
	public void signInUser () {
		generalLogInHandlerInterface.createAndShowLoadingDialog();
		// TODO: 6/10/2017 check why some users are not registered in the database
		resetAuthProvidersForSafety();
		((FacebookLoginHandlerInterface)generalLogInHandlerInterface).setFacebookCallBackManager(CallbackManager.Factory.create());
		LoginManager.getInstance().logInWithReadPermissions(generalLogInHandlerInterface.getAppCompatActivity(), Arrays.asList("email", "public_profile"));
		LoginManager.getInstance().registerCallback(((FacebookLoginHandlerInterface)generalLogInHandlerInterface).getFacebookCallBackManager(), new FacebookCallback<LoginResult>() {
			@Override
			public void onSuccess ( LoginResult loginResult ) {
				// TODO: 6/7/2017 restore the next line and add account type listener to user-account type node and write to settings file
//				callForAccountTypeInfo(task.getResult().getUser());
				FacebookLoginHandler.super.firebaseAuthUsingCredential(FacebookAuthProvider.getCredential(loginResult.getAccessToken().getToken()));
			}

			@Override
			public void onCancel () {
				generalLogInHandlerInterface.dismissLoadingDialog();
			}

			@Override
			public void onError ( FacebookException error ) {
				generalLogInHandlerInterface.OnLogInIsFailed();
				error.printStackTrace();
			}
		});
	}
}
