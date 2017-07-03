package com.kareem.yladoctor.Views.Fragments.MainScreensFragments.DoctorMainScreenFragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.kareem.yladoctor.R;

import butterknife.BindView;
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

public class DoctorMainScreenOne extends Fragment implements OnMapReadyCallback {
	@OnClick(R.id.doctorMainViewOne_imageView_menu)
	public void onMenuClicked () {

	}

	@OnClick(R.id.doctorMainViewOne_button_editSchedule)
	public void onEditScheduleClicked () {

	}

	@OnClick(R.id.doctorMainViewOne_button_viewAsPatient)
	public void onViewAsPatientClicked () {

	}

	@OnClick(R.id.doctorMainViewOne_textView_changeBusinessLocation)
	public void onChangeBusinessLocationClicked () {

	}

	@OnClick(R.id.doctorMainViewOne_textView_changeMapLocation)
	public void onChangeMapLocationClicked () {

	}

	@OnClick(R.id.doctorMainViewOne_textView_changeExperience)
	public void onChangeExperienceClicked () {

	}

	@OnClick(R.id.doctorMainViewOne_textView_changePrice)
	public void onChangePriceClicked () {

	}

	@OnClick(R.id.doctorMainViewOne_textView_changeInterval)
	public void onChangeIntervalClicked () {

	}

	@OnClick(R.id.doctorMainViewOne_textView_changeHealthInsuranceCompanies)
	public void onChangeHealthInsuranceCompaniesClicked () {

	}

	@OnClick(R.id.doctorMainViewOne_textView_changeMobileNumber)
	public void onChangeMobileNumberClicked () {

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
	RatingBar votes;

	@Override
	public void onViewCreated ( View view, @Nullable Bundle savedInstanceState ) {
		super.onViewCreated(view, savedInstanceState);
		ButterKnife.bind(this, view);
		initializeVariables();
	}

	@Nullable
	@Override
	public View onCreateView ( LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState ) {
		return inflater.inflate(R.layout.doctor_mainview_one, container, false);
	}

	private void initializeVariables () {
		map.onCreate(null);
		map.getMapAsync(this);

	}
//
//	@Override
//	public void onResume () {
//		super.onResume();
////		ImageView img = (ImageView) getView().findViewById(R.id.tesst);
////		String Link = "http://maps.google.com/maps/api/staticmap?center=" + 30.1462225 + "," + 31.355292968749993 + "&zoom=17&size=" + img.getMeasuredWidth()+ "x" + img.getMeasuredWidth() + "&sensor=false";
////		Log.e("bbbb", Link);
////		Glide.with(this.getActivity()).load(Link).into(img);
//	}

	@Override
	public void onMapReady ( GoogleMap map ) {
		this.map.setMinimumHeight(this.map.getWidth());
//		map.addMarker(new MarkerOptions()
//				  .position(new LatLng(30.1462225,31.355292968749993))
//				  .title("Marker"));
		map.addMarker(new MarkerOptions().title("kareem").position(new LatLng(30.1462225, 31.355292968749993)));
		map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(30.1462225, 31.355292968749993), 17));
	}
}
