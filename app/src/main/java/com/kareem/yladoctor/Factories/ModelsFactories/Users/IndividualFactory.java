package com.kareem.yladoctor.Factories.ModelsFactories.Users;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.kareem.yladoctor.Factories.ModelsFactories.AttachmentFactories.PictureFactory;
import com.kareem.yladoctor.Models.Contracts.FirebaseContracts;
import com.kareem.yladoctor.Models.Helpers.SetValueListener;
import com.kareem.yladoctor.Models.Interfaces.newValueListener;
import com.kareem.yladoctor.Models.Modules.Attachments.Picture;
import com.kareem.yladoctor.Models.Modules.User.Individual;

/**
 * Created by kareem on 7/7/2017 - YlaDoctor.
 * <br></br>
 * description goes here
 *
 * @author kareem
 * @version %I%
 */

public class IndividualFactory extends UserFactory {
	private Individual individual;

	public IndividualFactory ( Individual user, newValueListener listener ) {
		super(user, listener);
		individual = user;
	}

	public boolean askUpdate ( String path, Object value ) {

		if (super.askUpdate(path, value))
			return true;
		if (! path.equals(FirebaseContracts.PATH_TO_USERS_INDIVIDUALUID_POINTS)
				  && ! path.equals(FirebaseContracts.PATH_TO_USERS_INDIVIDUALUID_MOBILENUMBER)
				  && ! path.equals(FirebaseContracts.PATH_TO_USERS_INDIVIDUALUID_NAME)
				  && ! path.equals(FirebaseContracts.PATH_TO_USERS_INDIVIDUALUID_PROFILEPICTURE))
			return false;
		if (path.equals(FirebaseContracts.PATH_TO_USERS_INDIVIDUALUID_POINTS)) {
			changePoints((Long) value);
			return true;
		}
		if (path.equals(FirebaseContracts.PATH_TO_USERS_INDIVIDUALUID_NAME)) {
			setUserName((String) value);
			return true;
		}
		if (path.equals(FirebaseContracts.PATH_TO_USERS_INDIVIDUALUID_PROFILEPICTURE)) {
			if (! (value instanceof Picture))
				return false;
			setProfilePicture((Picture) value);
			return true;
		}
		// TODO: 7/8/2017 add changing mobilenumber authentication with firebase and new link if first insert
		new SetValueListener(this).setNewValue(value, value, FirebaseContracts.PATH_TO_USERS + "/" + individual.getUID() + "/" + path, path);
		return true;
	}

	@Override
	public void onSucceed ( Object value, String ID ) {
		super.onSucceed(value, ID);
		boolean accessed = true;
		switch (ID) {
			case FirebaseContracts.PATH_TO_USERS_INDIVIDUALUID_POINTS:
				individual.setPoints((Integer) value);
				break;
			case FirebaseContracts.PATH_TO_USERS_INDIVIDUALUID_MOBILENUMBER:
				individual.setMobileNumber((String) value);
				break;
			case FirebaseContracts.PATH_TO_USERS_INDIVIDUALUID_NAME:
				individual.setName((String) value);
				break;
			case FirebaseContracts.PATH_TO_USERS_INDIVIDUALUID_PROFILEPICTURE:
				individual.setProfilePicture((Picture) value);
				break;
			default:
				accessed = false;
		}
		triggerNotification(accessed, value, ID);
	}

	private void setProfilePicture ( Picture picture ) {
		new PictureFactory(this).askUpdate(FirebaseContracts.STORAGE_PATH_TO_IMAGES + "/" + FirebaseContracts.STORAGE_PATH_TO_IMAGES_PERSONAL + "/" + individual.getUID(), FirebaseContracts.PATH_TO_USERS + "/" + individual.getUID() + "/" + FirebaseContracts.PATH_TO_USERS_INDIVIDUALUID_PROFILEPICTURE, picture,FirebaseContracts.PATH_TO_USERS_INDIVIDUALUID_PROFILEPICTURE);
	}

	private void setUserName ( final String name ) {

		//noinspection ConstantConditions
		FirebaseAuth.getInstance().getCurrentUser().updateProfile(new UserProfileChangeRequest.Builder().setDisplayName(name).build()).addOnFailureListener(new OnFailureListener() {
			@Override
			public void onFailure ( @NonNull Exception e ) {
				IndividualFactory.this.onFailure(name, e, FirebaseContracts.PATH_TO_USERS_INDIVIDUALUID_NAME);
			}
		}).addOnSuccessListener(new OnSuccessListener<Void>() {
			@Override
			public void onSuccess ( Void aVoid ) {
				new SetValueListener(IndividualFactory.this).setNewValue(name, name, FirebaseContracts.PATH_TO_USERS + "/" + individual.getUID() + "/" + FirebaseContracts.PATH_TO_USERS_INDIVIDUALUID_NAME, FirebaseContracts.PATH_TO_USERS_INDIVIDUALUID_NAME);
//				onSucceed(name, FirebaseContracts.PATH_TO_USERS_INDIVIDUALUID_NAME);
			}
		});
	}

	private void changePoints ( final Long value ) {
		FirebaseDatabase.getInstance().getReference().child(FirebaseContracts.PATH_TO_USERS + "/" + individual.getUID() + "/" + FirebaseContracts.PATH_TO_USERS_INDIVIDUALUID_POINTS).runTransaction(new Transaction.Handler() {
			@Override
			public Transaction.Result doTransaction ( MutableData mutableData ) {
				if (mutableData != null) {
					@SuppressWarnings("ConstantConditions") long points = mutableData.getValue(Long.class);
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
}
