/*
 * Copyright (c) 2017. KAREEM ElSAYED ALY  ALL RIGHTS RESERVED.
 */

package com.kareem.yladoctor.Models.Modules.AppointmentHistories;

/**
 * Created by kareem on 2/14/2017 - HealMe.
 * <br></br>
 * description goes here
 *
 * @author kareem
 * @version %I%
 */

public class AppointmentHistory {
	private String duid, puid, date, field, time;
	private Review PReview;
	private Review DReview;

	public AppointmentHistory ( String duid, String puid, String date, String field, String time ) {
		this.duid = duid;
		this.puid = puid;
		this.date = date;
		this.field = field;
		this.time = time;
	}

	public AppointmentHistory () {
	}

	public Review getDReview () {
		return DReview;
	}

	public void setDReview ( Review DReview ) {
		this.DReview = DReview;
	}

	public Review getPReview () {
		return PReview;
	}

	public void setPReview ( Review PReview ) {
		this.PReview = PReview;
	}

	public String getTime () {
		return time;
	}

	public void setTime ( String time ) {
		this.time = time;
	}

	public String getField () {
		return field;
	}

	public void setField ( String field ) {
		this.field = field;
	}

	public String getDate () {
		return date;
	}

	public void setDate ( String date ) {
		this.date = date;
	}

	public String getPuid () {
		return puid;
	}

	public void setPuid ( String puid ) {
		this.puid = puid;
	}

	public String getDuid () {
		return duid;
	}

	public void setDuid ( String duid ) {
		this.duid = duid;
	}
}
