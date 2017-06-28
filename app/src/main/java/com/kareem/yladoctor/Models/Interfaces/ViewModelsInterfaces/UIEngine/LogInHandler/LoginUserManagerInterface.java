package com.kareem.yladoctor.Models.Interfaces.ViewModelsInterfaces.UIEngine.LogInHandler;

import android.content.Intent;

/**
 * Created by kareem on 6/14/2017 - YlaDoctor.
 * <br></br>
 * description goes here
 *
 * @author kareem
 * @version %I%
 */

public interface LoginUserManagerInterface {
	public void SignInUsingFacebook();
	public void SignInUsingGoogle();
	public void SignInUsingTwitter();
	public void SignInUsingEmailAndPassword(String email,String password);
	public void SignInUsingMobileNumber(String mobileNumber);
	public void getGoogleAccountSignedInAndReturnedWithResults(Intent data);
	public void getFacebookAndTwitterAccountSignedInAndCallTheirCallBackManager( int requestCode, int resultCode, Intent data );
}
