package com.kareem.yladoctor.Models.Interfaces.ViewModelsInterfaces.UIModels;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.widget.FrameLayout;

import java.util.List;

/**
 * Created by kareem on 6/2/2017 - YlaDoctor.
 * <br></br>
 * description goes here
 *
 * @author kareem
 * @version %I%
 */

public interface EntryContainerInterface extends ContainerInterface{
	List<Fragment> getListOfSplashScreenFragments();
	Fragment getLoginFragment();
	void callNextSplashScreen();
	void callPreviousSplashScreen();
	void callSkipSplashScreen();
}
