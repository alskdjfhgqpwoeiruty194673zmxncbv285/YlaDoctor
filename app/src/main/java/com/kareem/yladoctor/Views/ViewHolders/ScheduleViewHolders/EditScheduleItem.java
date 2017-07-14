package com.kareem.yladoctor.Views.ViewHolders.ScheduleViewHolders;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.FirebaseDatabase;
import com.kareem.yladoctor.Factories.DatabasePathFactory;
import com.kareem.yladoctor.Models.Contracts.FirebaseContracts;
import com.kareem.yladoctor.Models.Enums.WeekDays;
import com.kareem.yladoctor.Models.Helpers.SetValueListener;
import com.kareem.yladoctor.Models.Interfaces.newValueListener;
import com.kareem.yladoctor.Models.Modules.ScheduleModules.ScheduleIdentifier;
import com.kareem.yladoctor.R;
import com.sdsmdg.tastytoast.TastyToast;

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

public class EditScheduleItem extends RecyclerView.ViewHolder {
	private String UID;
	private WeekDays day;
	private String userUID;
	private Context context;

	@OnClick(R.id.doctorEditScheduleItem_button_fromTime)
	public void onFromTimeClicked () {

	}

	@OnClick(R.id.doctorEditScheduleItem_button_toTime)
	public void onToTimeClicked () {

	}

	@OnClick(R.id.doctorEditScheduleItem_imageView_delete)
	public void onDeleteClicked () {
		if (context != null) {
			new AlertDialog.Builder(context).setCancelable(true).setPositiveButton("YES", new DialogInterface.OnClickListener() {
				@Override
				public void onClick ( DialogInterface dialog, int which ) {
					new SetValueListener(new newValueListener() {
						@Override
						public void onSucceed ( Object data, String ID ) {
							TastyToast.makeText(context, "Schedule time removed", TastyToast.LENGTH_SHORT, TastyToast.SUCCESS);
						}

						@Override
						public void onFailure ( Object data, Throwable error, String ID ) {
							TastyToast.makeText(context, "Failed", TastyToast.LENGTH_SHORT, TastyToast.ERROR);
						}
					})
//							  .setNewValue(null, null, DatabasePathFactory.pathTo_ScheduleIdentifier_userUID_weekDay(userUID, day), FirebaseContracts.PATH_TO_SCHEDULEIDENTIFIER)
 ;
				}
			}).setTitle("Are you sure?\nthis will remove the schedule with booked appointments?").show();
		}
	}

	@BindView(R.id.doctorEditScheduleItem_button_toTime)
	Button toTimeButton;
	@BindView(R.id.doctorEditScheduleItem_button_fromTime)
	Button fromTimeButton;

	public EditScheduleItem ( View itemView ) {
		super(itemView);
		ButterKnife.bind(this, itemView);
	}

	public void initializeGUI ( String userUID, WeekDays day, ScheduleIdentifier identifier, Context context ) {
		this.userUID = userUID;
		this.day = day;
		toTimeButton.setText(identifier.getEnd());
		fromTimeButton.setText(identifier.getStart());
		UID = identifier.getUID();
		this.context = context;
	}
}
