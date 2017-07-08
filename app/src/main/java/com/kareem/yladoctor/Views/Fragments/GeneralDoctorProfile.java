package com.kareem.yladoctor.Views.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.gms.maps.MapView;
import com.kareem.yladoctor.Models.Interfaces.ViewModelsInterfaces.UIModels.GeneralDoctorProfileInterface;
import com.kareem.yladoctor.R;
import com.kareem.yladoctor.ViewModels.UIModels.fragments.general.DoctorProfileViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by kareem on 7/4/2017 - YlaDoctor.
 * <br></br>
 * description goes here
 *
 * @author kareem
 * @version %I%
 */

public abstract class GeneralDoctorProfile extends AppCompatActivity implements GeneralDoctorProfileInterface {
	DoctorProfileViewModel doctorProfileViewModel;

	// TODO: 7/6/2017   change activated doctor view color
	@OnClick(R.id.doctorProfile_button_BookAppointment)
	public void onBookAppointmentClicked () {
		doctorProfileViewModel.onBookAppointmentClicked();
	}

	@OnClick(R.id.doctorProfile_button_Share)
	public void onShareClicked () {
		doctorProfileViewModel.onShareClicked();
	}

	@OnClick(R.id.doctorProfile_imageView_favourite)
	public void onFavouriteClicked () {
		doctorProfileViewModel.onFavouriteClicked();
	}

	@BindView(R.id.doctorProfile_textView_name)
	TextView name;

	@BindView(R.id.doctorProfile_imageView_profilePicture)
	ImageView profilePicture;

	@BindView(R.id.doctorProfile_editText_businessLocation)
	EditText businessLocation;

	@BindView(R.id.doctorProfile_mapView_mapLocation)
	MapView map;

	@BindView(R.id.doctorProfile_editText_experience)
	EditText experience;

	@BindView(R.id.doctorProfile_editText_price)
	EditText price;

	@BindView(R.id.doctorProfile_editText_interval)
	EditText interval;

	@BindView(R.id.doctorProfile_editText_healthInsuranceCompanies)
	EditText healthInsuranceCompanies;

	@BindView(R.id.doctorProfile_editText_mobileNumber)
	EditText mobileNumber;

	@BindView(R.id.doctorProfile_editText_medicalField)
	EditText medicalField;

	@BindView(R.id.doctorProfile_ratingBar_rate)
	RatingBar rate;

	@BindView(R.id.doctorProfile_textView_votes)
	TextView votes;

	@Override
	protected void onCreate ( @Nullable Bundle savedInstanceState ) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.doctor_profile);
		ButterKnife.bind(this);
		doctorProfileViewModel = new DoctorProfileViewModel(this);
	}

	@Override
	public TextView getNameTextView () {
		return name;
	}

	@Override
	public ImageView getProfilePicture () {
		return profilePicture;
	}

	@Override
	public EditText getBusinessLocationEditText () {
		return businessLocation;
	}

	@Override
	public MapView getMapMapView () {
		return map;
	}

	@Override
	public EditText getExperienceEditText () {
		return experience;
	}

	@Override
	public EditText getPriceEditText () {
		return price;
	}

	@Override
	public EditText getIntervalEditText () {
		return interval;
	}

	@Override
	public EditText getHealthInsuranceCompaniesEditText () {
		return healthInsuranceCompanies;
	}

	@Override
	public EditText getMobileNumberEditText () {
		return mobileNumber;
	}

	@Override
	public EditText getMedicalFieldEditText () {
		return medicalField;
	}

	@Override
	public RatingBar getRateRatingBar () {
		return rate;
	}

	@Override
	public TextView getVotes () {
		return votes;
	}

	@Override
	public Context getMyContext () {
		return this;
	}
}
