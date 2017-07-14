package com.kareem.yladoctor.Views.ViewHolders.ScheduleViewHolders;

import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.kareem.infiniteexpandablelist.InfiniteExpandableListViewHolder;
import com.kareem.yladoctor.Models.Contracts.ContractValues;
import com.kareem.yladoctor.Models.Contracts.FirebaseContracts;
import com.kareem.yladoctor.Models.Enums.AccountType;
import com.kareem.yladoctor.Models.Modules.ScheduleModules.Schedule;
import com.kareem.yladoctor.Models.Modules.ScheduleModules.ScheduleDay;
import com.kareem.yladoctor.Models.Singletons.InfiniteExpandableListSingleton;
import com.kareem.yladoctor.R;
import com.kareem.yladoctor.ViewModels.Engine.UserAccountTypeManager;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kareem on 7/2/2017 - YlaDoctor.
 * <br></br>
 * description goes here
 *
 * @author kareem
 * @version %I%
 */

public class ScheduleDayViewHolder extends InfiniteExpandableListViewHolder {
	@BindView(R.id.scheduleItem_imageView_arrowDirection)
	ImageView arrowDirection;
	@BindView(R.id.scheduleItem_textView_text)
	TextView text;

	boolean clickable = true;
	private AccountType accountType;

	public ScheduleDayViewHolder ( View view, Object data ) {
		super(view, data);
		ButterKnife.bind(this, view);
		if (data instanceof ScheduleDay) {
			text.setText(((ScheduleDay) data).getName().toString());
		} else {
			if (data instanceof Schedule) {
				Schedule s = (Schedule) data;
				text.setText(s.getScheduleStartTime());
				switch (UserAccountTypeManager.getAccountType(view.getContext())) {
//					case DOCTOR:
//						accountType = AccountType.DOCTOR;
//						if (s.getCondition()
//								  .equals(FirebaseContracts.PATH_TO_SCHEDULE_DOCTORUID_DAYNAME_SLOTTIME_CONDITION_NOVALUE)) {
//							dashText();
//							clickable = false;
//						}
//						break;
					case PATIENT:
						accountType = AccountType.PATIENT;
						if (! s.getCondition()
								  .equals(FirebaseContracts.PATH_TO_SCHEDULE_DOCTORUID_DAYNAME_SLOTTIME_CONDITION_NOVALUE) || s.getCondition().equals(FirebaseContracts.PATH_TO_SCHEDULE_DOCTORUID_DAYNAME_SLOTTIME_CONDITION_ONHOLD)) {
							dashText();
							clickable = false;
						}
						break;
					default:
						accountType = AccountType.UNKNOWN;
				}
			}
		}
	}

	@Override
	public void onInfiniteExpandableListViewItemIsCollapsing ( Object o, int i, int i1 ) {
		arrowDirection.setImageResource(R.mipmap.arrow_down);
	}

	@Override
	public void onInfiniteExpandableListViewItemIsExpanding ( Object o, int i, int i1 ) {
		arrowDirection.setImageResource(R.mipmap.arrow_left);
		if (getData() instanceof ScheduleDay)
			InfiniteExpandableListSingleton.getInstance(null).createLevel(((ScheduleDay) getData()).listOfSchedules(), ScheduleDayViewHolder.class, R.layout.general_schedule_item);
		else if (getData() instanceof Schedule && clickable)
			HandleTimeClicked((Schedule) getData());
	}

	public void HandleTimeClicked ( Schedule schedule ) {
		switch (accountType) {
			case DOCTOR:
				InfiniteExpandableListSingleton.getInstance(null).createLevel(new Schedule[]{ schedule }, ScheduleItemDoctorViewHolder.class, R.layout.doctor_appointment_history_collapsed);
				break;
			case PATIENT:
				InfiniteExpandableListSingleton.getInstance(null).createLevel(new Schedule[]{ schedule }, ScheduleItemPatientViewHolder.class, R.layout.patient_schedule_time_child);
				break;
		}

	}

	private void dashText () {
		text.setPaintFlags(text.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
		text.setTextColor(Color.parseColor(ContractValues.dashedTextColor));
	}
}
