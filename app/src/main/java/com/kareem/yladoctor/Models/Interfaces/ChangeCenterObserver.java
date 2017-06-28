/*
 * Copyright (c) 2016. KAREEM ElSAYED ALY  ALL RIGHTS RESERVED.
 */

package com.kareem.yladoctor.Models.Interfaces;

/**
 * Created by kareem on 12/23/2016 - HealMe.
 * <br></br>
 * get notified when a change happens
 *
 * @author kareem
 * @version %I%
 */

public interface ChangeCenterObserver {
    /**
     * update method is called whenever listening data has changed
     * @param data data passed from the change
     * @param ID id for the change
     */
    public void update ( Object data, String ID );
}
