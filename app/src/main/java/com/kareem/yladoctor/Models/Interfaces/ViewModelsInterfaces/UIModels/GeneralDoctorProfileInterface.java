package com.kareem.yladoctor.Models.Interfaces.ViewModelsInterfaces.UIModels;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.gms.maps.MapView;
import com.kareem.yladoctor.Models.Modules.User.Businesses.Individuals.Doctor;

public interface GeneralDoctorProfileInterface {
	public TextView getNameTextView ();

	public ImageView getProfilePicture ();

	public EditText getBusinessLocationEditText ();

	public MapView getMapMapView ();

	public EditText getExperienceEditText ();

	public EditText getPriceEditText ();

	public EditText getIntervalEditText ();

	public EditText getHealthInsuranceCompaniesEditText ();

	public EditText getMobileNumberEditText ();

	public EditText getMedicalFieldEditText ();

	public RatingBar getRateRatingBar ();

	public TextView getVotes ();

	public Context getMyContext ();

	public Doctor getDoctor ();

	public boolean isMainUserDoctor ();
}