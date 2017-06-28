package com.kareem.yladoctor.Models.Modules.User;


import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.Exclude;
import com.kareem.yladoctor.Models.Enums.AccountType;

import java.util.HashMap;

/**
 * Created by kareem on 5/28/2017 - Akshef.
 * <br></br>
 * properties needed for any application user
 *
 * @author kareem
 * @version %I%
 */

public class User {
	//determine user's account type
	private AccountType accountType;
	//user's email address
	private String email;
	@Exclude
	//user's password related with his account
	private String password;
	//unique id for that user
	private String UID;

	/**
	 * constructor to create new user
	 *
	 * @param accountType account type for that user
	 * @param email       email address for that account
	 * @param password    password related to that account
	 */
	public User ( AccountType accountType, String email, String password, String UID ) {
		this.accountType = accountType;
		this.email = email;
		this.password = password;
		this.UID = UID;
	}

	public User ( AccountType accountType, String UID ) {
		this.UID = UID;
		this.accountType = accountType;
	}

	/**
	 * empty constructor for firebase
	 */
	public User () {
	}

	public User ( AccountType accountType, FirebaseUser firebaseUser ) {
		this.accountType = accountType;
		this.email = firebaseUser.getEmail();
		this.UID = firebaseUser.getUid();

	}

	public AccountType getAccountType () {
		return accountType;
	}

	public void setAccountType ( AccountType accountType ) {
		this.accountType = accountType;
	}

	public String getEmail () {
		return email;
	}

	public void setEmail ( String email ) {
		this.email = email;
	}

	public String getPassword () {
		return password;
	}

	public void setPassword ( String password ) {
		this.password = password;
	}

	public String getUID () {
		return UID;
	}

	public void setUID ( String UID ) {
		this.UID = UID;
	}
}
