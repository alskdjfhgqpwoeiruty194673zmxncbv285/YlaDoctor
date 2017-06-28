/*
 * Copyright (c) 2016. kareem elsayed aly,no one has the authority to edit,delete,copy any part without my permission
 */

package com.kareem.yladoctor.Models.Modules.AppointmentHistories;

import com.google.firebase.database.Exclude;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kareem on 9/28/2016 - HealMe.
 * <br></br>
 * introduce reviews and all it's required variables
 *
 * @author kareem
 * @version %I%
 */

public class Review {
	private String Desc;
	private float Rate;

	public Review () {
	}

	public Review ( String desc, float rate ) {
		Desc = desc;
		Rate = rate;
	}

	public String getDesc () {
		return Desc;
	}

	public void setDesc ( String desc ) {
		Desc = desc;
	}

	public float getRate () {
		return Rate;
	}

	public void setRate ( float rate ) {
		Rate = rate;
	}

}
