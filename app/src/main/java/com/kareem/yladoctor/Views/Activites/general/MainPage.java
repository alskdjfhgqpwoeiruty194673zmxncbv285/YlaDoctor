/*
 * Copyright (c) 2017. KAREEM ElSAYED ALY  ALL RIGHTS RESERVED.
 */

package com.kareem.yladoctor.Views.Activites.general;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.kareem.yladoctor.MainApplication;
import com.kareem.yladoctor.R;
import com.kareem.yladoctor.Views.Adapters.GeneralPagerAdapter;
import com.kareem.yladoctor.Views.Fragments.MainScreensFragments.DoctorMainScreenFragments.DoctorMainScreenOne;
import com.kareem.yladoctor.Views.Fragments.MainScreensFragments.DoctorMainScreenFragments.DoctorMainScreenThree;
import com.kareem.yladoctor.Views.Fragments.MainScreensFragments.DoctorMainScreenFragments.DoctorMainScreenTwo;
import com.kareem.yladoctor.Views.Fragments.MainScreensFragments.PatientMainScreenFramgents.PatientMainScreenOne;
import com.kareem.yladoctor.Views.Fragments.MainScreensFragments.PatientMainScreenFramgents.PatientMainScreenThree;
import com.kareem.yladoctor.Views.Fragments.MainScreensFragments.PatientMainScreenFramgents.PatientMainScreenTwo;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kareem on 5/22/2017 - HealMe.
 * <br></br>
 * description goes here
 *
 * @author kareem
 * @version %I%
 */

public class MainPage extends AppCompatActivity implements View.OnClickListener {
	//	private static final int PositionTagKey = 0;
	@BindView(R.id.newGeneralMainPage_viewPager_pages)
	ViewPager viewPager;
	@BindView(R.id.newGeneralMainPage_linearLayout_dotsHolder)
	LinearLayout dots;
	ArrayList<ImageView> imageViews = new ArrayList<>();

	@Override
	protected void onCreate ( @Nullable Bundle savedInstanceState ) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.general_mainpage);
		ButterKnife.bind(MainPage.this);
		List<Fragment> listOfData = new ArrayList<>();
		switch (((MainApplication) this.getApplication()).getUser().getAccountType()) {
			case PATIENT:
				listOfData.add(new PatientMainScreenTwo());
				listOfData.add(new PatientMainScreenOne() {
					@Override
					public ViewPager getMyViewPager () {
						return viewPager;
					}

					@Override
					public int getHistoryFragmentPosition () {
						return 2;
					}

					@Override
					public int getRecordsFragmentPosition () {
						return 0;
					}
				});
				listOfData.add(new PatientMainScreenThree());
				break;
			case DOCTOR:
				listOfData.add(new DoctorMainScreenTwo());
				listOfData.add(new DoctorMainScreenOne());
				listOfData.add(new DoctorMainScreenThree());
				break;
		}
		//todo add list of fragments to listOfData
		initDots(listOfData.size());
		viewPager.setAdapter(new GeneralPagerAdapter(getFragmentManager(), listOfData));
		viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			@Override
			public void onPageScrolled ( int position, float positionOffset, int positionOffsetPixels ) {

			}

			@Override
			public void onPageSelected ( int position ) {
				setActiveDot(position);
			}

			@Override
			public void onPageScrollStateChanged ( int state ) {

			}
		});
		viewPager.setCurrentItem(1);
	}

	private void initDots ( int length ) {
		for (int i = 0; i < length; i++) {
			ImageView img = new ImageView(this);
			img.setImageResource(R.mipmap.inactivedot);
			float densityValue = getResources().getDisplayMetrics().density;
			img.setLayoutParams(new LinearLayout.LayoutParams((int) (densityValue * 10 + 0.5f), (int) (densityValue * 10 + 0.5f)));
			img.setTag(i);
			img.setOnClickListener(this);
			img.setMaxHeight(10);
			img.setId(i);
			img.setMaxWidth(10);
			int paddedValue = (int) (densityValue * 0.9 + 0.5f);
			img.setPadding(paddedValue, paddedValue, paddedValue, paddedValue);
			dots.addView(img);
			imageViews.add(img);
		}
	}

	private void setActiveDot ( int position ) {
		resetCurrentActiveDot();
		imageViews.get(position).setImageResource(R.mipmap.activedot);
	}

	private void resetCurrentActiveDot () {
		for (ImageView dot : imageViews) {
			dot.setImageResource(R.mipmap.inactivedot);
		}
	}

	@Override
	public void onClick ( View v ) {
		viewPager.setCurrentItem((Integer) v.getTag());
	}

	public void convertViewToHomeFramgents () {

	}

	public void convertViewToSettingsFramgents () {

	}
}
