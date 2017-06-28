package com.kareem.yladoctor.ViewModels.Engine;

import android.content.Context;

import com.kareem.yladoctor.Models.Contracts.ContractValues;
import com.kareem.yladoctor.Models.Enums.AccountType;
import com.kareem.yladoctor.Models.Enums.DefaultLanguages;
import com.kareem.yladoctor.Models.Modules.User.Businesses.Individuals.Doctor;
import com.kareem.yladoctor.Models.Modules.User.Businesses.Individuals.Patient;
import com.kareem.yladoctor.Models.Modules.User.User;

/**
 * Created by kareem on 6/28/2017 - YlaDoctor.
 * <br></br>
 * description goes here
 *
 * @author kareem
 * @version %I%
 */

public class DefaultApplicationLanguageManager {
	public static DefaultLanguages getDefaultLanguage ( Context context ) {
		try {
			return DefaultLanguages.valueOf(context.getSharedPreferences(ContractValues.FILE_SETTINGS, Context.MODE_PRIVATE)
					  .getString(ContractValues.CALL_DeEFAULTLANGUAGE, String.valueOf(DefaultLanguages.Arabic)));
		} catch (IllegalArgumentException i) {
			return DefaultLanguages.Arabic;
		}
	}

	public static void setDefaultLanguage ( Context context, DefaultLanguages defaultLanguages ) {
		context.getSharedPreferences(ContractValues.FILE_SETTINGS, Context.MODE_PRIVATE)
				  .edit().putString(ContractValues.CALL_DeEFAULTLANGUAGE, String.valueOf(defaultLanguages)).apply();
	}
}
