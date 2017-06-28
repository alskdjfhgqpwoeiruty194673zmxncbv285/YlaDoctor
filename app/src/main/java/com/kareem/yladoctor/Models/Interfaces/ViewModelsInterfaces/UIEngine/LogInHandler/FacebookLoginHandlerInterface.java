package com.kareem.yladoctor.Models.Interfaces.ViewModelsInterfaces.UIEngine.LogInHandler;

import com.facebook.CallbackManager;

/**
 * Created by kareem on 6/12/2017 - YlaDoctor.
 * <br></br>
 * description goes here
 *
 * @author kareem
 * @version %I%
 */

public interface FacebookLoginHandlerInterface extends GeneralLogInHandlerInterface {
	CallbackManager getFacebookCallBackManager ();

	void setFacebookCallBackManager ( CallbackManager callbackManager );
}
