/*
 * Copyright (c) $today.year.kareem elsayed aly,no one has the authority to edit,delete,copy any part without my permission
 */

package com.kareem.yladoctor.Models.Interfaces;

/**
 * Created by kareem on 9/11/2016.
 */

public interface newValueListener {
    /**
     *
     * @param data general passed data
     * @param ID id for the succeeded change
     */
    public void onSucceed ( Object data, String ID );

    /**
     *
      * @param data general passed data
     * @param error error that caused the failure
     * @param ID id for the failed task
     */
    public void onFailure ( Object data, Throwable error, String ID );
}
