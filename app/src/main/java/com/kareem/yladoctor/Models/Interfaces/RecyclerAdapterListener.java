/*
 * Copyright (c) $today.year.kareem elsayed aly,no one has the authority to edit,delete,copy any part without my permission
 */

package com.kareem.yladoctor.Models.Interfaces;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

/**
 * Created by kareem on 9/11/2016.
 */

public interface RecyclerAdapterListener<T, E extends RecyclerView.ViewHolder> {
	/**
	 * @param data       general data passed
	 * @param viewHolder is the viewHolder for the item-view-
	 * @param holder     is the data itself
	 * @param position   position of the item
	 * @param ID         id of the task
	 */
	public void recyclerAdapterListener ( Object data, E viewHolder, T holder, int position, String ID );

	/**
	 * @return context from the activity
	 */
	public Context getMyContext ();

}