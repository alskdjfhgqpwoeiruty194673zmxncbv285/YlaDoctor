package com.kareem.yladoctor.Models.Modules.ScheduleModules;

/**
 * Created by kareem on 7/8/2017 - YlaDoctor.
 * <br></br>
 * description goes here
 *
 * @author kareem
 * @version %I%
 */

public class ScheduleIdentifier {
	private String UID, start, end;

	public ScheduleIdentifier ( String UID, String start, String end ) {
		this.UID = UID;
		this.start = start;
		this.end = end;
	}

	public ScheduleIdentifier () {
	}

	public String getUID () {
		return UID;
	}

	public void setUID ( String UID ) {
		this.UID = UID;
	}

	public String getStart () {
		return start;
	}

	public void setStart ( String start ) {
		this.start = start;
	}

	public String getEnd () {
		return end;
	}

	public void setEnd ( String end ) {
		this.end = end;
	}
}
