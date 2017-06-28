package com.kareem.yladoctor.Models.Contracts;

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

	public static final String PATH_TO_GeoFireLocation = "geoFireLocation";

	public static final String ARABIC = "Arabic";
	public static final String ENGLISH = "English";

	public static final String PASSWORD = "yladoctor";
}
