package com.kareem.yladoctor.Views.Fragments.MainScreensFragments.DoctorMainScreenFragments;

import com.google.firebase.database.DataSnapshot;
import com.kareem.yladoctor.MainApplication;
import com.kareem.yladoctor.Models.Modules.ScheduleModules.ScheduleWeek;
import com.kareem.yladoctor.Models.Singletons.InfiniteExpandableListSingleton;
import com.kareem.yladoctor.R;
import com.kareem.yladoctor.Views.Fragments.BusinessSchedules.GeneralBusinessSchedule;
import com.kareem.yladoctor.Views.ViewHolders.ScheduleViewHolders.ScheduleDayDoctorViewHolder;

/**
 * Created by kareem on 7/3/2017 - YlaDoctor.
 * <br></br>
 * description goes here
 *
 * @author kareem
 * @version %I%
 */

public class DoctorMainViewTwoBusinessSchedule extends GeneralBusinessSchedule {
	@Override
	protected void initializeData () {
		ScheduleWeek scheduleWeek = new ScheduleWeek();
		InfiniteExpandableListSingleton.getInstance(null).createLevel(scheduleWeek.listOfAllDays(), ScheduleDayDoctorViewHolder.class, R.layout.doctor_schedule_day_item);
	}

	@Override
	public String getUID () {
		return ((MainApplication) this.getActivity().getApplication()).getUser().getUID();
	}

	@Override
	public void onDaysCalled ( Object data, DataSnapshot dataSnapshot ) {
		//is not called
//		ScheduleWeek scheduleWeek = dataSnapshot.getValue(ScheduleWeek.class);
//		if (scheduleWeek == null)
//			scheduleWeek = new ScheduleWeek();
//		InfiniteExpandableListSingleton.getInstance(null).createLevel(scheduleWeek.listOfAllDays(), ScheduleDayDoctorViewHolder.class, R.layout.doctor_schedule_day_item);
	}
}
