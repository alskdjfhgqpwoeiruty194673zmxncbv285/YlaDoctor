package com.kareem.yladoctor.Models.Interfaces.ViewModelsInterfaces.UIEngine.LogInHandler;

import android.content.Intent;

/**
 * Created by kareem on 6/12/2017 - YlaDoctor.
 * <br></br>
 * description goes here
 *
 * @author kareem
 * @version %I%
 */

public interface GoogleLoginHandlerInterface extends GeneralLogInHandlerInterface {
	void onLostConnection();
	void startCallGoogleWindowActivity(Intent intent);
}
