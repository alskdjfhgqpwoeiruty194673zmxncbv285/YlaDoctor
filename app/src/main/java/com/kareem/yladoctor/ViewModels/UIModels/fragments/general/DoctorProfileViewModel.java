package com.kareem.yladoctor.ViewModels.UIModels.fragments.general;

import com.bumptech.glide.Glide;
import com.firebase.geofire.GeoFire;
import com.firebase.geofire.GeoLocation;
import com.firebase.geofire.LocationCallback;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.kareem.yladoctor.Factories.DatabasePathFactory;
import com.kareem.yladoctor.Models.Contracts.ContractValues;
import com.kareem.yladoctor.Models.Contracts.FirebaseContracts;
import com.kareem.yladoctor.Models.Helpers.FirebaseListener;
import com.kareem.yladoctor.Models.Interfaces.FirebaseListeners;
import com.kareem.yladoctor.Models.Interfaces.ViewModelsInterfaces.UIModels.GeneralDoctorProfileInterface;
import com.kareem.yladoctor.Models.Modules.User.Businesses.Individuals.Doctor;
import com.kareem.yladoctor.ViewModels.Engine.DefaultApplicationLanguageManager;

/**
 * Created by kareem on 7/4/2017 - YlaDoctor.
 * <br></br>
 * description goes here
 *
 * @author kareem
 * @version %I%
 */

public class DoctorProfileViewModel implements OnMapReadyCallback {
	private Doctor doctor;
	private String defaultLanguage;
	GeneralDoctorProfileInterface generalDoctorProfileInterface;

	public DoctorProfileViewModel ( final GeneralDoctorProfileInterface generalDoctorProfileInterface ) {
		this.generalDoctorProfileInterface = generalDoctorProfileInterface;
		doctor = generalDoctorProfileInterface.getDoctor();
		defaultLanguage = DefaultApplicationLanguageManager.getDefaultLanguage(generalDoctorProfileInterface.getMyContext()).toString();
		initializeGUI();

	}

	public void initializeGUI () {
		if (doctor.getNames() != null)
			generalDoctorProfileInterface.getNameTextView().setText(doctor.getNames().get(defaultLanguage));
		if (doctor.getProfilePicture() != null)
			Glide.with(generalDoctorProfileInterface.getMyContext()).load(doctor.getProfilePicture().getURL()).into(generalDoctorProfileInterface.getProfilePicture());
		if (doctor.getCareer().getBusinessLocations() != null)
			generalDoctorProfileInterface.getBusinessLocationEditText().setText(doctor.getCareer().getBusinessLocations().get(defaultLanguage));
		generalDoctorProfileInterface.getMapMapView().onCreate(null);
		generalDoctorProfileInterface.getMapMapView().getMapAsync(this);
		generalDoctorProfileInterface.getExperienceEditText().setText(doctor.getCareer().getExperienceYears() + "");
		generalDoctorProfileInterface.getPriceEditText().setText(doctor.getCareer().getPrice() + "");
		generalDoctorProfileInterface.getIntervalEditText().setText(doctor.getCareer().getInterval() + "");
		String healthInsuranceCompaniesInString = "";
		if (doctor.getCareer().getListOfHealthInsuranceCompanies() != null) {
			for (String healthInsuranceCompany : doctor.getCareer().getListOfHealthInsuranceCompanies())
				healthInsuranceCompaniesInString += healthInsuranceCompany + "\n";
			generalDoctorProfileInterface.getHealthInsuranceCompaniesEditText().setText(healthInsuranceCompaniesInString);
		}
		generalDoctorProfileInterface.getMobileNumberEditText().setText(doctor.getCareer().getBusinessMobileNumber());
		new FirebaseListener(new FirebaseListeners() {
			@Override
			public void ValueEventListener ( Object data, DataSnapshot dataSnapshot, String ID ) {

			}

			@Override
			public void SingleValueEventListener ( Object data, DataSnapshot dataSnapshot, String ID ) {
				if (dataSnapshot.getValue() != null)
					generalDoctorProfileInterface.getMedicalFieldEditText().setText(dataSnapshot.getValue(String.class));
			}

			@Override
			public void onFailure ( Object data, Throwable error, String ID ) {

			}
		}).initSingleValueEventListener(null, DatabasePathFactory.pathTo_MedicalFieldIdentifier_FieldUID_Name_Language(doctor.getCareer().getFieldID(), defaultLanguage), FirebaseContracts.PATH_TO_MEDICALFIELDSIDENTIFIER);
		generalDoctorProfileInterface.getRateRatingBar().setRating(Float.valueOf(String.valueOf(doctor.getCareer().getRate())));
		generalDoctorProfileInterface.getVotes().setText(doctor.getCareer().getVotes() + "");
	}

	@Override
	public void onMapReady ( final GoogleMap googleMap ) {
		new GeoFire(DatabasePathFactory.pathTo_GeoFireLocation()).getLocation(doctor.getUID(), new LocationCallback() {
			@Override
			public void onLocationResult ( String key, GeoLocation location ) {
				LatLng latLng = new LatLng(location.latitude, location.longitude);
				googleMap.addMarker(new MarkerOptions().title(doctor.getNames().get(defaultLanguage)).position(latLng));
				googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, ContractValues.ZOOMLEVELMAP));
			}

			@Override
			public void onCancelled ( DatabaseError databaseError ) {

			}
		});
	}

	public void onFavouriteClicked () {
		// TODO: 7/4/2017 add favourite functionality later
	}

	public void onBookAppointmentClicked () {
		// TODO: 7/4/2017 add book appointment functionality later
	}

	public void onShareClicked () {
		//// TODO: 7/4/2017 add share functionality later
	}
}
