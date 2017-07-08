/*
 * Copyright (c) 2016. KAREEM ElSAYED ALY  ALL RIGHTS RESERVED.
 */

package com.kareem.yladoctor.Models.Modules.Attachments;

import android.graphics.Bitmap;

import com.google.firebase.database.Exclude;
import com.kareem.yladoctor.Models.Enums.AttachmentType;

/**
 * Created by kareem on 11/10/2016 - HealMe.
 * <br></br>
 * image module
 *
 * @author kareem
 * @version %I%
 */

public class Picture extends Attachment {
	// img related to this url and Attachment
	private Bitmap image;

	/**
	 * create picture using url
	 *
	 * @param URL            url of this online picture
	 * @param attachmentName name of this picture
	 * @param attachmentSize size of the picture
	 */
	public Picture ( String URL, String attachmentName, String attachmentSize ) {
		super(URL, AttachmentType.IMAGE, attachmentName, attachmentSize);
	}

	/**
	 * create picture using offline image and online one
	 *
	 * @param URL            url of the online image
	 * @param attachmentName name of that image
	 * @param attachmentSize size of that image
	 * @param image          offline version of that url
	 */
	public Picture ( String URL, String attachmentName, String attachmentSize, Bitmap image ) {
		super(URL, AttachmentType.IMAGE, attachmentName, attachmentSize);
		this.image = image;
	}

	public Picture(String URL){
		super(URL,AttachmentType.IMAGE);
	}

	/**
	 * create picture using offline bitmap
	 *
	 * @param attachmentName name of the picture
	 * @param attachmentSize size of the picture
	 * @param image          offline bitmap of the picture
	 */
	public Picture ( String attachmentName, String attachmentSize, Bitmap image ) {
		super(null, AttachmentType.IMAGE, attachmentName, attachmentSize);
		this.image = image;
	}

	/**
	 * create new picture using offline bitmap image
	 *
	 * @param image img of the offline picture
	 */
	public Picture ( Bitmap image ) {
		this.image = image;
	}

	/**
	 * empty constructor for firebase
	 */
	public Picture () {
	}



	@Exclude
	public void setImage ( Bitmap image ) {
		image = image;
	}

	@Exclude
	public Bitmap getImage () {
		return image;
	}


}
