package com.kareem.yladoctor.Views.ViewHolders.ScheduleViewHolders;

import android.view.View;

import com.kareem.infiniteexpandablelist.InfiniteExpandableListViewHolder;
import com.kareem.yladoctor.Factories.ScheduleFactory;
import com.kareem.yladoctor.Models.Modules.ScheduleModules.Schedule;
import com.kareem.yladoctor.R;

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

public class SchedulePatientTimeChildViewHolder extends InfiniteExpandableListViewHolder {
	//// TODO: 7/2/2017  request server time from our function
	private final String dateOfToday = "15/07/2017";

	@OnClick(R.id.patientScheduleTimeChild_button_confirmAppointment)
	public void OnConfirmAppointmentClicked () {
		new ScheduleFactory((Schedule) getData(), getView().getContext()).bookAppointment(dateOfToday);
	}

	@OnClick(R.id.patientScheduleTimeChild_button_cancelAppointment)
	public void OnCancelAppointmentClicked () {
		//// TODO: 7/3/2017 change it later for the parent to be collapsed
		getView().setVisibility(View.INVISIBLE);
	}

	public SchedulePatientTimeChildViewHolder ( View view, Object data ) {
		super(view, data);
		ButterKnife.bind(this, view);
	}

	@Override
	public void onInfiniteExpandableListViewItemIsCollapsing ( Object o, int i, int i1 ) {

	}

	@Override
	public void onInfiniteExpandableListViewItemIsExpanding ( Object o, int i, int i1 ) {

	}
}
