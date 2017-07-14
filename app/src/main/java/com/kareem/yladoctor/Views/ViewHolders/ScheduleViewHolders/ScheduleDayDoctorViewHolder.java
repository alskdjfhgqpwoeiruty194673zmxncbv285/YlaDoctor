package com.kareem.yladoctor.Views.ViewHolders.ScheduleViewHolders;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.kareem.infiniteexpandablelist.InfiniteExpandableListViewHolder;
import com.kareem.yladoctor.Factories.DatabasePathFactory;
import com.kareem.yladoctor.Factories.ScheduleFactory;
import com.kareem.yladoctor.Models.Contracts.ContractValues;
import com.kareem.yladoctor.Models.Contracts.FirebaseContracts;
import com.kareem.yladoctor.Models.Enums.AccountType;
import com.kareem.yladoctor.Models.Helpers.FirebaseListener;
import com.kareem.yladoctor.Models.Interfaces.FirebaseListeners;
import com.kareem.yladoctor.Models.Modules.ScheduleModules.Schedule;
import com.kareem.yladoctor.Models.Modules.ScheduleModules.ScheduleDay;
import com.kareem.yladoctor.Models.Singletons.InfiniteExpandableListSingleton;
import com.kareem.yladoctor.R;
import com.kareem.yladoctor.Views.Dialogs.LoadingDialog;
import com.sdsmdg.tastytoast.TastyToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by kareem on 7/2/2017 - YlaDoctor.
 * <br></br>
 * description goes here
 *
 * @author kareem
 * @version %I%
 */

public class ScheduleDayDoctorViewHolder extends InfiniteExpandableListViewHolder implements FirebaseListeners {
	@BindView(R.id.scheduleItem_imageView_arrowDirection)
	ImageView arrowDirection;
	@BindView(R.id.scheduleItem_textView_text)
	TextView text;

	@OnClick(R.id.scheduleItem_switch_disactivateDay)
	public void onActivationDayClicked ( Switch s ) {
		if (s.isChecked())
			// TODO: 7/14/2017 schedule.getDay() returns null for the first use so handle it
			new ScheduleFactory(getView().getContext(), FirebaseAuth.getInstance().getCurrentUser().getUid()).setDayActive(scheduleDay.getName());
		else {
			new AlertDialog.Builder(getView().getContext()).setTitle("Are you sure?!").setMessage("this Action will cancel all booked appointments in this day").setCancelable(true).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
				@Override
				public void onClick ( DialogInterface dialog, int which ) {
					new ScheduleFactory(getView().getContext(), FirebaseAuth.getInstance().getCurrentUser().getUid()).setDayOnHold(scheduleDay.getName());
				}
			}).show();
		}
	}

	@OnClick(R.id.scheduleItem_imageView_addNewSchedule)
	public void onNewScheduleClicked ( ImageButton b ) {
		new ScheduleFactory(getView().getContext(), FirebaseAuth.getInstance().getCurrentUser().getUid()).createNewSchedule(scheduleDay.getName());
	}

	private ScheduleDay scheduleDay;
	private LoadingDialog loadingDialog;

	public ScheduleDayDoctorViewHolder ( View view, Object data ) {
		super(view, data);
		ButterKnife.bind(this, view);
		scheduleDay = (ScheduleDay) getData();
		text.setText(scheduleDay.getName().toString());
		loadingDialog = new LoadingDialog(getView().getContext());
//		else {
//			if (data instanceof Schedule) {
//				schedule = (Schedule) data;
//				text.setText(schedule.getDay().toString());
//				switch (UserAccountTypeManager.getAccountType(view.getContext())) {
//					case DOCTOR:
//						accountType = AccountType.DOCTOR;
//						if (schedule.getCondition()
//								  .equals(FirebaseContracts.PATH_TO_SCHEDULE_DOCTORUID_DAYNAME_SLOTTIME_CONDITION_NOVALUE)) {
//							dashText();
//							clickable = false;
//						}
//						break;
//					case PATIENT:
//						accountType = AccountType.PATIENT;
//						if (! schedule.getCondition()
//								  .equals(FirebaseContracts.PATH_TO_SCHEDULE_DOCTORUID_DAYNAME_SLOTTIME_CONDITION_NOVALUE) || schedule.getCondition().equals(FirebaseContracts.PATH_TO_SCHEDULE_DOCTORUID_DAYNAME_SLOTTIME_CONDITION_ONHOLD)) {
//							dashText();
//							clickable = false;
//						}
//						break;
//					default:
//						accountType = AccountType.UNKNOWN;
//				}
//			}
//		}
	}

	@Override
	public void onInfiniteExpandableListViewItemIsCollapsing ( Object o, int i, int i1 ) {
		arrowDirection.setImageResource(R.mipmap.arrow_left);
	}

	@Override
	public void onInfiniteExpandableListViewItemIsExpanding ( Object o, int i, int i1 ) {
		if (scheduleDay.isActive()) {
			if (scheduleDay.getName() != null) {
				loadingDialog.show();
				new FirebaseListener(this).initSingleValueEventListener(null, DatabasePathFactory.pathTo_Schedule_userUID_weekDay(FirebaseAuth.getInstance().getCurrentUser().getUid(), scheduleDay.getName()), FirebaseContracts.PATH_TO_SCHEDULEIDENTIFIER);
//		else if (getData() instanceof Schedule && clickable)
//			HandleTimeClicked((Schedule) getData());
			}
		} else
			TastyToast.makeText(getView().getContext(), scheduleDay.getName().toString() + " is disactivated", TastyToast.LENGTH_SHORT, TastyToast.INFO);
	}

//	public void HandleTimeClicked ( Schedule schedule ) {
//		switch (accountType) {
//			case DOCTOR:
//				InfiniteExpandableListSingleton.getInstance(null).createLevel(new Schedule[]{ schedule }, ScheduleItemDoctorViewHolder.class, R.layout.doctor_appointment_history_collapsed);
//				break;
//			case PATIENT:
//				InfiniteExpandableListSingleton.getInstance(null).createLevel(new Schedule[]{ schedule }, ScheduleItemPatientViewHolder.class, R.layout.patient_schedule_time_child);
//				break;
//		}
//
//	}

	private void dashText () {
		text.setPaintFlags(text.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
		text.setTextColor(Color.parseColor(ContractValues.dashedTextColor));
	}

	@Override
	public void ValueEventListener ( Object data, DataSnapshot dataSnapshot, String ID ) {

	}

	@Override
	public void SingleValueEventListener ( Object data, DataSnapshot dataSnapshot, String ID ) {
		loadingDialog.dismiss();
		if (dataSnapshot.getValue() != null) {
			arrowDirection.setImageResource(R.mipmap.arrow_down);
			scheduleDay = dataSnapshot.getValue(ScheduleDay.class);
			InfiniteExpandableListSingleton.getInstance(null).createLevel(scheduleDay.listOfSchedules(), ScheduleTimeDoctorViewHolder.class, R.layout.doctor_schedule_slot_item);
		} else
			TastyToast.makeText(getView().getContext(), "no schedule yet", TastyToast.LENGTH_SHORT, TastyToast.INFO);
	}

	@Override
	public void onFailure ( Object data, Throwable error, String ID ) {

	}
}
