package com.kareem.yladoctor.Models.Contracts;

import com.kareem.yladoctor.Models.Enums.DefaultLanguages;

/**
 * Created by kareem on 5/28/2017 - Akshef.
 * <br></br>
 * firebase database relations
 *
 * @author kareem
 * @version %I%
 */

public class FirebaseContracts {

	public static final String PATH_TO_USERS = "users";
	public static final String PATH_TO_USERS_USERUID_ACCOUNTTYPE = "accountType";
	public static final String PATH_TO_USERS_USERSUID_EMAIL = "email";
	public static final String PATH_TO_USERS_USERSUID_NAME = "name";
	public static final String PATH_TO_USERS_USERSUID_UID = "uid";
	public static final String PATH_TO_USERS_USERSUID_PASSWORD = "password";

	public static final String PATH_TO_USERS_INDIVIDUALSUID_POINTS = "points";
	public static final String PATH_TO_USERS_INDIVIDUALSUID_ATTACHMENT = "attachment";
	public static final String PATH_TO_USERS_INDIVIDUALSUID_ATTACHMENT_URL = "URL";
	public static final String PATH_TO_USERS_INDIVIDUALSUID_ATTACHMENT_NAME = "name";
	public static final String PATH_TO_USERS_INDIVIDUALSUID_ATTACHMENT_SIZE = "size";
	public static final String PATH_TO_USERS_INDIVIDUALSUID_ATTACHMENT_ATTACHMENTTYPE = "attachmentType";

	public static final String PATH_TO_USERS_PATIENTSUID_MOBILENUMBER = "mobileNumber";
	public static final String PATH_TO_USERS_PATIENTSUID_EMERGENCYMOBILENUMBER = "emergencyMobileNumber";
	public static final String PATH_TO_USERS_PATIENTSUID_ADDRESS = "address";
	public static final String PATH_TO_USERS_PATIENTSUID_WITHINSURANCECOMPANY = "withInsuranceCompany";

	public static final String PATH_TO_USERS_USERSUID_CAREER = "career";
	public static final String PATH_TO_USERS_USERSUID_CAREER_BUSINESSLOCATION = "businessLocation";
	public static final String PATH_TO_USERS_USERSUID_CAREER_BUSINESSMOBILENUMBER = "businessMobileNumber";
	public static final String PATH_TO_USERS_USERSUID_CAREER_RATE = "rate";
	public static final String PATH_TO_USERS_USERSUID_CAREER_VOTES = "votes";
	public static final String PATH_TO_USERS_USERSUID_DOCTORCAREER_ACCEPTINSURANCEUSERS = "acceptInsuranceUsers";
	public static final String PATH_TO_USERS_USERSUID_DOCTORCAREER_VERIFIEDDOCTOR = "verifiedDoctor";
	public static final String PATH_TO_USERS_USERSUID_DOCTORCAREER_EXPERIENCEYEARS = "experienceYears";
	public static final String PATH_TO_USERS_USERSUID_DOCTORCAREER_PRICE = "price";
	public static final String PATH_TO_USERS_USERSUID_DOCTORCAREER_INTERVAL = "interval";
	public static final String PATH_TO_USERS_USERSUID_DOCTORCAREER_FIELDUID = "fieldUID";

	public static final String PATH_TO_MEDICALFIELDSIDENTIFIER = "MedicalFieldsIdentifier";
	public static final String PATH_TO_MEDICALFIELDSIDENTIFIER_MEDICALFIELDSIDENTIFIERID_NAMES_ARABIC = "Arabic";
	public static final String PATH_TO_MEDICALFIELDSIDENTIFIER_MEDICALFIELDSIDENTIFIERID_NAMES_ENGLISH = "English";
	public static final String PATH_TO_MEDICALFIELDSIDENTIFIER_MEDICALFIELDSIDENTIFIERID_DESCRIPTIONS_ENGLISH = "English";
	public static final String PATH_TO_MEDICALFIELDSIDENTIFIER_MEDICALFIELDSIDENTIFIERID_DESCRIPTIONS_ARABIC = "Arabic";
	public static final String PATH_TO_MEDICALFIELDSIDENTIFIER_MEDICALFIELDSIDENTIFIERID_UID = "UID";
	public static final String PATH_TO_MEDICALFIELDSIDENTIFIER_MEDICALFIELDSIDENTIFIERID_NUMBEROFDOCTORS = "numberOfDoctors";

	public static final String PATH_TO_MEDICALFIELDS = "MedicalFields";
	public static final String PATH_TO_MEDICALFIELDS_MEDICALFIELDSID_UID = "UID";
	public static final String PATH_TO_MEDICALFIELDS_MEDICALFIELDSID_DOCTORS = "doctors";
	public static final String PATH_TO_MEDICALFIELDS_MEDICALFIELDSID_DOCTORS_DOCTORSUID_UID = "UID";

	public static final String PATH_TO_HEALTHINSURANCECOMPANIES = "healthInsuranceCompanies";
	public static final String PATH_TO_HEALTHINSURANCECOMPANIES_HEALTHINSURANCECOMPANIESID_UID = "UID";
	public static final String PATH_TO_HEALTHINSURANCECOMPANIES_HEALTHINSURANCECOMPANIESID_NAMES_ARABIC = "Arabic";
	public static final String PATH_TO_HEALTHINSURANCECOMPANIES_HEALTHINSURANCECOMPANIESID_NAMES_ENGLISH = "English";

	public static final String PATH_TO_HISTORYIDENTIFIER = "historyIdentifier";
	public static final String PATH_TO_HISTORYIDENTIFIER_USERUID_SAVEDID_ID = "id";
	public static final String PATH_TO_HISTORYIDENTIFIER_USERUID_SAVEDID_UID = "uid";

	public static final String PATH_TO_APPOINTMENTHISTORY = "appointmentHistory";
	public static final String PATH_TO_APPOINTMENTHISTORY_SAVEDID_DATE = "date";
	public static final String PATH_TO_APPOINTMENTHISTORY_SAVEDID_DUID = "duid";
	public static final String PATH_TO_APPOINTMENTHISTORY_SAVEDID_FIELD = "field";
	public static final String PATH_TO_APPOINTMENTHISTORY_SAVEDID_PUID = "puid";
	public static final String PATH_TO_APPOINTMENTHISTORY_SAVEDID_PATIENTREVIEW = "PReview";
	public static final String PATH_TO_APPOINTMENTHISTORY_SAVEDID_DOCTORREVIEW = "DReview";
	public static final String PATH_TO_APPOINTMENTHISTORY_SAVEDID_TIME = "time";
	public static final String PATH_TO_APPOINTMENTHISTORY_SAVEDID_VOTE = "vote";
	public static final String PATH_TO_APPOINTMENTHISTORY_SAVEDID_REVIEWOWNER_RATE = "rate";
	public static final String PATH_TO_APPOINTMENTHISTORY_SAVEDID_REVIEWOWNER_DESC = "desc";

	public static final String PATH_TO_GeoFireLocation = "geoFireLocation";

	public static final String ARABIC = String.valueOf(DefaultLanguages.Arabic);
	public static final String ENGLISH = String.valueOf(DefaultLanguages.English);
	public static final String PATH_TO_GEOFIRELOCATION = "geoFireLocation";

	public static final String PASSWORD = "yladoctor";
}
