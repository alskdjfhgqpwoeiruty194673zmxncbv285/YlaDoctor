package com.kareem.yladoctor.Models.Exceptions;

/**
 * Created by kareem on 11/9/2016 - MyLibraries.
 * <br></br>
 * Exception that gets thrown by FirebaseNodeListener
 *
 * @author kareem
 * @version %I%
 */

public class DatabaseErrorNodeException extends RuntimeException {
	/**
	 * thrown error if any method in FirebaseNodeListener class failed to load the proper data
	 *
	 * @param msg the Unique msg corresponding to the cause of the error
	 * @since 1.0
	 */
	public DatabaseErrorNodeException ( String msg ) {
		super(msg);
	}
}