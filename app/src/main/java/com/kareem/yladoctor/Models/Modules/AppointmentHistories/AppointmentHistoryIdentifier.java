package com.kareem.yladoctor.Models.Modules.AppointmentHistories;

/**
 * Created by kareem on 6/28/2017 - YlaDoctor.
 * <br></br>
 * description goes here
 *
 * @author kareem
 * @version %I%
 */

public class AppointmentHistoryIdentifier {
	private String ID, UID;

	public AppointmentHistoryIdentifier ( String ID, String UID ) {
		this.ID = ID;
		this.UID = UID;
	}

	public AppointmentHistoryIdentifier () {
	}

	public String getID () {
		return ID;
	}

	public void setID ( String ID ) {
		this.ID = ID;
	}

	public String getUID () {
		return UID;
	}

	public void setUID ( String UID ) {
		this.UID = UID;
	}
}
