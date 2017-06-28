package com.kareem.yladoctor.ViewModels.Engine.LogInHandler;

import android.content.Intent;

import com.facebook.CallbackManager;
import com.kareem.yladoctor.Models.Interfaces.ViewModelsInterfaces.UIEngine.LogInHandler.FacebookLoginHandlerInterface;
import com.kareem.yladoctor.Models.Interfaces.ViewModelsInterfaces.UIEngine.LogInHandler.GoogleLoginHandlerInterface;
import com.kareem.yladoctor.Models.Interfaces.ViewModelsInterfaces.UIEngine.LogInHandler.LoginUserManagerInterface;
import com.kareem.yladoctor.Models.Interfaces.ViewModelsInterfaces.UIEngine.LogInHandler.TwitterLoginHandlerInterface;
import com.kareem.yladoctor.ViewModels.Engine.LogInHandler.FederatedLoginHandler.FacebookLoginHandler;
import com.kareem.yladoctor.ViewModels.Engine.LogInHandler.FederatedLoginHandler.GoogleLoginHandler;
import com.kareem.yladoctor.ViewModels.Engine.LogInHandler.FederatedLoginHandler.MobileNumberLoginHandler;
import com.kareem.yladoctor.ViewModels.Engine.LogInHandler.FederatedLoginHandler.TwitterLoginHandler;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;

/**
 * Created by kareem on 6/14/2017 - YlaDoctor.
 * <br></br>
 * description goes here
 *
 * @author kareem
 * @version %I%
 */

public abstract class LoginUserManager implements FacebookLoginHandlerInterface, LoginUserManagerInterface, GoogleLoginHandlerInterface, TwitterLoginHandlerInterface {

	private CallbackManager FBCallbackManager;
	private TwitterAuthClient twitterAuthClient;
	private GoogleLoginHandler googleLoginHandler;

	@Override
	public void SignInUsingFacebook () {
		new FacebookLoginHandler(this).signInUser();
	}

	public void SignInUsingGoogle () {
		if (googleLoginHandler == null)
			googleLoginHandler = new GoogleLoginHandler(this);
		googleLoginHandler.signInUser();
	}

	@Override
	public void SignInUsingTwitter () {
		new TwitterLoginHandler(this).signInUser();
	}

	@Override
	public void SignInUsingEmailAndPassword ( String email, String password ) {
		new EmailAndPasswordLoginHandler(this, email, password).signInUser();
	}

	@Override
	public void SignInUsingMobileNumber ( String mobileNumber ) {
		new MobileNumberLoginHandler(this, mobileNumber).signInUser();
	}

	@Override
	public void getGoogleAccountSignedInAndReturnedWithResults ( Intent data ) {
		googleLoginHandler.GoogleAccountSignedInAndReturnedWithResults(data);
	}

	@Override
	public void getFacebookAndTwitterAccountSignedInAndCallTheirCallBackManager ( int requestCode, int resultCode, Intent data ) {
		if (getFacebookCallBackManager()!= null)
			getFacebookCallBackManager().onActivityResult(requestCode, resultCode, data);
		if (getTwitterAuthClient() != null)
			getTwitterAuthClient().onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public CallbackManager getFacebookCallBackManager () {
		return FBCallbackManager;
	}

	@Override
	public void setFacebookCallBackManager ( CallbackManager callbackManager ) {
		this.FBCallbackManager = callbackManager;
	}

	@Override
	public TwitterAuthClient getTwitterAuthClient () {
		return twitterAuthClient;
	}

	@Override
	public void setTwitterAuthClient ( TwitterAuthClient twitterAuthClient ) {
		this.twitterAuthClient = twitterAuthClient;
	}

}
