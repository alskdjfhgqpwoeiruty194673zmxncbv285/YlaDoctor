package com.kareem.yladoctor.Views.ViewHolders.ScheduleViewHolders;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.kareem.infiniteexpandablelist.InfiniteExpandableListViewHolder;
import com.kareem.yladoctor.Factories.DatabasePathFactory;
import com.kareem.yladoctor.Factories.ScheduleFactory;
import com.kareem.yladoctor.MainApplication;
import com.kareem.yladoctor.Models.Contracts.FirebaseContracts;
import com.kareem.yladoctor.Models.Helpers.FirebaseListener;
import com.kareem.yladoctor.Models.Interfaces.FirebaseListeners;
import com.kareem.yladoctor.Models.Modules.ScheduleModules.Schedule;
import com.kareem.yladoctor.Models.Modules.User.Businesses.Individuals.Patient;
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

public class ScheduleDoctorTimeChildViewHolder extends InfiniteExpandableListViewHolder implements FirebaseListeners {
	private Patient patient;
	@BindView(R.id.appointmentHistoryCollapsed_imageView_profilePicture)
	ImageView profilePicture;
	@BindView(R.id.appointmentHistoryCollapsed_textView_name)
	TextView name;

	@BindView(R.id.appointmentHistoryCollapsed_textView_time)
	TextView date;

	@OnClick(R.id.doctorScheduleTimeChild_imageView_approve)
	public void onCheckClicked () {
		doctorClickOnAppointment();
	}

	private void doctorClickOnAppointment () {
		if (patient == null) {
			TastyToast.makeText(getView().getContext(), "Please Wait", TastyToast.LENGTH_SHORT, TastyToast.DEFAULT);
			return;
		}
		String value = null;
		String condition = ((Schedule) getData()).getCondition();
		// TODO: 7/3/2017 add refuse this appointment
		if (condition.equals(FirebaseContracts.PATH_TO_SCHEDULE_DOCTORUID_DAYNAME_SLOTTIME_CONDITION_NEEDAPPROVAL))
			value = "Approve this appointment?";
		else if (condition.equals(FirebaseContracts.PATH_TO_SCHEDULE_DOCTORUID_DAYNAME_SLOTTIME_CONDITION_APPROVEDANDNEEDRELEASING))
			value = "Finish this appointment?";
		if (value == null)
			return;
		new AlertDialog.Builder(getView().getContext()).setTitle(value).setPositiveButton("YES", new DialogInterface.OnClickListener() {
			@Override
			public void onClick ( DialogInterface dialog, int which ) {
				new ScheduleFactory((Schedule) getData(), getView().getContext()).approveAppointment();
			}
		}).show();
	}

	@OnClick(R.id.doctorAppointmentHistoryCollapsed_button_viewRecords)
	public void onViewRecordsClicked () {
		if (patient == null) {
			TastyToast.makeText(getView().getContext(), "Please Wait", TastyToast.LENGTH_SHORT, TastyToast.DEFAULT);
			return;
		}
		((MainApplication) getView().getContext().getApplicationContext()).setOtherUser(patient);
		// TODO: 7/3/2017 restore lines below
//		Intent i = new Intent(getView().getContext(), MedicalHistoryContainer.class);
//		getView().getContext().startActivity(i);
	}

	@OnClick(R.id.appointmentHistoryCollapsed_button_viewProfile)
	public void onViewProfileClicked () {
		if (patient == null) {
			TastyToast.makeText(getView().getContext(), "Please Wait", TastyToast.LENGTH_SHORT, TastyToast.DEFAULT);
			return;
		}
		((MainApplication) getView().getContext().getApplicationContext()).setOtherUser(patient);
		// TODO: 7/3/2017 restore lines below
//		Intent i = new Intent(getView().getContext(), PatientProfile.class);
//		getView().getContext().startActivity(i);
	}

	public ScheduleDoctorTimeChildViewHolder ( View view, Object data ) {
		super(view, data);
		ButterKnife.bind(this, view);
		Schedule s = (Schedule) data;
		date.setText(s.getDate());
		new FirebaseListener(this).initSingleValueEventListener(null, DatabasePathFactory.pathTo_User_UserUID(s.getUid()), FirebaseContracts.PATH_TO_USERS);
	}

	@Override
	public void ValueEventListener ( Object data, DataSnapshot dataSnapshot, String ID ) {

	}

	@Override
	public void SingleValueEventListener ( Object data, DataSnapshot dataSnapshot, String ID ) {
		patient = dataSnapshot.getValue(Patient.class);
		if (patient != null) {
			name.setText(patient.getName());
			if (patient.getProfilePicture() != null)
				Glide.with(getView().getContext()).load(patient.getProfilePicture()).into(profilePicture);
		}
	}

	@Override
	public void onFailure ( Object data, Throwable error, String ID ) {
		TastyToast.makeText(getView().getContext(), "Error occurred", TastyToast.LENGTH_SHORT, TastyToast.ERROR);
	}

	@Override
	public void onInfiniteExpandableListViewItemIsCollapsing ( Object o, int i, int i1 ) {
		doctorClickOnAppointment();
	}

	@Override
	public void onInfiniteExpandableListViewItemIsExpanding ( Object o, int i, int i1 ) {
		doctorClickOnAppointment();
	}
}
