/*
 * Copyright (c) 2017. KAREEM ElSAYED ALY  ALL RIGHTS RESERVED.
 */

package com.kareem.yladoctor.Models.Modules.Fields;

import java.util.HashMap;

/**
 * Created by kareem on 2/25/2017 - HealMe.
 * <br></br>
 * describes general field
 *
 * @author kareem
 * @version %I%
 */

public class FieldIdentifier {
	//unique ID for that field
	private String UID;
	private HashMap<String, String> descriptions;
	private HashMap<String, String> names;

	/**
	 * empty constructor for firebase
	 */
	public FieldIdentifier () {
	}

	/**
	 * constructor to create new field
	 *
	 * @param UID unique id for that field
	 */
	public FieldIdentifier ( String UID, HashMap<String, String> descriptions, HashMap<String, String> names ) {
		this.UID = UID;
		this.descriptions = descriptions;
		this.names = names;
	}

	public String getUID () {
		return UID;
	}

	public void setUID ( String UID ) {
		this.UID = UID;
	}

	public HashMap<String, String> getDescriptions () {
		return descriptions;
	}

	public void setDescriptions ( HashMap<String, String> descriptions ) {
		this.descriptions = descriptions;
	}

	public HashMap<String, String> getNames () {
		return names;
	}

	public void setNames ( HashMap<String, String> names ) {
		this.names = names;
	}
}
