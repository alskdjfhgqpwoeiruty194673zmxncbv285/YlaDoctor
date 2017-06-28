/*
 * Copyright (c) $today.year.kareem elsayed aly,no one has the authority to edit,delete,copy any part without my permission
 */

package com.kareem.yladoctor.Models.Helpers;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.kareem.yladoctor.Models.Interfaces.newValueListener;

import java.util.HashMap;

/**
 * Created by kareem on 9/10/2016.
 * <br></br>
 * it's responsible for overwriting new data to a specific node
 *
 * @author kareem
 * @version %I%, %G%
 * @since 1.1
 */
public class SetValueListener {
	private newValueListener listener;

	/**
	 * construct and initialize listener to retrieve and send data with it
	 *
	 * @param listener is the instance of the class that implements the newValueListener interface
	 * @see newValueListener
	 * @since 1.1
	 */
	public SetValueListener ( newValueListener listener ) {
		this.listener = listener;
	}

	/**
	 * return reference from the FireBase file
	 * <br></br>
	 * =<code>FireBaseDatabase.getInstance().getReference()</code>
	 *
	 * @return #DatabaseReference
	 * @see DatabaseReference
	 * @since 1.1
	 */
	public static DatabaseReference getReference () {
		return FirebaseDatabase.getInstance().getReference();
	}

	/**
	 * responsible for overwriting new data in specific node
	 *
	 * @param generalData is the data to be passed through the listener-received to --> from SingleValueEventListener method,doesn't get affected-
	 * @param data        #Object is to be set in the given Node
	 * @param path        path to the node which new data will replace old ones-even if null-
	 * @param ID          is the associated ID with this operation-every process get identified by it's assigned ID,passed to RecyclerAdapterListener method-
	 * @see FirebaseDatabase
	 * @since 1.1
	 */
	public void setNewValue ( final Object generalData, final Object data, String path, final String ID ) {
		setNewValue(generalData, data, getReference().child(path), ID);
	}

	/**
	 * responsible for overwriting new data in specific node
	 *
	 * @param generalData is the data to be passed through the listener-received to --> from SingleValueEventListener method,doesn't get affected-
	 * @param data        #Object is to be set in the given Node
	 * @param path        #DatabaseReference to the node which new data will replace old ones-even if null-
	 * @param ID          is the associated ID with this operation-every process get identified by it's assigned ID,passed to RecyclerAdapterListener method-
	 * @see FirebaseDatabase
	 * @since 1.1
	 */
	public void setNewValue ( final Object generalData, final Object data, DatabaseReference path, final String ID ) {
		path.setValue(data).addOnCompleteListener(new OnCompleteListener<Void>() {
			@Override
			public void onComplete ( @NonNull Task<Void> task ) {
				if (task.isSuccessful())
					listener.onSucceed(generalData, ID);
				else
					listener.onFailure(generalData, task.getException(), ID);
			}
		}).addOnFailureListener(new OnFailureListener() {
			@Override
			public void onFailure ( @NonNull Exception e ) {
				listener.onFailure(generalData, e, ID);
			}
		});
	}

	public void UpdateChildeValue ( final Object generalData, final HashMap<String, Object> data, String path, final String ID ) {
		getReference().child(path).updateChildren(data).addOnCompleteListener(new OnCompleteListener<Void>() {
			@Override
			public void onComplete ( @NonNull Task<Void> task ) {
				if (task.isSuccessful())
					listener.onSucceed(generalData, ID);
				else
					listener.onFailure(generalData, task.getException(), ID);
			}
		}).addOnFailureListener(new OnFailureListener() {
			@Override
			public void onFailure ( @NonNull Exception e ) {
				listener.onFailure(generalData, e, ID);
			}
		});
	}

	/**
	 * is to create an automatic key for a certain node for fireBaseDatabase
	 *
	 * @return created key
	 * @since 1.2
	 */
	public static String createKey () {
		return FirebaseDatabase.getInstance().getReference().push().getKey();
	}
}
