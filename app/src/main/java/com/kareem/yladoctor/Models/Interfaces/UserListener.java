/*
 * Copyright (c) $today.year.kareem elsayed aly,no one has the authority to edit,delete,copy any part without my permission
 */

package com.kareem.yladoctor.Models.Interfaces;

import android.app.Activity;

/**
 * Created by kareem on 9/11/2016.
 */

public interface UserListener {
    /**
     *
     * @return reference to the activity
     */
    public Activity getMyActivity ();

    /**
     *
     * @param data general passed data
     * @param ID id of a specific task
     */
    public void onSucceed ( Object data, int ID );

    /**
     *
     * @param data general passed data
     * @param ID id of a specific task
     */
    public void onFailure ( Object data, int ID );
}
