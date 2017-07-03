package com.kareem.yladoctor.Views.Fragments.MainScreensFragments.DoctorMainScreenFragments;

import com.kareem.yladoctor.MainApplication;
import com.kareem.yladoctor.Views.Fragments.BusinessSchedules.GeneralBusinessSchedule;

/**
 * Created by kareem on 7/3/2017 - YlaDoctor.
 * <br></br>
 * description goes here
 *
 * @author kareem
 * @version %I%
 */

public class DoctorMainViewTwoBusinessSchedule extends GeneralBusinessSchedule {
	@Override
	public String getUID () {
		return ((MainApplication) this.getActivity().getApplication()).getUser().getUID();
	}
}
