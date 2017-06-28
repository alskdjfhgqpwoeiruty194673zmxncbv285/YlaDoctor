/*
 * Copyright (c) $today.year.kareem elsayed aly,no one has the authority to edit,delete,copy any part without my permission
 */

package com.kareem.yladoctor.Models.Interfaces;

import com.google.firebase.database.DataSnapshot;

/**
 * Created by kareem on 9/10/2016 - MyLibraries.
 <br></br>
 An Interface that must be implemented in a class to use FirebaseNodeListener
 *@author kareem
 *@version %I%
 */

public interface FirebaseListeners {
    /**
     * method that gets called when initValueEventListener() is called from FirebaseNodeListener Class
     * @param data data you have passed will be returned to you
     * @param dataSnapshot data downloaded from the Node
     * @param ID the Unique ID which is related to your certain Call
     * @see com.kareem.yladoctor.Models.Helpers.FirebaseListener
     * @since 1.0
     */
    public void ValueEventListener ( Object data, DataSnapshot dataSnapshot, String ID );
    /**
     * method that gets called when initSingleValueEventListener() is called from FirebaseNodeListener Class
     * @param data data you have passed will be returned to you
     * @param dataSnapshot data downloaded from the Node
     * @param ID the Unique ID which is related to your certain Call
     * @see com.kareem.yladoctor.Models.Helpers.FirebaseListener
     * @since 1.0
     */
    public void SingleValueEventListener ( Object data, DataSnapshot dataSnapshot, String ID );
    /**
     * method that gets called when any method from FirebaseNodeListener Class fails loading the required Data
     * @param data data you have passed will be returned to you
     * @param error error caused the listener to failure
     * @param ID the Unique ID which is related to your certain Call
     * @see com.kareem.yladoctor.Models.Helpers.FirebaseListener
     * @since 1.0
     */
    public void onFailure ( Object data, Throwable error, String ID );
}
