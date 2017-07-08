package com.kareem.yladoctor.Factories.ModelsFactories.AttachmentFactories;

import com.kareem.yladoctor.Models.Helpers.SetValueListener;
import com.kareem.yladoctor.Models.Interfaces.newValueListener;
import com.kareem.yladoctor.Models.Modules.Attachments.Attachment;

/**
 * Created by kareem on 7/7/2017 - YlaDoctor.
 * <br></br>
 * description goes here
 *
 * @author kareem
 * @version %I%
 */

public class AttachmentFactory {
	//	Attachment attachment;
	 newValueListener listener;

	public AttachmentFactory ( newValueListener listener ) {
//		this.attachment = attachment;
		this.listener = listener;
	}

	boolean askUpdate ( String pathToWrite, Attachment value, String path ) {

		if (value == null)
			return false;
		new SetValueListener(listener).setNewValue(value, value, pathToWrite, path);
		return true;
	}
}
