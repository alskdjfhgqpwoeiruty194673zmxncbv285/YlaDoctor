package com.kareem.yladoctor.Models.Interfaces.ViewModelsInterfaces.UIEngine.LogInHandler;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by kareem on 6/12/2017 - YlaDoctor.
 * <br></br>
 * description goes here
 *
 * @author kareem
 * @version %I%
 */

public interface GeneralLogInHandlerInterface {

	AppCompatActivity getAppCompatActivity ();

	void onLogInCompleted ();

	void createAndShowLoadingDialog ();

	void dismissLoadingDialog ();

	void OnLogInIsFailed ();
}
