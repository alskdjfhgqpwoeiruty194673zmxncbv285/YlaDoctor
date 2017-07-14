/*
 * Copyright (c) 2017. KAREEM ElSAYED ALY  ALL RIGHTS RESERVED.
 */

package com.kareem.yladoctor.Models.Modules.ScheduleModules;


import com.kareem.yladoctor.Models.Contracts.FirebaseContracts;
import com.kareem.yladoctor.Models.Enums.WeekDays;

/**
 * Created by kareem on 2/21/2017 - HealMe.
 * <br></br>
 * description goes here
 *
 * @author kareem
 * @version %I%
 */

public class Schedule {
	//UID is the reference to the patient reference
	//ID is the reference to the appointment history ID
	//time is the slot time= starting time
	//day is the day of the week,
	private String UID, ID, scheduleStartTime, scheduleEndTime;
	private String condition=FirebaseContracts.PATH_TO_SCHEDULE_DOCTORUID_DAYNAME_SLOTTIME_CONDITION_NOVALUE;
	private WeekDays day;


	public Schedule ( String time, WeekDays day ) {
		UID = "";
		condition = FirebaseContracts.PATH_TO_SCHEDULE_DOCTORUID_DAYNAME_SLOTTIME_CONDITION_NOVALUE;
		this.day = day;
	}

	public Schedule (String ID, String time, String condition, WeekDays day ) {
		this.ID = ID;
		this.condition = condition;
		this.day = day;
	}

	public Schedule ( String ID, String scheduleStartTime, String scheduleEndTime ) {
		this.ID = ID;
		this.scheduleStartTime = scheduleStartTime;
		this.scheduleEndTime = scheduleEndTime;
		this.condition = FirebaseContracts.PATH_TO_SCHEDULE_DOCTORUID_DAYNAME_SLOTTIME_CONDITION_NOVALUE;
	}

	public Schedule () {
	}

	public String getUID () {
		return UID;
	}

	public void setUID ( String UID ) {
		this.UID = UID;
	}

	public String getID () {
		return ID;
	}

	public void setID ( String ID ) {
		this.ID = ID;
	}

	public String getCondition () {
		return condition;
	}

	public void setCondition ( String condition ) {
		this.condition = condition;
	}

	public String getScheduleStartTime () {
		return scheduleStartTime;
	}

	public void setScheduleStartTime ( String scheduleStartTime ) {
		this.scheduleStartTime = scheduleStartTime;
	}

	public String getScheduleEndTime () {
		return scheduleEndTime;
	}

	public void setScheduleEndTime ( String scheduleEndTime ) {
		this.scheduleEndTime = scheduleEndTime;
	}

	public WeekDays getDay () {
		return day;
	}

	public void setDay ( WeekDays day ) {
		this.day = day;
	}
}
