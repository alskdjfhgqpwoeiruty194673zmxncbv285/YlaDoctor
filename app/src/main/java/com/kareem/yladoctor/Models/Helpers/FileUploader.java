package com.kareem.yladoctor.Models.Helpers;

import android.graphics.Bitmap;
import android.net.Uri;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.UploadTask;
import com.kareem.yladoctor.Models.Interfaces.FileUploaderListener;

import java.io.ByteArrayOutputStream;
import java.io.File;

/**
 * Created by kareem on 5/29/2017 - Akshef.
 * <br></br>
 * upload files to firebase storage
 *
 * @author kareem
 * @version %I%
 */

public class FileUploader {
	private FileUploaderListener fileUploaderListener;

	/**
	 * constructor
	 *
	 * @param fileUploaderListener keep reference of it
	 * @see com.kareem.yladoctor.Models.Interfaces.FileUploaderListener
	 */
	public FileUploader ( FileUploaderListener fileUploaderListener ) {
		this.fileUploaderListener = fileUploaderListener;
	}

	/**
	 * control the upload method used to upload an object
	 *
	 * @param generalData  general data to be passed as is
	 * @param data         data that will be uploaded
	 * @param pathToUpload path to upload data on it
	 * @param ID           id for the upload task
	 */
	public void uploadFile ( final Object generalData, Object data, String pathToUpload, final String ID ) {
		if (data instanceof Uri)
			uploadFile(generalData, ((Uri) data), pathToUpload, ID);
		if (data instanceof File)
			uploadFile(generalData, Uri.fromFile((File) data), pathToUpload, ID);
		if (data instanceof Bitmap)
			uploadFile(generalData, ((Bitmap) data), pathToUpload, ID);
	}

	/**
	 * upload bitmap to database
	 *
	 * @param generalData  general data to be passed as is
	 * @param data         image that will be uploaded
	 * @param pathToUpload path to upload data on it
	 * @param ID           id for the upload task
	 */
	private void uploadFile ( final Object generalData, Bitmap data, String pathToUpload, final String ID ) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		data.compress(Bitmap.CompressFormat.JPEG, 100, baos);
		byte[] dataArray = baos.toByteArray();
		FirebaseStorage.getInstance().getReference().child(pathToUpload).putBytes(dataArray).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
			@Override
			public void onSuccess ( UploadTask.TaskSnapshot taskSnapshot ) {
				fileUploaderListener.onFileUploaded(generalData, taskSnapshot, ID);
			}
		}).addOnFailureListener(new OnFailureListener() {
			@Override
			public void onFailure ( @NonNull Exception e ) {
				fileUploaderListener.onUploadFailed(generalData, e, ID);
			}
		});

	}

	/**
	 * upload uri refers to offline file to database
	 *
	 * @param generalData  general data to be passed as is
	 * @param data         offline url for the file that will be uploaded
	 * @param pathToUpload path to upload data on it
	 * @param ID           id for the upload task
	 */
	private void uploadFile ( final Object generalData, Uri data, String pathToUpload, final String ID ) {
		FirebaseStorage.getInstance().getReference().child(pathToUpload).putFile(data).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
			@Override
			public void onSuccess ( UploadTask.TaskSnapshot taskSnapshot ) {
				fileUploaderListener.onFileUploaded(generalData, taskSnapshot, ID);
			}
		}).addOnFailureListener(new OnFailureListener() {
			@Override
			public void onFailure ( @NonNull Exception e ) {
				fileUploaderListener.onUploadFailed(generalData, e, ID);
			}
		});

	}
}
