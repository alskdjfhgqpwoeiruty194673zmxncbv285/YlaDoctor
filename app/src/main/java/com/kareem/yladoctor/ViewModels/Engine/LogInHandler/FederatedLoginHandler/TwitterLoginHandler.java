package com.kareem.yladoctor.ViewModels.Engine.LogInHandler.FederatedLoginHandler;

import com.google.firebase.auth.TwitterAuthProvider;
import com.kareem.yladoctor.Models.Interfaces.ViewModelsInterfaces.UIEngine.LogInHandler.TwitterLoginHandlerInterface;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.Twitter;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.TwitterSession;
import com.twitter.sdk.android.core.identity.TwitterAuthClient;

/**
 * Created by kareem on 6/12/2017 - YlaDoctor.
 * <br></br>
 * description goes here
 *
 * @author kareem
 * @version %I%
 */

public class TwitterLoginHandler extends FederatedIdentityLoginHandler {
	public TwitterLoginHandler ( TwitterLoginHandlerInterface twitterLoginHandlerInterface ) {
		super(twitterLoginHandlerInterface);
	}

	@Override
	void resetAuthProvidersForSafety () {
		((TwitterLoginHandlerInterface) generalLogInHandlerInterface).setTwitterAuthClient(null);
	}

	@Override
	public void signInUser () {
		generalLogInHandlerInterface.createAndShowLoadingDialog();
		resetAuthProvidersForSafety();
//		Fabric.with(generalLogInHandlerInterface.getAppCompatActivity(), new TwitterCore(authConfig));
		Twitter.initialize(generalLogInHandlerInterface.getAppCompatActivity());
		((TwitterLoginHandlerInterface) generalLogInHandlerInterface).setTwitterAuthClient(new TwitterAuthClient());
		((TwitterLoginHandlerInterface) generalLogInHandlerInterface).getTwitterAuthClient().authorize(generalLogInHandlerInterface.getAppCompatActivity(), new Callback<TwitterSession>() {
			@Override
			public void success ( Result<TwitterSession> result ) {
				// TODO: 6/7/2017 restore the next line and add account type listener to user-account type node and write to settings file
//				callForAccountTypeInfo(task.getResult().getUser());
				TwitterLoginHandler.super.firebaseAuthUsingCredential(TwitterAuthProvider.getCredential(result.data.getAuthToken().token, result.data.getAuthToken().secret));
			}

			@Override
			public void failure ( TwitterException exception ) {
				generalLogInHandlerInterface.OnLogInIsFailed();
			}
		});
	}
}
