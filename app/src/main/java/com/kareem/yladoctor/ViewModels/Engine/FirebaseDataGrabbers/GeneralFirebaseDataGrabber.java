package com.kareem.yladoctor.ViewModels.Engine.FirebaseDataGrabbers;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.kareem.yladoctor.Models.Helpers.FirebaseListener;
import com.kareem.yladoctor.Models.Interfaces.FirebaseListeners;

/**
 * Created by kareem on 6/12/2017 - YlaDoctor.
 * <br></br>
 * description goes here
 *
 * @author kareem
 * @version %I%
 */

public abstract class GeneralFirebaseDataGrabber implements FirebaseListeners {

	public void getValueEventListenerToNode(Object data){
		new FirebaseListener(this).initValueEventListener(data,pathToDataOnFirebase(),pathToDataOnFirebase());
	}
	public void getSingleValueListenerToNode(Object data){
		new FirebaseListener(this).initSingleValueEventListener(data,pathToDataOnFirebase(),pathToDataOnFirebase());
	}

	public abstract String pathToDataOnFirebase();
	@Override
	public abstract void ValueEventListener ( Object data, DataSnapshot dataSnapshot, String ID );

	@Override
	public abstract void SingleValueEventListener ( Object data, DataSnapshot dataSnapshot, String ID );

	@Override
	public abstract void onFailure ( Object data, Throwable error, String ID );
	public FirebaseAuth getFirebaseAuth(){
		return FirebaseAuth.getInstance();
	}

	public FirebaseUser getFirebaseUser () {
		return getFirebaseAuth().getCurrentUser();
	}
}
