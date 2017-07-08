package com.kareem.yladoctor.Views.Fragments.MainScreensFragments.DoctorMainScreenFragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.MapView;
import com.kareem.yladoctor.MainApplication;
import com.kareem.yladoctor.Models.Contracts.FirebaseContracts;
import com.kareem.yladoctor.Models.Interfaces.ViewModelsInterfaces.UIModels.GeneralDoctorMainScreenOneInterface;
import com.kareem.yladoctor.Models.Modules.User.Businesses.Individuals.Doctor;
import com.kareem.yladoctor.R;
import com.kareem.yladoctor.ViewModels.UIModels.fragments.general.DoctorMainScreenOneViewModel;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by kareem on 6/16/2017 - YlaDoctor.
 * <br></br>
 * description goes here
 *
 * @author kareem
 * @version %I%
 */

public class DoctorMainScreenOne extends Fragment implements GeneralDoctorMainScreenOneInterface {
	private static final int locationCode=0;
	DoctorMainScreenOneViewModel doctorMainScreenOneViewModel;
	String valueOne;
	@BindViews({ R.id.doctorMainViewOne_textView_changeBusinessLocation, R.id.doctorMainViewOne_textView_changeMapLocation, R.id.doctorMainViewOne_textView_changeExperience, R.id.doctorMainViewOne_textView_changePrice, R.id.doctorMainViewOne_textView_changeInterval, R.id.doctorMainViewOne_textView_changeHealthInsuranceCompanies, R.id.doctorMainViewOne_textView_changeMobileNumber })
	List<TextView> changeTextViews;

	@OnClick(R.id.doctorMainViewOne_imageView_menu)
	public void onMenuClicked () {
		doctorMainScreenOneViewModel.onMenuClicked();
	}

	@OnClick(R.id.doctorMainViewOne_button_editSchedule)
	public void onEditScheduleClicked () {
		// TODO: 7/6/2017 edit schedule dialog
	}

	@OnClick(R.id.doctorMainViewOne_button_viewAsPatient)
	public void onViewAsPatientClicked () {
		doctorMainScreenOneViewModel.onViewAsPatientClicked();
	}

	@OnClick(R.id.doctorMainViewOne_textView_changeBusinessLocation)
	public void onChangeBusinessLocationClicked ( TextView button ) {
		callForBusinessLocationChanging(businessLocation, businessLocation.getText().toString(), button);
	}

	private void callForBusinessLocationChanging ( EditText businessLocation, String s, TextView button ) {
		if (button.getTag() != null) {
			if ((boolean) button.getTag()) {
				if (valueOne == null) {
					valueOne = s;
					businessLocationLanguage.setText(FirebaseContracts.ARABIC);
				}
				else {
					HashMap<String, String> map = new HashMap<>();
					map.put(FirebaseContracts.ARABIC, s);
					map.put(FirebaseContracts.ENGLISH, valueOne);
					doctorMainScreenOneViewModel.saveData(FirebaseContracts.PATH_TO_USERS_INDIVIDUALBUSINESSUID_CAREER_BUSINESSLOCATIONS, map);
				businessLocationLanguage.setText(null);
				}

			} else {
				if (valueOne==null) {
					doctorMainScreenOneViewModel.resetButtons();
					doctorMainScreenOneViewModel.resetEditTexts();
					doctorMainScreenOneViewModel.initializeGUI();
					doctorMainScreenOneViewModel.activateChangeDataEditText(businessLocation, button);
					businessLocationLanguage.setText(FirebaseContracts.ENGLISH);
				}
			}
		}
	}

	@OnClick(R.id.doctorMainViewOne_textView_changeMapLocation)
	public void onChangeMapLocationClicked ( TextView button ) {
		// TODO: 7/6/2017 change location by mapPicker
		try {
			this.startActivityForResult(new PlacePicker.IntentBuilder().build(this.getActivity()), locationCode);
		} catch (GooglePlayServicesRepairableException | GooglePlayServicesNotAvailableException e) {
			e.printStackTrace();
		}
	}

	@OnClick(R.id.doctorMainViewOne_textView_changeExperience)
	public void onChangeExperienceClicked ( TextView button ) {
		callForEitTextDataChange(FirebaseContracts.PATH_TO_USERS_INDIVIDUALDOCTORUID_DOCTORCAREER_EXPERIENCEYEARS, experience, Integer.valueOf(experience.getText().toString()), button);
	}

	@OnClick(R.id.doctorMainViewOne_textView_changePrice)
	public void onChangePriceClicked ( TextView button ) {
		callForEitTextDataChange(FirebaseContracts.PATH_TO_USERS_INDIVIDUALDOCTORUID_DOCTORCAREER_PRICE, price, Integer.valueOf(price.getText().toString()), button);
	}

	@OnClick(R.id.doctorMainViewOne_textView_changeInterval)
	public void onChangeIntervalClicked ( TextView button ) {
		callForEitTextDataChange(FirebaseContracts.PATH_TO_USERS_INDIVIDUALDOCTORUID_DOCTORCAREER_INTERVAL, interval, Integer.valueOf(interval.getText().toString()), button);
	}

	@OnClick(R.id.doctorMainViewOne_textView_changeHealthInsuranceCompanies)
	public void onChangeHealthInsuranceCompaniesClicked () {
		//// TODO: 7/6/2017 add health insurance companies
	}

	@OnClick(R.id.doctorMainViewOne_textView_changeMobileNumber)
	public void onChangeMobileNumberClicked ( TextView button ) {
		callForEitTextDataChange(FirebaseContracts.PATH_TO_USERS_INDIVIDUALBUSINESSUID_CAREER_BUSINESSMOBILENUMBER, mobileNumber, mobileNumber.getText().toString(), button);
	}

	@BindView(R.id.doctorMainViewOne_textView_name)
	TextView name;

	@BindView(R.id.doctorMainViewOne_view_activatedDoctor)
	View activatedDoctor;

	@BindView(R.id.doctorMainViewOne_imageView_profilePicture)
	ImageView profilePicture;

	@BindView(R.id.doctorMainViewOne_editText_businessLocation)
	EditText businessLocation;

	@BindView(R.id.doctorMainViewOne_textView_businessLocationLanguage)
	TextView businessLocationLanguage;

	@BindView(R.id.doctorMainViewOne_mapView_mapLocation)
	MapView map;

	@BindView(R.id.doctorMainViewOne_editText_experience)
	EditText experience;

	@BindView(R.id.doctorMainViewOne_editText_price)
	EditText price;

	@BindView(R.id.doctorMainViewOne_editText_interval)
	EditText interval;

	@BindView(R.id.doctorMainViewOne_editText_healthInsuranceCompanies)
	EditText healthInsuranceCompanies;

	@BindView(R.id.doctorMainViewOne_editText_mobileNumber)
	EditText mobileNumber;

	@BindView(R.id.doctorMainViewOne_editText_medicalField)
	EditText medicalField;

	@BindView(R.id.doctorMainViewOne_ratingBar_rate)
	RatingBar rate;

	@BindView(R.id.doctorMainViewOne_textView_votes)
	TextView votes;

	@Override
	public void onViewCreated ( View view, @Nullable Bundle savedInstanceState ) {
		super.onViewCreated(view, savedInstanceState);
		ButterKnife.bind(this, view);
		doctorMainScreenOneViewModel = new DoctorMainScreenOneViewModel(this);
		for (TextView textView : changeTextViews
				  ) {
			textView.setTag(false);
		}
		this.getActivity().getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
	}

	@Nullable
	@Override
	public View onCreateView ( LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState ) {
		return inflater.inflate(R.layout.doctor_mainview_one, container, false);
	}

	private void callForEitTextDataChange ( String path, EditText editTextToChange, Object object, TextView changeTextView ) {
		valueOne = null;
		if (changeTextView.getTag() != null) {
			if ((boolean) changeTextView.getTag()) {
				doctorMainScreenOneViewModel.saveData(path, object);
			} else {
				doctorMainScreenOneViewModel.resetButtons();
				doctorMainScreenOneViewModel.resetEditTexts();
				doctorMainScreenOneViewModel.initializeGUI();
				doctorMainScreenOneViewModel.activateChangeDataEditText(editTextToChange, changeTextView);
			}
		}
	}

	@Override
	public TextView getNameTextView () {
		return name;
	}

	@Override
	public View getMainView () {
		return this.getView();
	}

	@Override
	public Activity getParentActivity () {
		return this.getActivity();
	}

	@Override
	public List<TextView> changeListViews () {
		return changeTextViews;
	}

	@Override
	public View getActivatedDoctorView () {
		return activatedDoctor;
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
	public TextView getBusinessLocationLanguageTextView () {
		return businessLocationLanguage;
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
		return this.getActivity();
	}

	@Override
	public Doctor getDoctor () {
		return (Doctor) ((MainApplication) this.getActivity().getApplication()).getUser();
	}

	@Override
	public boolean isMainUserDoctor () {
		return true;
	}

}
