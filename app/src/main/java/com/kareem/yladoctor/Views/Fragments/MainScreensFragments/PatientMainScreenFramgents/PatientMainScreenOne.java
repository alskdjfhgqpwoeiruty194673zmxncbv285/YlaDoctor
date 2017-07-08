/*
 * Copyright (c) 2017. KAREEM ElSAYED ALY  ALL RIGHTS RESERVED.
 */

package com.kareem.yladoctor.Views.Fragments.MainScreensFragments.PatientMainScreenFramgents;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kareem.yladoctor.R;
import com.kareem.yladoctor.Views.Activites.patients.MedicalFields;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by kareem on 5/26/2017 - HealMe.
 * <br></br>
 * description goes here
 *
 * @author kareem
 * @version %I%
 */

public abstract class PatientMainScreenOne extends Fragment {
	@OnClick(R.id.newGeneralPage2_ImageButton_BookAppointment)
	public void OnBookAppointmentClicked () {
		Intent i = new Intent(PatientMainScreenOne.this.getActivity(), MedicalFields.class);
		startActivity(i);
	}

	@OnClick(R.id.newGeneralPage2_ImageButton_emergency)
	public void OnEmergencyClicked () {
		// TODO: 5/26/2017 add the function of map later


	}

	@OnClick(R.id.newGeneralPage2_LinearLayout_history)
	public void onHistoryClicked () {
		getMyViewPager().setCurrentItem(getHistoryFragmentPosition());
	}

	@OnClick(R.id.newGeneralPage2_LinearLayout_records)
	public void onRecordsClicked () {
		getMyViewPager().setCurrentItem(getRecordsFragmentPosition());
	}

	@Nullable
	@Override
	public View onCreateView ( LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState ) {
		return inflater.inflate(R.layout.patient_mainview_one, container, false);
	}

	@Override
	public void onViewCreated ( View view, @Nullable Bundle savedInstanceState ) {
		super.onViewCreated(view, savedInstanceState);
		ButterKnife.bind(this, view);
	}

	public abstract ViewPager getMyViewPager ();

	public abstract int getHistoryFragmentPosition ();

	public abstract int getRecordsFragmentPosition ();
}
