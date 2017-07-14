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
import com.kareem.yladoctor.Models.Singletons.InfiniteExpandableListSingleton;
import com.kareem.yladoctor.R;
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

public class ScheduleTimeDoctorViewHolder extends InfiniteExpandableListViewHolder {
	@BindView(R.id.scheduleItem_imageView_arrowDirection)
	ImageView arrowDirection;
	@BindView(R.id.scheduleItem_textView_text)
	TextView text;

	@OnClick(R.id.scheduleItem_imageView_removeSchedule)
	public void onRemoveSlotClicked ( ImageView s ) {

	}

	boolean clickable = true;
	private AccountType accountType;
	private Schedule schedule;

	public ScheduleTimeDoctorViewHolder ( View view, Object data ) {
		super(view, data);
		ButterKnife.bind(this, view);

		assert data instanceof Schedule;
		schedule = (Schedule) getData();
		text.setText(schedule.getScheduleStartTime());
//		else {
//			if (data instanceof Schedule) {
//				schedule = (Schedule) data;
//				text.setText(schedule.getDay().toString());
//				switch (UserAccountTypeManager.getAccountType(view.getContext())) {
//					case DOCTOR:
//						accountType = AccountType.DOCTOR;
		if (schedule.getCondition()
				  .equals(FirebaseContracts.PATH_TO_SCHEDULE_DOCTORUID_DAYNAME_SLOTTIME_CONDITION_NOVALUE)) {
			dashText();
			clickable = false;
		}
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

		if (schedule != null) {
			arrowDirection.setImageResource(R.mipmap.arrow_down);

			InfiniteExpandableListSingleton.getInstance(null).createLevel(new Schedule[]{ schedule }, ScheduleItemDoctorViewHolder.class, R.layout.doctor_appointment_history_collapsed);
		}else
			TastyToast.makeText(getView().getContext(),"no booked appointment",TastyToast.LENGTH_SHORT,TastyToast.INFO);
	}

	private void dashText () {
		text.setPaintFlags(text.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
		text.setTextColor(Color.parseColor(ContractValues.dashedTextColor));
	}

}
