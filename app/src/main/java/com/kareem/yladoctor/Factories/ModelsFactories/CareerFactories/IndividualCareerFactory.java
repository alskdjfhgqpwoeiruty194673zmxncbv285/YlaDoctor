package com.kareem.yladoctor.Factories.ModelsFactories.CareerFactories;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.kareem.yladoctor.Models.Contracts.FirebaseContracts;
import com.kareem.yladoctor.Models.Helpers.SetValueListener;
import com.kareem.yladoctor.Models.Interfaces.newValueListener;
import com.kareem.yladoctor.Models.Modules.Career.Individuals.IndividualCareer;

import java.util.HashMap;

/**
 * Created by kareem on 7/7/2017 - YlaDoctor.
 * <br></br>
 * description goes here
 *
 * @author kareem
 * @version %I%
 */

public class IndividualCareerFactory implements newValueListener {
	newValueListener listener;
	private IndividualCareer individualCareer;
	 String UID;

	public IndividualCareerFactory ( String UID, newValueListener listener, IndividualCareer individualCareer ) {
		this.listener = listener;
		this.individualCareer = individualCareer;
		this.UID = UID;
	}

	public boolean askUpdate ( String path, Object value ) {
		if (! path.equals(FirebaseContracts.PATH_TO_USERS_INDIVIDUALBUSINESSUID_CAREER_BUSINESSLOCATIONS)
				  && ! path.equals(FirebaseContracts.PATH_TO_USERS_INDIVIDUALBUSINESSUID_CAREER_BUSINESSMOBILENUMBER)
				  && ! path.equals(FirebaseContracts.PATH_TO_USERS_INDIVIDUALBUSINESSUID_CAREER_RATE)
				  && ! path.equals(FirebaseContracts.PATH_TO_USERS_INDIVIDUALBUSINESSUID_CAREER_VOTES)
				  && ! path.equals(FirebaseContracts.PATH_TO_USERS_INDIVIDUALBUSINESSUID_CAREER_NEEDSAPPROVAL))
			return false;
		if (path.equals(FirebaseContracts.PATH_TO_USERS_INDIVIDUALBUSINESSUID_CAREER_RATE)
				  || path.equals(FirebaseContracts.PATH_TO_USERS_INDIVIDUALBUSINESSUID_CAREER_VOTES)) {
			changeVariablesByTransactions(path, (Double) value);
			return true;
		}
		// TODO: 7/8/2017 add changing mobilenumber authentication with firebase and new link if first insert
		new SetValueListener(this).setNewValue(value, value, FirebaseContracts.PATH_TO_USERS + "/" + UID + "/" + FirebaseContracts.PATH_TO_USERS_INDIVIDUALBUSINESSUID_CAREER + "/" + path, path);
		return true;
	}

	private void changeVariablesByTransactions ( String path, final double value ) {
		FirebaseDatabase.getInstance().getReference().child(FirebaseContracts.PATH_TO_USERS + "/" + UID + "/" + FirebaseContracts.PATH_TO_USERS_INDIVIDUALBUSINESSUID_CAREER + "/" + path).runTransaction(new Transaction.Handler() {
			@Override
			public Transaction.Result doTransaction ( MutableData mutableData ) {
				if (mutableData != null) {
					@SuppressWarnings("ConstantConditions") double points = mutableData.getValue(Double.class);
					points += value;
					mutableData.setValue(points);
					Transaction.success(mutableData);
				} else
					Transaction.abort();
				return null;
			}

			@Override
			public void onComplete ( DatabaseError databaseError, boolean b, DataSnapshot dataSnapshot ) {
				if (databaseError == null)
					onSucceed(dataSnapshot.getValue(), FirebaseContracts.PATH_TO_USERS_INDIVIDUALUID_POINTS);
				else
					onFailure(dataSnapshot.getValue(), databaseError.toException(), FirebaseContracts.PATH_TO_USERS_INDIVIDUALUID_POINTS);
			}
		});
}

	@Override
	public void onSucceed ( Object value, String ID ) {
		boolean accessed = true;
		switch (ID) {
			case FirebaseContracts.PATH_TO_USERS_INDIVIDUALBUSINESSUID_CAREER_BUSINESSLOCATIONS:
				individualCareer.setBusinessLocations((HashMap<String, String>) value);
				break;
			case FirebaseContracts.PATH_TO_USERS_INDIVIDUALBUSINESSUID_CAREER_BUSINESSMOBILENUMBER:
				individualCareer.setBusinessMobileNumber((String) value);
				break;
			case FirebaseContracts.PATH_TO_USERS_INDIVIDUALBUSINESSUID_CAREER_RATE:
				individualCareer.setRate((Double) value);
				break;
			case FirebaseContracts.PATH_TO_USERS_INDIVIDUALBUSINESSUID_CAREER_VOTES:
				individualCareer.setVotes((Integer) value);
				break;
			case FirebaseContracts.PATH_TO_USERS_INDIVIDUALBUSINESSUID_CAREER_NEEDSAPPROVAL:
				individualCareer.setNeedsApproval((Boolean) value);
				break;
			default:
				accessed = false;
		}
		triggerNotification(accessed, value);
	}

	void triggerNotification ( boolean accessed, Object value ) {
		if (accessed) {
			listener.onSucceed(value, FirebaseContracts.PATH_TO_USERS_INDIVIDUALBUSINESSUID_CAREER_ITEM);
		}
	}

	@Override
	public void onFailure ( Object data, Throwable error, String ID ) {
		listener.onFailure(data, error, ID);
	}

}
