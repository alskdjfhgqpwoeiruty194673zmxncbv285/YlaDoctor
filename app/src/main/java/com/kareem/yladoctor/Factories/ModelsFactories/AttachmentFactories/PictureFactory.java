package com.kareem.yladoctor.Factories.ModelsFactories.AttachmentFactories;


import android.graphics.Bitmap;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.UploadTask;
import com.kareem.yladoctor.Models.Interfaces.newValueListener;
import com.kareem.yladoctor.Models.Modules.Attachments.Picture;

import java.io.ByteArrayOutputStream;

/**
 * Created by kareem on 7/7/2017 - YlaDoctor.
 * <br></br>
 * description goes here
 *
 * @author kareem
 * @version %I%
 */

public class PictureFactory extends AttachmentFactory {
	private final String PICTURE = "picture";
//	Picture picture;

	public PictureFactory ( newValueListener listener ) {
		super(listener);
	}

	public boolean askUpdate ( String pathToUpload, final String pathToWrite, final Picture value, final String path ) {

		if (value == null)
			return false;
		ByteArrayOutputStream BAOS = new ByteArrayOutputStream();
		value.getImage().compress(Bitmap.CompressFormat.JPEG, 100, BAOS);
		FirebaseStorage.getInstance().getReference().child(pathToUpload).putBytes(BAOS.toByteArray()).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
			@Override
			public void onSuccess ( UploadTask.TaskSnapshot taskSnapshot ) {
				if (taskSnapshot.getDownloadUrl() != null) {
					value.setURL(taskSnapshot.getDownloadUrl().toString());
					PictureFactory.super.askUpdate(pathToWrite, value, path);
				} else
					listener.onFailure(value, taskSnapshot.getError(), PICTURE);
			}
		}).addOnFailureListener(new OnFailureListener() {
			@Override
			public void onFailure ( @NonNull Exception e ) {
				listener.onFailure(value, e, PICTURE);
			}
		});
//		new SetValueListener(this).setNewValue(value, value, path, PICTURE);
		return true;
	}
}
