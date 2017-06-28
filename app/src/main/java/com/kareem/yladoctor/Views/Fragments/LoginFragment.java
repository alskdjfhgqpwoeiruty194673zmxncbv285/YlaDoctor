package com.kareem.yladoctor.Views.Fragments;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.kareem.yladoctor.Models.Dialogs.LoadingDialog;
import com.kareem.yladoctor.R;
import com.kareem.yladoctor.ViewModels.Engine.LogInHandler.LoginUserManager;
import com.kareem.yladoctor.ViewModels.UIModels.Activities.general.LoginFragmentViewModel;
import com.kareem.yladoctor.Views.Activites.general.MainPage;
import com.sdsmdg.tastytoast.TastyToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by kareem on 5/30/2017 - Akshef.
 * <br></br>
 * fragment for login
 *
 * @author kareem
 * @version %I%
 */

public class LoginFragment extends Fragment {
	private final int GOOGLE_SIGNIN_CODE = 1;
	//	private CallbackManager callbackManager;
//	private TwitterAuthClient twitterAuthClient;
	private LoginFragmentViewModel loginFragmentViewModel;
	private LoginUserManager loginUserManager;
	private LoadingDialog loadingDialog;
	@BindView(R.id.generalFragmentLogin_editText_emailInputText)
	EditText emailInputEditText;
	@BindView(R.id.generalFragmentLogin_editText_passwordInputText)
	EditText passwordInputEditText;
	@BindView(R.id.generalFragmentLogin_editText_mobileNumberInputText)
	EditText mobileNumberInputEditText;

	@OnClick(R.id.generalFragmentLogin_button_logInUsingEmailAndPassword)
	public void onClickLogInUsingEmailAndPasswordButton () {
		if (loginFragmentViewModel.checkEditTextInputIsEmptyOrNot(emailInputEditText) && loginFragmentViewModel.checkEditTextInputIsEmptyOrNot(passwordInputEditText))
			loginUserManager.SignInUsingEmailAndPassword(loginFragmentViewModel.returnEditTextString(emailInputEditText), loginFragmentViewModel.returnEditTextString(passwordInputEditText));
	}

	@Override
	public void onViewCreated ( View view, @Nullable Bundle savedInstanceState ) {
		super.onViewCreated(view, savedInstanceState);
		ButterKnife.bind(this,view);
		initializeVariables();
	}

	@OnClick(R.id.generalFragmentLogin_button_logInUsingMobileNumber)
	public void onClickLogInUsingMobileNumberButton () {
// TODO: 6/7/2017 add it later and check for editTextInputIsEmptyOrNot first
		if (loginFragmentViewModel.checkEditTextInputIsEmptyOrNot(mobileNumberInputEditText))
			loginUserManager.SignInUsingMobileNumber(getString(R.string.local_code) + loginFragmentViewModel.returnEditTextString(mobileNumberInputEditText));
	}

	@OnClick(R.id.generalFragmentLogin_button_logInUsingGoogle)
	public void onClickLogInUsingGoogleButton () {
		loginUserManager.SignInUsingGoogle();
	}

	@OnClick(R.id.generalFragmentLogin_button_logInUsingTwitter)
	public void onClickLogInUsingTwitterButton () {
		loginUserManager.SignInUsingTwitter();
	}

	@OnClick(R.id.generalFragmentLogin_button_logInUsingFacebook)
	public void onClickLogInUsingFacebookButton () {
		loginUserManager.SignInUsingFacebook();
	}

	@OnClick(R.id.generalFragmentLogin_textView_createNewAccount)
	public void onClickCreateNewAccount () {
// TODO: 6/15/2017 add message later
	}

	@Override
	public void onActivityResult ( int requestCode, int resultCode, Intent data ) {
		if (requestCode == GOOGLE_SIGNIN_CODE)
			loginUserManager.getGoogleAccountSignedInAndReturnedWithResults(data);
		else {
			super.onActivityResult(requestCode, resultCode, data);
			loginUserManager.getFacebookAndTwitterAccountSignedInAndCallTheirCallBackManager(requestCode, resultCode, data);
		}

	}

	@Nullable
	@Override
	public View onCreateView ( LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState ) {
		return inflater.inflate(R.layout.general_fragment_login, container, false);
	}

	public void initializeVariables () {
		loginUserManager = new LoginUserManager() {
			@Override
			public void onLostConnection () {
				dismissLoadingDialog();
				TastyToast.makeText(LoginFragment.this.getActivity(), "Connection lost", TastyToast.LENGTH_SHORT, TastyToast.ERROR);
			}

			@Override
			public void startCallGoogleWindowActivity ( Intent intent ) {
				LoginFragment.this.startActivityForResult(intent, GOOGLE_SIGNIN_CODE);
			}

			@Override
			public AppCompatActivity getAppCompatActivity () {
				return (AppCompatActivity) LoginFragment.this.getActivity();
			}

			@Override
			public void onLogInCompleted () {
				dismissLoadingDialog();
				LoginFragment.this.getActivity().startActivity(new Intent(LoginFragment.this.getActivity(), MainPage.class));
				LoginFragment.this.getActivity().finish();
			}

			@Override
			public void createAndShowLoadingDialog () {
				if (loadingDialog == null)
					loadingDialog = new LoadingDialog(LoginFragment.this.getActivity());
				loadingDialog.show();
			}

			@Override
			public void dismissLoadingDialog () {
				if (loadingDialog != null)
					loadingDialog.dismiss();
			}

			@Override
			public void OnLogInIsFailed () {
				dismissLoadingDialog();
				TastyToast.makeText(LoginFragment.this.getActivity(), "Account type couldn't be determined", TastyToast.LENGTH_SHORT, TastyToast.ERROR);
			}
		};
		loginFragmentViewModel = new LoginFragmentViewModel(loginUserManager);
	}

	@Override
	public void onStop () {
		super.onStop();
		loginUserManager.dismissLoadingDialog();
	}
}
