package com.kareem.yladoctor.Views.Activites.general;

import android.app.Fragment;
import android.content.Intent;

import com.kareem.yladoctor.Models.Interfaces.ViewModelsInterfaces.UIModels.EntryContainerInterface;
import com.kareem.yladoctor.ViewModels.UIModels.Activities.general.EntryContainerViewModel;
import com.kareem.yladoctor.Views.Fragments.LoginFragment;
import com.kareem.yladoctor.Views.Fragments.SplashScreensFragments.SplashScreenOneFragment;
import com.kareem.yladoctor.Views.Fragments.SplashScreensFragments.SplashScreenSecondFragment;
import com.kareem.yladoctor.Views.Fragments.SplashScreensFragments.SplashScreenThirdFragment;
import com.kareem.yladoctor.Views.Fragments.SplashScreensFragments.SplashScreenZeroFragment;

import java.util.Arrays;
import java.util.List;

/**
 * Created by kareem on 6/2/2017 - YlaDoctor.
 * <br></br>
 * description goes here
 *
 * @author kareem
 * @version %I%
 */

public class EntryContainer extends Container implements EntryContainerInterface {
	LoginFragment loginFragment;
	EntryContainerViewModel entryContainerViewModel;

	@Override
	public void initializeVariables () {
		entryContainerViewModel = new EntryContainerViewModel(this);
	}

	@Override
	public List<Fragment> getListOfSplashScreenFragments () {
		return Arrays.asList(new SplashScreenZeroFragment(), new SplashScreenOneFragment(), new SplashScreenSecondFragment(), new SplashScreenThirdFragment());
	}

	@Override
	public Fragment getLoginFragment () {
		if (loginFragment==null)
		loginFragment= new LoginFragment();
		return loginFragment;
	}
	@Override
	public void callNextSplashScreen () {
		entryContainerViewModel.getNextPage();
	}

	@Override
	public void callPreviousSplashScreen () {
		entryContainerViewModel.getPreviousPage();
	}

	@Override
	public void callSkipSplashScreen () {
		entryContainerViewModel.destroyOrSkipOrFinishSplashScreen();
	}
	@Override
	public void onActivityResult ( int requestCode, int resultCode, Intent data ) {
		getLoginFragment().onActivityResult(requestCode, resultCode, data);
	}
}
