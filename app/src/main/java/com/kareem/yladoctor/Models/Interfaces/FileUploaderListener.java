package com.kareem.yladoctor.Models.Interfaces;

import com.google.firebase.storage.UploadTask;

/**
 * Created by kareem on 5/29/2017 - Akshef.
 * <br></br>
 * to be implemented whenever deal with files uploading using helper class
 *
 * @author kareem
 * @version %I%
 */

public interface FileUploaderListener {
	/**
	 * indicates that uploading task is complete and was successful
	 *
	 * @param data         general data that is passed as is
	 * @param taskSnapshot returned taskSnapShot from uploading task
	 * @param ID           id for the uploading task
	 */
	public void onFileUploaded ( Object data, UploadTask.TaskSnapshot taskSnapshot, String ID );

	/**
	 * indicates that uploading task failed
	 *
	 * @param data  general data that is passed as is
	 * @param error error describing the cause of the failure
	 * @param ID    id for the uploading task
	 */
	public void onUploadFailed ( Object data, Throwable error, String ID );
}
