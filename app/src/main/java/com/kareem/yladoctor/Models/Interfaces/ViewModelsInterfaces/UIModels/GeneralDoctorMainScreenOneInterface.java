package com.kareem.yladoctor.Models.Interfaces.ViewModelsInterfaces.UIModels;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public interface GeneralDoctorMainScreenOneInterface extends GeneralDoctorProfileInterface {

	public View getActivatedDoctorView ();

	public TextView getBusinessLocationLanguageTextView ();

	public View getMainView ();

	public Activity getParentActivity ();
	public List<TextView> changeListViews();
}