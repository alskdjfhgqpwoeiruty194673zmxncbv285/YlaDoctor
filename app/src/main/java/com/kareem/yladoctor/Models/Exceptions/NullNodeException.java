package com.kareem.yladoctor.Models.Exceptions;

/**
 * Created by kareem on 11/9/2016.
 */

public class NullNodeException extends NullPointerException {
	//error that will be thrown when a null node is requested
	public NullNodeException () {
		super("Assigned Path is pointing to null Node");
	}

	public NullNodeException ( String msg ) {
		super(msg);
	}
}
