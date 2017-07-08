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
import com.kareem.yladoctor.Models.Modules.User.Businesses.Individuals.IndividualBusiness;

import java.util.HashMap;

/**
 * Created by kareem on 7/7/2017 - YlaDoctor.
 * <br></br>
 * description goes here
 *
 * @author kareem
 * @version %I%
 */

public class IndividualBusinessFactory extends UserFactory {
	private IndividualBusiness individualBusiness;

	public IndividualBusinessFactory ( IndividualBusiness user, newValueListener listener ) {
		super(user, listener);
		individualBusiness = user;
	}


	public boolean askUpdate ( String path, Object value ) {

		if (super.askUpdate(path, value))
			return true;
		if (! path.equals(FirebaseContracts.PATH_TO_USERS_INDIVIDUALBUSINESSUID_POINTS)
				  && ! path.equals(FirebaseContracts.PATH_TO_USERS_INDIVIDUALBUSINESSUID_MOBILENUMBER)
				  && ! path.equals(FirebaseContracts.PATH_TO_USERS_INDIVIDUALBUSINESSUID_NAMES)
				  && ! path.equals(FirebaseContracts.PATH_TO_USERS_INDIVIDUALBUSINESSUID_PROFILEPICTURE))
			return false;
		if (path.equals(FirebaseContracts.PATH_TO_USERS_INDIVIDUALBUSINESSUID_POINTS)) {
			changePoints((Long) value);
			return true;
		}
		if (path.equals(FirebaseContracts.PATH_TO_USERS_INDIVIDUALBUSINESSUID_NAMES)) {
			if (! (value instanceof HashMap))
				return false;
			setUserName((HashMap<String, String>) value);
			return true;
		}
		if (path.equals(FirebaseContracts.PATH_TO_USERS_INDIVIDUALBUSINESSUID_PROFILEPICTURE)) {
			if (! (value instanceof Picture))
				return false;
			setProfilePicture((Picture) value);
			return true;
		}
		new SetValueListener(this).setNewValue(value, value, FirebaseContracts.PATH_TO_USERS + "/" + individualBusiness.getUID() + "/" + path, path);
		return true;
	}

	@Override
	public void onSucceed ( Object value, String ID ) {
		super.onSucceed(value, ID);
		boolean accessed = true;
		switch (ID) {
			case FirebaseContracts.PATH_TO_USERS_INDIVIDUALBUSINESSUID_POINTS:
				individualBusiness.setPoints((Integer) value);
				break;
			case FirebaseContracts.PATH_TO_USERS_INDIVIDUALBUSINESSUID_MOBILENUMBER:
				individualBusiness.setMobileNumber((String) value);
				break;
			case FirebaseContracts.PATH_TO_USERS_INDIVIDUALBUSINESSUID_NAMES:
				individualBusiness.setNames((HashMap<String, String>) value);
				break;
			case FirebaseContracts.PATH_TO_USERS_INDIVIDUALBUSINESSUID_PROFILEPICTURE:
				individualBusiness.setProfilePicture((Picture) value);
				break;
			default:
				accessed = false;
		}
		triggerNotification(accessed, value, ID);
	}

	private void setProfilePicture ( Picture picture ) {
		new PictureFactory(this).askUpdate(FirebaseContracts.STORAGE_PATH_TO_IMAGES + "/" + FirebaseContracts.STORAGE_PATH_TO_IMAGES_PERSONAL + "/" + individualBusiness.getUID(), FirebaseContracts.PATH_TO_USERS + "/" + individualBusiness.getUID() + "/" + FirebaseContracts.PATH_TO_USERS_INDIVIDUALBUSINESSUID_PROFILEPICTURE, picture,FirebaseContracts.PATH_TO_USERS_INDIVIDUALBUSINESSUID_PROFILEPICTURE);
	}

	private void setUserName ( final HashMap<String, String> names ) {

		//noinspection ConstantConditions
		FirebaseAuth.getInstance().getCurrentUser().updateProfile(new UserProfileChangeRequest.Builder().setDisplayName(names.get(FirebaseContracts.PATH_TO_USERS_INDIVIDUALBUSINESSUID_NAMES_ENGLISH)).build()).addOnFailureListener(new OnFailureListener() {
			@Override
			public void onFailure ( @NonNull Exception e ) {
				IndividualBusinessFactory.this.onFailure(names, e, FirebaseContracts.PATH_TO_USERS_INDIVIDUALBUSINESSUID_NAMES);
			}
		}).addOnSuccessListener(new OnSuccessListener<Void>() {
			@Override
			public void onSuccess ( Void aVoid ) {
				new SetValueListener(IndividualBusinessFactory.this).setNewValue(names, names, FirebaseContracts.PATH_TO_USERS + "/" + individualBusiness.getUID() + "/" + FirebaseContracts.PATH_TO_USERS_INDIVIDUALBUSINESSUID_NAMES, FirebaseContracts.PATH_TO_USERS_INDIVIDUALBUSINESSUID_NAMES);
//				onSucceed(name, FirebaseContracts.PATH_TO_USERS_INDIVIDUALUID_NAME);
			}
		});
	}

	private void changePoints ( final Long value ) {
		FirebaseDatabase.getInstance().getReference().child(FirebaseContracts.PATH_TO_USERS + "/" + individualBusiness.getUID() + "/" + FirebaseContracts.PATH_TO_USERS_INDIVIDUALBUSINESSUID_POINTS).runTransaction(new Transaction.Handler() {
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
					onSucceed(dataSnapshot.getValue(), FirebaseContracts.PATH_TO_USERS_INDIVIDUALBUSINESSUID_POINTS);
				else
					onFailure(dataSnapshot.getValue(), databaseError.toException(),
							  FirebaseContracts.PATH_TO_USERS_INDIVIDUALBUSINESSUID_POINTS);
			}
		});
	}
}
