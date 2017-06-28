package com.kareem.yladoctor.Views.ViewHolders.MedicalHistoryViewHolders;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.kareem.yladoctor.Models.Contracts.FirebaseContracts;
import com.kareem.yladoctor.Models.Enums.AccountType;
import com.kareem.yladoctor.Models.Helpers.FirebaseListener;
import com.kareem.yladoctor.Models.Interfaces.FirebaseListeners;
import com.kareem.yladoctor.Models.Modules.AppointmentHistories.AppointmentHistory;
import com.kareem.yladoctor.Models.Modules.User.Businesses.Individuals.Doctor;
import com.kareem.yladoctor.Models.Modules.User.User;
import com.kareem.yladoctor.R;
import com.kareem.yladoctor.ViewModels.Engine.DefaultApplicationLanguageManager;
import com.sdsmdg.tastytoast.TastyToast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by kareem on 6/28/2017 - YlaDoctor.
 * <br></br>
 * description goes here
 *
 * @author kareem
 * @version %I%
 */

public abstract class MedicalHistoryViewHolder extends RecyclerView.ViewHolder implements FirebaseListeners {
	private Context context;
	@BindView(R.id.appointmentHistoryExpanded_imageView_profilePicture)
	ImageView profilePictureExpanded;
	@BindView(R.id.appointmentHistoryCollapsed_imageView_profilePicture)
	ImageView profilePictureCollapsed;
	/*****************************************/
	@BindView(R.id.appointmentHistoryExpanded_textView_name)
	TextView nameExpanded;
	@BindView(R.id.appointmentHistoryCollapsed_textView_name)
	TextView nameCollapsed;
	/*****************************************/
	@BindView(R.id.appointmentHistoryExpanded_textView_time)
	TextView timeExpanded;
	@BindView(R.id.appointmentHistoryCollapsed_textView_time)
	TextView timeCollapsed;
	/*****************************************/
	@BindView(R.id.appointmentHistoryExpanded_RatingBar_rate)
	RatingBar rate;

	/****************************************/
	@BindView(R.id.appointmentHistoryExpanded_textView_feedback)
	TextView feedback;

	/****************************************/
	@OnClick({ R.id.appointmentHistoryExpanded_button_viewProfile, R.id.appointmentHistoryCollapsed_button_viewProfile })
	public void onViewProfileClicked () {

	}

	/****************************************/
	@OnClick(R.id.appointmentHistoryExpanded_button_editReview)
	public void onEditReviewClicked () {

	}

	public MedicalHistoryViewHolder ( View itemView ) {
		super(itemView);
		ButterKnife.bind(this, itemView);
	}

	public void initializeItem ( Context context, AppointmentHistory appointmentHistory ) {
		this.context = context;

		setTime(appointmentHistory.getDate() + "  " + appointmentHistory.getTime());
		setRate(extractRate(appointmentHistory));
		setFeedback(extractFeedback(appointmentHistory));

		new FirebaseListener(this).initSingleValueEventListener(null, FirebaseContracts.PATH_TO_USERS + "/" + extractUID(appointmentHistory), FirebaseContracts.PATH_TO_USERS);
	}

	public void initializePersonalData ( String URL, String name ) {
		if (URL != null)
			setProfilePicture(URL);
		if (name != null)
			setName(name);
	}

	public abstract String extractUID ( AppointmentHistory appointmentHistory );

	public abstract float extractRate ( AppointmentHistory appointmentHistory );

	public abstract String extractFeedback ( AppointmentHistory appointmentHistory );

	private void setFeedback ( String feedbackWords ) {

		feedback.setText(feedbackWords == null || feedbackWords.trim().isEmpty() ? "No feedback is given yet" : feedbackWords);
	}

	private void setRate ( float rating ) {
		rate.setRating(rating);
		rate.setClickable(false);
		rate.setActivated(false);
		rate.setFocusable(false);
	}

	private void setName ( String name ) {
		nameCollapsed.setText(name);
		nameExpanded.setText(name);
	}

	private void setTime ( String time ) {
		timeCollapsed.setText(time);
		timeExpanded.setText(time);
	}

	private void setProfilePicture ( String URL ) {
		Glide.with(context).load(URL).into(profilePictureCollapsed);
		Glide.with(context).load(URL).into(profilePictureExpanded);
	}

	@Override
	public void ValueEventListener ( Object data, DataSnapshot dataSnapshot, String ID ) {

	}

	@Override
	public void SingleValueEventListener ( Object data, DataSnapshot dataSnapshot, String ID ) {
		AccountType accountType = dataSnapshot.child(FirebaseContracts.PATH_TO_USERS_USERUID_ACCOUNTTYPE).getValue(AccountType.class);
		switch (accountType) {
			case DOCTOR:
				Doctor doctor = dataSnapshot.getValue(Doctor.class);
				initializePersonalData(doctor.getProfilePicture().getURL(),doctor.getNames().get(DefaultApplicationLanguageManager.getDefaultLanguage(context)));
		}
	}

	@Override
	public void onFailure ( Object data, Throwable error, String ID ) {
		TastyToast.makeText(context, error.getMessage(), TastyToast.LENGTH_SHORT, TastyToast.ERROR);
	}
}
