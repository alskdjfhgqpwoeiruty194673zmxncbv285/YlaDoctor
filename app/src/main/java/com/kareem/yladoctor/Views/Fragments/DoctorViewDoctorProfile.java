package com.kareem.yladoctor.Views.Fragments;

import com.kareem.yladoctor.MainApplication;
import com.kareem.yladoctor.Models.Modules.User.Businesses.Individuals.Doctor;

/**
 * Created by kareem on 7/4/2017 - YlaDoctor.
 * <br></br>
 * description goes here
 *
 * @author kareem
 * @version %I%
 */

public class DoctorViewDoctorProfile extends GeneralDoctorProfile {
	@Override
	public Doctor getDoctor () {
		return (Doctor) ((MainApplication)this.getApplication()).getUser();
	}

	@Override
	public boolean isMainUserDoctor () {
		return true;
	}
}
