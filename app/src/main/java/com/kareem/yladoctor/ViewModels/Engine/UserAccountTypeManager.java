package com.kareem.yladoctor.ViewModels.Engine;

import android.content.Context;

import com.kareem.yladoctor.Models.Contracts.ContractValues;
import com.kareem.yladoctor.Models.Enums.AccountType;
import com.kareem.yladoctor.Models.Modules.User.Businesses.Individuals.Doctor;
import com.kareem.yladoctor.Models.Modules.User.Businesses.Individuals.Patient;
import com.kareem.yladoctor.Models.Modules.User.User;

/**
 * Created by kareem on 6/14/2017 - YlaDoctor.
 * <br></br>
 * description goes here
 *
 * @author kareem
 * @version %I%
 */

public class UserAccountTypeManager {
	public static AccountType getAccountType ( Context context ) {
		try {
			return AccountType.valueOf(context.getSharedPreferences(ContractValues.FILE_SETTINGS, Context.MODE_PRIVATE)
					  .getString(ContractValues.CALL_ACCOUNTTYPE, String.valueOf(AccountType.UNKNOWN)));
		} catch (IllegalArgumentException i) {
			return AccountType.UNKNOWN;
		}
	}

	public static void setAccountType ( Context context, AccountType accountType ) {
		context.getSharedPreferences(ContractValues.FILE_SETTINGS, Context.MODE_PRIVATE)
				  .edit().putString(ContractValues.CALL_ACCOUNTTYPE, String.valueOf(accountType)).apply();
	}

	public static User createUserAccordingToUserAccountType ( String UID, AccountType accountType ) {
		switch (accountType) {
			case PATIENT:
				return new Patient(UID);
			case DOCTOR:
				return new Doctor(UID);
			default:
				return new User(accountType,"","",UID);
		}
	}
}
