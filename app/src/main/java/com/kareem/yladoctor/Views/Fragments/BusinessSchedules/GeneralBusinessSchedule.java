package com.kareem.yladoctor.Views.Fragments.BusinessSchedules;
/*
 * Copyright (c) $today.year.kareem elsayed aly,no one has the authority to edit,delete,copy any part without my permission
 */

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.kareem.infiniteexpandablelist.InfiniteExpandableListInterface;
import com.kareem.yladoctor.Factories.DatabasePathFactory;
import com.kareem.yladoctor.Models.Contracts.FirebaseContracts;
import com.kareem.yladoctor.Models.Helpers.FirebaseListener;
import com.kareem.yladoctor.Models.Interfaces.FirebaseListeners;
import com.kareem.yladoctor.Models.Modules.ScheduleModules.ScheduleWeek;
import com.kareem.yladoctor.Models.Singletons.InfiniteExpandableListSingleton;
import com.kareem.yladoctor.R;
import com.kareem.yladoctor.Views.ViewHolders.ScheduleViewHolders.ScheduleDayTimeViewHolder;

import java.util.ArrayList;

/**
 * Created by kareem on 9/19/2016 - YlaDoctor.
 * <br></br>
 * description goes here
 *
 * @author kareem
 * @version %I%
 */

public abstract class GeneralBusinessSchedule extends Fragment implements FirebaseListeners {
	//	private ScheduleRegulator scheduleRegulator;
	LayoutInflater inflater;
	String UID;
	ArrayList<View> days;
	ArrayList<View> slots;
	ArrayList<View> inside;

	@Override
	public View onCreateView ( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {
		this.inflater = inflater;
		return inflater.inflate(R.layout.general_business_schedule, container, false);
	}

	@Override
	public void onViewCreated ( View view, Bundle savedInstanceState ) {
		super.onViewCreated(view, savedInstanceState);
		UID = getUID();
		new FirebaseListener(this).initSingleValueEventListener(null, DatabasePathFactory.pathTo_Business_schedule(UID), FirebaseContracts.PATH_TO_SCHEDULEIDENTIFIER);
		days = new ArrayList<>();
		slots = new ArrayList<>();
		inside = new ArrayList<>();
		InfiniteExpandableListSingleton.getInstance(new InfiniteExpandableListInterface() {
			@Override
			public Activity getActivity () {
				return GeneralBusinessSchedule.this.getActivity();
			}

			@Override
			public int getLayoutContainer () {
				return R.id.generalBusinessSchedule_linearLayout_container;
			}
		});

	}

	public abstract String getUID ();

	@Override
	public void ValueEventListener ( Object data, DataSnapshot dataSnapshot, String ID ) {

	}

	@Override
	public void SingleValueEventListener ( Object data, DataSnapshot dataSnapshot, String ID ) {
		switch (ID) {
			case FirebaseContracts.PATH_TO_SCHEDULEIDENTIFIER:
				ScheduleWeek scheduleWeek = dataSnapshot.getValue(ScheduleWeek.class);
				if (scheduleWeek != null)
					InfiniteExpandableListSingleton.getInstance(null).createLevel(scheduleWeek.listOfDays(), ScheduleDayTimeViewHolder.class, R.layout.general_schedule_item);
				else {
					GeneralBusinessSchedule.this.getView().findViewById(R.id.generalBusinessSchedule_textView_noAssignedScheduleWarning).setVisibility(View.VISIBLE);
				}
				break;
		}
	}

	@Override
	public void onFailure ( Object data, Throwable error, String ID ) {

	}
}
