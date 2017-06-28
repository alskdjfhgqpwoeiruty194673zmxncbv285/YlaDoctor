package com.kareem.yladoctor.Views.Fragments.SplashScreensFragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kareem.yladoctor.R;
import com.kareem.yladoctor.Views.Activites.general.EntryContainer;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by kareem on 6/2/2017 - YlaDoctor.
 * <br></br>
 * description goes here
 *
 * @author kareem
 * @version %I%
 */

public class SplashScreenThirdFragment extends Fragment {
	@OnClick(R.id.generalFragmentSplashScreenThree_button_DoneSplashScreen)
	public void OnDoneSplashScreen () {
		skipSplash();
	}

	@OnClick(R.id.generalFragmentSplashScreenThree_button_previousSplashScreen)
	public void OnPreviousSplashScreen () {
		previousSplashScreen();
	}

	@Nullable
	@Override
	public View onCreateView ( LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState ) {
		return inflater.inflate(R.layout.general_fragment_splashscreen_three, container, false);
	}

	@Override
	public void onViewCreated ( View view, @Nullable Bundle savedInstanceState ) {
		super.onViewCreated(view, savedInstanceState);
		ButterKnife.bind(this, view);
	}

	public void skipSplash () {
		Activity a = getActivity();
		if (a instanceof EntryContainer)
			((EntryContainer) a).callSkipSplashScreen();

	}

	public void previousSplashScreen(){
		Activity a = getActivity();
		if(a instanceof EntryContainer)
			((EntryContainer) a).callPreviousSplashScreen();
	}
}
