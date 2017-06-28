package com.kareem.yladoctor;

import android.support.multidex.MultiDexApplication;

import com.kareem.yladoctor.Models.Modules.User.User;

/**
 * Created by kareem on 5/28/2017 - Akshef.
 * <br></br>
 * Main application for the app
 * keeps references to main data used in the whole app
 *
 * @author kareem
 * @version %I%
 */

public class MainApplication extends MultiDexApplication{
	private User user;
	private User otherUser;
	private Object unkownObject;

	@Override
	public void onCreate() {
		super.onCreate();
//		FirebaseDatabase.getInstance().setLogLevel(Logger.Level.DEBUG);
//        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
	}

	public User getUser(){
		return user;

	}

	public void setUser(User user) {
		this.user = user;
	}

	public User getOtherUser() {
//        Assert.assertNotNull("otherUser is null value \r\n not defined yet",otherUser);
		return otherUser;
	}

	public void setOtherUser(User otherUser) {
		this.otherUser = otherUser;
	}

	public Object getUnkownObject () {
		return unkownObject;
	}

	public void setUnkownObject ( Object unkownObject ) {
		this.unkownObject = unkownObject;
	}
}
