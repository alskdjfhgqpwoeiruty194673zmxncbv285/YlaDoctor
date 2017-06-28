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

public class DoctorMedicalHistoryViewHolder extends MedicalHistoryViewHolder {
	/****************************************/
	@OnClick(R.id.doctorAppointmentHistoryExpanded_button_viewRecords)
	public void onViewRecordsClicked () {

	}

	public DoctorMedicalHistoryViewHolder ( View itemView ) {
		super(itemView);
	}

	@Override
	public String extractUID ( AppointmentHistory appointmentHistory ) {
		return appointmentHistory.getPuid();
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
