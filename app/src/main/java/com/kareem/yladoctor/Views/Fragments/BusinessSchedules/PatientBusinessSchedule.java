package com.kareem.yladoctor.Views.Fragments.BusinessSchedules;

import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.kareem.yladoctor.Factories.DatabasePathFactory;
import com.kareem.yladoctor.MainApplication;
import com.kareem.yladoctor.Models.Contracts.FirebaseContracts;
import com.kareem.yladoctor.Models.Helpers.FirebaseListener;
import com.kareem.yladoctor.Models.Modules.ScheduleModules.ScheduleWeek;
import com.kareem.yladoctor.Models.Singletons.InfiniteExpandableListSingleton;
import com.kareem.yladoctor.R;
import com.kareem.yladoctor.Views.ViewHolders.ScheduleViewHolders.ScheduleDayViewHolder;

/**
 * Created by kareem on 7/3/2017 - YlaDoctor.
 * <br></br>
 * description goes here
 *
 * @author kareem
 * @version %I%
 */

public class PatientBusinessSchedule extends GeneralBusinessSchedule {
	@Override
	protected void initializeData () {
		new FirebaseListener(this).initSingleValueEventListener(null, DatabasePathFactory.pathTo_Business_schedule(UID), FirebaseContracts.PATH_TO_SCHEDULEIDENTIFIER);
	}

	@Override
	public String getUID () {
		return ((MainApplication) this.getActivity().getApplication()).getOtherUser().getUID();
	}

	@Override
	public void onDaysCalled (Object data, DataSnapshot dataSnapshot) {
		ScheduleWeek scheduleWeek = dataSnapshot.getValue(ScheduleWeek.class);
		if (scheduleWeek != null)
			InfiniteExpandableListSingleton.getInstance(null).createLevel(scheduleWeek.listOfDays(), ScheduleDayViewHolder.class, R.layout.general_schedule_item);
		else {
			PatientBusinessSchedule.super.getView().findViewById(R.id.generalBusinessSchedule_textView_noAssignedScheduleWarning).setVisibility(View.VISIBLE);
		}
	}
}
