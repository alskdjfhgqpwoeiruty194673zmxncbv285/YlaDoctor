package com.kareem.yladoctor.Models.Interfaces.ViewModelsInterfaces.UIEngine.LogInHandler;

import com.twitter.sdk.android.core.identity.TwitterAuthClient;

/**
 * Created by kareem on 6/12/2017 - YlaDoctor.
 * <br></br>
 * description goes here
 *
 * @author kareem
 * @version %I%
 */

public interface TwitterLoginHandlerInterface extends GeneralLogInHandlerInterface{
	TwitterAuthClient getTwitterAuthClient ();
	void setTwitterAuthClient ( TwitterAuthClient twitterAuthClient );
}
