package com.kareem.yladoctor.Models.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;

import com.kareem.yladoctor.R;
import com.kareem.yladoctor.ViewModels.Engine.LogInHandler.FederatedLoginHandler.MobileNumberLoginHandler;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by kareem on 6/17/2017 - YlaCreateContent.
 * <br></br>
 * description goes here
 *
 * @author kareem
 * @version %I%
 */

public class MobileNumberVerificationDialog extends Dialog {
	private MobileNumberLoginHandler mobileNumberLoginHandler;
	@BindView(R.id.verifyMobileNumberView_EditText_verificationCode)
	EditText verificationCode;

	@OnClick(R.id.verifyMobileNumberView_button_cancel)
	public void onCancelIsClicked () {
		this.dismiss();
	}

	@OnClick(R.id.verifyMobileNumberView_button_resendVerificationCode)
	public void onResendVerificationCodeIsClicked () {
		mobileNumberLoginHandler.signInUser();
	}

	@OnClick(R.id.verifyMobileNumberView_button_submitCode)
	public void onsubmitCodeIsClicked () {
		if (checkEditTextValidity(verificationCode)) {
			String code = verificationCode.getText().toString().trim();
			mobileNumberLoginHandler.authenticateWithInsertedCode(code);
		}
	}

	public MobileNumberVerificationDialog ( @NonNull Context context, MobileNumberLoginHandler mobileNumberLoginHandler ) {
		super(context);
		View view = View.inflate(getContext(), R.layout.verify_mobile_number_view, null);
		setContentView(view);
		this.mobileNumberLoginHandler = mobileNumberLoginHandler;
		ButterKnife.bind(this, view);
		this.show();
	}

	private boolean checkEditTextValidity ( EditText editText ) {
		editText.setError(null);
		String s = editText.getText().toString().trim();
		if (s.isEmpty() || s.equals("")) {
			editText.setError("cannot be empty");
			return false;
		}
		return true;
	}
}
