package com.kareem.yladoctor.Factories.ModelsFactories.CareerFactories;

import com.kareem.yladoctor.Models.Contracts.FirebaseContracts;
import com.kareem.yladoctor.Models.Helpers.SetValueListener;
import com.kareem.yladoctor.Models.Interfaces.newValueListener;
import com.kareem.yladoctor.Models.Modules.Career.Individuals.DoctorIndividualCareer;

/**
 * Created by kareem on 7/7/2017 - YlaDoctor.
 * <br></br>
 * description goes here
 *
 * @author kareem
 * @version %I%
 */

public class DoctorIndividualCareerFactory extends IndividualCareerFactory {
	private DoctorIndividualCareer doctorIndividualCareer;

	public DoctorIndividualCareerFactory ( String UID, newValueListener listener, DoctorIndividualCareer individualCareer ) {
		super(UID, listener, individualCareer);
		this.doctorIndividualCareer=individualCareer;
	}

	public boolean askUpdate ( String path, Object value ) {
		if (super.askUpdate(path, value))
			return true;
		if (! path.equals(FirebaseContracts.PATH_TO_USERS_INDIVIDUALDOCTORUID_DOCTORCAREER_ACTIVATEDDOCTOR)
				  && ! path.equals(FirebaseContracts.PATH_TO_USERS_INDIVIDUALDOCTORUID_DOCTORCAREER_EXPERIENCEYEARS)
				  && ! path.equals(FirebaseContracts.PATH_TO_USERS_INDIVIDUALDOCTORUID_DOCTORCAREER_FIELDID)
				  && ! path.equals(FirebaseContracts.PATH_TO_USERS_INDIVIDUALDOCTORUID_DOCTORCAREER_INTERVAL)
				  && ! path.equals(FirebaseContracts.PATH_TO_USERS_INDIVIDUALDOCTORUID_DOCTORCAREER_LISTOFHEALTHINSURANCECOMPANIES)
				  && ! path.equals(FirebaseContracts.PATH_TO_USERS_INDIVIDUALDOCTORUID_DOCTORCAREER_PRICE))
			return false;

		new SetValueListener(this).setNewValue(value, value, FirebaseContracts.PATH_TO_USERS + "/" + UID + "/" + FirebaseContracts.PATH_TO_USERS_INDIVIDUALBUSINESSUID_CAREER + "/" + path, path);
		return true;
	}

	@Override
	public void onSucceed ( Object value, String ID ) {
		boolean accessed = true;
		switch (ID) {
			case FirebaseContracts.PATH_TO_USERS_INDIVIDUALDOCTORUID_DOCTORCAREER_ACTIVATEDDOCTOR:
				doctorIndividualCareer.setActivatedDoctor((Boolean) value);
				break;
			case FirebaseContracts.PATH_TO_USERS_INDIVIDUALDOCTORUID_DOCTORCAREER_EXPERIENCEYEARS:
				doctorIndividualCareer.setExperienceYears((Integer) value);
				break;
			case FirebaseContracts.PATH_TO_USERS_INDIVIDUALDOCTORUID_DOCTORCAREER_FIELDID:
				doctorIndividualCareer.setFieldID((String) value);
				break;
			case FirebaseContracts.PATH_TO_USERS_INDIVIDUALDOCTORUID_DOCTORCAREER_INTERVAL:
				doctorIndividualCareer.setInterval((Integer) value);
				break;
			case FirebaseContracts.PATH_TO_USERS_INDIVIDUALDOCTORUID_DOCTORCAREER_LISTOFHEALTHINSURANCECOMPANIES:
				doctorIndividualCareer.setListOfHealthInsuranceCompanies((String[]) value);
				break;
			case FirebaseContracts.PATH_TO_USERS_INDIVIDUALDOCTORUID_DOCTORCAREER_PRICE:
				doctorIndividualCareer.setPrice((Integer) value);
				break;
			default:
				accessed = false;
		}
		triggerNotification(accessed, value);
	}
}
