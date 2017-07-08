package com.kareem.yladoctor.Factories.ModelsFactories.Users;

import com.kareem.yladoctor.Factories.ModelsFactories.CareerFactories.DoctorIndividualCareerFactory;
import com.kareem.yladoctor.Models.Contracts.FirebaseContracts;
import com.kareem.yladoctor.Models.Helpers.SetValueListener;
import com.kareem.yladoctor.Models.Interfaces.newValueListener;
import com.kareem.yladoctor.Models.Modules.Career.Individuals.DoctorIndividualCareer;
import com.kareem.yladoctor.Models.Modules.Career.Individuals.IndividualCareer;
import com.kareem.yladoctor.Models.Modules.User.Businesses.Individuals.Doctor;

/**
 * Created by kareem on 7/7/2017 - YlaDoctor.
 * <br></br>
 * description goes here
 *
 * @author kareem
 * @version %I%
 */

public class DoctorFactory extends IndividualBusinessFactory {
	Doctor doctor;

	public DoctorFactory ( Doctor user, newValueListener listener ) {
		super(user, listener);
		doctor = user;
	}

	public boolean askUpdate ( String path, Object value ) {

		if (super.askUpdate(path, value))
			return true;
		if (path.equals(FirebaseContracts.PATH_TO_USERS_INDIVIDUALBUSINESSUID_CAREER)) {
			if (! (value instanceof IndividualCareer))
				return false;
			new SetValueListener(this).setNewValue(value, value, FirebaseContracts.PATH_TO_USERS + "/" + doctor.getUID() + "/" + path, path);
			return true;
		}
		if (path.equals(FirebaseContracts.PATH_TO_USERS_INDIVIDUALBUSINESSUID_CAREER_BUSINESSLOCATIONS)
				  || ! path.equals(FirebaseContracts.PATH_TO_USERS_INDIVIDUALBUSINESSUID_CAREER_BUSINESSMOBILENUMBER)
				  || ! path.equals(FirebaseContracts.PATH_TO_USERS_INDIVIDUALBUSINESSUID_CAREER_RATE)
				  || ! path.equals(FirebaseContracts.PATH_TO_USERS_INDIVIDUALBUSINESSUID_CAREER_VOTES)
				  || ! path.equals(FirebaseContracts.PATH_TO_USERS_INDIVIDUALBUSINESSUID_CAREER_NEEDSAPPROVAL)
				  || ! path.equals(FirebaseContracts.PATH_TO_USERS_INDIVIDUALDOCTORUID_DOCTORCAREER_ACTIVATEDDOCTOR)
				  || ! path.equals(FirebaseContracts.PATH_TO_USERS_INDIVIDUALDOCTORUID_DOCTORCAREER_EXPERIENCEYEARS)
				  || ! path.equals(FirebaseContracts.PATH_TO_USERS_INDIVIDUALDOCTORUID_DOCTORCAREER_PRICE)
				  || ! path.equals(FirebaseContracts.PATH_TO_USERS_INDIVIDUALDOCTORUID_DOCTORCAREER_INTERVAL)
				  || ! path.equals(FirebaseContracts.PATH_TO_USERS_INDIVIDUALDOCTORUID_DOCTORCAREER_FIELDID)
				  || ! path.equals(FirebaseContracts.PATH_TO_USERS_INDIVIDUALDOCTORUID_DOCTORCAREER_LISTOFHEALTHINSURANCECOMPANIES)) {
			new DoctorIndividualCareerFactory(doctor.getUID(), this, doctor.getCareer()).askUpdate(path, value);
			return true;
		}
		return false;

	}

	@Override
	public void onSucceed ( Object value, String ID ) {
		super.onSucceed(value, ID);
		boolean accessed = true;
		switch (ID) {
			case FirebaseContracts.PATH_TO_USERS_INDIVIDUALBUSINESSUID_CAREER:
				doctor.setCareer((DoctorIndividualCareer) value);
				break;
			case FirebaseContracts.PATH_TO_USERS_INDIVIDUALBUSINESSUID_CAREER_ITEM:
				break;
			default:
				accessed = false;
		}
		triggerNotification(accessed, value, ID);
	}
}
