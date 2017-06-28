package com.kareem.yladoctor.Factories;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kareem.yladoctor.Models.Contracts.FirebaseContracts;

/**
 * Created by kareem on 6/28/2017 - YlaDoctor.
 * <br></br>
 * description goes here
 *
 * @author kareem
 * @version %I%
 */

public class DatabasePathFactory {
	private static DatabaseReference getReference () {
		return FirebaseDatabase.getInstance().getReference();
	}

	public static DatabaseReference pathTo_User_UserUID ( String UID ) {
		return getReference().child(FirebaseContracts.PATH_TO_USERS).child(UID);
	}

	public static DatabaseReference pathTo_User_UserUID_AccountType ( String UID ) {
		return pathTo_User_UserUID(UID).child(FirebaseContracts.PATH_TO_USERS_USERUID_ACCOUNTTYPE);

	}
}
