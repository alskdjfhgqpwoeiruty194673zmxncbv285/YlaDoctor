/*
 * Copyright (c) $today.year.kareem elsayed aly,no one has the authority to edit,delete,copy any part without my permission
 */

package com.kareem.yladoctor.Views.Fragments.MainScreensFragments.PatientMainScreenFramgents;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.kareem.yladoctor.MainApplication;
import com.kareem.yladoctor.Models.Contracts.FirebaseContracts;
import com.kareem.yladoctor.Models.Helpers.FirebaseListener;
import com.kareem.yladoctor.Models.Helpers.RecyclerAdapter;
import com.kareem.yladoctor.Models.Interfaces.FirebaseListeners;
import com.kareem.yladoctor.Models.Interfaces.RecyclerAdapterListener;
import com.kareem.yladoctor.Models.Modules.AppointmentHistories.AppointmentHistory;
import com.kareem.yladoctor.Models.Modules.AppointmentHistories.AppointmentHistoryIdentifier;
import com.kareem.yladoctor.Models.Modules.User.Businesses.Individuals.Patient;
import com.kareem.yladoctor.R;
import com.kareem.yladoctor.Views.ViewHolders.MedicalHistoryViewHolders.PatientMedicalHistoryViewHolder;

import butterknife.ButterKnife;

/**
 * Created by kareem on 9/17/2016 - HealMe.
 * <br></br>
 * description goes here
 *
 * @author kareem
 * @version %I%
 */

@SuppressWarnings("unchecked")
public class PatientMainScreenThree extends Fragment implements RecyclerAdapterListener, FirebaseListeners {
	private static final String CLASS_TAG = "AppointmentHistory";
	RecyclerAdapter<AppointmentHistoryIdentifier, PatientMedicalHistoryViewHolder> recyclerAdapter;

	//    SetValueListener setValueListener;
	Patient patient;

	@Nullable
	@Override
	public View onCreateView ( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState ) {
		return inflater.inflate(R.layout.general_recyclerview_listofdata, container, false);
	}

	@Override
	public void onViewCreated ( View view, Bundle savedInstanceState ) {
		super.onViewCreated(view, savedInstanceState);
		initVariables(view);
	}

	public void initVariables ( View view ) {
		ButterKnife.bind(this, view);
		try {
			patient = (Patient) ((MainApplication) this.getActivity().getApplication()).getUser();
		} catch (Exception e) {
			Log.e(CLASS_TAG, e.getMessage());
		}
		recyclerAdapter = new RecyclerAdapter<>(this);
		recyclerAdapter.initRecyclerAdapterListener(null, (RecyclerView) view.findViewById(R.id.generalRecyclerViewListOfData_recyclerView_listOfItems), AppointmentHistoryIdentifier.class, PatientMedicalHistoryViewHolder.class, R.layout.patient_appointment_history_item, recyclerAdapter.getReference().child(FirebaseContracts.PATH_TO_HISTORYIDENTIFIER).child(patient.getUID()).limitToFirst(10), FirebaseContracts.PATH_TO_HISTORYIDENTIFIER);
	}


	@Override
	public void ValueEventListener ( Object data, DataSnapshot dataSnapshot, String ID ) {

	}


	// TODO: 12/24/2016 unCheck that and modify later
	@Override
	public void SingleValueEventListener ( Object data, DataSnapshot dataSnapshot, String ID ) {
		if (dataSnapshot != null && dataSnapshot.getValue() != null) {
			final PatientMedicalHistoryViewHolder patientMedicalHistoryViewHolder = (PatientMedicalHistoryViewHolder) data;
			switch (ID) {
				case FirebaseContracts.PATH_TO_HISTORYIDENTIFIER:
					AppointmentHistory appointmentHistory = dataSnapshot.getValue(AppointmentHistory.class);
					patientMedicalHistoryViewHolder.initializeItem(PatientMainScreenThree.this.getActivity(), appointmentHistory);
					break;
			}
		}
	}

	@Override
	public void onFailure ( Object data, Throwable error, String ID ) {
		Log.e(CLASS_TAG, error.getMessage() + " : " + ID);
	}

	@Override
	public void recyclerAdapterListener ( Object data, RecyclerView.ViewHolder viewHolder, Object holder, int position, String ID ) {
		switch (ID) {
			case FirebaseContracts.PATH_TO_HISTORYIDENTIFIER:
				PatientMedicalHistoryViewHolder patientMedicalHistoryViewHolder = (PatientMedicalHistoryViewHolder) viewHolder;
				AppointmentHistoryIdentifier appointmentHistoryIdentifier = (AppointmentHistoryIdentifier) holder;

				new FirebaseListener(this).initSingleValueEventListener(patientMedicalHistoryViewHolder, FirebaseContracts.PATH_TO_APPOINTMENTHISTORY + "/" + appointmentHistoryIdentifier.getID(), FirebaseContracts.PATH_TO_HISTORYIDENTIFIER);
				break;
		}
	}

	@Override
	public Context getMyContext () {
		return this.getActivity();
	}
}
