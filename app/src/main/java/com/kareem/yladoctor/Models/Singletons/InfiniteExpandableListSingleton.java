package com.kareem.yladoctor.Models.Singletons;

import com.kareem.infiniteexpandablelist.InfiniteExpandableList;
import com.kareem.infiniteexpandablelist.InfiniteExpandableListInterface;

/**
 * Created by kareem on 7/2/2017 - YlaDoctor.
 * <br></br>
 * description goes here
 *
 * @author kareem
 * @version %I%
 */

public class InfiniteExpandableListSingleton extends InfiniteExpandableList {

	private static InfiniteExpandableListSingleton ourInstance;

	public static InfiniteExpandableListSingleton getInstance ( InfiniteExpandableListInterface infiniteExpandableListInterface ) {
		if (ourInstance == null) {
//			assert infiniteExpandableListInterface != null;
			ourInstance = new InfiniteExpandableListSingleton(infiniteExpandableListInterface);
			ourInstance.setPixelsPadding(20,20,20,20);
		}
		return ourInstance;
	}

	private InfiniteExpandableListSingleton ( InfiniteExpandableListInterface parent ) {
		super(parent);
	}

}
