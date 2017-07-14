package com.kareem.yladoctor.Views.Dialogs;

import android.app.Dialog;
import android.content.Context;
import android.graphics.RectF;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;

import com.alamkanak.weekview.MonthLoader;
import com.alamkanak.weekview.WeekView;
import com.alamkanak.weekview.WeekViewEvent;
import com.kareem.yladoctor.Factories.DatabasePathFactory;
import com.kareem.yladoctor.Factories.ScheduleFactory;
import com.kareem.yladoctor.MainApplication;
import com.kareem.yladoctor.Models.Contracts.FirebaseContracts;
import com.kareem.yladoctor.Models.Enums.WeekDays;
import com.kareem.yladoctor.Models.Helpers.RecyclerAdapter;
import com.kareem.yladoctor.Models.Interfaces.RecyclerAdapterListener;
import com.kareem.yladoctor.Models.Modules.ScheduleModules.ScheduleIdentifier;
import com.kareem.yladoctor.R;
import com.kareem.yladoctor.Views.ViewHolders.ScheduleViewHolders.EditScheduleItem;
import com.sdsmdg.tastytoast.TastyToast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by kareem on 7/8/2017 - YlaDoctor.
 * <br></br>
 * description goes here
 *
 * @author kareem
 * @version %I%
 */

public class EditScheduleDialog extends Dialog implements RecyclerAdapterListener {
	private String UID;
	private int focusedDay = 0;
	private final WeekDays[] weekDays = new WeekDays[]{ WeekDays.Saturday, WeekDays.Sunday, WeekDays.Monday, WeekDays.Tuesday, WeekDays.Wednesday, WeekDays.Thursday, WeekDays.Friday };


	@OnClick(R.id.doctorEditSchedule_imageButton_previousDay)
	public void onPreviousDayClicked () {
		focusedDay--;
		if (focusedDay < 0)
			focusedDay = 6;
//		initializeDay(weekDays[focusedDay]);
	}

	@OnClick(R.id.doctorEditSchedule_imageButton_nextDay)
	public void onNextDayClicked () {
		focusedDay++;
		if (focusedDay > 6)
			focusedDay = 0;
//		initializeDay(weekDays[focusedDay]);
	}

	@OnClick(R.id.doctorEditSchedule_imageView_addSchedule)
	public void onAddNewScheduleClicked () {
//		new ScheduleFactory(null, getMyContext()).createNewSchedule(UID, weekDays[focusedDay]);
	}

	@BindView(R.id.doctorEditSchedule_switch_activateDay)
	Switch activateDay;

	@BindView(R.id.doctorEditSchedule_textView_weekDayName)
	TextView dayName;

	@BindView(R.id.doctorEditSchedule_button_fromTime)
	Button fromTime;
	@BindView(R.id.doctorEditSchedule_button_toTime)
	Button toTime;
	@BindView(R.id.doctorEditSchedule_recyclerView_listOfActiveSchedules)
	RecyclerView recyclerView;

	public EditScheduleDialog ( @NonNull Context context ) {
		super(context);
//		this.setContentView(R.layout.doctor_edit_schedule);
		View v = View.inflate(context, R.layout.doctor_edit_schedule, null);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(v);
		ButterKnife.bind(this, v);
		UID = ((MainApplication) getContext().getApplicationContext()).getUser().getUID();
//		initializeDay(weekDays[focusedDay]);

	}

//	private void initializeDay ( WeekDays weekDay ) {
//		dayName.setText(weekDay.toString());
//		new RecyclerAdapter(this).initRecyclerAdapterListener(null, recyclerView, ScheduleIdentifier.class, EditScheduleItem.class, R.layout.doctor_edit_schedule_item, DatabasePathFactory.pathTo_ScheduleIdentifier_userUID_weekDay(UID, weekDay), FirebaseContracts.PATH_TO_SCHEDULEIDENTIFIER);
//	}

	@Override
	public void recyclerAdapterListener ( Object data, RecyclerView.ViewHolder viewHolder, Object holder, int position, String ID ) {
		activateDay.setChecked(true);
		if (ID.equals(FirebaseContracts.PATH_TO_SCHEDULEIDENTIFIER)) {
			EditScheduleItem item = (EditScheduleItem) viewHolder;
			ScheduleIdentifier scheduleIdentifier = (ScheduleIdentifier) holder;
			item.initializeGUI(UID, weekDays[focusedDay], scheduleIdentifier, getMyContext());
		}
	}

	@Override
	public Context getMyContext () {
		return this.getContext();
	}
}
