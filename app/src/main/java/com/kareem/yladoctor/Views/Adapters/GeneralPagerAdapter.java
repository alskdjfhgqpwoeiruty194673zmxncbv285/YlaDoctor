package com.kareem.yladoctor.Views.Adapters;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by kareem on 6/2/2017 - YlaDoctor.
 * <br></br>
 * description goes here
 *
 * @author kareem
 * @version %I%
 */

public class GeneralPagerAdapter extends FragmentPagerAdapter {
	private List<Fragment> fragments;

	public GeneralPagerAdapter ( FragmentManager fm, List<Fragment> fragments ) {
		super(fm);
		this.fragments = fragments;
	}

	@Override
	public Fragment getItem ( int position ) {
		return fragments.get(position);
	}

	@Override
	public int getCount () {
		return fragments.size();
	}
}
