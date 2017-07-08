package com.kareem.yladoctor.Factories.ModelsFactories.Users;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.kareem.yladoctor.Models.Contracts.FirebaseContracts;
import com.kareem.yladoctor.Models.Enums.AccountType;
import com.kareem.yladoctor.Models.Helpers.SetValueListener;
import com.kareem.yladoctor.Models.Interfaces.ChangeCenterObserver;
import com.kareem.yladoctor.Models.Interfaces.ChangeCenterSkeleton;
import com.kareem.yladoctor.Models.Interfaces.newValueListener;
import com.kareem.yladoctor.Models.Modules.User.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kareem on 7/7/2017 - YlaDoctor.
 * <br></br>
 * description goes here
 *
 * @author kareem
 * @version %I%
 */

public class UserFactory implements newValueListener, ChangeCenterSkeleton {
	private User user;
	private newValueListener listener;
	private static ArrayList<ChangeCenterObserver> changeListeners;

	public UserFactory ( User user, newValueListener listener ) {
		this.user = user;
		this.listener = listener;
	}

	public boolean askUpdate ( String path, Object value ) {

		if (! path.equals(FirebaseContracts.PATH_TO_USERS_USERUID_EMAIL)
				  && ! path.equals(FirebaseContracts.PATH_TO_USERS_USERUID_ACCOUNTTYPE)
				  && ! path.equals(FirebaseContracts.PATH_TO_USERS_USERUID_PASSWORD)
				  && ! path.equals(FirebaseContracts.PATH_TO_USERS)
				  && ! path.equals(FirebaseContracts.PATH_TO_USERS_USERUID_UID))
			return false;
		if (path.equals(FirebaseContracts.PATH_TO_USERS_USERUID_PASSWORD)) {
			setDataPassword((String) value, listener);
			return true;
		}
		if (path.equals(FirebaseContracts.PATH_TO_USERS)) {
			setUser((User) value, listener);
			return true;
		}
		new SetValueListener(this).setNewValue(value, value, FirebaseContracts.PATH_TO_USERS + "/" + user.getUID() + "/" + path, path);
		return true;
	}

	private void setUser ( User user, final newValueListener listener ) {
		Map<String, Object> map = new HashMap<>();
		map.put("listener", listener);
		new SetValueListener(this).setNewValue(map, user, FirebaseContracts.PATH_TO_USERS + "/" + user.getUID(), FirebaseContracts.PATH_TO_USERS);
	}


	public static void SignOut () {
		FirebaseAuth.getInstance().signOut();
	}

	private void setDataPassword ( final String password, final newValueListener listener ) {
		//noinspection ConstantConditions
		FirebaseAuth.getInstance().getCurrentUser().updatePassword(password).addOnSuccessListener(new OnSuccessListener<Void>() {
			@Override
			public void onSuccess ( Void aVoid ) {
				user.setPassword(password);
				listener.onSucceed(null, FirebaseContracts.PATH_TO_USERS_USERUID_PASSWORD);
			}
		}).addOnFailureListener(new OnFailureListener() {
			@Override
			public void onFailure ( @NonNull Exception e ) {
				listener.onFailure(null, e, FirebaseContracts.PATH_TO_USERS_USERUID_PASSWORD);
			}
		});
	}

	@Override
	public void onSucceed ( Object value, String ID ) {
		boolean accessed = true;
		switch (ID) {
			case FirebaseContracts.PATH_TO_USERS_USERUID_EMAIL:
				user.setEmail((String) value);
				break;
			case FirebaseContracts.PATH_TO_USERS_USERUID_UID:
				user.setUID((String) value);
				break;
			case FirebaseContracts.PATH_TO_USERS:
				break;
			case FirebaseContracts.PATH_TO_USERS_USERUID_ACCOUNTTYPE:
				user.setAccountType((AccountType) value);
				break;
			case FirebaseContracts.PATH_TO_USERS_USERUID_PASSWORD:
				user.setPassword((String) value);
				break;
			default:
				accessed = false;
		}
		triggerNotification(accessed, value, ID);
	}

	void triggerNotification ( boolean accessed, Object value, String ID ) {
		if (accessed) {
			listener.onSucceed(value, ID);
			notifyObservers(value, ID);
		}
	}

	@Override
	public void onFailure ( Object data, Throwable error, String ID ) {
		listener.onFailure(data, error, ID);
	}

	@Override
	public void register ( ChangeCenterObserver observer ) {
		if (changeListeners == null)
			changeListeners = new ArrayList<>();
		changeListeners.add(observer);
	}

	@Override
	public void unregister ( ChangeCenterObserver observer ) {
		if (changeListeners == null)
			return;
		changeListeners.remove(observer);
	}

	@Override
	public void notifyObservers ( Object data, String ID ) {
		if (changeListeners != null) {
			for (ChangeCenterObserver observer :
					  changeListeners) {
				observer.update(data, ID);
			}
		}
	}
}
