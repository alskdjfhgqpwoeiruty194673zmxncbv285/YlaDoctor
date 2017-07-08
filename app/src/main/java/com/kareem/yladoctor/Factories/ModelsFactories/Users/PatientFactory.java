package com.kareem.yladoctor.Factories.ModelsFactories.Users;

import com.kareem.yladoctor.Models.Contracts.FirebaseContracts;
import com.kareem.yladoctor.Models.Helpers.SetValueListener;
import com.kareem.yladoctor.Models.Interfaces.newValueListener;
import com.kareem.yladoctor.Models.Modules.User.Businesses.Individuals.Patient;

/**
 * Created by kareem on 7/7/2017 - YlaDoctor.
 * <br></br>
 * description goes here
 *
 * @author kareem
 * @version %I%
 */

public class PatientFactory extends IndividualFactory {
	private Patient patient;

	public PatientFactory ( Patient user, newValueListener listener ) {
		super(user, listener);
		patient = user;
	}

	public boolean askUpdate ( String path, Object value ) {

		if (super.askUpdate(path, value))
			return true;
		if (! path.equals(FirebaseContracts.PATH_TO_USERS_PATIENTSUID_EMERGENCYMOBILENUMBER))
			return false;
		new SetValueListener(this).setNewValue(value, value, FirebaseContracts.PATH_TO_USERS + "/" + patient.getUID() + "/" + path, path);
		return true;
	}

	@Override
	public void onSucceed ( Object value, String ID ) {
		super.onSucceed(value, ID);
		boolean accessed = true;
		switch (ID) {
			case FirebaseContracts.PATH_TO_USERS_INDIVIDUALUID_POINTS:
				patient.setEmergencyNumber((String) value);
				break;
			default:
				accessed = false;
		}
		triggerNotification(accessed, value, ID);
	}
}
