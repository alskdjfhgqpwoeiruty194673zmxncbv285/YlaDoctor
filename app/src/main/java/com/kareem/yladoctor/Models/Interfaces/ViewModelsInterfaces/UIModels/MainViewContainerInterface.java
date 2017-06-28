package com.kareem.yladoctor.Models.Interfaces.ViewModelsInterfaces.UIModels;

import android.app.Fragment;

import java.util.List;

/**
 * Created by kareem on 6/12/2017 - YlaDoctor.
 * <br></br>
 * description goes here
 *
 * @author kareem
 * @version %I%
 */

public interface MainViewContainerInterface extends ContainerInterface {
	public List<Fragment> getListOfMainFragments ();
}
