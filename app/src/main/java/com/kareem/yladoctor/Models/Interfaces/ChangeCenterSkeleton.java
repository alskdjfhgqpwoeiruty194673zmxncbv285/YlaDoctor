/*
 * Copyright (c) 2016. KAREEM ElSAYED ALY  ALL RIGHTS RESERVED.
 */

package com.kareem.yladoctor.Models.Interfaces;

/**
 * Created by kareem on 12/23/2016 - HealMe.
 * <br></br>
 * used to register an observer to a certain property
 *
 * @author kareem
 * @version %I%
 */

public interface ChangeCenterSkeleton {
    /**
     *
     * @param observer add observer to the list of change listeners
     */
    public void register ( ChangeCenterObserver observer );

    /**
     *
     * @param observer remove added observer from the list of change listeners
     */
    public void unregister ( ChangeCenterObserver observer );

    /**
     * is called when a change is requested
     * @param data is the data changed
     * @param ID is the id of the changed data
     */
    public void notifyObservers ( Object data, String ID );

}
