package com.kareem.yladoctor.ViewModels.UIModels.fragments.general;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.kareem.yladoctor.Factories.ModelsFactories.Users.DoctorFactory;
import com.kareem.yladoctor.Models.Interfaces.ViewModelsInterfaces.UIModels.GeneralDoctorMainScreenOneInterface;
import com.kareem.yladoctor.Models.Interfaces.newValueListener;
import com.kareem.yladoctor.R;
import com.kareem.yladoctor.ViewModels.Engine.LogInHandler.GeneralLoginHandler;
import com.kareem.yladoctor.Views.Activites.general.EntryContainer;
import com.kareem.yladoctor.Views.Activites.general.MainPage;
import com.kareem.yladoctor.Views.Fragments.DoctorViewDoctorProfile;
import com.sdsmdg.tastytoast.TastyToast;
import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;

/**
 * Created by kareem on 7/4/2017 - YlaDoctor.
 * <br></br>
 * description goes here
 *
 * @author kareem
 * @version %I%
 */

public class DoctorMainScreenOneViewModel extends DoctorProfileViewModel implements View.OnClickListener {
	private final String Home = "Home";
	private final String Settings = "Settings";
	private final String SignOut = "SignOut";

	private GeneralDoctorMainScreenOneInterface doctorMainScreenOneInterface;
	private ResideMenu resideMenu;

	public DoctorMainScreenOneViewModel ( GeneralDoctorMainScreenOneInterface doctorMainScreenOneInterface ) {
		super(doctorMainScreenOneInterface);
		this.doctorMainScreenOneInterface = doctorMainScreenOneInterface;

		resideMenu = new ResideMenu(doctorMainScreenOneInterface.getMyContext());
		resideMenu.setMinimumHeight(doctorMainScreenOneInterface.getMainView().getHeight() + 100);
		resideMenu.setBackground(R.mipmap.logo);
		resideMenu.attachToActivity(doctorMainScreenOneInterface.getParentActivity());

		// create menu items;
		String titles[] = { Home, Settings, SignOut };
		int icon[] = {
				  R.mipmap.home
				  , R.mipmap.settings
				  , R.mipmap.exit
		};

		for (int i = 0; i < titles.length; i++) {
			ResideMenuItem item = new ResideMenuItem(doctorMainScreenOneInterface.getParentActivity(), icon[i], titles[i]);
			item.setTag(titles[i]);
			item.setOnClickListener(this);
			resideMenu.addMenuItem(item, ResideMenu.DIRECTION_LEFT); // or  ResideMenu.DIRECTION_RIGHT

		}

		resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_RIGHT);
		resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_LEFT);
	}

	public void resetEditTexts () {
		EditText[] editTextArray = new EditText[]{ doctorMainScreenOneInterface.getBusinessLocationEditText(), doctorMainScreenOneInterface.getExperienceEditText(), doctorMainScreenOneInterface.getPriceEditText(), doctorMainScreenOneInterface.getPriceEditText(), doctorMainScreenOneInterface.getMobileNumberEditText() };
		for (EditText editText :
				  editTextArray) {
			resetEditText(editText);
		}
	}

	private void resetEditText ( EditText editText ) {
		editText.setEnabled(false);
		editText.clearFocus();
	}

	private void resetButton ( TextView textView ) {
		textView.setText("change");
		textView.setTag(false);
	}

	public void resetButtons () {
		for (TextView changeTextView :
				  doctorMainScreenOneInterface.changeListViews()) {
			resetButton(changeTextView);
		}

	}

	public void activateChangeDataEditText ( EditText editText, TextView changeTextView ) {
		editText.setEnabled(true);
		editText.requestFocus();
		changeTextView.setTag(true);
		changeTextView.setText("Save");

	}

	public void saveData ( String path, Object data) {
//// TODO: 7/6/2017 save data by sending it to factories
		if (
				  new DoctorFactory(doctorMainScreenOneInterface.getDoctor(), new newValueListener() {
					  @Override
					  public void onSucceed ( Object data, String ID ) {
						  TastyToast.makeText(doctorMainScreenOneInterface.getMyContext(), "Change succeeded", TastyToast.LENGTH_SHORT, TastyToast.SUCCESS);
					  }

					  @Override
					  public void onFailure ( Object data, Throwable error, String ID ) {
						  TastyToast.makeText(doctorMainScreenOneInterface.getMyContext(), "Change Failed", TastyToast.LENGTH_SHORT, TastyToast.ERROR);
					  }
				  }).askUpdate(path, data)) {
			resetEditTexts();
			resetButtons();
		}
	}

	public void onViewAsPatientClicked () {
		doctorMainScreenOneInterface.getMyContext().startActivity(new Intent(doctorMainScreenOneInterface.getMyContext(), DoctorViewDoctorProfile.class));
	}

	public void onMenuClicked () {
		resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
	}

	public void onEditScheduleClicked () {
		// TODO: 7/4/2017 add this edit schedule functionality
	}

	@Override
	public void onClick ( View v ) {
		ResideMenuItem resideMenuItem = (ResideMenuItem) v;
		switch (resideMenuItem.getTag().toString()) {
			case Home:
				((MainPage) doctorMainScreenOneInterface.getParentActivity()).convertViewToHomeFramgents();
				break;
			case Settings:
				break;
			case SignOut:
				GeneralLoginHandler.getUserSignOut();
				doctorMainScreenOneInterface.getParentActivity().startActivity(new Intent(doctorMainScreenOneInterface.getParentActivity(), EntryContainer.class));
				doctorMainScreenOneInterface.getParentActivity().finish();
				break;
		}
	}
}
