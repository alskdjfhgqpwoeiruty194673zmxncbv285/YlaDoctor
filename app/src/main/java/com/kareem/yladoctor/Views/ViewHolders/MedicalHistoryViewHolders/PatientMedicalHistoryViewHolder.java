package com.kareem.yladoctor.Views.ViewHolders.MedicalHistoryViewHolders;

import android.view.View;

import com.kareem.yladoctor.Models.Modules.AppointmentHistories.AppointmentHistory;
import com.kareem.yladoctor.R;

import butterknife.OnClick;

/**
 * Created by kareem on 6/28/2017 - YlaDoctor.
 * <br></br>
 * description goes here
 *
 * @author kareem
 * @version %I%
 */

public class PatientMedicalHistoryViewHolder extends MedicalHistoryViewHolder {

	/****************************************/
	@OnClick(R.id.patientAppointmentHistoryExpanded_button_bookAppointment)
	public void onBookAppointmentClicked () {

	}
	public PatientMedicalHistoryViewHolder ( View itemView ) {
		super(itemView);
	}

	@Override
	public String extractUID ( AppointmentHistory appointmentHistory ) {
		return appointmentHistory.getDuid();
	}
	@Override
	public float extractRate ( AppointmentHistory appointmentHistory ) {
		return appointmentHistory.getDReview().getRate();
	}

	@Override
	public String extractFeedback ( AppointmentHistory appointmentHistory ) {
		return appointmentHistory.getDReview().getDesc();
	}
}
