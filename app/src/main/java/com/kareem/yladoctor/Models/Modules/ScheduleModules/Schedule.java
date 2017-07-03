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
	private String date, uid, time, condition;
	private WeekDays day;


	public Schedule ( String time, WeekDays day ) {
		date = "";
		uid = "";
		this.time = time;
		condition = FirebaseContracts.PATH_TO_SCHEDULE_DOCTORUID_DAYNAME_SLOTTIME_CONDITION_NOVALUE;
		this.day = day;
	}

	public Schedule ( String date, String uid, String time, String condition, WeekDays day ) {
		this.date = date;
		this.uid = uid;
		this.time = time;
		this.condition = condition;
		this.day = day;
	}

	public Schedule () {
	}

	public String getDate () {
		return date;
	}

	public void setDate ( String date ) {
		this.date = date;
	}

	public String getUid () {
		return uid;
	}

	public void setUid ( String uid ) {
		this.uid = uid;
	}

	public String getTime () {
		return time;
	}

	public void setTime ( String time ) {
		this.time = time;
	}

	public String getCondition () {
		return condition;
	}

	public void setCondition ( String condition ) {
		this.condition = condition;
	}

	public WeekDays getDay () {
		return day;
	}

	public void setDay ( WeekDays day ) {
		this.day = day;
	}
}
