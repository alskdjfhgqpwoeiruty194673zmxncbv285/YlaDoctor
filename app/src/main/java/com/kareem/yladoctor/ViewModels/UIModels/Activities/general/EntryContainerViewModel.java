package com.kareem.yladoctor.ViewModels.UIModels.Activities.general;

import android.app.FragmentTransaction;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.kareem.yladoctor.Models.Contracts.ContractValues;
import com.kareem.yladoctor.Models.Interfaces.ViewModelsInterfaces.UIModels.EntryContainerInterface;
import com.kareem.yladoctor.Views.Adapters.GeneralPagerAdapter;

/**
 * Created by kareem on 6/2/2017 - YlaDoctor.
 * <br></br>
 * description goes here
 *
 * @author kareem
 * @version %I%
 */

public class EntryContainerViewModel {
	private EntryContainerInterface entryContainerInterface;
	private int currentSplashScreenPosition = 0;
	private ViewPager splashViewer;

	public EntryContainerViewModel ( EntryContainerInterface entryContainerInterface ) {
		this.entryContainerInterface = entryContainerInterface;
		startTransactions();
	}

	private void startTransactions () {
		clearViews();
		if (callSplashScreen())
			createSplashScreen();
		else
			createLoginScreen();
	}

	private void clearViews () {
		entryContainerInterface.getFragmentContainer().removeAllViews();
	}

	private void createSplashScreen () {
		splashViewer = new ViewPager(entryContainerInterface.getActivityContext());
		splashViewer.setAdapter(new GeneralPagerAdapter(entryContainerInterface.getFragmentManager(), entryContainerInterface.getListOfSplashScreenFragments()));
		splashViewer.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
		splashViewer.setId(View.generateViewId());
		entryContainerInterface.getFragmentContainer().addView(splashViewer);
		splashViewer.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrolled ( int position, float positionOffset, int positionOffsetPixels ) {

			}

			@Override
			public void onPageSelected ( int position ) {
				currentSplashScreenPosition = position;
			}

			@Override
			public void onPageScrollStateChanged ( int state ) {

			}
		});
	}

	private void createLoginScreen () {
		FragmentTransaction fragmentTransaction=entryContainerInterface.getFragmentManager().beginTransaction();
		//noinspection ResourceType
//		fragmentTransaction.setCustomAnimations(R.anim.test,R.anim.test,R.anim.slide_in_right,R.anim.slide_out_left);
		fragmentTransaction.add(entryContainerInterface.getFragmentContainerID(), entryContainerInterface.getLoginFragment()).commit();
	}

	private boolean callSplashScreen () {
		return entryContainerInterface.getActivityContext().getSharedPreferences(ContractValues.FILE_SETTINGS, Context.MODE_PRIVATE).getBoolean(ContractValues.CALL_SPLASHSCREEN, true);
	}

	public void destroyOrSkipOrFinishSplashScreen () {
		writeSplashScreenBooleanOnSharedPreferenceFile();
		startTransactions();
	}

	private void writeSplashScreenBooleanOnSharedPreferenceFile () {
		entryContainerInterface.getActivityContext().getSharedPreferences(ContractValues.FILE_SETTINGS, Context.MODE_PRIVATE).edit().putBoolean(ContractValues.CALL_SPLASHSCREEN, false).apply();
	}

	public void getNextPage () {
		setCurrentSplashScreenPosition(1);
	}

	public void getPreviousPage () {
		setCurrentSplashScreenPosition(- 1);
	}

	private void setCurrentSplashScreenPosition ( int number ) {
		currentSplashScreenPosition += number;
		splashViewer.setCurrentItem(currentSplashScreenPosition);
	}
}
