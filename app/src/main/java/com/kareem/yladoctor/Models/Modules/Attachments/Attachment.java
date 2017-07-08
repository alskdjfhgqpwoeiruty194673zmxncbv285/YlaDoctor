package com.kareem.yladoctor.Models.Modules.Attachments;


import com.kareem.yladoctor.Models.Enums.AttachmentType;

/**
 * Created by kareem on 5/29/2017 - Akshef.
 * <br></br>
 * general class for any attached object
 *
 * @author kareem
 * @version %I%
 */

public class Attachment {
	//url for that Attachment
	private String URL;
	//attachment type whether an image, file,etc
	private AttachmentType attachmentType;
	//name of the attachment
	private String attachmentName;
	//size of that attachment
	private String attachmentSize;

	/**
	 * constructor to create new Attachment
	 *
	 * @param URL            url of the online Attachment
	 * @param attachmentType type of that Attachment
	 * @param attachmentName name of that Attachment
	 * @param attachmentSize size of the Attachment
	 */
	public Attachment ( String URL, AttachmentType attachmentType, String attachmentName, String attachmentSize ) {
		this.URL = URL;
		this.attachmentType = attachmentType;
		this.attachmentName = attachmentName;
		this.attachmentSize = attachmentSize;
	}

	/**
	 * constructor to create new Attachment
	 *
	 * @param URL            url of the online Attachment
	 * @param attachmentType type of that Attachment
	 */
	public Attachment ( String URL, AttachmentType attachmentType ) {
		this.URL = URL;
		this.attachmentType = attachmentType;
	}

	/**
	 * empty constructor for firebase
	 */
	public Attachment () {
	}

	public AttachmentType getAttachmentType () {
		return attachmentType;
	}

	public void setAttachmentType ( AttachmentType attachmentType ) {
		this.attachmentType = attachmentType;
	}

	public String getAttachmentName () {
		return attachmentName;
	}

	public void setAttachmentName ( String attachmentName ) {
		this.attachmentName = attachmentName;
	}

	public String getAttachmentSize () {
		return attachmentSize;
	}

	public void setAttachmentSize ( String attachmentSize ) {
		this.attachmentSize = attachmentSize;
	}

	public String getURL () {
		return URL;
	}

	public void setURL ( String URL ) {
		this.URL = URL;
	}
}
