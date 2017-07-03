package com.kareem.yladoctor.Views.Fragments.BusinessSchedules;

import com.kareem.yladoctor.MainApplication;

/**
 * Created by kareem on 7/3/2017 - YlaDoctor.
 * <br></br>
 * description goes here
 *
 * @author kareem
 * @version %I%
 */

public class PatientBusinessSchedule extends GeneralBusinessSchedule {
	@Override
	public String getUID () {
		return ((MainApplication) this.getActivity().getApplication()).getOtherUser().getUID();
	}
}
