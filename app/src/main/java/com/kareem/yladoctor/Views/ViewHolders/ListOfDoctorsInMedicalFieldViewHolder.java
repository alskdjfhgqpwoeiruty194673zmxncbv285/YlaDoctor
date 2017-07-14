package com.kareem.yladoctor.Views.ViewHolders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kareem.yladoctor.Models.Modules.User.Businesses.Individuals.Doctor;
import com.kareem.yladoctor.R;
import com.kareem.yladoctor.ViewModels.Engine.DefaultApplicationLanguageManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by kareem on 7/14/2017 - YlaDoctor.
 * <br></br>
 * description goes here
 *
 * @author kareem
 * @version %I%
 */

public class ListOfDoctorsInMedicalFieldViewHolder extends RecyclerView.ViewHolder {
	@BindView(R.id.listOfDoctorMedicalFieldItem_imageView_image)
	ImageView profilePicture;
	@BindView(R.id.listOfDoctorMedicalFieldItem_ratingBar_rate)
	RatingBar rate;
	@BindView(R.id.listOfDoctorMedicalFieldItem_textView_numberOfDoctors)
	TextView votes;
	@BindView(R.id.doctorMedicalFieldItem_textView_name)
	TextView name;
	@BindView(R.id.doctorMedicalFieldItem_textView_location)
	TextView location;
	@BindView(R.id.doctorMedicalFieldItem_textView_price)
	TextView price;
	@BindView(R.id.doctorMedicalFieldItem_textView_experience)
	TextView experience;
	@BindView(R.id.doctorMedicalFieldItem_textView_points)
	TextView points;

	@OnClick(R.id.doctorMedicalFieldItem_ImageView_favourite)
	public void onFavouriteClicked () {

	}

	@OnClick(R.id.doctorMedicalFieldItem_button_bookAppointment)
	public void onBookAppointmentClicked () {

	}

	@OnClick(R.id.doctorMedicalFieldItem_button_viewProfile)
	public void onViewProfileClicked () {

	}

	public ListOfDoctorsInMedicalFieldViewHolder ( View itemView ) {
		super(itemView);
		ButterKnife.bind(this, itemView);
	}

	public void initializeGUI ( Context context, Doctor doctor ) {
		if (doctor != null) {
			if (doctor.getProfilePicture() != null && doctor.getProfilePicture().getURL() != null)
				Glide.with(context).load(doctor.getProfilePicture().getURL()).into(profilePicture);
			name.setText(doctor.getNames().get(DefaultApplicationLanguageManager.getDefaultLanguage(context)));
			rate.setRating((float) doctor.getCareer().getRate());
			votes.setText(doctor.getCareer().getVotes() + "");
			location.setText(doctor.getCareer().getBusinessLocations().get(DefaultApplicationLanguageManager.getDefaultLanguage(context)));
			price.setText(doctor.getCareer().getPrice()+"");
			experience.setText(doctor.getCareer().getExperienceYears() + "");
			points.setText(doctor.getPoints() + "");
		}
	}
}
