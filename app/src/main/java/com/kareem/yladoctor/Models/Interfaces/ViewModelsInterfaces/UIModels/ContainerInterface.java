package com.kareem.yladoctor.Models.Interfaces.ViewModelsInterfaces.UIModels;

import android.app.FragmentManager;
import android.content.Context;
import android.widget.FrameLayout;

/**
 * Created by kareem on 6/12/2017 - YlaDoctor.
 * <br></br>
 * description goes here
 *
 * @author kareem
 * @version %I%
 */

public interface ContainerInterface {
	Context getActivityContext ();
	FrameLayout getFragmentContainer();
	int getFragmentContainerID();
	FragmentManager getFragmentManager();
}
