package com.kareem.yladoctor.Views.Activites.general;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

import com.kareem.yladoctor.Models.Interfaces.ViewModelsInterfaces.UIModels.ContainerInterface;
import com.kareem.yladoctor.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kareem on 6/12/2017 - YlaDoctor.
 * <br></br>
 * description goes here
 *
 * @author kareem
 * @version %I%
 */

public abstract class Container extends AppCompatActivity implements ContainerInterface{
	@BindView(R.id.generalActivityContainer_frameLayout_fragmentsContainer)
	FrameLayout fragmentContainer;

	@Override
	protected void onCreate ( @Nullable Bundle savedInstanceState ) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.general_activity_container);
		ButterKnife.bind(this);
		initializeVariables();
	}
public abstract void initializeVariables ();
	@Override
	public Context getActivityContext () {
		return Container.this;
	}

	@Override
	public FrameLayout getFragmentContainer () {
		return fragmentContainer;
	}

	@Override
	public int getFragmentContainerID () {
		return R.id.generalActivityContainer_frameLayout_fragmentsContainer;
	}
}
